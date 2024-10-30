
# TheWorld
TheWorld is a text-based adventure game inspired by the Doctor Lucky series of games. The game is played through printable text data, usually through a console. The player takes on the role of the protagonist in an interactive story driven by exploration and puzzle-solving. The game world consists of a number of non-overlapping spaces laid out on a 2D grid, with items, a target character and some players that moves around the world.


## Requirements
- Java 8 (or a specific version required for the project)
- Command line or terminal access
## Features
- World Representation: The game world is represented in memory, including spaces, items, and the target character.
- Player Interaction: Players can move around the world, pick up items, and look around to gather information.
- Multiple Players: The game supports multiple players, each taking turns in the order they were added.
- Computer-Controlled Player: A computer-controlled player automatically chooses actions during its turn.
- Graphical Representation: The world map can be created and saved as a PNG file.
- Text-Based Controller: A simple text-based controller is used to interact with the model.



## Project Structure
- Model: Represents the game world, spaces, items, players, and the target character.
- Controller: Manages user input and executes commands to interact with the model.
- Commands: Implements the command design pattern to handle various actions (e.g., move player, pick up item).
- Driver: Launches the controller and handles command-line arguments.
## Classes and Interfaces
Model
- TheWorld: Represents the entire game world.
- Space: Represents a space or room in the world.
- Item: Represents an item in the world.
- Player: Represents a player in the game.
- Target: Represents the target character.
- TheWorldFacade: Provides a facade for creating and managing the model of the world.
- SpaceFactory: Factory class for creating instances of Space.
- ItemFactory: Factory class for creating instances of Item.
- Interfaces: SpaceInterface, ItemInterface, PlayerInterface, CharacterInterface, MapInterface

Controller
- CommandInterface: Interface for commands.
- AddPlayer: Command to add a player to the game.
- Move: Command to move a player to a neighboring space.
- Pickup: Command to allow a player to pick up an item.
- DropOff: Command to allow a player to drop off an item.
- LookAround: Command to allow a player to look around.
- ShowPlayer: Command to display a description of a specific player.
- ShowSpace: Command to display information about a specified space.
- DrawMap: Command to create and save a graphical representation of the world map.
- TheWorldController: Manages user input and executes commands.

Driver
- Driver: Launches the controller and handles command-line arguments.
## Usage
Running the Game
To run the game, use the following command in Terminal:
java -jar TheWorld.jar sampleInput.txt 10
## Demo

