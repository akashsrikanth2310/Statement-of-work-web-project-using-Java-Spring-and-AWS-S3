package com.accolite.ppm.sow.v1.service;

import java.util.List;

import com.accolite.ppm.sow.v1.model.SOWListMapModel;
import com.accolite.ppm.sow.v1.model.SOWv1;

public interface SowServiceV1Interface {

	public SOWv1 saveSow(SOWv1 newSow);
	public SOWv1 updateSow(SOWv1 newSow);
	public Object getSowById(String id);
	public List<SOWv1> getAllSow();
	public List<SOWListMapModel> getAllSowByRole() ;
	
}
