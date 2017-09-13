package Engine;

import java.util.*;

public class Entity
{
	public final String NAME;
	public final String ID;
	public final String INSTANCE;
	public boolean PURGE = false;

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
		System.out.println("Engine.Entity look");
		Interface.display(NAME);
	}

	public void take()
	{
		Interface.display(NAME + " can not be taken");
	}

	public void open()
	{
		Interface.display(NAME + " can not be opened");
	}

	public String getKey()
	{
		return ID + ":" + INSTANCE;
	}

	public List<String[]> getEffects()
	{
		List<String[]> output = new ArrayList<>();
		output.add(new String[]{"none"});
		return output;
	}
}
