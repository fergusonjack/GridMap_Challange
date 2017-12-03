# GridMap_Challange

## Assumptions
* A maximum of 1000 tickets
* The event of the tile you selected will not be returned
* Price of tickets is random between $10 and $1500

## Building
This project uses Maven so to compile run in the main directory:

> mvn package

However, if you do not have Maven I have included a pre-compiled jar file which can be run with while in the main directory:

> java -cp .\target\viagogo-0.0.1-SNAPSHOT-jar-with-dependencies.jar display.DisplayWithGUI

## Usage
There is a GUI to use that allows you to click on locations on the grid instead of inputting the coordinates directly however if you want to input coordinates there is a box for the x and y coordinates to allow you to do this then click the search button to searh for the events. There is also a reset button that will reset the gridmap back to its original state and display all the events rather than just the closest 5 to the tile selected.

![alt Main Screen](img/mainScreen.PNG)
![alt After Selection](img/Selected.PNG)


## How might you change your program if you needed to support multiple events at the same location?
I have a tile class that currently contains an optional type of Event this could be changed to work with an ArrayList that would support multiple events at a location.


## How would you change your program if you were working with a much larger world size?
I have variables that allow for the change of the world size very easily just 4 variables however larger sized maps might not fit on the GUI so well and may require adjustment of the size of the buttons (this could be done by taking the size of the array in the x and y direction and having each button about 0.75 times the size of this in pixels). However, the buttons would get very small with a larger number of tiles. It would be possible to have some sort of zoom in function or selecting just a section of the map to look at but I felt this was beyond the specification at this point.
