/**
 * 
 */
package org.archcorner.belfort.telecom.metadata;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.archcorner.belfort.telecom.util.XMLUtil;
import org.w3c.dom.Node;

/**
 * @author bhagvan_kommadi
 *
 */
public class AppConfigManager {

	private static final String DELETE = "delete";
	private static final String SEARCH_FORM_FIELD = "searchFormField";
	private static final String FORM_ID_FIELD = "formIdField";
	private static final String UPDATE = "update";
	private static final String FORM_FIELD = "formField";
	private static final String FORM_FIELDS = "formFields";
	private static final String FORMNO = "formno";
	private static final String FORM = "form";
	private static final String VIEWNO = "viewno";
	private static final String NAME = "name";
	private static final String VIEW_FIELD = "viewField";
	private static final String VIEW_FIELDS = "viewFields";
	private static final String VIEW = "view";
	private static final String APP = "app";
	//private static final String BELFORTAPPCONFIG_XML = "/users/bhagvan_kommadi/documents/belfort_nextgen/Belfort/belfortappconfig.xml";
	/**
	 * 
	 */
	public AppConfigManager() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDeleteSearchFormField(InputStream inputStream,String formno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		String searchFormField = null;
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, DELETE);
		for(Node node: nodeList)
		{
			List<Node> deleteFormNodes = xmlUtil.getChildNodes(node,FORMNO);
			if(deleteFormNodes.get(0).getTextContent().equals(formno))
			{
				List<Node> deleteSearchFormFieldNodes = xmlUtil.getChildNodes(node, SEARCH_FORM_FIELD);
				searchFormField = deleteSearchFormFieldNodes.get(0).getTextContent();
			}
		}
		
