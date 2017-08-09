# Exploits analysis System
Exploits analysis System (EAS) is a system that analyzes security exploits for given diagram.

System diagrams created in accordance with given XML schema can be loaded into the EAS. After that, EAS decomposes input diagrams into risk patterns. A set of rules, which is defined in rule-based engine, is applied to every single risk pattern that was found. Rules analyze components, attributes and assets of every pattern. List of exploits discovered for given patterns will be created for user as an XML repport, consisted of detail description and recommended countermeasures.

Exploits and assets are defined in separate XML files and can easely be changed. Rules are written using drools.

## Motivation
This system is created as master's thesis work. The overal goal is to create useful software for exploits analysis. Moreover, this is a great opportunity for improving knowledge and capabilities in the domain of IT security and rule-based systems.

## Contribute
### Prerequisites:
- Installed Java 1.8
- Installed Maven

### Building and Running:
- Open project with your Java IDE (Eclipse, InteliJ, or some other)
- Build project (Maven will automatically download required libraries)
- Run application

## Install
### Prerequisites:
- Installed Java 1.8

### Download and Install:
(for Windows)
- Open [download link](#) and download installation file
- Run installation file
- Run the application after installation completes 

## Author
+ Nemanja Miladinović - [NNemanjaMM](https://github.com/NNemanjaMM)

## Additional info
For more information see this repository's [wiki page](https://github.com/NNemanjaMM/TAS/wiki).

## Licence
This project is licensed under the terms of the MIT License - for more information take a look at [license page](https://github.com/NNemanjaMM/TAS/blob/master/LICENSE) 
