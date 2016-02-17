// This entire file is part of my masterpiece.
// Anirudh Jonnavithula
// I think this code is well designed because it hides the data structure that stores the parsed arguments but allows
// other parts of the program to access them without harmfully changing them. It is also easy to extend; subclasses
// could have other methods defined that read in the string as different data types. The could also have other methods
// that read in files that are not XML files (for example csv files with column headers).
package automaton;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import simexception.ConfigFileException;

/**
 * Class that parses the XML file
 * @author aj168 - Anirudh Jonanvithula
 *
 */

public class XMLArgs {
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors";
	private Map<String, String> map;
	private ResourceBundle myResources;

	/**
	 * Initializes the map that will hold the parsed values
	 */
	public XMLArgs() {
		 map = new HashMap<String, String>();
		 myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
	}

	/**
	 * Reads the XML file and puts it in the map
	 * @param fileName XML file to be read
	 * @return The map containing values from the XML file
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public void readXML(String fileName) throws IOException, ParserConfigurationException, SAXException {
		InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(in);
		doc.getDocumentElement().normalize();
		NodeList n = doc.getDocumentElement().getChildNodes();
		for(int i=1; i<n.getLength();i+=2) {
			map.put(n.item(i).getNodeName(), n.item(i).getTextContent());
		}
	
	}
	
	/**
	 * Returns the key value as a string
	 * @param key
	 * @return map value as string
	 */
	public String getAsString(String key) {
		validateKey(key);
		return map.get(key);
	}

	/**
	 * Get key value as int
	 * @param key 
	 * @return map value as int
	 */
	public int getAsInt(String key) {
		validateKey(key);
		if(isInteger(map.get(key))) {
			return Integer.parseInt(map.get(key));
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), key);
		}
	}
	
	/**
	 * Returns the key value as a list of doubles
	 * @param key
	 * @return map value as double
	 */
	public double getAsDouble(String key) {
		validateKey(key);
		if(isDouble(map.get(key))) {
			return Double.parseDouble(map.get(key));
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), key);
		}
	}

	/**
	 * Returns the key value as a list of strings
	 * @param key
	 * @return map value as list of strings
	 */
	public List<String> getAsListOfString(String key) {
		validateKey(key);
		return Arrays.asList(map.get(key).split(" "));
	}

	/**
	 * Returns the key value as a list of ints
	 * @param key
	 * @return map value as list of ints
	 */
	public List<Integer> getAsListOfInteger(String key) {
		validateKey(key);
		List<String> sList= getAsListOfString(key);
		List<Integer> iList = new ArrayList<Integer>(); 
		for(String s : sList) {
			if(isInteger(s)) {
				iList.add(Integer.parseInt(s));
			}
			else {
				throw new ConfigFileException(myResources.getString("NotValid"), key);
			}
		}
		return iList;
	}
	
	/**
	 * Checks if key exists in map
	 * @param key String to check if it is a key in map
	 */
	protected void validateKey(String key) {
		if(!map.containsKey(key)) {
			throw new ConfigFileException(myResources.getString("NotFound"), key);
		}
	}
	
	/**
	 * Checks if string can be converted to a double
	 * @param s String to check
	 * @return true if string can be converted
	 */
	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		}
		catch(NumberFormatException e) { 
	        return false; 
		}
		return true;
	}
	
	/**
	 * Checks if string can be converted to an int
	 * @param s String to check
	 * @return true if string can be converted
	 */
	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		}
		catch(NumberFormatException e) { 
	        return false; 
		}
		return true;
	}
	
	/**
	 * 
	 * @return Map that is constructed when the XML file is read
	 */
	protected Map<String, String> getMap() {
		return map;
	}
	
	/**
	 * Clears the map
	 */
	public void clearArgs() {
		map.clear();
	}
}
