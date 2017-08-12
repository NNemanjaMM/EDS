package com.tas.utils;

import java.io.File;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class KieRulesBase {

	public static StatelessKieSession createStatelessSession() {
		
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		
		if (ResourcesLocation.getInstance().getRulesLocations().size() == 0) {
			return null;
		}
		
		for (String rule_location : ResourcesLocation.getInstance().getRulesLocations()) {
			kieFileSystem.write(kieServices.getResources().newFileSystemResource(new File(rule_location)));			
		}
		
		kieServices.newKieBuilder(kieFileSystem).buildAll();
		
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());		
		StatelessKieSession kieSession = kieContainer.newStatelessKieSession();

		return kieSession;
	}	
	
}
