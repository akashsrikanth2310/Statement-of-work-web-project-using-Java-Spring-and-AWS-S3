package com.accolite.ppm.sow.cronjob;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.accolite.app.email.EmailDAO;
import com.accolite.app.employee.EmployeeBasic;
import com.accolite.app.mq.RabbitMqProducerService;
import com.accolite.app.util.EnvUtil;
import com.accolite.ezhire.common.Constants;
import com.accolite.ezhire.enums.EmailType;

import com.accolite.ppm.DAO.SOWDao;
import com.accolite.ppm.model.ExpiringSow;

public class SOWCronJobs {

	@Autowired
	SOWDao sowDao;

	@Autowired
	EnvUtil envUtil;

	@Autowired
	EmailDAO emailDAO;

	@Autowired
	private RabbitMqProducerService rabbitMqProducerService;

	@Scheduled(cron = "0 1 1 * * ?")
	public void mailExpiringSow() {
		System.out.println("SOWCronJobs.mailExpiringSow()");
		List<ExpiringSow> sowMailDetail = new ArrayList<ExpiringSow>();
		String[] duration = { "0 days", "15 days","30 days","45 days","60 days" };
		int[] days = { 0, 15, 30, 45, 60 };
		for (int i = 0; i < duration.length; i++) {
			List<ExpiringSow> expiredSow = sowDao.getExpiringSow(duration[i], days[i]);
			if ((expiredSow != null) && (expiredSow.size() > 0))
				sowMailDetail.addAll(expiredSow);
		}
		if (sowMailDetail != null) {
			for (ExpiringSow sow : sowMailDetail) {
				Map<String, Object> emailBodyMap = new HashMap<String, Object>();
				emailBodyMap.put("id", sow.getId());
				emailBodyMap.put("sowBudget", sow.getSowBudget());
				emailBodyMap.put("sowId", sow.getSowId());
				emailBodyMap.put("sowName", sow.getName());
				emailBodyMap.put("clientContact", sow.getClientContact());

				emailBodyMap.put("sowProposedEndDate",
						new SimpleDateFormat("dd-MM-yyyy").format(sow.getProposedEndDate()));
				emailBodyMap.put("sowActualEndDate", sow.getActualEndDate());
				emailBodyMap.put("sowActualStartDate", sow.getActualStartDate());
				emailBodyMap.put("buHead", sow.getBuHead().getName());

				String cc = sow.getBuHead().getWorkEmail();
				String to = "";
				if (sow.getPmoList() != null) {
					for (EmployeeBasic pmo : sow.getPmoList())
						to += pmo.getWorkEmail() + ",";
				}
				to += Constants.EZHIRE_INFO_EMAIL;
				EmailType SOW_CLOSURE_ALERT;
				switch (sow.getNumExpiringDays()) {
				case 15:
					SOW_CLOSURE_ALERT = EmailType.SOW_CLOSURE_ALERT_15DAYS;
					break;
				case 30:
					SOW_CLOSURE_ALERT = EmailType.SOW_CLOSURE_ALERT_30DAYS;
					break;
				case 0:
					SOW_CLOSURE_ALERT = EmailType.SOW_CLOSURE_ALERT_0DAYS;
					break;
				case 45:
					SOW_CLOSURE_ALERT = EmailType.SOW_CLOSURE_ALERT_45DAYS;
					break;
				case 60:
					SOW_CLOSURE_ALERT = EmailType.SOW_CLOSURE_ALERT_60DAYS;
					break;
				default:
					SOW_CLOSURE_ALERT = EmailType.SOW_CLOSURE_ALERT_30DAYS;
				}
				Long emailId = emailDAO.saveEmail(null, null, null, to, emailBodyMap, cc, SOW_CLOSURE_ALERT);
				rabbitMqProducerService.sendEmail(emailId);
			}
		}
	}

}
