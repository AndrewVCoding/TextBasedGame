import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DataHandler
{
	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static String DIR_LOC = System.getProperty("user.dir") + "\\TextBasedGame";
	private static String ROOMS_LOC = DIR_LOC + "\\resources\\rooms\\";
	private static String ITEMS_LOC = DIR_LOC + "\\resources\\objects\\items\\";
	private static String CONTAINERS_LOC = DIR_LOC + "\\resources\\objects\\containers\\";
	private static String MAP_LOC = DIR_LOC + "\\resources\\data\\worldData\\";
	private static String SAVE_LOC = DIR_LOC + "\\resources\\data\\saves\\";
	private static List<SaveState> SAVES = new ArrayList<>();
	private static SaveState SAVE_STATE = null;

	public static void loadAllBaseFiles()
	{
		//First load all items
		loadItems();

		//Load all containers
		loadContainers();

		//Load all rooms
		loadRooms();

		//Load list of all saved games
		loadSaves();
	}

	public static void loadItems()
	{
		List<Item> itemList = new ArrayList<>();
		JsonReader jReader;
		File[] itemFiles = new File(ITEMS_LOC).listFiles();
		try
		{
			for(File file : itemFiles)
			{
				try
				{
					jReader = new JsonReader(new FileReader(file));
					itemList.add(GSON.fromJson(jReader, Item.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not find file" + file.getName());
				}
			}
			World.ITEMS = itemList;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load items");
		}
	}

	public static void loadContainers()
	{
		//Load all containers
		List<Container> containerList = new ArrayList<>();
		JsonReader jReader;
		File[] containerFiles = new File(CONTAINERS_LOC).listFiles();
		try
		{
			for(File file : containerFiles)
			{
				try
				{
					jReader = new JsonReader(new FileReader(file));
					containerList.add(GSON.fromJson(jReader, Container.class));
				} catch(FileNotFoundException e)
				{
					System.out.println("Could not find file" + file.getName());
				}
			}
			World.CONTAINERS = containerList;
		} catch(NullPointerException e)
		{
			System.out.println("Could not load containers");
		}
	}

	public static void loadRooms()
	{
		List<Room> roomList = new ArrayList<>();
		JsonReader jReader;
		File[] roomFiles = new File(ROOMS_LOC).listFiles();
		try
		{
			for(File file : roomFiles)
			{
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

	public static void loadSaves()
	{
		List<SaveState> saveStateList = new ArrayList<>();
		JsonReader jReader;
		File[] saveFiles = new File(SAVE_LOC).listFiles();
		try
		{
			for(File file : saveFiles)
			{
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

	public static void loadPaths()
	{
		List<String> exits = new ArrayList<>();
		try
		{
			FileInputStream fileInputStream = new FileInputStream(MAP_LOC);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

			int numLines = Integer.parseInt(bufferedReader.readLine());

			for(int i = 0; i < numLines; i++)
				exits.add(bufferedReader.readLine());

			World.EXITS = exits;
		} catch(FileNotFoundException e)
		{
			System.out.println("Could not load exit data: FileNotFound");
		} catch(IOException e)
		{
			System.out.println("Could not read number of lines in exit data");
		}
	}

	public static void loadGame(String saveName)
	{
		//Find the correct save file
		for(SaveState saveState : SAVES)
		{
			if(saveState.PLAYER_STATE.NAME.equals(saveName))
			{
				SAVE_STATE = saveState;
				break;
			}
		}
		//If it was not found, print out that it was not found
		if(SAVE_STATE != null)
		{
			//Find the parents of all containers
			for(SaveState.containerState containerState : SAVE_STATE.CONTAINER_STATES)
			{
				//Try to find a room with the parentID
				Room room = World.getRoom(containerState.parentID);
				if(room != null)
					room.CONTAINERS.add(containerState.container);
				else //Try to find a container with the parentID
				{
					Container container = World.getContainer(containerState.parentID);
					if(container != null)
						container.CONTAINERS.add(containerState.container);
					else if(containerState.parentID.equals("P-INVENTORY"))
						Player.addToInventory(containerState.container.ID);
					else
						System.out.println("Could not find parent of container: " + containerState.container.NAME);
				}
			}
			//find the parents of all items
			for(SaveState.itemState itemState : SAVE_STATE.ITEM_STATES)
			{
				//Try to find a room with the parentID
				Room room = World.getRoom(itemState.parentID);
				if(room != null)
					room.ITEMS.add(itemState.item);
				else //Try to find a container with the parentID
				{
					Container container = World.getContainer(itemState.parentID);
					if(container != null)
						container.ITEMS.add(itemState.item);
					else if(itemState.parentID.equals("P-INVENTORY"))
						Player.addToInventory(itemState.item.ID);
					else
						System.out.println("Could not find parent of item: " + itemState.item.NAME);
				}
			}
		}
		else
			System.out.println("Could not find save file: " + saveName);
	}

	public void saveGame()
	{
		//Create a new SaveState
		SaveState newSave = new SaveState();

		//add itemStates and containerStates for all objects in the player's inventory
		for(ContentSlot contentSlot : Player.INVENTORY)
		{
			for(String ID : contentSlot.CONTENT_IDS)
			{
				if(ID.matches("I-\\d*"))
					newSave.ITEM_STATES.add(newSave.createItemState(ID, "P-INVENTORY"));
				else if(ID.matches("C-\\d*"))
					newSave.CONTAINER_STATES.add(newSave.createContainerState(ID, "P-INVENTORY"));
			}
		}
	}
}
