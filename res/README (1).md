
# TheWorld
TheWorld is a text-based adventure game inspired by the Doctor Lucky series of games. The game is played through printable text data, usually through a console. The player takes on the role of the protagonist in an interactive story driven by exploration and puzzle-solving. The game world consists of a number of non-overlapping spaces laid out on a 2D grid, with items, a target character, a pet and some players that moves around the world.


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
- Pet: Represents the pet in the game.
- TheWorldFacade: Provides a facade for creating and managing the model of the world.
- SpaceFactory: Factory class for creating instances of Space.
- ItemFactory: Factory class for creating instances of Item.
- Interfaces: SpaceInterface, ItemInterface, PlayerInterface, CharacterInterface, MapInterface, PetInterface

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
- Attempt: Command for a player to attack the target.
- MovePet: Command to move a pet to a different space
- TheWorldController: Manages user input and executes commands.
- ControllerInterface: Interface for TheWorld Controller.

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
d
Now, we can enter the creepy castle to begin our trip...
===========================================================================
It is turn 0
Now is robot turn:
you are not in any space right now
robot has already moved to Potion Room
The Earl Decuras has already moved to No. 0 Throne Room
Fortune the Cat has already moved to No. 0 Throne Room
===========================================================================
It is turn 1
Now is Ella turn:
you are not in any space right now
Enter a move for Ella to 0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
0
Ella has already moved to Throne Room
The Earl Decuras has already moved to No. 1 Grand Ballroom
Fortune the Cat has already moved to No. 15 Alchemy Lab
===========================================================================
It is turn 2
Now is robot turn:
you are in the space 18 Potion Room
robot decide to drop off an item to the space
No item carried by robot.
===========================================================================
It is turn 2
Now is robot turn:
you are in the space 18 Potion Room
robot decide to look up around
this is the player robot, he/she is in the space No.18 Potion Room
Item Spectral Blade is in the space
Item Fangstinger Sickle is in the space
now he/she is watching the space:
Space No.3 Coffin Chamber; upleft:25,15; downright:37,20;
this space includes 0 items
it has 3 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.9 Secret Passage is a neighbor
- Space No.18 Potion Room is a neighbor
now he/she is watching the space:
Space No.5 Dungeon; upleft:30,25; downright:37,31;
this space includes 0 items
it has 1 neighbors
- Space No.18 Potion Room is a neighbor
The Earl Decuras has already moved to No. 2 Blood Fountain
Fortune the Cat has already moved to No. 14 Blacksmith's Forge
===========================================================================
It is turn 3
Now is Ella turn:
you are in the space 0 Throne Room
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
1
Enter a move for Ella to 2. Blood Fountain 15. Alchemy Lab 
2
Ella has already moved to Blood Fountain
The Earl Decuras has already moved to No. 3 Coffin Chamber
Fortune the Cat has already moved to No. 10 Vampire's Lair
===========================================================================
It is turn 4
Now is robot turn:
you are in the space 18 Potion Room
robot decide to drop off an item to the space
No item carried by robot.
===========================================================================
It is turn 4
Now is robot turn:
you are in the space 18 Potion Room
robot decide to pickup an item
Please pick an item showing below.
13. Spectral Blade 15. Fangstinger Sickle 
The item had been picked up by robot.
The Earl Decuras has already moved to No. 4 Bat Tower
Fortune the Cat has already moved to No. 8 Shadow Gallery
===========================================================================
It is turn 5
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
Please pick an item showing below.
6. Nosferatu's Claw 11. Hellfire Mace 
6
The item has been picked up by Ella.
The Earl Decuras has already moved to No. 5 Dungeon
Fortune the Cat has already moved to No. 17 Feeding Hall
===========================================================================
It is turn 6
Now is robot turn:
you are in the space 18 Potion Room
robot decide to pickup an item
Please pick an item showing below.
15. Fangstinger Sickle 
The item had been picked up by robot.
The Earl Decuras has already moved to No. 6 Torture Chamber
Fortune the Cat has already moved to No. 13 Moonlit Balcony
===========================================================================
It is turn 7
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
3
Please leave an item in the space, items are shown below.
6. Nosferatu's Claw 
6
The item has been dropped off by Ella.
The Earl Decuras has already moved to No. 7 Dark Library
Fortune the Cat has already moved to No. 19 Mirror Room
===========================================================================
It is turn 8
Now is robot turn:
you are in the space 18 Potion Room
robot decide to drop off an item to the space
Please leave an item in the space, items are shown below.
13. Spectral Blade 15. Fangstinger Sickle 
The item had been left to the space Potion Room by robot.
The Earl Decuras has already moved to No. 8 Shadow Gallery
Fortune the Cat has already moved to No. 16 Guard Room
===========================================================================
It is turn 9
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
4
this is the player Ella, he/she is in the space No.2 Blood Fountain
Item Hellfire Mace is in the space
Item Nosferatu's Claw is in the space
now he/she is watching the space:
Space No.0 Throne Room; upleft:2,4; downright:10,8;
this space includes 1 items
- Curse Whip, cause 3 damage
it has 2 neighbors
- Space No.2 Blood Fountain is a neighbor
- Space No.15 Alchemy Lab is a neighbor
now he/she is watching the space:
Space No.1 Grand Ballroom; upleft:8,18; downright:24,23;
this space includes 1 items
- Soulreaver Axe, cause 2 damage
it has 8 neighbors
- Space No.2 Blood Fountain is a neighbor
- Space No.3 Coffin Chamber is a neighbor
- Space No.4 Bat Tower is a neighbor
- Space No.6 Torture Chamber is a neighbor
- Space No.7 Dark Library is a neighbor
- Space No.8 Shadow Gallery is a neighbor
- Space No.11 Crypt is a neighbor
- Space No.12 Ritual Hall is a neighbor
now he/she is watching the space:
Space No.7 Dark Library; upleft:6,18; downright:7,23;
this space includes 1 items
- Gravekeeper's Spade, cause 2 damage
it has 5 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.2 Blood Fountain is a neighbor
- Space No.6 Torture Chamber is a neighbor
- Space No.16 Guard Room is a neighbor
- Space No.19 Mirror Room is a neighbor
now he/she is watching the space:
Space No.8 Shadow Gallery; upleft:13,12; downright:21,17;
this space includes 2 items
- Bloodthirst Blade, cause 3 damage
- Nightmare Crossbow, cause 3 damage
it has 4 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.2 Blood Fountain is a neighbor
- Space No.10 Vampire's Lair is a neighbor
- Space No.14 Blacksmith's Forge is a neighbor
now he/she is watching the space:
Space No.15 Alchemy Lab; upleft:11,4; downright:14,8;
this space includes 1 items
- Crimson Halberd, cause 3 damage
it has 3 neighbors
- Space No.0 Throne Room is a neighbor
- Space No.2 Blood Fountain is a neighbor
- Space No.10 Vampire's Lair is a neighbor
The neighbor space Guard Room is invisible
The Earl Decuras has already moved to No. 9 Secret Passage
Fortune the Cat has already moved to No. 7 Dark Library
===========================================================================
It is turn 10
Now is robot turn:
you are in the space 18 Potion Room
robot decide to look up around
this is the player robot, he/she is in the space No.18 Potion Room
Item Fangstinger Sickle is in the space
now he/she is watching the space:
Space No.3 Coffin Chamber; upleft:25,15; downright:37,20;
this space includes 0 items
it has 3 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.9 Secret Passage is a neighbor
- Space No.18 Potion Room is a neighbor
now he/she is watching the space:
Space No.5 Dungeon; upleft:30,25; downright:37,31;
this space includes 0 items
it has 1 neighbors
- Space No.18 Potion Room is a neighbor
The Earl Decuras has already moved to No. 10 Vampire's Lair
Fortune the Cat has already moved to No. 6 Torture Chamber
===========================================================================
It is turn 11
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
5
Add a player controlled by computer? Press Y to create a robot, or any other key to create a human-controlled player
n
Please enter the name: 
Ada
New player Ada has been added
===========================================================================
It is turn 11
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
6
Please input the file name to store the picture:
image1.png
The map has been stored to the file image1.png
===========================================================================
It is turn 11
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
7
Please pick a player to show description
0. robot 1. Ella 2. Ada 
2
this is the player Ada, he/she is not in any space right now.
===========================================================================
It is turn 11
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
8
please pick a space to show description
0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
1
Space No.1 Grand Ballroom; upleft:8,18; downright:24,23;
this space includes 1 items
- Soulreaver Axe, cause 2 damage
it has 8 neighbors
- Space No.2 Blood Fountain is a neighbor
- Space No.3 Coffin Chamber is a neighbor
- Space No.4 Bat Tower is a neighbor
- Space No.6 Torture Chamber is a neighbor
- Space No.7 Dark Library is a neighbor
- Space No.8 Shadow Gallery is a neighbor
- Space No.11 Crypt is a neighbor
- Space No.12 Ritual Hall is a neighbor
===========================================================================
It is turn 11
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
9
Move the pet to an space showing below .
0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
3
The Earl Decuras has already moved to No. 11 Crypt
Fortune the Cat has already moved to No. 3 Coffin Chamber
===========================================================================
It is turn 12
Now is Ada turn:
you are not in any space right now
Enter a move for Ada to 0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
1
Ada has already moved to Grand Ballroom
The Earl Decuras has already moved to No. 12 Ritual Hall
Fortune the Cat has already moved to No. 5 Dungeon
===========================================================================
It is turn 13
Now is robot turn:
you are in the space 18 Potion Room
robot decide to drop off an item to the space
Please leave an item in the space, items are shown below.
13. Spectral Blade 
The item had been left to the space Potion Room by robot.
The Earl Decuras has already moved to No. 13 Moonlit Balcony
Fortune the Cat has already moved to No. 18 Potion Room
===========================================================================
It is turn 14
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
10
target is not in the space, try other options.
===========================================================================
It is turn 14
Now is Ella turn:
you are in the space 2 Blood Fountain
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
1
Enter a move for Ella to 0. Throne Room 1. Grand Ballroom 7. Dark Library 8. Shadow Gallery 15. Alchemy Lab 16. Guard Room 
8
Ella has already moved to Shadow Gallery
The Earl Decuras has already moved to No. 14 Blacksmith's Forge
Fortune the Cat has already moved to No. 21 Cursed Armory
===========================================================================
It is turn 15
Now is Ada turn:
you are in the space 1 Grand Ballroom
Choose an action for Ada, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
Please pick an item showing below.
8. Soulreaver Axe 
8
The item has been picked up by Ada.
The Earl Decuras has already moved to No. 15 Alchemy Lab
Fortune the Cat has already moved to No. 9 Secret Passage
===========================================================================
It is turn 16
Now is robot turn:
you are in the space 18 Potion Room
robot decide to pickup an item
Please pick an item showing below.
15. Fangstinger Sickle 13. Spectral Blade 
The item had been picked up by robot.
The Earl Decuras has already moved to No. 16 Guard Room
Fortune the Cat has already moved to No. 16 Guard Room
===========================================================================
It is turn 17
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
Please pick an item showing below.
0. Bloodthirst Blade 3. Nightmare Crossbow 
3
The item has been picked up by Ella.
The Earl Decuras has already moved to No. 17 Feeding Hall
Fortune the Cat has already moved to No. 19 Mirror Room
===========================================================================
It is turn 18
Now is Ada turn:
you are in the space 1 Grand Ballroom
Choose an action for Ada, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
10
target is not in the space, try other options.
===========================================================================
It is turn 18
Now is Ada turn:
you are in the space 1 Grand Ballroom
Choose an action for Ada, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
9
Move the pet to an space showing below .
0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
3
The Earl Decuras has already moved to No. 18 Potion Room
Fortune the Cat has already moved to No. 3 Coffin Chamber
===========================================================================
It is turn 19
Now is robot turn:
you are in the space 18 Potion Room
robot decide to attack target
robot is trying to attack target by using Spectral Blade 
The Target has been attacked by Spectral Blade and cause 3 hurt. Health number is left for 197
The Earl Decuras has already moved to No. 19 Mirror Room
Fortune the Cat has already moved to No. 5 Dungeon
===========================================================================
It is turn 20
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
8
please pick a space to show description
0. Throne Room 1. Grand Ballroom 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 5. Dungeon 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 9. Secret Passage 10. Vampire's Lair 11. Crypt 12. Ritual Hall 13. Moonlit Balcony 14. Blacksmith's Forge 15. Alchemy Lab 16. Guard Room 17. Feeding Hall 18. Potion Room 19. Mirror Room 20. Blood Chamber 21. Cursed Armory 
8
Space No.8 Shadow Gallery; upleft:13,12; downright:21,17;
Player Ella is in the space
this space includes 1 items
- Bloodthirst Blade, cause 3 damage
it has 4 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.2 Blood Fountain is a neighbor
- Space No.10 Vampire's Lair is a neighbor
- Space No.14 Blacksmith's Forge is a neighbor
===========================================================================
It is turn 20
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
Please pick an item showing below.
0. Bloodthirst Blade 
0
The item has been picked up by Ella.
The Earl Decuras has already moved to No. 20 Blood Chamber
Fortune the Cat has already moved to No. 18 Potion Room
===========================================================================
It is turn 21
Now is Ada turn:
you are in the space 1 Grand Ballroom
Choose an action for Ada, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
1
Enter a move for Ada to 2. Blood Fountain 3. Coffin Chamber 4. Bat Tower 6. Torture Chamber 7. Dark Library 8. Shadow Gallery 11. Crypt 12. Ritual Hall 
20
Wrong input, please re-enter the space number.
6
Ada has already moved to Torture Chamber
The Earl Decuras has already moved to No. 21 Cursed Armory
Fortune the Cat has already moved to No. 21 Cursed Armory
===========================================================================
It is turn 22
Now is robot turn:
you are in the space 18 Potion Room
robot decide to look up around
this is the player robot, he/she is in the space No.18 Potion Room
Item Fangstinger Sickle is in the space
now he/she is watching the space:
Space No.3 Coffin Chamber; upleft:25,15; downright:37,20;
this space includes 0 items
it has 3 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.9 Secret Passage is a neighbor
- Space No.18 Potion Room is a neighbor
now he/she is watching the space:
Space No.5 Dungeon; upleft:30,25; downright:37,31;
this space includes 0 items
it has 1 neighbors
- Space No.18 Potion Room is a neighbor
The Earl Decuras has already moved to No. 0 Throne Room
Fortune the Cat has already moved to No. 9 Secret Passage
===========================================================================
It is turn 23
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
10
target is not in the space, try other options.
===========================================================================
It is turn 23
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
No item list in the room.
something wrong occurred, please try something else.
===========================================================================
It is turn 23
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
1
Enter a move for Ella to 1. Grand Ballroom 2. Blood Fountain 10. Vampire's Lair 14. Blacksmith's Forge 
14
Ella has already moved to Blacksmith's Forge
The Earl Decuras has already moved to No. 1 Grand Ballroom
Fortune the Cat has already moved to No. 16 Guard Room
===========================================================================
It is turn 24
Now is Ada turn:
you are in the space 6 Torture Chamber
Choose an action for Ada, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
Please pick an item showing below.
10. Bone Spear 12. Widow's Kiss Bow 
12
The item has been picked up by Ada.
The Earl Decuras has already moved to No. 2 Blood Fountain
Fortune the Cat has already moved to No. 19 Mirror Room
===========================================================================
It is turn 25
Now is robot turn:
you are in the space 18 Potion Room
robot decide to look up around
this is the player robot, he/she is in the space No.18 Potion Room
Item Fangstinger Sickle is in the space
now he/she is watching the space:
Space No.3 Coffin Chamber; upleft:25,15; downright:37,20;
this space includes 0 items
it has 3 neighbors
- Space No.1 Grand Ballroom is a neighbor
- Space No.9 Secret Passage is a neighbor
- Space No.18 Potion Room is a neighbor
now he/she is watching the space:
Space No.5 Dungeon; upleft:30,25; downright:37,31;
this space includes 0 items
it has 1 neighbors
- Space No.18 Potion Room is a neighbor
The Earl Decuras has already moved to No. 3 Coffin Chamber
Fortune the Cat has already moved to No. 13 Moonlit Balcony
===========================================================================
It is turn 26
Now is Ella turn:
you are in the space 14 Blacksmith's Forge
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
1
Enter a move for Ella to 8. Shadow Gallery 10. Vampire's Lair 
8
Ella has already moved to Shadow Gallery
The Earl Decuras has already moved to No. 4 Bat Tower
Fortune the Cat has already moved to No. 17 Feeding Hall
===========================================================================
It is turn 27
Now is Ada turn:
you are in the space 6 Torture Chamber
Choose an action for Ada, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
2
Please pick an item showing below.
10. Bone Spear 
10
The item has been picked up by Ada.
The Earl Decuras has already moved to No. 5 Dungeon
Fortune the Cat has already moved to No. 4 Bat Tower
===========================================================================
It is turn 28
Now is robot turn:
you are in the space 18 Potion Room
robot decide to pickup an item
Please pick an item showing below.
15. Fangstinger Sickle 
The item had been picked up by robot.
The Earl Decuras has already moved to No. 6 Torture Chamber
Fortune the Cat has already moved to No. 11 Crypt
===========================================================================
It is turn 29
Now is Ella turn:
you are in the space 8 Shadow Gallery
Choose an action for Ella, only press 1-10: 
1.move to another space 
2.pickup an item in the space 
3.dropoff an item to the space 
4.look around 
5.add a player 
6.draw the map 
7.show a player 
8.show a space
9.move pet 
10.attempt to target 
quit game press q.
q
User quit game, Bye!