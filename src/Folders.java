import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

public class Folders {
	private static void recursiveSearch(NodeList nodeList, Collection<String> result, char startingLetter) {
	/*	for (Node tempNode:nodeList.) {

		}*/

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node pivot = nodeList.item(i);
			if (pivot.getNodeType() == Node.ELEMENT_NODE) {
				if (pivot.hasChildNodes()) {
					recursiveSearch(pivot.getChildNodes(), result, startingLetter);
				}
			}
			Element e = (Element) pivot;
			String folderName = e.getAttribute("name");
			if (folderName.charAt(0) == startingLetter) {
				result.add(folderName);
			}
		}
	}

	public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {
		Collection<String> result = new LinkedList<String>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		//DocumentBuilder dBuilder = new DocumentBuilder();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		StringReader isR = new StringReader(xml);
		InputSource is = new InputSource(isR);

		NodeList nodeList = dBuilder.parse(is).getChildNodes();
		/*for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			Element e = (Element) tempNode;
			String folderName = e.getAttribute("name");
			if (folderName.charAt(0) == startingLetter)
				result.add(folderName);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				if (tempNode.hasChildNodes()) {
					for(){

				}
			}
		}*/

		recursiveSearch(nodeList, result, startingLetter);

		return result;
	}

	public static void main(String[] args) throws Exception {
		String xml =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
						"<folder name=\"c\">" +
						"<folder name=\"program files\">" +
						"<folder name=\"uninstall information\" />" +
						"</folder>" +
						"<folder name=\"users\" />" +
						"</folder>";

		Collection<String> names = folderNames(xml, 'u');
		for(String name: names)
			System.out.println(name);
	}
}