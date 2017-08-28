mkdir temp
cd temp
xjc -p com.tas.EDS_Library.diagram %~f1
echo main-class: AppMainClass>manifest.txt
echo package com.tas.EDS_Library;import com.tas.EDS_Library.diagram.*;public class AppMainClass{public static void main(String[] args){}}>com\tas\EDS_Library\diagram\AppMainClass.java
javac com/tas/EDS_Library/diagram/*.java
jar cfm ../TAS-Solution/lib/model_lib.jar manifest.txt com/tas/EDS_Library/diagram/*.class
cd ..
rd /s /q temp