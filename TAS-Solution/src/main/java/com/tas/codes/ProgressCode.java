package com.tas.codes;

public class ProgressCode {

	public static final int INITIALIZED = 0;
	public static final int STARTED = 1;
	
	// 1- Validating diagrams - 10
	public static final int VALIDATED_DIAGRAM = 4;
	public static final int VALIDATED_ASSETS = 7;
	public static final int VALIDATED_EXPLOITS = 10;
	
	// 2- Importing diagrams - 15
	public static final int READED_DIAGRAM = 19;
	public static final int READED_ASSETS = 25;
	
	// 3- Merging diagram and assets - 15
	public static final int MERGED_DIAGRAM_ASSETS = 40;
	
	// 4- Decomposing diagram - 20
	public static final int DECOMPOSED_DIAGRAM = 60;
	
	// 5- Analyzing diagram components - 25
	public static final int RULES_ANALYZED = 85;
	
	// 6- Applying exploits - 10
	public static final int READED_EXPLOITS = 88;
	public static final int MERGED_DIAGRAM_EXPLOITS = 95;
	
	// 7- Creating report - 5
	public static final int GENERATIED_REPORT_PATTERNS = 97;
	public static final int GENERATED_REPORT = 99;

	public static final int DONE = 100;
	public static final int CANCELED = 111;
	
}
