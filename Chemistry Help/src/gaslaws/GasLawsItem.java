package gaslaws;

import laws.BoylesLaw;
import laws.IGLHead;

public class GasLawsItem
{
	// Variables
	private String item;
	private String description;
	/**
	 * I don't know what this holds.
	 */
	private String icon;
	private int iconId;
	
	public GasLawsItem(IGLHead head){
		this.item = head.getLawTitle();
		this.description = head.getLawDescription();
		this.icon = head.getLawIconString();
		this.iconId = head.getLawIconId();
	}
	
	
	// Constructor
	public GasLawsItem(String item, String description, String icon)
	{
		super();
		this.item = item;
		this.description = description;
		this.icon = icon;
	}
	
	
	public GasLawsItem(Class<BoylesLaw> class1) {
		// TODO Auto-generated constructor stub
	}


	// Getter and Setter Methods
	public String getItemName()
	{
		return item;
	}
	
	public void setItemName(String item)
	{
		this.item = item;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Returns path to icon.
	 * 
	 * 
	 * @return CAN IT BE NULL???
	 */
	public String getIcon()
	{
		return icon;
	}
	
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	
	
	public int getIconId(){
		return this.iconId;
	}
}
