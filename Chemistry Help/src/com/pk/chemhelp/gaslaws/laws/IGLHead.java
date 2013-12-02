package com.pk.chemhelp.gaslaws.laws;

/**
 * This interface contains "head" information about specific gas law.
 * 
 * <p>
 * For example title of the gas law, formula and id.
 * </p>
 *  
 * 
 * @author Ernesto Guevara
 *
 */
public interface IGLHead {
	
	String getLawTitle();
	
	String getLawDescription();
	
	
	/**
	 * Returns resource path to the icon. (Or so I thought).
	 * 
	 * @return NULL when there's no item, or absolute path to the icon in the resources.
	 */
	String getLawIconString();

	
	int getLawIconId();
}
