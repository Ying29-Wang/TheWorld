
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
- Graphical User Interface: A Swing-based GUI is provided for interacting with the game.



## Project Structure
- Model: Represents the game world, spaces, items, players, and the target character.
- Controller: Manages user input and executes commands to interact with the model.
- Commands: Implements the command design pattern to handle various actions (e.g., move player, pick up item).
- Driver: Launches the controller and handles command-line arguments.
- Viewer: Provides a graphical user interface for the game.

## Classes and Interfaces
### Model
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

### Controller
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
- AdapterInterface: Interface for adapting input and output operations between the view and the controller.
- IOAdapter: Implements AdapterInterface to handle input and output operations.

### Viewer
- IView: Interface for the graphical user interface.
- Viewer: Implements IView and provides a Swing-based GUI for the game.

### Driver
- Driver: Launches the controller and handles command-line arguments.

## Usage
### Running the Game
To run the game, use the following command in Terminal:
java -jar TheWorld.jar sampleInput.txt 10

## Demo
