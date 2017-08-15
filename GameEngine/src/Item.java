public class Item
{
	public String NAME;
	public String DESCRIPTION;
	public String ACT_DESC;
	public String PICKUP;
	public String ID;
	public boolean TAKEABLE = false;
	public boolean ACTIVATED = false;
	public String[] EFFECTS;

	public String look()
	{
		if(ACTIVATED)
			return ACT_DESC;
		return DESCRIPTION;
	}

	public void pickup()
	{
		Interface.display(Interface.DISPLAY + "\n" + PICKUP);
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
}