		return searchFormField;
	}
	public String getUpdateSearchFormField(InputStream inputStream,String formno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		String searchFormField = null;
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, UPDATE);
		for(Node node: nodeList)
		{
			List<Node> updateFormNodes = xmlUtil.getChildNodes(node,FORMNO);
			if(updateFormNodes.get(0).getTextContent().equals(formno))
			{
				List<Node> updateSearchFormFieldNodes = xmlUtil.getChildNodes(node, SEARCH_FORM_FIELD);
				searchFormField = updateSearchFormFieldNodes.get(0).getTextContent();
			}
		}
		
		return searchFormField;
	}
	
	public String getDeleteFormIdField(InputStream inputStream,String formno)
	{
	  XMLUtil xmlUtil = new XMLUtil();
	  Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
	  String deleteFormIdField = null;
	  List<Node> nodeList = xmlUtil.getChildNodes(rootNode, DELETE);
	  
	  for(Node node: nodeList)
	  {
		  List<Node> deleteFormNodes = xmlUtil.getChildNodes(node, FORMNO);
		  if(deleteFormNodes.get(0).getTextContent().equals(formno))
		  {
			  List<Node> deleteFormNameNodes = xmlUtil.getChildNodes(node, FORM_ID_FIELD);
			  deleteFormIdField = deleteFormNameNodes.get(0).getTextContent();
		  }
	  }
	  
	  return deleteFormIdField;
	}
	public String getUpdateFormIdField(InputStream inputStream,String formno)
	{
	  XMLUtil xmlUtil = new XMLUtil();
	  Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
	  String updateFormIdField = null;
	  List<Node> nodeList = xmlUtil.getChildNodes(rootNode, UPDATE);
	  
	  for(Node node: nodeList)
	  {
		  List<Node> updateFormNodes = xmlUtil.getChildNodes(node, FORMNO);
		  if(updateFormNodes.get(0).getTextContent().equals(formno))
		  {
			  List<Node> updateFormNameNodes = xmlUtil.getChildNodes(node, FORM_ID_FIELD);
			  updateFormIdField = updateFormNameNodes.get(0).getTextContent();
		  }
	  }
	  
	  return updateFormIdField;
	}
	public String getDeleteFormName(InputStream inputStream,String formno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		String deleteFormName = null;
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, UPDATE);
		Node deleteFormNode = null;
		for(Node node: nodeList)
		{
			List<Node> deleteFormNodes = xmlUtil.getChildNodes(node, FORMNO);
			if(deleteFormNodes.get(0).getTextContent().equals(formno))
			{
				List<Node> deleteFormNameNodes = xmlUtil.getChildNodes(node, NAME);
				deleteFormName = deleteFormNameNodes.get(0).getTextContent();
			}
		}
		return deleteFormName;
		
	}
	public String getUpdateFormName(InputStream inputStream,String formno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		String updateFormName = null;
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, UPDATE);
		Node updateFormNode = null;
		for(Node node: nodeList)
		{
			List<Node> updateFormNodes = xmlUtil.getChildNodes(node, FORMNO);
			if(updateFormNodes.get(0).getTextContent().equals(formno))
			{
				List<Node> updateFormNameNodes = xmlUtil.getChildNodes(node, NAME);
				updateFormName = updateFormNameNodes.get(0).getTextContent();
			}
		}
		return updateFormName;
		
	}
	public String getFormName(InputStream inputStream,String formno)
	{
      XMLUtil xmlUtil = new XMLUtil();
      Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
      String formName = null;
      List<Node> nodeList = xmlUtil.getChildNodes(rootNode, FORM);
      
      Node formNode = null;
      for(Node node: nodeList)
      {
    	  List<Node> formNodes = xmlUtil.getChildNodes(node, FORMNO);
    	  if(formNodes.get(0).getTextContent().equals(formno))
    	  {
    		  List<Node> formNameNodes = xmlUtil.getChildNodes(node, NAME);
    		  
    		  formName = formNameNodes.get(0).getTextContent();
    	  }
      }
      
      return formName;
	}
	public String getViewName(InputStream inputStream,String viewno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		String viewName = null;
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, VIEW);
		
		Node viewNode = null;
		for(Node node: nodeList)
		{
			List<Node> viewNodes = xmlUtil.getChildNodes(node, VIEWNO);
			if(viewNodes.get(0).getTextContent().equals(viewno))
			{
				List<Node> viewNameNodes= xmlUtil.getChildNodes(node, NAME);
				
				viewName = viewNameNodes.get(0).getTextContent();
			}
		}
		
		return viewName;
		
	}
	public List<String> getDeleteFormFieldsByFormNo(InputStream inputStream,String formno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		List<String> deleteFormFields = new ArrayList<String>();
		List<Node> deleteNodeList = xmlUtil.getChildNodes(rootNode, UPDATE);
		
		Node deleteFormNode = null;
		
		for(Node node: deleteNodeList)
		{
			List<Node> formNoNodes = xmlUtil.getChildNodes(node, FORMNO);
			
			if(formNoNodes.get(0).getTextContent().equals(formno))
			{
				List<Node> formFieldsNodeList = xmlUtil.getChildNodes(node, FORM_FIELDS);
				
				List<Node> formFieldNodes = xmlUtil.getChildNodes(formFieldsNodeList.get(0), FORM_FIELD);
				
				for(Node formField: formFieldNodes)
				{
					deleteFormFields.add(formField.getTextContent());
				}
			}
		}
		
		return deleteFormFields;
	}
	public List<String> getUpdateFormFieldsByFormNo(InputStream inputStream,String formno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		List<String> updateFormFields = new ArrayList<String>();
		List<Node> updateNodeList = xmlUtil.getChildNodes(rootNode, UPDATE);
		
		Node updateFormNode = null;
		
		for(Node node: updateNodeList)
		{
			List<Node> formNoNodes = xmlUtil.getChildNodes(node, FORMNO);
			
			if(formNoNodes.get(0).getTextContent().equals(formno))
			{
				List<Node> formFieldsNodeList = xmlUtil.getChildNodes(node, FORM_FIELDS);
				
				List<Node> formFieldNodes = xmlUtil.getChildNodes(formFieldsNodeList.get(0), FORM_FIELD);
				
				for(Node formField: formFieldNodes)
				{
					updateFormFields.add(formField.getTextContent());
				}
			}
		}
		
		return updateFormFields;
	}
	public List<String> getFormFieldsByFormNo(InputStream inputStream,String formno)
	{
	   XMLUtil xmlUtil = new XMLUtil();
	   Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
	   List<String> formFields = new ArrayList<String>();
	   
	   List<Node> nodeList = xmlUtil.getChildNodes(rootNode, FORM);
	   
	   Node formNode = null;
	   
	   for(Node node: nodeList)
	   {
		   List<Node> formNoNodes = xmlUtil.getChildNodes(node, FORMNO);
		   if(formNoNodes.get(0).getTextContent().equals(formno))
		   {
			   List<Node> formFieldsNodeList = xmlUtil.getChildNodes(node, FORM_FIELDS);
			   
			   List<Node> formFieldNodes = xmlUtil.getChildNodes(formFieldsNodeList.get(0), FORM_FIELD);
			   
			   for(Node formField: formFieldNodes)
			   {
				   formFields.add(formField.getTextContent());
			   }
		   }
	   }
	   
	   return formFields;
	   
	}
	public List<String> getViewFieldsByViewNo(InputStream inputStream,String viewno)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream, APP);
		List<String> viewFields = new ArrayList<String>();
		
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, VIEW);
		
		Node viewNode = null;
		
		for(Node node: nodeList)
		{
			List<Node> viewNoNodes = xmlUtil.getChildNodes(node, VIEWNO);
			
			if(viewNoNodes.get(0).getTextContent().equals(viewno))
			{
				List<Node> viewFieldsNodeList = xmlUtil.getChildNodes(node, VIEW_FIELDS);
				
				List<Node> viewFieldNodes = xmlUtil.getChildNodes(viewFieldsNodeList.get(0), VIEW_FIELD);
				
				for(Node viewField: viewFieldNodes)
				{
					viewFields.add(viewField.getTextContent());
				}
			}
			
		}
		
		return viewFields;
	}
			
			
	
	public List<String> getViewFields(InputStream inputStream,String viewName)
	{
		XMLUtil xmlUtil = new XMLUtil();
		Node rootNode = xmlUtil.getRootNodeFromStream(inputStream,APP);
		
		//System.out.println(rootNode.getNodeName());
		

		List<String> viewFields = new ArrayList<String>();
		
		List<Node> nodeList = xmlUtil.getChildNodes(rootNode, VIEW);
		
		
		Node viewNode = null;
		
		
		for(Node node: nodeList)
		{
			List<Node> viewNameNodes = xmlUtil.getChildNodes(node,NAME);
			
			if(viewNameNodes.get(0).getTextContent().equals(viewName))
			{
				
				//System.out.println(viewName);
				List<Node> viewFieldsNodeList = xmlUtil.getChildNodes(node, VIEW_FIELDS);
						
				
				//System.out.println(viewFieldsNodeList.get(0).getNodeName());
			
			    List<Node> viewFieldNodes = xmlUtil.getChildNodes(viewFieldsNodeList.get(0),VIEW_FIELD);
				
			
			for(Node viewFieldNode: viewFieldNodes)
			{
				//System.out.println(viewFieldNode.getNodeName());
				viewFields.add(viewFieldNode.getTextContent());
				
				
			}
			
			}
		 
		}
		
		
		
		
		return viewFields;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AppConfigManager manager = new AppConfigManager();
		/*List<String> viewFields = manager.getViewFields("UserList");
		
		for(String viewField: viewFields)
		{
			System.out.println(viewField);
		}*/
	}

}
