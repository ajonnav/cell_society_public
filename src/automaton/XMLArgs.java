package automaton;
import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Class that parses the XML file
 * @author aj168 - Anirudh Jonanvithula
 *
 */

public class XMLArgs {
	
	private HashMap<String, String> map;

	/**
	 * Initializes the map that will hold the parsed values
	 */
	public XMLArgs() {
		 map = new HashMap<String, String>();
	}

	/**
	 * Reads the XML file and puts it in the map
	 * @param fileName XML file to be read
	 * @return The map containing values from the XML file
	 */
	public HashMap<String, String> readXML(String fileName) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(fileName));
			doc.getDocumentElement().normalize();
			NodeList n = doc.getDocumentElement().getChildNodes();
			for(int i=1; i<n.getLength();i+=2) {
				map.put(n.item(i).getNodeName(), n.item(i).getTextContent());
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
		return map;
	}

	/**
	 * Clears the map
	 */
	public void clearArgs() {
		map.clear();
	}
}
