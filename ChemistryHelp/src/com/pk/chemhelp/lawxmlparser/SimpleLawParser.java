package com.pk.chemhelp.lawxmlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.util.Log;


public class SimpleLawParser extends BaseLawParser
implements Constants
{

	private static final String LOG = SimpleLawParser.class.getName();

	/**
	 * 
	 * @param xmlRawResourceId RAW resource id (I wonder if it will work properly cross package).
	 * @param ctx
	 */
	public SimpleLawParser(int xmlRawResourceId, Context ctx) {
		super(xmlRawResourceId, ctx);
	}

	/**
	 * Returns law elements acquired from the resource.
	 * 
	 * @throws RuntimeException in case there was error parsing.
	 */
	@Override
	public List<LawElement> getLawElements() {
		
		List<LawElement> list = new ArrayList<LawElement>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(this.getInputStream());
			Element root = dom.getDocumentElement();
			NodeList items = root.getElementsByTagName(TAG_ITEM);
			
			for(int i = 0 ; i < items.getLength(); i++){
				Node node = items.item(i);
				
				list.add(makeLawElementFromNode(node));
			}
			
			
			
		} catch (ParserConfigurationException e) {
			handleException(e);
		} catch (SAXException e) {
			handleException(e);
		} catch (IOException e) {
			handleException(e);
		}
		catch(RuntimeException rtex){
			// there was some error probably makingLawelementFromnode.
			handleException(rtex);
		}
		
		
		return list;
	}
	

	/**
	 * Wraps any exception in runtime exception and throws it.
	 * 
	 * <p>
	 * The purpose of the method is to allow to propagate only 
	 * runtime exception. We could have propagated our custom 
	 * exception, but I don't want to pollute signature of the method.
	 * </p>
	 * 
	 * @param e
	 * @throws Exception
	 */
	private void handleException(Exception e) throws RuntimeException {
		
		if ( e instanceof RuntimeException) { // avoid wrapping RTEX
			throw (RuntimeException) e;
		}
		throw new RuntimeException(e);
		
	}

	/**
	 * Tries making law element.
	 * 
	 * @param node
	 * @throws RuntimeException in case fatal error happened.
	 * @return
	 */
	private LawElement makeLawElementFromNode(Node node) {
		// TODO Auto-generated method stub
		LawElement lawElement = new LawElement();
		NodeList properties = node.getChildNodes();
		lawElement.setTitle(getPropertyOrThrow(properties, TAG_TITLE));
		lawElement.setDescription(getPropertyOrThrow(properties, TAG_DESCRIPTION));
		lawElement.setActivityClass(getPropertyOrThrow(properties, TAG_ACTIVITYCLASSNAME));
		lawElement.setDescriptionHtml(getPropertyOrNull(properties, TAG_DESCRIPTION_HTML));
		lawElement.setIconName(getPropertyOrNull(properties, TAG_ICON_NAME));
		return lawElement;
	}

	
	private String getPropertyOrNull(NodeList nodeList,
			String tagTitle) {
		
		
		List<String> elementNames = new ArrayList<String>();
		
		for(int i = 0  ; i < nodeList.getLength() ; i++){
			Node node = nodeList.item(i);
			String name = node.getNodeName();
			elementNames.add(name); // for error message
			if ( name.equalsIgnoreCase(tagTitle)) {
				String val = node.getFirstChild().getNodeValue();
//				String val = node.getNodeValue();
				Log.v(LOG, "getPropertyOrNull() for property [" + tagTitle + "] we have value [" + val + "]");
				
				
				return val; 
			}
		}
		String msg = "There was no element with tag [" + tagTitle + "] amonst elements: " + elementNames.toString();
		Log.v(SimpleLawParser.class.getName(), msg);
		return null;
	}

	/**
	 * Gets value of the subnode or throws runtime exception.
	 * @param nodeList
	 * @param tagTitle
	 * 
	 * @throws RuntimeException in case there's no subnode with such tagname.
	 * 
	 * @return
	 */
	private String getPropertyOrThrow(NodeList nodeList, String tagTitle) {
		
		List<String> elementNames = new ArrayList<String>();
		
		for(int i = 0  ; i < nodeList.getLength() ; i++){
			Node node = nodeList.item(i);
			String name = node.getNodeName();
			elementNames.add(name); // for error message
			if ( name.equalsIgnoreCase(tagTitle)) {
				return node.getFirstChild().getNodeValue(); 
			}
		}
		throw new RuntimeException("There was no element with tag [" + tagTitle + "] amonst elements: " + elementNames.toString());
	
	}

}
