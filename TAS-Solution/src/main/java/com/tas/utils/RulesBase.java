package com.tas.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;

public class RulesBase {
	
	private static final String RULES_PATH = "src\\main\\resources\\com\\tas\\rules\\rules_source.drl"; 
	
	public static RuleBase createBase() throws DroolsParserException, IOException {

		InputStream resourceStream = new FileInputStream(new File(RULES_PATH));
		
	    PackageBuilder packageBuilder = new PackageBuilder();

		Reader reader = new InputStreamReader(resourceStream);
	    packageBuilder.addPackageFromDrl(reader);

		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();		
        ruleBase.addPackage(rulesPackage);
		
        resourceStream.close();
		return ruleBase;		
	}
	
	
}
