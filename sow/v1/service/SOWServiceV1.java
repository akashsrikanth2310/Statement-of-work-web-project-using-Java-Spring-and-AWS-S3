package com.accolite.ppm.sow.v1.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.accolite.app.security.authentication.SecurityContext;
import com.accolite.ppm.QUERIES;
import com.accolite.ppm.sow.v1.model.SOWListMapModel;
import com.accolite.ppm.sow.v1.model.SOWv1;

@Service
public class SOWServiceV1 implements SowServiceV1Interface {

	private static SessionFactory factory;
	private Session session;

	static {
		try {
			factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("****************************Failed to create sessionFactory object." + ex);
		}
	}

	// function to save sow in database
	public SOWv1 saveSow(SOWv1 newSow) {
		session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(newSow);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			session.close();
		}
		return newSow;
	}

	// function to update sow
	public SOWv1 updateSow(SOWv1 newSow) {
		session = factory.openSession();
		// session.clear();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(newSow);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
			throw ex;
		} finally {
			session.close();
		}
		return newSow;
	}

	// function to filter sow by ID
	public Object getSowById(String id) {
		session = factory.openSession();
		Object sow;
		sow = session.get(SOWv1.class, id);
		session.flush();
		return sow;
	}

	// function to list all sow
	public List<SOWv1> getAllSow() {

		try {
			session = factory.openSession();
			return session.createCriteria(SOWv1.class).list();
		} catch (Exception e) {
			System.out.println("Exception In List Sow");
			return new ArrayList<>();
		}
	}

	// functio to list sow by role
	public List<SOWListMapModel> getAllSowByRole() {
		session = factory.openSession();
		if (SecurityContext.hasRole("CEO"))
			return getAllSowForCEO();
		if (SecurityContext.hasRole("FINANCE"))
			return getAllSowForCEO();
		if (SecurityContext.hasRole("HEAD_FINANCE"))
			return getAllSowForCEO();
		else if (SecurityContext.hasRole("BU_HEAD"))
			return getAllSowForBU();
		else if (SecurityContext.hasRole("PROJECT_MANAGER"))
			return getAllSowForProjectManager();
		else
			return new ArrayList<>();
	}

	public List<SOWListMapModel> getAllSowForBU() {
		String joinQuery = QUERIES.GET_ALL_SOW_BY_USER_FOR_BU(SecurityContext.getCurrentUser().getId());
		Query query = session.createSQLQuery(joinQuery);
		query.setResultTransformer(Transformers.aliasToBean(SOWListMapModel.class));
		List<SOWListMapModel> aliasToValueMapList = (List<SOWListMapModel>) (query.list());
		return aliasToValueMapList;
	}

	public List<SOWListMapModel> getAllSowForCEO() {
		String joinQuery = QUERIES.GET_ALL_SOW_FOR_CEO();
		Query query = session.createSQLQuery(joinQuery);
		query.setResultTransformer(Transformers.aliasToBean(SOWListMapModel.class));
		List<SOWListMapModel> aliasToValueMapList = (List<SOWListMapModel>) (query.list());
		return aliasToValueMapList;
	}

	public List<SOWListMapModel> getAllSowForProjectManager() {
		String joinQuery = QUERIES.GET_ALL_SOW_FOR_PROJECT_MANAGER(SecurityContext.getCurrentUser().getId());
		Query query = session.createSQLQuery(joinQuery);
		query.setResultTransformer(Transformers.aliasToBean(SOWListMapModel.class));
		List<SOWListMapModel> aliasToValueMapList = (List<SOWListMapModel>)(query.list());
		return aliasToValueMapList;
	}
}
