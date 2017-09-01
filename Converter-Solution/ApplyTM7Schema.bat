mkdir lib
mkdir temp
cd temp
xjc -p com.eds.Converter_Solution.diagram %~f1
javac com/eds/Converter_Solution/diagram/*.java
jar cfvM ../lib/tm7_model_lib.jar com/eds/com.eds.Converter_Solution/diagram/*.class
cd ..
rd /s /q temp