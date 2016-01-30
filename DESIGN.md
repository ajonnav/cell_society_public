
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
	




## Design Considerations

At this stage of the project, we are making decisions that should allow for flexibility down the road for additional features as well as changes in requirements.   For example, our decision to use a graph structure instead of a grid should allow us to develop more more complex simulations in the future.  We are still deciding on which specific data structure we are going to use for our adjacency composition.  Another issue we are considering is how we will create an organized and consistent way of creating rules for how the cells interact with each other.  An independent object will take the results of the XML parsing and format the data so that the CA class can gather the necessary information for the simulation.  


In our consideration of animating the simulation, we have decided to use the AnimationTimer() javaFX class to step through each part of the simulation.  This should give us more control over the workings of the game in comparison to Timeline. Another design consideration is our use of Canvas to draw our objects.  Instead of creating several nodes into our scene, each simulation is going to be drawn on a canvas object.  We are still debating about what we are going to do when we switch between simulations.  As of now, each CA will have its own canvas, and as the CA's are created and used, their respective canvases will be brought to the front.  In other words, we are not deleting any CA objects, we are simply pausing and/or reseting their simulations and bringing it to front.

Having the CA object handle the rule checking was different from our original intention which was to have the cells check each other.  We realized it would have been redundant for each cell to have its own copy of the rules and have to check and apply with their neighbors.  Having the simulation manage state changes will prevent redundancy as well as contradictions that may arise as the result of each cell individually changing and checking its state.  


## Team Responsibilities

For the first round of this project, we have decided to break up the work in the following way:

1. Ani will be responsible for creating the XMLParsing and results classes.

2. Christine will create the Main class and the basic UI.

3. Colin will develop the Cell class.

Our goal is to have these pieces built by the end of the day Tuesday, so that we may all begin to work on the creation of the CA class.  This will take up the rest of the week as we work towards getting the program up and running.   

We will all work in our respective branches and plan to commit once a major component of each class is completed, and at the end of each working session.  We will also try to pull back in to the master branch at the end of each day.  

We plan to convene to plan how we are going to implement CA sometime on either Tuesday or Wednesday once we have the separate components completed.  

