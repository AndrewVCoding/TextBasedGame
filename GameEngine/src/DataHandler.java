import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DataHandler
{
	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static String DIR_LOC = System.getProperty("user.dir");
	private static String ROOMS_LOC = DIR_LOC + "\\resources\\rooms\\";
	private static String ITEMS_LOC = DIR_LOC + "\\resources\\objects\\items\\";
	private static String CONTAINERS_LOC = DIR_LOC + "\\resources\\objects\\containers\\";
	private static String SAVE_LOC = DIR_LOC + "\\resources\\data\\saves\\";
	private static String PATHS_LOC = DIR_LOC + "\\resources\\data\\worldData\\";
	private static List<SaveState> SAVES = new ArrayList<>();
	private static SaveState SAVE_STATE;

	/**
	 * Loads all basic files from resources needed to construct the game world
	 */
	public static void loadAllBaseFiles()
	{
		//First load all entities and send through the EntityManager
		System.out.println("Loading Entities");
		loadEntities();

		//Load all rooms
		System.out.println("Loading Rooms");
		loadRooms();

		//Load base paths
		System.out.println("Loading Paths");
		loadPaths();

		//Load list of all saved games
		System.out.println("Loading SaveStates");
		loadSaves();
	}

	/**
	 * Loads all entities from files
	 */
	public static void loadEntities()
	{
		List<Entity> entities = new ArrayList<>();
		JsonReader jReader;
		File[] itemFiles = new File(ITEMS_LOC).listFiles();
		File[] containerFiles = new File(CONTAINERS_LOC).listFiles();
		try
		{
			assert itemFiles != null;
			for(File file : itemFiles)
			{
				System.out.println("Reading File: " + ITEMS_LOC + file.getName());
				try
				{
					jReader = new JsonReader(new FileReader(file));
					entities.add(GSON.fromJson(jReader, Item.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not read file" + file.getName());
				}
			}

			assert containerFiles != null;
			for(File file : containerFiles)
			{
				System.out.println("Reading File: " + CONTAINERS_LOC + file.getName());
				try
				{
					jReader = new JsonReader(new FileReader(file));
					entities.add(GSON.fromJson(jReader, Container.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not read file" + file.getName());
				}
			}
			World.ENTITIES = entities;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load items");
		}
	}

	/**
	 * Loads a list of all the default rooms for the game
	 */
	public static void loadRooms()
	{
		List<Room> roomList = new ArrayList<>();
		JsonReader jReader;
		File[] roomFiles = new File(ROOMS_LOC).listFiles();
		try
		{
			assert roomFiles != null;
			for(File file : roomFiles)
			{
				System.out.println("Reading File: " + ITEMS_LOC + file.getName());
				try
				{
					jReader = new JsonReader(new FileReader(file));
					roomList.add(GSON.fromJson(jReader, Room.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not find file" + file.getName());
				}
			}
			World.ROOMS = roomList;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load rooms");
		}
	}

	/**
	 * loads a list of all save states
	 */
	public static void loadSaves()
	{
		List<SaveState> saveStateList = new ArrayList<>();
		JsonReader jReader;
		File[] saveFiles = new File(SAVE_LOC).listFiles();
		try
		{
			assert saveFiles != null;
			for(File file : saveFiles)
			{
				System.out.println("Reading File: " + SAVE_LOC + file.getName());
				try
				{
					jReader = new JsonReader(new FileReader(file));
					saveStateList.add(GSON.fromJson(jReader, SaveState.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not find saveFile" + file.getName());
				}
			}

			SAVES = saveStateList;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load save files");
		}
	}

	/**
	 * loads the file containing all paths between rooms
	 */
	public static void loadPaths()
	{
		JsonReader jReader;
		Paths paths = new Paths();
		File pathFile = new File(PATHS_LOC + "paths.json");
		try
		{
			assert pathFile != null;
			System.out.println("Reading Path File: " + PATHS_LOC + pathFile.getName());
			try
			{
				jReader = new JsonReader(new FileReader(pathFile));
				paths = GSON.fromJson(jReader, Paths.class);
			} catch(FileNotFoundException e)
			{
				System.out.println("Could not find Paths File" + pathFile.getName());
			}


			World.PATHS = paths.PATHS;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load save files");
		}
	}

	/**
	 * loads a particular save file
	 * @param saveName the name of the save file
	 */
	public static void loadGame(String saveName)
	{
		//Find the correct save file
		for(SaveState saveState : SAVES)
		{
			if(saveState.NAME.equals(saveName))
			{
				SAVE_STATE = saveState;
				break;
			}
		}
		if(SAVE_STATE != null)
		{
			World.ROOMS = SAVE_STATE.ROOMS;
			World.PATHS = SAVE_STATE.PATHS;
			//Put all entities in a list for the EntityManager
			List<Entity> entities = new ArrayList<>();
			for(Item item : SAVE_STATE.ITEMS)
				entities.add(item);
			for(Container container : SAVE_STATE.CONTAINERS)
				entities.add(container);

			GameSystems.GAME_STATE = SAVE_STATE.GAME_STATE;

			Player.createFromTemplate(SAVE_STATE.PLAYER);

			Interface.INTERACTIONS = SAVE_STATE.INTERACTIONS;
			Interface.HEADER = SAVE_STATE.HEADER;
			Interface.DISPLAY = SAVE_STATE.DISPLAY;

			World.buildWorld();
		}
		else
			System.out.println("Could not find save file: " + saveName);
	}

	/**
	 * saves the current game to a SaveState
	 * @param name the name of the SaveState
	 */
	public static void saveGame(String name)
	{
		//Create a new SaveState
		SaveState newSave = new SaveState(name);

		//Save it as a Gson object
		String json = GSON.toJson(newSave);

		PrintWriter out;
		try
		{
			out = new PrintWriter(SAVE_LOC + newSave.NAME + ".json");
			out.println(json);

			System.out.println("game saved to: " + SAVE_LOC + newSave.NAME + ".json");

			out.flush();
			out.close();
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not write to file: " + SAVE_LOC + newSave.NAME + ".json");
		}

		//After saving a game, reload the list of saves so it includes the new one
		loadSaves();
	}

	/**
	 * used for saving templates/test versions of resources
	 */
	public static void saveBaseFiles()
	{
		for(Entity entity:World.ENTITIES)
			save(entity);
		for(Room room : World.ROOMS)
			save(room);
	}

	public static void save(Entity entity)
	{
		String json = GSON.toJson(entity);

		String directory = DIR_LOC + "\\resources\\";
		if(entity.ID.matches("I-\\d{6}"))
			directory = ITEMS_LOC;
		if(entity.ID.matches("C-\\d{6}"))
			directory = CONTAINERS_LOC;

		PrintWriter out;
		try
		{
			out = new PrintWriter(directory + entity.ID + entity.NAME + ".json");
			out.println(json);

			System.out.println("game saved to: " + directory + entity.ID + entity.NAME + ".json");

			out.flush();
			out.close();
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not write to file: " + directory + entity.ID + entity.NAME + ".json");
		}
	}

	public static void save(Room room)
	{
		String json = GSON.toJson(room);

		PrintWriter out;
		try
		{
			out = new PrintWriter(ROOMS_LOC + room.ID + room.NAME + ".json");
			out.println(json);

			System.out.println("game saved to: " + ROOMS_LOC + room.ID + room.NAME + ".json");

			out.flush();
			out.close();
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not write to file: " + ROOMS_LOC + room.ID + room.NAME + ".json");
		}
	}

	public static void listSaves()
	{
		String output = "";
		int i = 0;
		for(SaveState saveState : SAVES)
		{
			output += i + ") " + saveState.NAME + "\n";
			i++;
		}

		Interface.display(output);
	}
}
