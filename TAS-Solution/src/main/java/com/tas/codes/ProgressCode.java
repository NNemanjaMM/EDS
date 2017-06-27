package com.tas.codes;

public class ProgressCode {

	public static final int INITIALIZED = 0;
	public static final int STARTED = 1;
	
	// 1- Validating diagrams
	public static final int VALIDATED_DIAGRAM = 1;
	public static final int VALIDATED_ASSETS = 1;
	public static final int VALIDATED_VULNERABILITIES = 1;
	
	// 2- Importing diagrams
	public static final int READED_DIAGRAM = 1;
	public static final int READED_ASSETS = 1;
	
	// 3- Merging diagram and assets
	public static final int MERGED_DIAGRAM_ASSETS = 1;
	
	// 4- Decomposing diagram
	public static final int DECOMPOSED_DIAGRAM = 1;
	
	// 5- Analyzing diagram components
	public static final int RULES_ANALYZED = 1;
	
	// 6- Applying vulnerabilities
	public static final int READED_VULNERABILITIES = 1;
	public static final int MERGED_DIAGRAM_VULNERABILITIES = 1;
	
	// 7- Creating report
	public static final int GENERATED_REPORT = 1;
	public static final int SAVED_REPORT = 1;

	public static final int DONE = 100;
	public static final int CANCELED = 111;
	
}
