import java.util.ArrayList;
import java.util.List;

public class Item extends Entity
{
	public final String DESCRIPTION;
	public final String ACT_DESC;
	public final String PICKUP;
	public boolean TAKEABLE;
	public boolean ACTIVATED;
	public final List<String[]> EFFECTS;

	public Item()
	{
		super();
		DESCRIPTION = "default";
		ACT_DESC = "default";
		PICKUP = "default";
		TAKEABLE = false;
		ACTIVATED = false;
		EFFECTS = new ArrayList<>();
	}

	public Item(String name, String id, String instance, String description, String actDescription, String pickup,
				boolean takeable, boolean activated, List<String[]> effects)
	{
		super(name, id, instance);
		DESCRIPTION = description;
		ACT_DESC = actDescription;
		PICKUP = pickup;
		TAKEABLE = takeable;
		ACTIVATED = activated;
		EFFECTS = new ArrayList<>();
		for(String[] effect : effects)
			EFFECTS.add(effect);
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

	public List<String[]> getEffects()
	{
		return EFFECTS;
	}

	public boolean isVisible()
	{
		return World.isVisible(this);
	}
}
