ENTITIES are the actual things that will appear in the game while playing
RELATIONSHIPS are the interactions between entities

------------
| ENTITIES |
------------
GAME_ENTITIES	(id, Name, Visible)
SPECIES			(id, Name)
DESCRIPTIONS	(Eid, Description)
ROOMS			(Eid, )
GAME_OBJECTS	()
ITEMS			()
CONTAINER		()
VEHICLES		()
ACTORS			()
CHARACTERS		(id, name, proffession)
MONSTERS		()
QUESTS			()
FACTIONS		()
EVENTS			()

-----------------
| RELATIONSHIPS |
-----------------
ALIASES			()
ACTIONS			(Eid, Action, Target, Description, Trigger)
TRIGGERS		()
CONTAINS		()
EXITS			()
USED_WITH		()
ACTIVATES		()
AFFECTS			()
HAS_STATS		()

------------
| METADATA |
------------
WORLDS			(ID, Name, Author, Description)
MODULE			(ID, Name, Author, Description)
TAGS			(ID, Tag)
BELONGS_TO		(World_id, Module_id)
USES			(Module_id, Entity_id)

