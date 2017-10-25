
All game resources are stored by modules. These modules are loaded starting at 00_moduleName and proceed in increasing order.
A runtime table is created to keep track of all entity IDs being read from each module and will fail to load the game if a repeated ID is found.
	The same will happen for any entity instances
	This will allow for easily adding new content to the game as I expand the test environment

gamePath/
	resources/
		world1/
			modules/
			game_entities/
				base.json
				instances.json
			game_objects/
				base.json
				instancesspecies.json
			items/
				base.json
				instances.json
			containers/
				base.json
				instances.json
			vehicles/
				base.json
				instances.json
			actors/
				base.json
				instances.json
			monsters/
				base.json
				instances.json
			descriptions.json
			rooms.json
			characters.json
			quests.json
			factions.json
			events.json