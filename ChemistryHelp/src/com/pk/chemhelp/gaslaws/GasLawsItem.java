package com.pk.chemhelp.gaslaws;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.pk.chemhelp.R;
import com.pk.chemhelp.lawxmlparser.LawElement;

public class GasLawsItem
{
	// Variables
	private String item;
	private String description;
	/**
	 * These would hold icon name and resolved icon id.
	 * But what happens when there's failure to load the icon?
	 */
	private String icon;
	private int iconId;
	
	private String activityClassName;
	private String mDescriptionHtml;
	private Context mContext;
	
	/**
	 * Default icon for list element, if it wasn't specified in the "source element".
	 */
	private static final int C_DEFAULT_ICON = R.drawable.gas_icon;
	private static final String LOG = GasLawsItem.class.getName();
	
//	public GasLawsItem(IGLHead head){
//		this.item = head.getLawTitle();
//		this.description = head.getLawDescription();
//		this.icon = head.getLawIconString();
//		this.iconId = head.getLawIconId();
//	}
	
	
//	// Constructor
//	public GasLawsItem(String item, String description, String icon)
//	{
//		super();
//		this.item = item;
//		this.description = description;
//		this.icon = icon;
//	}
	
	
//	public GasLawsItem(Class<? extends Activity> class1) {
//		// TODO Auto-generated constructor stub
//		this.class1 = class1;
//	}


	public GasLawsItem(LawElement el, Context ctx) {
		Log.v(LOG, "GasLawsItem(): Received lawElement:" + el.toString());
		this.mContext = ctx;
		this.item = el.getmTitle();
		this.description = el.getmDescription();
		this.activityClassName = el.getmActivityClass();
		
		
		this.icon = el.getIconName();
		if ( icon != null ){
			this.iconId = calculateDrawableIdFromName(icon); // can return 0 (invalid id)
		}
		
		if ( iconId == 0 ){
			Log.v(LOG, "GasLawsItem(): Resetting to default icon.");
			this.iconId = C_DEFAULT_ICON;
		}
		
		this.mDescriptionHtml = el.getmDescriptionHtml();
	}


	/**
	 * Calculates drawable id from name of the drawable
	 * @failable is it or not?
	 * @param icon2
	 * @return
	 */
	private int calculateDrawableIdFromName(String drawableName) {
		int id = this.mContext.getResources().getIdentifier(drawableName, "drawable", mContext.getPackageName());
		return id;
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
	 * @deprecated currently this value is undefined, we don't know what it is returning.
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
	
	
	/**
	 * @deprecated currently this one is undefined as well. I don't know what kind of value it shoud return.
	 * @return
	 */
	public int getIconId(){
		return this.iconId;
	}


	/**
	 * Returns class of the activity.
	 * 
	 * @throws RuntimeException when there was error creating class.
	 * @return
	 */
	public Class<? extends Activity> getActivityClass() {
		Class<?> c = null;
		if ( this.activityClassName == null ){
			throw new RuntimeException("activity className property is NULL.");
		}
		
		try {
			Log.v(GasLawsItem.class.getName(), "Trying to make activity: [" + this.activityClassName + "]");
			c = Class.forName(this.activityClassName);
			return (Class<? extends Activity>) c;  // this will be RuntimeException child ClassCastException
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found: " + e.getMessage(), e);
		}
// 		no need, becase it is subclass of RTEX		
		catch (ClassCastException ccex){
			throw new RuntimeException("Class cast exception: " + ccex.getMessage(), ccex);
		}
	}


	/**
	 * 
	 * @return NULL if it is not set.
	 */
	public Spanned getDescriptionHtmlAsSpanned() {
		if ( mDescriptionHtml == null) {
			return null;
		}
		return Html.fromHtml(mDescriptionHtml);
	}
}
