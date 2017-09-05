# Exploits Detection System

<p align="center"><img src="https://github.com/NNemanjaMM/EDS/blob/master/Wiki-Resources/icon.png"/></p>

Exploits Detection System (EDS) is a system that analyzes data flow diagrams (DFD), in order to find potential security exploits.

EDS decomposes the input diagrams into risk patterns. The risk pattern consists of two diagram nodes and all other links and nodes which are linking them. A set of rules, which are defined for the rule-based engine, is applied to every single risk pattern that was found. Rules analyze components of the DFD, attributes of those components and assets mapped to those components for every pattern, in order to find vulnerabilities. Rules are then assigning exploits that could exploit the vulnerability that was found. List of exploits discovered for given patterns will be created for the user as an XML report, consisted of detailed descriptions of the vulnerabilities and exploits, as well as the recommended countermeasures.

Exploits and assets are defined in separate XML files and can easily be changed. Rules are written using drools.

The system contains two tools. The first tool is used to apply changes in XML schema by creating a library with classes corresponding to XML schema elements. The second one can be used to convert DFDs created with Microsoft Threat Modelling Tool to DFDs suitable for this system.

System structure alongside with its input and output can be seen in the image below.

<p align="center"><img width="600" src="https://github.com/NNemanjaMM/EDS/blob/master/Wiki-Resources/structure.png"/></p>

## Motivation
This system is created as master's thesis work. The overall goal is to create useful software for detecting security exploits over the given system diagram. Moreover, this is a great opportunity for improving knowledge and capabilities in the domain of IT security and rule-based systems.

## Contribute
### Prerequisites:
- Installed Java 1.8
- Installed Maven

### Building and Running:
- Clone or download project
- Open project with your Java IDE (Eclipse, IntelliJ, or some other)
- Build project (Maven will automatically download required libraries)
- Run application

## Install
### Prerequisites:
- Installed Java 1.8
- Java is set as an environment variable

### Download and Install:
(for Windows)
- Open [download page](https://github.com/NNemanjaMM/EDS/blob/master/Setup/Setup.exe) and download installation file
- Run installation file
- After installation completes run the application or tools 

## Author
+ Nemanja Miladinović - [NNemanjaMM](https://github.com/NNemanjaMM)

## Additional info
For more information see this repository's [wiki page](https://github.com/NNemanjaMM/EDS/wiki).

## Licence
This project is licensed under the terms of the MIT License - for more information take a look at [license page](https://github.com/NNemanjaMM/TAS/blob/master/LICENSE) 
