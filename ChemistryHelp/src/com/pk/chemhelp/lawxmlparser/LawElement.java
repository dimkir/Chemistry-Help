package com.pk.chemhelp.lawxmlparser;

/**
 * POJO holding info about element.
 *
 */
public class LawElement {

	
	private String mTitle;
	private String mDescription;
	private String mActivityClass;
	private String mDescriptionHtml;
	private String mIconName;

	
	
	

	public String getmTitle() {
		return mTitle;
	}


	public String getmDescription() {
		return mDescription;
	}


	public String getmActivityClass() {
		return mActivityClass;
	}


	public void setTitle(String value) {
		// TODO Auto-generated method stub
		mTitle  = value;
	}

	
	public void setDescription(String value) {
		mDescription = value;
	}

	
	public void setActivityClass(String value) {
		mActivityClass = value;
	}
	
	
	public void setIconName(String iconName){
		mIconName = iconName;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("Title: " + mTitle + "\n")
				.append("Description: " + mDescription + "\n")
				.append("ActivityClass: " + mActivityClass + "\n")
				.append("iconName: " + mIconName + "\n")
				.append("\n")
				.toString();
	}


	public String getmDescriptionHtml() {
		return this.mDescriptionHtml;
	}


	public void setDescriptionHtml(String value) {
		this.mDescriptionHtml = value;
	}


	/**
	 * Returns name of the drawable for this icon (if set).
	 * @return NULL in case there's no element set.
	 */
	public String getIconName() {
		return mIconName;
	}
	
}