Insert gif or link to demo
Welcome! The game is start
------------------------------------------------------------
The Dracula's haunted Castle is open, let's go and take an adventure!
Before that, please create at least one player to start our journey.
Add a player controlled by computer? Press Y to create a robot, or any other key to create a human-controlled player
y
Please enter the name: 
robot
New player robot has been added
Press Y to add more player, press any key to continue the game.
y
Add a player controlled by computer? Press Y to create a robot, or any other key to create a human-controlled player
n
Please enter the name: 
Ella
New player Ella has been added
Press Y to add more player, press any key to continue the game.
y
Add a player controlled by computer? Press Y to create a robot, or any other key to create a human-controlled player
n
Please enter the name: 
Ada
New player Ada has been added
Press Y to add more player, press any key to continue the game.
n
Now, we can enter the creepy castle to begin our trip...
===========================================================================
It is turn 0
Now is robot turn:
robot has already moved to Alchemy Lab
The Earl Decuras has already moved to No. 0 Throne Room
===========================================================================
It is turn 1
Now is Ella turn:
Enter a move for Ella to 0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
2
Ella has already moved to Blood Fountain
The Earl Decuras has already moved to No. 1 Grand Ballroom
===========================================================================
It is turn 2
Now is Ada turn:
Enter a move for Ada to 0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
9
Ada has already moved to Secret Passage
The Earl Decuras has already moved to No. 2 Blood Fountain
===========================================================================
It is turn 3
Now is robot turn:
robot decide to look up around
this is the player robot, he/she is in the space No.15 Alchemy Lab
now he/she is watching the space:
Space No.0 Throne Room; upleft:2,4; downright:10,8;
includes 1 items
- Curse Whip, cause 3 damage
it has 2 neighbors
- Space No.2 Blood Fountain is a neighbor
- Space No.15 Alchemy Lab is a neighbor
now he/she is watching the space:
Space No.2 Blood Fountain; upleft:6,9; downright:12,17;
includes 2 items
- Nosferatu's Claw, cause 4 damage
- Hellfire Mace, cause 4 damage
it has 6 neighbors
- Space No.0 Throne Room is a neighbor
- Space No.1 Grand Ballroom is a neighbor
- Space No.7 Dark Library is a neighbor
- Space No.8 Shadow Gallery is a neighbor
- Space No.15 Alchemy Lab is a neighbor
- Space No.16 Guard Room is a neighbor
now he/she is watching the space:
Space No.10 Vampire's Lair; upleft:15,6; downright:20,11;
includes 0 items
it has 3 neighbors
- Space No.8 Shadow Gallery is a neighbor
- Space No.14 Blacksmith's Forge is a neighbor
- Space No.15 Alchemy Lab is a neighbor
The Earl Decuras has already moved to No. 3 Coffin Chamber
===========================================================================
It is turn 4
Now is Ella turn:
Choose an action for Ella, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
2
Please pick an item showing below.
6. Nosferatu's Claw 11. Hellfire Mace 
11
The item has been picked up by Ella.
The Earl Decuras has already moved to No. 4 Bat Tower
===========================================================================
It is turn 5
Now is Ada turn:
Choose an action for Ada, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
4
this is the player Ada, he/she is in the space No.9 Secret Passage
now he/she is watching the space:
Space No.3 Coffin Chamber; upleft:25,15; downright:37,20;
includes 0 items
it has 3 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.9 Secret Passage is a neighbor
- Space No.18 Potion Room is a neighbor
now he/she is watching the space:
Space No.21 Cursed Armory; upleft:29,3; downright:35,9;
includes 0 items
it has 1 neighbors
- Space No.9 Secret Passage is a neighbor
The Earl Decuras has already moved to No. 5 Dungeon
===========================================================================
It is turn 6
Now is robot turn:
robot decide to drop off an item to the space
No item carried by robot.
===========================================================================
It is turn 6
Now is robot turn:
robot decide to move to a space
robot has already moved to Blood Fountain
The Earl Decuras has already moved to No. 6 Torture Chamber
===========================================================================
It is turn 7
Now is Ella turn:
Choose an action for Ella, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
6
Please input the file name to store the picture:
sampleRun.png
The map has been stored to the file sampleRun.png
===========================================================================
It is turn 7
Now is Ella turn:
Choose an action for Ella, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
7
Please pick a player to show description
0. robot 1. Ella 2. Ada 
2
this is the player Ada, he/she is in the space No.9 Secret Passage
===========================================================================
It is turn 7
Now is Ella turn:
Choose an action for Ella, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
8
please pick a space to show description
0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
8
Space No.8 Shadow Gallery; upleft:13,12; downright:21,17;
includes 2 items
- Bloodthirst Blade, cause 3 damage
- Nightmare Crossbow, cause 3 damage
it has 4 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.2 Blood Fountain is a neighbor
- Space No.10 Vampire's Lair is a neighbor
- Space No.14 Blacksmith's Forge is a neighbor
===========================================================================
It is turn 7
Now is Ella turn:
Choose an action for Ella, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
1
Enter a move for Ella to 0. Throne Room 1. Grand Ballroom 7. Dark Library 8. Shadow Gallery 15. Alchemy Lab 16. Guard Room 
10
Wrong input, please re-enter the space number.
16
Ella has already moved to Guard Room
The Earl Decuras has already moved to No. 7 Dark Library
===========================================================================
It is turn 8
Now is Ada turn:
Choose an action for Ada, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
3
No item carried by Ada.
something wrong occurred, please try something else.
===========================================================================
It is turn 8
Now is Ada turn:
Choose an action for Ada, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
2
Please pick an item showing below.
18. Wraith’s Bow 
18
The item has been picked up by Ada.
The Earl Decuras has already moved to No. 8 Shadow Gallery
===========================================================================
It is turn 9
Now is robot turn:
robot decide to drop off an item to the space
No item carried by robot.
===========================================================================
It is turn 9
Now is robot turn:
robot decide to move to a space
robot has already moved to Dark Library
The Earl Decuras has already moved to No. 9 Secret Passage
===========================================================================
It is turn 10
Now is Ella turn:
Choose an action for Ella, only press 1-8: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
quit game press q.
3
Please leave an item in the space, items are shown below.
11. Hellfire Mace 
11
The item has been dropped off by Ella.
The Earl Decuras has already moved to No. 10 Vampire's Lair
You are running out of turns, game is over, Bye!
