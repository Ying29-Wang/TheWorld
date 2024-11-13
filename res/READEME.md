<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome file</title>
  <link rel="stylesheet" href="https://stackedit.io/style.css" />
</head>

<body class="stackedit">
  <div class="stackedit__html"><h1 id="table-contents">Table contents</h1>
<h1 id="res">Res</h1>
<p>This is the resource file of the model, it including the resource files of this programme as well as the examples runs, as showed in blow.</p>
<h2 id="the-wrold.pdf">the wrold.pdf</h2>
<p>The file is the first edition of the preliminary UML design.</p>
<h2 id="theworldupdated.pdf">theworldUpdated.pdf</h2>
<p>This file is the updated UML design, a theworldFacade class has been updated compared to the first edition. The implementation is based on this design.</p>
<h2 id="testplan.docx">testplan.docx</h2>
<p>This file shows the test plan.</p>
<h2 id="sampleinput.txt">sampleInput.txt</h2>
<p>This txt file contains a new design of the world, which is the input source for the implementation.</p>
<h2 id="theworld.txt">theworld.txt</h2>
<p>This is the output of theworld model, it moves the character in the different spaces in orderly and shows the detailed information of every space with its basic information and the items that in it, also show all the neighbors of that particular space.</p>
<h2 id="map.png">map.png</h2>
<p>This is also the output of the world model. It print the layout of all the spaces in the world.</p>
<h1 id="srcthe-world">Src/the world</h1>
<p>This is the source package of the model, under it there is one package named the world including thirteen java documents, detailed description is provided as below.</p>
<h2 id="characterinterface.java">CharacterInterface.java</h2>
<p>This is the Character Interface. It provides the name, the current position and  also move it to its next destination in the world.</p>
<h2 id="target.java">Target.java</h2>
<p>This class represents the target of the game. It provide the name and health number. Also, it gives the current position and target position of the target.</p>
<h2 id="iteminterface.java">ItemInterface.java</h2>
<p>Item interface containing the ID, name, damage amount and current position of this target.</p>
<h2 id="itemfactory.java">ItemFactory.java</h2>
<p>This is a factory class for creating instances of Item. It provides a method to create items with given values like id, name, and damage value.</p>
<h2 id="item.java">Item.java</h2>
<p>This class represent an item that contains a name, an id, its damage value. Also it provide the current space.</p>
<h2 id="spaceinterface.java">SpaceInterface.java</h2>
<p>This class represents a general interface for spaces, providing methods to access the space’s properties such as its name, id, and boundary coordinates.</p>
<h2 id="spacefactory.java">SpaceFactory.java</h2>
<p>This class provides a factory method to create instances of space. It encapsulates the object creation logic and handles exceptions during the instantiation process.</p>
<h2 id="space.java">Space.java</h2>
<p>This class represents a space in the world, defined by its unique ID, name, upper-left and lower-right coordinates, neighboring spaces, and the items it contains. This class provides methods to access and manipulate the space’s attributes and calculate its neighbors based on spatial proximity.</p>
<h2 id="mapinterface.java">MapInterface.java</h2>
<p>This interface represents a blueprint for a map. It defines the methods that provide its dimensions and the collection of spaces.</p>
<h2 id="theworld.java">TheWorld.java</h2>
<p>This class represents a world consisting of multiple spaces and items. It manages the layout of the spaces, items, and a target within the world.<br>
Each world is defined by its name, number of rows, and number of columns.</p>
<h2 id="mode.java">Mode.java</h2>
<p>The enum represents the different modes that can be used by the target in the world.</p>
<h2 id="theworldfacade.java">TheWorldFacade.java</h2>
<p>This class provides a facade for creating and managing the model of the world. It including adding items, spaces, and targets. It also handles the visual representation of the world.</p>
<h2 id="driver.java">Driver.java</h2>
<p>This class serves as the entry point for the application. It initializes the TheWorldFacade, pass a specification file, and executes actions on the TheWorld instance based on command-line arguments.</p>
<h1 id="test">Test</h1>
<p>This is the test package which contains five test java documents to test the functionalities of the world model.</p>
<h2 id="itemtest.java">ItemTest.java</h2>
<p>This class tests the functionalities of the Item class, including its constructor and methods. It also covers both normal and exceptional cases to ensure robust functionality.</p>
<h2 id="targettest.java">TargetTest.java</h2>
<p>Test class for Target, including methods for creating targets, moving them into spaces, and checking health and other attributes.</p>
<h2 id="spacetest.java">SpaceTest.java</h2>
<p>Test class for Space class, including methods for creating spaces, managing items, and calculating neighbor spaces.</p>
<h2 id="theworldtest.java">TheWorldTest.java</h2>
<p>This test class is designed to verify the functionality of the TheWorld class. It includes tests for various aspects such as constructing the world, adding items and spaces, retrieving information, and validating target-related functions.</p>
<h2 id="theworldfacade.java-1">TheWorldFacade.java</h2>
<p>This test class is designed to verify the functionality of the TheWorldFacade class. It includes tests for parsing the world from a file and moving targets within the world.</p>
</div>
</body>

</html>
