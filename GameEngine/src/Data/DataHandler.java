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
	private final List<Object[]> CONTAINS;
	private final List<Object[]> CONNECTS_TO;
	private final List<Object[]> USED_WITH;
	private final List<Object[]> ACTIVATES;
	private final List<Object[]> AFFECTS;
	private final List<Object[]> HAS_STATS;
	private final List<Object[]> DESCRIBED_BY;
	private final List<Object[]> TAGS;
	private final List<Object[]> USES;
	private final List<Object[]> BELONGS_TO;
	private final List<Object[]> WORLDS;

	/**
	 * Initializes all data to the tables
	 * @param path
	 */
	public DataHandler(String path)
	{
		//Load all base entities
		WORLDS = DataLoader.loadEntities(path);
		MODULES = DataLoader.loadEntities(path);
		TAGS = DataLoader.loadEntities(path);
		USES = DataLoader.loadEntities(path);
		GAME_ENTITYS = DataLoader.loadEntities(path);
		SPECIES = DataLoader.loadEntities(path);
		DESCRIPTIONS = DataLoader.loadEntities(path);
		ROOMS = DataLoader.loadEntities(path);
		GAME_OBJECTS = DataLoader.loadEntities(path);
		ITEMS = DataLoader.loadEntities(path);
		CONTAINERS = DataLoader.loadEntities(path);
		VEHICLES = DataLoader.loadEntities(path);
		ACTORS = DataLoader.loadEntities(path);
		CHARACTERS = DataLoader.loadEntities(path);
		MONSTERS = DataLoader.loadEntities(path);
		QUESTS = DataLoader.loadEntities(path);
		FACTIONS = DataLoader.loadEntities(path);
		EVENTS = DataLoader.loadEntities(path);
		BELONGS_TO = DataLoader.loadEntities(path);

		//Load all instances of entities
		INSTANCE_GAME_ENTITY = DataLoader.loadEntities(path);
		INSTANCE_ROOM = DataLoader.loadEntities(path);
		INSTANCE_GAME_OBJECT = DataLoader.loadEntities(path);
		INSTANCE_ITEM = DataLoader.loadEntities(path);
		INSTANCE_CONTAINER = DataLoader.loadEntities(path);
		INSTANCE_VEHICLE = DataLoader.loadEntities(path);
		INSTANCE_ACTOR = DataLoader.loadEntities(path);
		INSTANCE_CHARACTER = DataLoader.loadEntities(path);
		INSTANCE_MONSTER = DataLoader.loadEntities(path);
		INSTANCE_QUEST = DataLoader.loadEntities(path);
		INSTANCE_FACTION = DataLoader.loadEntities(path);

		//Load all relationship tables
		CONTAINS = DataLoader.loadEntities(path);
		CONNECTS_TO = DataLoader.loadEntities(path);
		USED_WITH = DataLoader.loadEntities(path);
		ACTIVATES = DataLoader.loadEntities(path);
		AFFECTS = DataLoader.loadEntities(path);
		HAS_STATS = DataLoader.loadEntities(path);
		DESCRIBED_BY = DataLoader.loadEntities(path);
	}
}
