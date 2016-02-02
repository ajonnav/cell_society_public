import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class XMLArgs {
	private HashMap<String, String> map;
	public XMLArgs() {
		 map = new HashMap<String, String>();
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}

	public void clearArgs() {
		map.clear();
	}
}
