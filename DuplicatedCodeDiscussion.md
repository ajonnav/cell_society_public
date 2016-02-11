Duplicated Code
===============
clz4 and mfz2
--------------
For cell society team 19, I chose to refactor the easiest to refactor parts of the code. These were places were the 
exact same code was used. The wator cells had some code that was exactly the same and I refactored this into a method.
Segregation cells also had subclasses that had the exact same method with duplicate code. In this, I moved the 
method into the superclass and deleted the duplicated code from the other classes. Other changes that were considered
were very similar code that could be changed through reflection. However, we have not gone over reflection quite yet.
Therefore these changes were not as urgent as code that was exactly duplicated in certain classes. 
