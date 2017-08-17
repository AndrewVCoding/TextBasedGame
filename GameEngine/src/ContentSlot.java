import java.util.List;

public class ContentSlot
{
	public String NAME;
	public List<String> CONTENT_IDS;
	public int QUANTITY;

	public void add(String id)
	{
		if(World.getIDName(id).equals(NAME))
			if(!CONTENT_IDS.contains(id))
			{
				CONTENT_IDS.add(id);
				QUANTITY++;
			}
	}

	public void add(ContentSlot slot)
	{
		if(slot.NAME.equals(NAME))
			for(String newID : slot.CONTENT_IDS)
				add(newID);
	}
}
