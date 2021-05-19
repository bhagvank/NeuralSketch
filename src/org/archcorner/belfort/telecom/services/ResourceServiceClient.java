/**
 * 
 */
package org.archcorner.belfort.telecom.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.util.Log;

/**
 * @author bhagvan_kommadi
 *
 */
public class ResourceServiceClient {

	
	private static final String NAMESPACE = "http://pojo.telecom.teal.archcorner.org";
	
	private static final String METHODNAME = "getAll";
	
	private static final String SOAPACTION = "getAll";
	
	private static final String GETMETHOD = "getResourceById";

	private static final String GETRESOURCEBYNAMEMETHOD = "getResourceByName";
	
	private static final String INSERTRESOURCEMETHOD = "insertResource";
	
	private static final String UPDATEMETHOD = "updateResource";
	
	private static final String DELETEMETHOD = "deleteResource";

	/**
	 * 
	 */
	public ResourceServiceClient() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertResource(String[] resource)
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, INSERTRESOURCEMETHOD);
			
			StringBuffer resourceProperties = new StringBuffer();
			for(int i=0; i< resource.length; i++)
			{
				if(i < resource.length-1)
				{
				 resourceProperties.append(resource[i]+"@@");
				}
				else
				{
					resourceProperties.append(resource[i]);
				}
			}
			Log.w("webservices", resourceProperties.toString());
			PropertyInfo propertyInfo = new PropertyInfo();
			propertyInfo.setName("resourceProperties");
			propertyInfo.setValue(resourceProperties.toString());
			
	        request.addProperty(propertyInfo);		
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.RESOURCE_SERVICE_URL);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			androidHttpTransport.call(SOAPACTION, envelope);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
	}
	
    public void updateResource(String[] resource)
    {
    	try
    	{
    	  SoapObject request = new SoapObject(NAMESPACE,UPDATEMETHOD);
    	  
    	  StringBuffer resourceProperties = new StringBuffer();
    	  for(int i=0; i< resource.length; i++)
    	  {
    		  if(i < resource.length-1)
    		  {
    			  resourceProperties.append(resource[i]+"@@");
    		  }
    		  else
    		  {
    			  resourceProperties.append(resource[i]);
    		  }
    	  }
    	  
    	  Log.w("webservices", resourceProperties.toString());
    	  PropertyInfo propertyInfo  = new PropertyInfo();
    	  propertyInfo.setName("resourceProperties");
    	  propertyInfo.setValue(resourceProperties.toString());
    
    	  request.addProperty(propertyInfo);
    	  
    	  AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.RESOURCE_SERVICE_URL);
    	  
    	  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    	  envelope.dotNet = false;
    	  envelope.setOutputSoapObject(request);
    	  
    	  androidHttpTransport.call(UPDATEMETHOD, envelope);
    	}
    	catch(Exception exception)
    	{
    		exception.printStackTrace();
    	}
    }
    public void deleteResource(String[] resource)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0; i < resource.length; i++)
    	{
    		if(i < resource.length-1)
    		{
    			stringBuffer.append(resource[i]+"@@");
    		}
    		else
    		{
    			stringBuffer.append(resource[i]);
    		}
    	}
    	try
    	{
    		SoapObject request = new SoapObject(NAMESPACE,DELETEMETHOD);
    		PropertyInfo propertyInfo = new PropertyInfo();
    		propertyInfo.setName("resourceProperties");
    		propertyInfo.setValue(stringBuffer.toString());
    		
    		request.addProperty(propertyInfo);
    		
    		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.RESOURCE_SERVICE_URL);
    		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    		envelope.dotNet = false;
    		envelope.setOutputSoapObject(request);
    		androidHttpTransport.call(SOAPACTION,envelope);
    	}
    	catch(Exception exception)
    	{
    		exception.printStackTrace();
    	}
    }
	public List<String> getResource(int resourceId,List<String> formFields)
	{
		List<String> resource = new ArrayList<String>();
		
		try
		{
			SoapObject request = new SoapObject(NAMESPACE,GETMETHOD);
			
			PropertyInfo propertyInfo = new PropertyInfo();
			   
			   propertyInfo.setName("resourceid");
			   propertyInfo.setValue(Integer.valueOf(resourceId));
			   
			   request.addProperty(propertyInfo);
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.RESOURCE_SERVICE_URL);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = false;
			
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport.call(SOAPACTION, envelope);
		
			SoapObject results = (SoapObject) envelope.getResponse();
			
			formFields.add(0,"ResourceId");
			
			 List<String> properties = new ArrayList<String>();
			   properties.add("Name");
			   properties.add("ResourceId");
			   properties.add("StartDate");
			   properties.add("type");
			
			   Map<String,String> propertiesMap = new HashMap<String,String>();
			   
			for(int i=0; i< results.getPropertyCount(); i++)
			{
				//resource.add(results.getProperty(i).toString());
				
				propertiesMap.put(properties.get(i), results.getProperty(i).toString());
			}
			
			for(int i=0; i < propertiesMap.size(); i++)
			{
				resource.add(propertiesMap.get(formFields.get(i)));
			}
			Log.w("service resource", resource.toString());
			
			formFields.remove(0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return resource;
	}
	
	public List<List<String>> getAll(List<String> formFields)
	{
		List<List<String>> resources = new ArrayList<List<String>>();
		try
		   {
			   SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
			   
			   AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.RESOURCE_SERVICE_URL);
			   
			   
			   
			   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			   
			   envelope.dotNet = false;
			   
			   envelope.setOutputSoapObject(request);
			   
			   androidHttpTransport.call(SOAPACTION, envelope);
			   
			   SoapObject results = (SoapObject) envelope.bodyIn;
			  
			   int update = 0;
	            if(!formFields.contains("ResourceId"))
	            {
			      formFields.add(0, "ResourceId");
			      update = 1;
	            }
			   List<String> properties = new ArrayList<String>();
			   properties.add("Name");
			   properties.add("ResourceId");
			   properties.add("StartDate");
			   properties.add("type");
			   
			   for(int i=0; i< results.getPropertyCount(); i++)
			   {
				   
				   SoapObject resource = (SoapObject) results.getProperty(i);
				   
				   List<String> resourceProperties = new ArrayList<String>();
				   Map<String,String> resourceProps = new HashMap();
				   
				   for(int j=0; j < resource.getPropertyCount(); j++)
				   {
					  //String prop =  resource.getPropertyInfo(j, "");
			       
					   resourceProps.put(properties.get(j), resource.getProperty(j).toString());
				   }
				   
				   for(int j=0; j < resourceProps.size(); j++)
				   {
					   resourceProperties.add(resourceProps.get(formFields.get(j)));
				   }
				   resources.add(resourceProperties);
				 
			   
			   }
			   if(update == 1)
			   {
			    formFields.remove(0);
			   }
	        Log.w("service ", resources.toString());		   
			   
		   }
		   catch(Exception exception)
		   {
			   exception.printStackTrace();
		   }
	
		return resources;
	}

}
