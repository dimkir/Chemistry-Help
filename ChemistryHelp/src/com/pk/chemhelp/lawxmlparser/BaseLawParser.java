package com.pk.chemhelp.lawxmlparser;

import java.io.InputStream;

import android.content.Context;

/**
 * This is abstract base for the the law parser, all it does is receives 
 * parameter as constructor, and provides method to receive it as stream.
 * 
 * @author Ernesto Guevara
 *
 */
abstract class BaseLawParser implements ILawParser {
	
	
	private InputStream is;


	/**
	 * 
	 * @param xmlRawResourceId RAW RESOURCE ID! (well i wonder how is this going to work in betwen packages);
	 * @param ctx
	 */
	public BaseLawParser(int xmlRawResourceId, Context ctx){
		is = ctx.getResources().openRawResource(xmlRawResourceId);
	}
	
	

	/**
	 * Just returns InputStream with the xml.
	 * 
	 * @return
	 */
	protected InputStream getInputStream(){
		return this.is;
	}

}
