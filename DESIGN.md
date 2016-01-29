
# cellsociety
Duke CompSci 308 Cell Society Project
Colin, Ani, Christine


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
	

