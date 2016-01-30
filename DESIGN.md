
# cellsociety
Duke CompSci 308 Cell Society Project
Colin, Ani, Christine

## Introduction

This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

The goal of this program is to animate 2-D cellular automata (CA) simulations. The primary design goal for this project is to be able to handle different types of simulations (different sets of rules) and different cell shapes. The program will read the specifications for the simulation via a XML configuration file. This file will contain information about which simulation to animate, the initial cell states and global parameters that are specific to the simulation. The program will then draw the initial state of the cells and repeatedly update the cells based on the rules until the simulation 'finishes'. The user will be able to start and stop the simulation as well as control the pace of the simulation. He/she will also be able to switch between simulations by loading in a different XML configuration file.

##Overview

In this program, there are a couple major components that will be necessary to create in order for the design to be dynamic and flexible for any CA scenario.  As such, the goal for our developing is to craft classes that capture the fundamental principles of Cellular Automata.  

Our overview begins with a basic layout of how the program will be separated:

**Grid**:
	*Responsible for the graphic representation of the grid and the 	initialization of the cell objects for that specific automaton.  
	*Rules and states will be passed to the cell objects through this 	medium.
	*Grid will be able to serve as a medium for the other cells to "communicate" with each other about their current state. 
	*Cells will be stored in a 2d Array.  This will help cells draw information from its neighbors.  

**Driver**:
	*Will handle the animation loop of the automaton.
	*Each Instance of an automaton will be created in this class--if the user wants to switch CA's, a new Grid object will be created and passed automaton parameters specific for that CA.  

**Automaton Data**
	*Parses information from XML file about specific automaton.
	*Parameters: CA Name, Collection of Rules and cell states.
	*Rules will be constructed in a format understood by Cell Objects
	

**Cell**
	*Will store it's state - a constant determined from the automaton class
	*Will either store it's rules or pull them from the automaton data class. 
	*method will update it's state using the rules and the states of the cells surrounding it.
	*Cell will make its current state available for the next round of updates
	*method will render the visual appearance of the cell to be shown in the grid.
	

By discretely laying out the various parts of the data in separate classes, I believe that our design will have the ability to adjust dynamically to different automatons.  We have isolated the principles of automata and separated them.  From basic development, we can add features with ease off of the foundational framework.    
	
##User Interface

The user interface to start with will have buttons that will allow the user to naviagate between screens. The first screen after the program is started will be the splash screen that says "Cell Society". On this screen, there will be buttons for each of the different cell animations under this. Above the button there will be text that says the name of the animation. 
![alt text](CellSociety.jpg "A mockup of splash screen")
 In the first week with the basic design, to adjust factors such as the size of the animation or things such as color to color percentage in the model of segregation, the user will change the xml file to change the different factors related to the game. Later, text boxes will be used for user input to adjust the different factors for each animation. The program will also have default settings if the user does not choose to input settings.There will be buttons on the screen for the user to choose to reset, start, and stop the animation as well. 
![alt text](AutomatonDisplay.jpg "A mockup of the display")
When a user enters erroneous information, a dialog will pop up telling the user that there has been an error and what kind of error was generated. 

