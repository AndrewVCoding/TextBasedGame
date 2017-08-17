import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class DataHandler
{
	private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static String DIR_LOC = System.getProperty("user.dir") + "\\TextBasedGame";
	private static String ROOMS_LOC = DIR_LOC + "\\resources\\rooms\\";
	private static String ITEMS_LOC = DIR_LOC + "\\resources\\objects\\items\\";
	private static String CONTAINERS_LOC = DIR_LOC + "\\resources\\objects\\containers\\";
	private static String WORLD_LOC = DIR_LOC + "\\resources\\data\\worldData\\";
	private static String SAVE_LOC = DIR_LOC + "\\resources\\data\\saves\\";

	public static void loadAllBaseFiles()
	{
		//First load all items
		List<Item> itemList = new ArrayList<>();
		JsonReader jReader;
		File[] itemFiles = new File(ITEMS_LOC).listFiles();
		try
		{
			for(File file : itemFiles)
				try
				{
					jReader = new JsonReader(new FileReader(file));
					itemList.add(GSON.fromJson(jReader, Item.class));
				}catch(FileNotFoundException e)
				{
					System.out.println("Could not find file" + file.getName());
				}
		}catch(NullPointerException e)
		{
			System.out.println("Could not load items");
		}

		//Load all containers
		List<Container> containerList = new ArrayList<>();
		File[] containerFiles = new File(CONTAINERS_LOC).listFiles();
		try
		{
			for(File file : containerFiles)
				try
				{
					jReader = new JsonReader(new FileReader(file));
					containerList.add(GSON.fromJson(jReader, Item.class));
				}catch(FileNotFoundException e)
				{
					System.out.println("Could not find file" + file.getName());
				}
		}catch(NullPointerException e)
		{
			System.out.println("Could not load containers");
		}

		//Load all rooms
		List<Container> roomList = new ArrayList<>();
		File[] roomFiles = new File(ROOMS_LOC).listFiles();
		try
		{
			for(File file : roomFiles)
				try
				{
					jReader = new JsonReader(new FileReader(file));
					containerList.add(GSON.fromJson(jReader, Item.class));
				}catch(FileNotFoundException e)
				{
					System.out.println("Could not find file" + file.getName());
				}
		}catch(NullPointerException e)
		{
			System.out.println("Could not load rooms");
		}
	}
}
