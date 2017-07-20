package com.tas.utils;

import java.io.File;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class KieRulesBase {
	
	private static final String RULES_PATH_PII = "src\\main\\resources\\com\\tas\\rules\\rules_pii.drl"; 
	private static final String RULES_PATH_COOOKIE = "src\\main\\resources\\com\\tas\\rules\\rules_cookie.drl";
	private static final String RULES_PATH_ADMIN_PASSWORD = "src\\main\\resources\\com\\tas\\rules\\rules_admin_password.drl";
	private static final String RULES_WORK_STATION = "src\\main\\resources\\com\\tas\\rules\\rules_work_station.drl";
	
	public static StatelessKieSession createStatelessSession() {
		
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		
		kieFileSystem.write(kieServices.getResources().newFileSystemResource(new File(RULES_PATH_COOOKIE)));
		kieFileSystem.write(kieServices.getResources().newFileSystemResource(new File(RULES_PATH_PII)));
		kieFileSystem.write(kieServices.getResources().newFileSystemResource(new File(RULES_PATH_ADMIN_PASSWORD)));
		kieFileSystem.write(kieServices.getResources().newFileSystemResource(new File(RULES_WORK_STATION)));
		kieServices.newKieBuilder(kieFileSystem).buildAll();
		
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());		
		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

		return kieSession;
	}
	
	
}
