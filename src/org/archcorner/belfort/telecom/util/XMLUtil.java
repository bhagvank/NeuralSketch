/**
 * 
 */
package org.archcorner.belfort.telecom.util;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author bhagvan_kommadi
 *
 */
public class XMLUtil {

	/**
	 * 
	 */
	public XMLUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Node> getChildNodes(Node parentNode,String childNodeName)
	{
		//System.out.println(parentNode.getNodeName());
		NodeList nodeList = parentNode.getChildNodes();
		
		List<Node> childNodeList = new ArrayList();
		
		for(int i=0; i< nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			
			//System.out.println(node.getNodeName());
			
			if(node.getNodeName().equals(childNodeName))
			{
				childNodeList.add(node);
			}
		}
		
		return childNodeList;
	}
	public Node getRootNodeFromStream(InputStream inputStream, String rootTagName)
	{
		 
         
         DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
         
         Node rootNode = null;
         
         try
         {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            document.normalize();
            NodeList nodeList = document.getElementsByTagName(rootTagName);
            
            rootNode = nodeList.item(0);
            
            //System.out.println(rootNode.getNodeName());
         }
         catch(Exception exception)
         {
       	  exception.printStackTrace();
         }
         finally
         {
       	  
         }
         return rootNode;
	}

	public Node getRootNode(String xmlFileName, String rootTagName)
	{
		 File xmlFile = new File(xmlFileName);
         
         DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
         
         Node rootNode = null;
         
         try
         {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.normalize();
            NodeList nodeList = document.getElementsByTagName(rootTagName);
            
            rootNode = nodeList.item(0);
            
            //System.out.println(rootNode.getNodeName());
         }
         catch(Exception exception)
         {
       	  exception.printStackTrace();
         }
         finally
         {
       	  
         }
         return rootNode;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNode("/users/bhagvan_kommadi/documents/belfort_nextgen/Belfort/belfortappconfig.xml","app");

		System.out.println(rootNode.getNodeName());
		
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, "view");
		
		for(Node node: nodeList)
		{
			System.out.println("Name " + node.getNodeName());
		    System.out.println("value" + node.getNodeValue());	
		
			
		}
		
		List<Node> viewFields = xmlUtil.getChildNodes(nodeList.get(0), "viewFields");
		
		for(Node node: viewFields)
		{
			System.out.println("Name " + node.getNodeName());
			System.out.println("Value "+ node.getNodeValue());
		}
		
		List<Node> viewFieldNodes = xmlUtil.getChildNodes(viewFields.get(0), "viewField");
		
		for(Node node: viewFieldNodes)
		{
			System.out.println("Name " + node.getNodeName());
			System.out.println("Value "+ node.getTextContent());
			
			
		}
		
		
}

}