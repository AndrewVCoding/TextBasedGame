package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * A snapshot of the current state of the game, including the Engine.Player, Engine.World, Engine.Room contents, Engine.Container contents, Engine.Map, Interactions, etc
 */
public class SaveState
{
	public String NAME;

	//Class: Engine.GameSystems
	public String GAME_STATE;

	//Class: Engine.Interface
	public String DISPLAY;
	public String INTERACTIONS;
	public String HEADER;

	//Class: Engine.Map
	//Saved in Engine.World

	//Class: Engine.Player saved as Engine.PlayerTemplate
	public PlayerTemplate PLAYER;

	//Class: Engine.World
	public List<Item> ITEMS = new ArrayList<>();
	public List<Container> CONTAINERS = new ArrayList<>();
	public List<Room> ROOMS = new ArrayList<>();
	public List<String> PATHS = new ArrayList<>();


	public SaveState(String name)
	{
		NAME = name;
		GAME_STATE = GameSystems.GAME_STATE;
		DISPLAY = Interface.DISPLAY;
		INTERACTIONS = Interface.INTERACTIONS;
		HEADER = Interface.HEADER;
		PLAYER = new PlayerTemplate();
		ROOMS = World.ROOMS;
		PATHS = World.MAP.toList();

		//Save all Entities to their respective lists
		for(Entity entity : World.ENTITIES)
		{
			if(entity.ID.matches("I-\\d{6}"))
				ITEMS.add((Item) entity);
			if(entity.ID.matches("C-\\d{6}"))
				CONTAINERS.add((Container) entity);
		}
	}
}
