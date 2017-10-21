package Data;

import java.util.List;

public class DataHandler
{
	/*
	All of the base entity lists to store the basic form of each GameEntity
	These contain all of the information for everything in the game and can have instances made of them. Those instances are what appear in the game
	world and can be manipulated by the game engine directly.

	todo finish determining all the attributes for these
	MODULE: ID Name Rooms Items Characters Quests Factions
	SPECIES: ID Name
	STATS: Actor_ID Stats...
	DESCRIPTION: GameEntityID and a whole bunch of attributes
	GAME_ENTITY: ID Name Description Journal_Entry
		ROOM: ID Entrance Look
		GAME_OBJECT: ID Act_Desc Look Visible Activated
			ITEM: ID Takeable Size Weight
				CONTAINER: ID Size_Capacity Weight_Capacity Locked Key_ID_Instance
					VEHICLE: ID Owner_Key Room_ID Fuel_Type Fuel_Capacity Fuel_Amount
		ACTOR: ID Species Description Level
			CHARACTER: Roaming Vendor Quest_Giver
			MONSTER:
		QUEST: ID Steps Requirements
		FACTION: ID Player_Standing
	*/
	private List<Object[]> MODULES;
	private List<Object[]> GAME_ENTITYS;
	private List<Object[]> SPECIES;
	private List<Object[]> DESCRIPTIONS;
	private List<Object[]> ROOMS;
	private List<Object[]> GAME_OBJECTS;
	private List<Object[]> ITEMS;
	private List<Object[]> CONTAINERS;
	private List<Object[]> VEHICLES;
	private List<Object[]> ACTORS;
	private List<Object[]> CHARACTERS;
	private List<Object[]> MONSTERS;
	private List<Object[]> QUESTS;
	private List<Object[]> FACTIONS;
	private List<Object[]> EVENTS;

	/*
	All instance data
	 */
	private List<Object[]> INSTANCE_GAME_ENTITY;
	private List<Object[]> INSTANCE_ROOM;
	private List<Object[]> INSTANCE_GAME_OBJECT;
	private List<Object[]> INSTANCE_ITEM;
	private List<Object[]> INSTANCE_CONTAINER;
	private List<Object[]> INSTANCE_VEHICLE;
	private List<Object[]> INSTANCE_ACTOR;
	private List<Object[]> INSTANCE_CHARACTER;
	private List<Object[]> INSTANCE_MONSTER;
	private List<Object[]> INSTANCE_QUEST;
	private List<Object[]> INSTANCE_FACTION;

	/*
	Relations between the GameEntities
	INSTANCE_ROOM CONTAINS INSTANCE_GAME_OBJECT
	INSTANCE_ROOM CONNECTS_TO INSTANCE_ROOM
	INSTANCE_CONTAINER CONTAINS INSTANCE_GAME_OBJECT
	INSTANCE_GAME_OBJECT USED_WITH INSTANCE_GAME_OBJECT
	INSTANCE_GAME_OBJECT ACTIVATES INSTANCE_GAME_OBJECT
	INSTANCE_GAME_OBJECT AFFECTS ACTOR
	INSTANCE_ACTOR HAS_STATS ATTRIBUTE
	INSTANCE_ACTOR DESCRIBED_BY DESCRIPTION
	 */
	private List<Object[]> CONTAINS;
	private List<Object[]> CONNECTS_TO;
	private List<Object[]> USED_WITH;
	private List<Object[]> ACTIVATES;
	private List<Object[]> AFFECTS;
	private List<Object[]> HAS_STATS;
	private List<Object[]> DESCRIBED_BY;
	private List<Object[]> TAGS;
	private List<Object[]> USES;
	private List<Object[]> BELONGS_TO;
	private List<Object[]> WORLDS;

	/**
	 * Loads the list of Worlds
	 * @param resources The folder containing the world files
	 */
	public DataHandler(String resources)
	{
		WORLDS = DataLoader.loadEntities(resources);
	}
	/**
	 * Loads all modules from the specified world
	 * @param world A world folder
	 */
	public void setWorld(String world)
	{
		//Load all base entities
		MODULES = DataLoader.loadEntities(world);
		TAGS = DataLoader.loadEntities(world);
		USES = DataLoader.loadEntities(world);
		GAME_ENTITYS = DataLoader.loadEntities(world);
		SPECIES = DataLoader.loadEntities(world);
		DESCRIPTIONS = DataLoader.loadEntities(world);
		ROOMS = DataLoader.loadEntities(world);
		GAME_OBJECTS = DataLoader.loadEntities(world);
		ITEMS = DataLoader.loadEntities(world);
		CONTAINERS = DataLoader.loadEntities(world);
		VEHICLES = DataLoader.loadEntities(world);
		ACTORS = DataLoader.loadEntities(world);
		CHARACTERS = DataLoader.loadEntities(world);
		MONSTERS = DataLoader.loadEntities(world);
		QUESTS = DataLoader.loadEntities(world);
		FACTIONS = DataLoader.loadEntities(world);
		EVENTS = DataLoader.loadEntities(world);
		BELONGS_TO = DataLoader.loadEntities(world);

		//Load all instances of entities
		INSTANCE_GAME_ENTITY = DataLoader.loadEntities(world);
		INSTANCE_ROOM = DataLoader.loadEntities(world);
		INSTANCE_GAME_OBJECT = DataLoader.loadEntities(world);
		INSTANCE_ITEM = DataLoader.loadEntities(world);
		INSTANCE_CONTAINER = DataLoader.loadEntities(world);
		INSTANCE_VEHICLE = DataLoader.loadEntities(world);
		INSTANCE_ACTOR = DataLoader.loadEntities(world);
		INSTANCE_CHARACTER = DataLoader.loadEntities(world);
		INSTANCE_MONSTER = DataLoader.loadEntities(world);
		INSTANCE_QUEST = DataLoader.loadEntities(world);
		INSTANCE_FACTION = DataLoader.loadEntities(world);

		//Load all relationship tables
		CONTAINS = DataLoader.loadEntities(world);
		CONNECTS_TO = DataLoader.loadEntities(world);
		USED_WITH = DataLoader.loadEntities(world);
		ACTIVATES = DataLoader.loadEntities(world);
		AFFECTS = DataLoader.loadEntities(world);
		HAS_STATS = DataLoader.loadEntities(world);
		DESCRIBED_BY = DataLoader.loadEntities(world);
	}
}
