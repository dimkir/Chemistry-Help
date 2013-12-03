package com.pk.chemhelp.gaslaws;

import java.util.ArrayList;
import java.util.List;

import com.pk.chemhelp.lawxmlparser.LawElement;
import com.pk.chemhelp.lawxmlparser.SimpleLawParser;

import android.content.Context;


/**
 * This class returns list of gas law items, which can be displayed in list
 * and as well can provide activity class to be launched.
 * 
 */
class GasLawProvider implements IGasLawsProvider {

	
	
	private Context ctx;
	
	public GasLawProvider(Context ctx) {
		this.ctx = ctx;
	}

	/**
	 * Returns list of gas laws items read via parser.
	 * 
	 */
	@Override
	public List<GasLawsItem> getGasLawItems() {
		// TODO Auto-generated method stub
		List<GasLawsItem> list = new ArrayList<GasLawsItem>();
		
		SimpleLawParser parser = new SimpleLawParser(com.pk.chemhelp.R.raw.gas_laws, ctx);
		for(LawElement el : parser.getLawElements())
		{
			list.add(new GasLawsItem(el, ctx));
		}
		
		return list;
		
	}

}
