package Data;

import Engine.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DataLoader
{
	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	public static String DIR_LOC = System.getProperty("user.dir");
	public static String GAME_ENTITIES = DIR_LOC + "\\resources\\rooms\\";
	public static String SPECIES = DIR_LOC + "\\resources\\rooms\\";
	public static String DESCRIPTIONS = DIR_LOC + "\\resources\\rooms\\";
	public static String ROOMS = DIR_LOC + "\\resources\\rooms\\";
	public static String ITEMS = DIR_LOC + "\\resources\\objects\\items\\";
	public static String CONTAINERS = DIR_LOC + "\\resources\\objects\\containers\\";
	public static String SAVES = DIR_LOC + "\\resources\\data\\saves\\";
	public static String PATHS_LOC = DIR_LOC + "\\resources\\data\\worldData\\";
	public static List<SaveState> SAVES_OLD = new ArrayList<>();
	public static SaveState SAVE_STATE;

	public static List<Object[]> loadEntities(String path)
	{
		List<Object[]> output = new ArrayList<>();
		JsonReader jReader;

		// Read all rows in the file
		try
		{
			jReader = new JsonReader(new FileReader(path));
			output.add(GSON.fromJson(jReader, Item.class));
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not read file " + path);
		}
		return output;
	}

	public static List<String> listFiles(String path)
	{
		List<String> output = new ArrayList<>();
		JsonReader jReader;
		File[] fileList = new File(path).listFiles();
		try
		{
			assert fileList != null;
			for(File file : fileList)
			{
				output.add(file.getName());
			}
		} catch(NullPointerException e)
		{
			System.out.println("Could not get list at " + path);
		}

		return output;
	}

	/**
	 * Loads all basic files from resources needed to construct the game world
	 */
	public static void loadAllBaseFiles()
	{
		//First load all entities and send through the Engine.EntityManager
		System.out.println("Loading Entities");
		loadEntities();

		//Load all rooms
		System.out.println("Loading Rooms");
		loadRooms();

		//Load base paths
		System.out.println("Loading Engine.PathsOld");
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
		File[] itemFiles = new File(ITEMS).listFiles();
		File[] containerFiles = new File(CONTAINERS).listFiles();
		try
		{
			assert itemFiles != null;
			for(File file : itemFiles)
			{
				System.out.println("Reading File: " + ITEMS + file.getName());
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
				System.out.println("Reading File: " + CONTAINERS + file.getName());
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
		File[] roomFiles = new File(ROOMS).listFiles();
		try
		{
			assert roomFiles != null;
			for(File file : roomFiles)
			{
				System.out.println("Reading File: " + ITEMS + file.getName());
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
	 * loads the file containing all paths between rooms
	 */
	public static void loadPaths()
	{
		JsonReader jReader;
		PathsOld paths = new PathsOld();
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
				System.out.println("Could not find Engine.PathsOld File" + pathFile.getName());
			}


			World.PATHS = paths.PATHS;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load save files");
		}
	}

	/**
	 * loads a list of all save states
	 */
	public static void loadSaves()
	{
		List<SaveState> saveStateList = new ArrayList<>();
		JsonReader jReader;
		File[] saveFiles = new File(SAVES).listFiles();
		try
		{
			assert saveFiles != null;
			for(File file : saveFiles)
			{
				System.out.println("Reading File: " + SAVES + file.getName());
				try
				{
					jReader = new JsonReader(new FileReader(file));
					saveStateList.add(GSON.fromJson(jReader, SaveState.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not find saveFile" + file.getName());
				}
			}

			SAVES_OLD = saveStateList;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load save files");
		}
	}

	/**
	 * loads a particular save file
	 *
	 * @param saveName the name of the save file
	 */
	public static void loadGame(String saveName)
	{
		//Find the correct save file
		for(SaveState saveState : SAVES_OLD)
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
			//Put all entities in a list for the Engine.EntityManager
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
	 * saves the current game to a Engine.SaveState
	 *
	 * @param name the name of the Engine.SaveState
	 */
	public static void saveGame(String name)
	{
		//Create a new Engine.SaveState
		SaveState newSave = new SaveState(name);

		//Save it as a Gson object
		String json = GSON.toJson(newSave);

		PrintWriter out;
		try
		{
			out = new PrintWriter(SAVES + newSave.NAME + ".json");
			out.println(json);

			System.out.println("game saved to: " + SAVES + newSave.NAME + ".json");

			out.flush();
			out.close();
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not write to file: " + SAVES + newSave.NAME + ".json");
		}

		//After saving a game, reload the list of saves so it includes the new one
		loadSaves();
	}

	/**
	 * used for saving templates/test versions of resources
	 */
	public static void saveBaseFiles()
	{
		for(Entity entity : World.ENTITIES)
			save(entity);
		for(Room room : World.ROOMS)
			save(room);
	}

	public static void save(Entity entity)
	{
		String json = GSON.toJson(entity);

		String directory = DIR_LOC + "\\resources\\";
		if(entity.ID.matches("I-\\d{6}"))
			directory = ITEMS;
		if(entity.ID.matches("C-\\d{6}"))
			directory = CONTAINERS;

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
			out = new PrintWriter(ROOMS + room.ID + room.NAME + ".json");
			out.println(json);

			System.out.println("game saved to: " + ROOMS + room.ID + room.NAME + ".json");

			out.flush();
			out.close();
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not write to file: " + ROOMS + room.ID + room.NAME + ".json");
		}
	}

	public static void listSaves()
	{
		String output = "";
		int i = 0;
		for(SaveState saveState : SAVES_OLD)
		{
			output += i + ") " + saveState.NAME + "\n";
			i++;
		}

		Interface.display(output);
	}
}
