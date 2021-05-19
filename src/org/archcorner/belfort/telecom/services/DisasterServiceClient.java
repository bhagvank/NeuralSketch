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
public class DisasterServiceClient {


	
	private static final String NAMESPACE = "http://pojo.telecom.teal.archcorner.org";
	
	private static final String METHODNAME = "getAll";
	
	private static final String SOAPACTION = "getAll";
	
	private static final String GETMETHOD = "getDisasterById";

	private static final String GETDISASTERBYNAMEMETHOD = "getDisasterByName";
	
	private static final String INSERTDISASTERMETHOD = "insertDisaster";
	
	private static final String UPDATEMETHOD = "updateDisaster";
	
	private static final String DELETEMETHOD = "deleteDisaster";
	/**
	 * 
	 */
	public DisasterServiceClient() {
		// TODO Auto-generated constructor stub
	}

	
	public void insertDisaster(String[] disaster)
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, INSERTDISASTERMETHOD);
			
			StringBuffer disasterProperties = new StringBuffer();
			for(int i=0; i< disaster.length; i++)
			{
				if(i < disaster.length-1)
				{
				 disasterProperties.append(disaster[i]+"@@");
				}
				else
				{
					disasterProperties.append(disaster[i]);
				}
			}
			Log.w("webservices", disasterProperties.toString());
			PropertyInfo propertyInfo = new PropertyInfo();
			propertyInfo.setName("disasterProperties");
			propertyInfo.setValue(disasterProperties.toString());
			
	        request.addProperty(propertyInfo);		
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.DISASTER_SERVICE_URL);
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
	
    public void updateDisaster(String[] disaster)
    {
    	try
    	{
    	  SoapObject request = new SoapObject(NAMESPACE,UPDATEMETHOD);
    	  
    	  StringBuffer disasterProperties = new StringBuffer();
    	  for(int i=0; i< disaster.length; i++)
    	  {
    		  if(i < disaster.length-1)
    		  {
    			  disasterProperties.append(disaster[i]+"@@");
    		  }
    		  else
    		  {
    			  disasterProperties.append(disaster[i]);
    		  }
    	  }
    	  
    	  Log.w("webservices", disasterProperties.toString());
    	  PropertyInfo propertyInfo  = new PropertyInfo();
    	  propertyInfo.setName("disasterProperties");
    	  propertyInfo.setValue(disasterProperties.toString());
    
    	  request.addProperty(propertyInfo);
    	  
    	  AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.DISASTER_SERVICE_URL);
    	  
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
    public void deleteDisaster(String[] disaster)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0; i < disaster.length; i++)
    	{
    		if(i < disaster.length-1)
    		{
    			stringBuffer.append(disaster[i]+"@@");
    		}
    		else
    		{
    			stringBuffer.append(disaster[i]);
    		}
    	}
    	try
    	{
    		SoapObject request = new SoapObject(NAMESPACE,DELETEMETHOD);
    		PropertyInfo propertyInfo = new PropertyInfo();
    		propertyInfo.setName("disasterProperties");
    		propertyInfo.setValue(stringBuffer.toString());
    		
    		request.addProperty(propertyInfo);
    		
    		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.DISASTER_SERVICE_URL);
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
	public List<String> getDisaster(int disasterId,List<String> formFields)
	{
		List<String> disaster = new ArrayList<String>();
		
		try
		{
			SoapObject request = new SoapObject(NAMESPACE,GETMETHOD);
			
			PropertyInfo propertyInfo = new PropertyInfo();
			   
			   propertyInfo.setName("disasterid");
			   propertyInfo.setValue(Integer.valueOf(disasterId));
			   
			   request.addProperty(propertyInfo);
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.DISASTER_SERVICE_URL);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = false;
			
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport.call(SOAPACTION, envelope);
		
			SoapObject results = (SoapObject) envelope.getResponse();
			
			formFields.add(0,"DisasterId");
			
			 List<String> properties = new ArrayList<String>();
			   properties.add("ContactNo");
			   properties.add("DisasterId");
			   properties.add("DisasterName");
			   properties.add("EventDescription");
			   properties.add("LocationName");
			   properties.add("ReporterName");
			   
			   Map<String,String> propertiesMap = new HashMap<String,String>();
			   
			for(int i=0; i< results.getPropertyCount(); i++)
			{
				//disaster.add(results.getProperty(i).toString());
				
				propertiesMap.put(properties.get(i), results.getProperty(i).toString());
			}
			
			for(int i=0; i < propertiesMap.size(); i++)
			{
				disaster.add(propertiesMap.get(formFields.get(i)));
			}
			Log.w("service disaster", disaster.toString());
			
			formFields.remove(0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return disaster;
	}
	
	public List<List<String>> getAll(List<String> formFields)
	{
		List<List<String>> disasters = new ArrayList<List<String>>();
		try
		   {
			   SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
			   
			   AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.DISASTER_SERVICE_URL);
			   
			   
			   
			   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			   
			   envelope.dotNet = false;
			   
			   envelope.setOutputSoapObject(request);
			   
			   androidHttpTransport.call(SOAPACTION, envelope);
			   
			   SoapObject results = (SoapObject) envelope.bodyIn;
			  
			   int update = 0;
	            if(!formFields.contains("DisasterId"))
	            {
			      formFields.add(0, "DisasterId");
			      update = 1;
	            }
			   List<String> properties = new ArrayList<String>();
			   properties.add("ContactNo");
			   properties.add("DisasterId");
			   properties.add("DisasterName");
			   properties.add("EventDescription");
			   properties.add("LocationName");
			   properties.add("ReporterName");
			   
			   
			   for(int i=0; i< results.getPropertyCount(); i++)
			   {
				   
				   SoapObject disaster = (SoapObject) results.getProperty(i);
				   
				   List<String> disasterProperties = new ArrayList<String>();
				   Map<String,String> disasterProps = new HashMap();
				   
				   for(int j=0; j < disaster.getPropertyCount(); j++)
				   {
					  //String prop =  disaster.getPropertyInfo(j, "");
			       
					   disasterProps.put(properties.get(j), disaster.getProperty(j).toString());
				   }
				   
				   for(int j=0; j < disasterProps.size(); j++)
				   {
					   disasterProperties.add(disasterProps.get(formFields.get(j)));
				   }
				   disasters.add(disasterProperties);
				 
			   
			   }
			   if(update == 1)
			   {
			    formFields.remove(0);
			   }
	        Log.w("service ", disasters.toString());		   
			   
		   }
		   catch(Exception exception)
		   {
			   exception.printStackTrace();
		   }
	
		return disasters;
	}

}
