public class Item
{
	public String NAME;
	public String DESCRIPTION;
	public String ACT_DESC;
	public String PICKUP;
	public boolean CONSUMABLE = false;
	public boolean EQUIPABLE = false;
	public boolean TAKEABLE = false;
	public boolean ACTIVATED = false;
	public String[] EFFECT;

	public String look()
	{
		if(ACTIVATED)
			return ACT_DESC;
		return DESCRIPTION;
	}

	public void pickup()
	{
		Interface.setDISPLAY(PICKUP);
	}
}
