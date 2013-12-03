package myparser;

import java.util.List;

import android.test.AndroidTestCase;

import com.pk.chemhelp.R;
import com.pk.chemhelp.lawxmlparser.ILawParser;
import com.pk.chemhelp.lawxmlparser.LawElement;
import com.pk.chemhelp.lawxmlparser.SimpleLawParser;

public class SimpleParser1 extends AndroidTestCase {

	
	public void testParser(){
		assertEquals(1,1);
		
	}	
	
	/**
	 * Attempts to parse file.
	 */
	public void testParseFile(){
		ILawParser parser = new SimpleLawParser(R.raw.gas_laws, getContext());
		List<LawElement> list =  parser.getLawElements();
		for(LawElement le : list){
			System.out.println("Got element:" +  le.toString());
		}
		assertEquals(list.size(), 5);
	}
	
}
