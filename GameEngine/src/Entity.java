public class Entity
{
	public final String NAME;
	public final String ID;
	public final String INSTANCE;
	public String LOCATION;

	public Entity()
	{
		NAME = "default";
		ID = "d-000000";
		INSTANCE = "000000";
	}

	public Entity(String name, String id, String instance)
	{
		NAME = name;
		ID = id;
		INSTANCE = instance;
	}

	public void look()
	{
		System.out.println("Entity look");
		Interface.display(NAME);
	}
}
