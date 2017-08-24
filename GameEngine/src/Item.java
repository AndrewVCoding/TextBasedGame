public class Item extends Entity
{
	public final String DESCRIPTION;
	public final String ACT_DESC;
	public final String PICKUP;
	public boolean TAKEABLE;
	public boolean ACTIVATED;
	public final String[] EFFECTS;

	public Item()
	{
		super();
		DESCRIPTION = "default";
		ACT_DESC = "default";
		PICKUP = "default";
		TAKEABLE = false;
		ACTIVATED = false;
		EFFECTS = new String[]{"default"};
	}

	public Item(String name, String id, String instance, String description, String actDescription, String pickup, boolean takeable, boolean activated, String[] effects)
	{
		super(name, id, instance);
		DESCRIPTION = description;
		ACT_DESC = actDescription;
		PICKUP = pickup;
		TAKEABLE = takeable;
		ACTIVATED = activated;
		EFFECTS = effects;
	}

	public void look()
	{
		System.out.println("Item look");
		if(ACTIVATED)
			Interface.display(ACT_DESC);
		else
			Interface.display(DESCRIPTION);
	}

	public void take()
	{
		Interface.display(PICKUP);
	}

	public int effect(String action)
	{
		int index = 0;
		for(String s : EFFECTS)
		{
			if(s.equals(action))
				return index;
			index++;
		}
		return -1;
	}

	public boolean isVisible()
	{
		return World.isVisible(this);
	}
}
