# Exploits Detection System
Exploits Detection System (EDS) is a system that analyzes given diagram in order to find potential security exploits.

System diagrams created in accordance with given XML schema can be loaded into the EDS. After that, EDS decomposes input diagrams into risk patterns. A set of rules, which is defined in rule-based engine, is applied to every single risk pattern that was found. Rules analyze components, attributes and assets of every pattern in order to find potential exploits. List of exploits discovered for given patterns will be created for user as an XML repport, consisted of detail description and recommended countermeasures.

Exploits and assets are defined in separate XML files and can easely be changed. Rules are written using drools.

## Motivation
This system is created as master's thesis work. The overal goal is to create useful software for detecting security exploits over the given system diagram. Moreover, this is a great opportunity for improving knowledge and capabilities in the domain of IT security and rule-based systems.

## Contribute
### Prerequisites:
- Installed Java 1.8
- Installed Maven

### Building and Running:
- Clone or download project
- Open project with your Java IDE (Eclipse, InteliJ, or some other)
- Build project (Maven will automatically download required libraries)
- Run application

## Install
### Prerequisites:
- Installed Java 1.8

### Download and Install:
(for Windows)
- Open [download link](https://github.com/NNemanjaMM/EDS/blob/master/Setup/Setup.exe) and download installation file
- Run installation file
- Run the application after installation completes 

## Author
+ Nemanja Miladinović - [NNemanjaMM](https://github.com/NNemanjaMM)

## Additional info
For more information see this repository's [wiki page](https://github.com/NNemanjaMM/EDS/wiki/Wiki-Page).

## Licence
This project is licensed under the terms of the MIT License - for more information take a look at [license page](https://github.com/NNemanjaMM/TAS/blob/master/LICENSE) 
