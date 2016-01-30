
# cellsociety
Duke CompSci 308 Cell Society Project
Colin, Ani, Christine


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
	

