
# cellsociety
Duke CompSci 308 Cell Society Project
Colin, Ani, Christine

## Introduction

This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

The goal of this program is to animate 2-D cellular automata (CA) simulations. The primary design goal for this project is to be able to handle different types of simulations (different sets of rules) and different cell shapes. The program will read the specifications for the simulation via a XML configuration file. This file will contain information about which simulation to animate, the initial cell states and global parameters that are specific to the simulation. The program will then draw the initial state of the cells and repeatedly update the cells based on the rules until the simulation 'finishes'. The user will be able to start and stop the simulation as well as control the pace of the simulation. He/she will also be able to switch between simulations by loading in a different XML configuration file.

##Overview

In this program, there are a couple major components that will be necessary to create in order for the design to be dynamic and flexible for any CA scenario.  As such, the goal for our developing is to craft classes that capture the fundamental principles of Cellular Automata.  

Our overview begins with a basic layout of how the program will be separated:



**Main Class**:

* This class is responsible for initializing/changing CA's.
* Extends Application and therefore will have override the start method.
* Within this, there will be a call to the ParseXML class.  This information will be used to create a new Instance of the CA class.
* The stage will be set such that the button bar at the bottom will remain throughout the program
* The window above the stage will be updated in Animation as the simulation goes on.
* Once the grid is initialized, CA will call its run method to being the simulation.
* The CA object will run the animation as well as the simulation updating
* The CA can be paused and switched to different simulations from the Main Class.
	

**ParseXML**

* Parses information from XML file about specific automaton.
* Parameters: CA Name, Collection of Rules and cell states.
* Rules will be constructed in a format understood by CA class
	
**CA**

* Each Simulation will be an instance of this class.
* parameters include: number of cells, shape of cells, rules, cell states
* our cell objects will be stored in a Graph data structure stored as a 2D array for its neighbors.
* States will be stored in a collection with index numbers
* Rules will be stored in a similar data structure.
* Global list of cells will be used to for simulation steps 
* The Simulation loop will carry out the following tasks:
	
1. Update cells using the rules and neighbors in order to determine the state
2. Render the cells for the current step of the simulation
3. Determine if the simulation is over

	
	

**Cell**

* Will store it's state - a constant determined from the CA class, and double x, y, coordinate location
* Abstract method draw will be over-ridden depending on the shape of the cell. 
* private int state will reference an index in CA data structure containing possible states. 
* getState and setState will be public methods the CA uses to adjust the cells in that step of the simulation.
	

By discretely laying out the various parts of the data in separate classes, I believe that our design will have the ability to adjust dynamically to different automatons.  We have isolated the principles of automata and separated them.  From basic development, we can add features with ease off of the foundational framework.    
	
##User Interface

The user interface to start with will have buttons that will allow the user to naviagate between screens. The first screen after the program is started will be the splash screen that says "Cell Society". On this screen, there will be buttons for each of the different cell animations under this. Above the button there will be text that says the name of the animation. 

![alt text](https://github.com/duke-compsci308-spring2016/cellsociety_team19/blob/master/resources/CellSociety.jpg "A mockup of splash screen")

 In the first week with the basic design, to adjust factors such as the size of the animation or things such as color to color percentage in the model of segregation, the user will change the XML file to change the different factors related to the game. There will be a button to upload a new XML file on the splash screen for the beginning. Later, the splash screen will give options to change the animation. There will be a splash screen and another window, the "display screen" that has the animation and the buttons related to the animation. On the display screen, text boxes or sliders will eventually be used for user input to adjust the different factors for each animation. The program will also have default settings if the user does not choose to input settings.There will also be other buttons on the screen for the user to choose to reset, start, pause, and step through the animation as well. 
 
![alt text](https://github.com/duke-compsci308-spring2016/cellsociety_team19/blob/master/resources/AutomatonDisplay.jpg "A mockup of the display")

To switch between screens, the user would close the display window and select another animation or upload an XML from the splash screen that remains open.

When a user enters erroneous information, a dialog will pop up telling the user that there has been an error and what kind of error was generated.

