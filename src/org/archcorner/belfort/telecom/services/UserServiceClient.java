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
public class UserServiceClient {

	
	
	private static final String NAMESPACE = "http://pojo.telecom.teal.archcorner.org";
	
	private static final String METHODNAME = "getAll";
	
	private static final String SOAPACTION = "getAll";
	
	private static final String GETMETHOD = "getUserById";

	private static final String GETUSERBYNAMEMETHOD = "getUserByName";
	
	private static final String INSERTUSERMETHOD = "insertUser";
	
	private static final String UPDATEMETHOD = "updateUser";
	
	private static final String DELETEMETHOD = "deleteUser";
	
	public void insertUser(String[] user)
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, INSERTUSERMETHOD);
			
			StringBuffer userProperties = new StringBuffer();
			for(int i=0; i< user.length; i++)
			{
				if(i < user.length-1)
				{
				 userProperties.append(user[i]+"@@");
				}
				else
				{
					userProperties.append(user[i]);
				}
			}
			Log.w("webservices", userProperties.toString());
			PropertyInfo propertyInfo = new PropertyInfo();
			propertyInfo.setName("userProperties");
			propertyInfo.setValue(userProperties.toString());
			
	        request.addProperty(propertyInfo);		
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.USER_SERVICE_URL);
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
	
    public void updateUser(String[] user)
    {
    	try
    	{
    	  SoapObject request = new SoapObject(NAMESPACE,UPDATEMETHOD);
    	  
    	  StringBuffer userProperties = new StringBuffer();
    	  for(int i=0; i< user.length; i++)
    	  {
    		  if(i < user.length-1)
    		  {
    			  userProperties.append(user[i]+"@@");
    		  }
    		  else
    		  {
    			  userProperties.append(user[i]);
    		  }
    	  }
    	  
    	  Log.w("webservices", userProperties.toString());
    	  PropertyInfo propertyInfo  = new PropertyInfo();
    	  propertyInfo.setName("userProperties");
    	  propertyInfo.setValue(userProperties.toString());
    
    	  request.addProperty(propertyInfo);
    	  
    	  AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.USER_SERVICE_URL);
    	  
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
    public void deleteUser(String[] user)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0; i < user.length; i++)
    	{
    		if(i < user.length-1)
    		{
    			stringBuffer.append(user[i]+"@@");
    		}
    		else
    		{
    			stringBuffer.append(user[i]);
    		}
    	}
    	try
    	{
    		SoapObject request = new SoapObject(NAMESPACE,DELETEMETHOD);
    		PropertyInfo propertyInfo = new PropertyInfo();
    		propertyInfo.setName("userProperties");
    		propertyInfo.setValue(stringBuffer.toString());
    		
    		request.addProperty(propertyInfo);
    		
    		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.USER_SERVICE_URL);
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
	public List<String> getUser(int userId,List<String> formFields)
	{
		List<String> user = new ArrayList<String>();
		
		try
		{
			SoapObject request = new SoapObject(NAMESPACE,GETMETHOD);
			
			PropertyInfo propertyInfo = new PropertyInfo();
			   
			   propertyInfo.setName("userid");
			   propertyInfo.setValue(Integer.valueOf(userId));
			   
			   request.addProperty(propertyInfo);
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.USER_SERVICE_URL);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = false;
			
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport.call(SOAPACTION, envelope);
		
			SoapObject results = (SoapObject) envelope.getResponse();
			
			formFields.add(0,"UserId");
			
			 List<String> properties = new ArrayList<String>();
			   properties.add("Password");
			   properties.add("UserId");
			   properties.add("UserName");
			
			   Map<String,String> propertiesMap = new HashMap<String,String>();
			   
			for(int i=0; i< results.getPropertyCount(); i++)
			{
				//user.add(results.getProperty(i).toString());
				
				propertiesMap.put(properties.get(i), results.getProperty(i).toString());
			}
			
			for(int i=0; i < propertiesMap.size(); i++)
			{
				user.add(propertiesMap.get(formFields.get(i)));
			}
			Log.w("service user", user.toString());
			
			formFields.remove(0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return user;
	}
	
	public List<List<String>> getAll(List<String> formFields)
	{
		List<List<String>> users = new ArrayList<List<String>>();
		try
		   {
			   SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
			   
			   AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.USER_SERVICE_URL);
			   
			   
			   
			   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			   
			   envelope.dotNet = false;
			   
			   envelope.setOutputSoapObject(request);
			   
			   androidHttpTransport.call(SOAPACTION, envelope);
			   
			   SoapObject results = (SoapObject) envelope.bodyIn;
			  
			   int update = 0;
	            if(!formFields.contains("UserId"))
	            {
			      formFields.add(0, "UserId");
			      update = 1;
	            }
			   List<String> properties = new ArrayList<String>();
			   properties.add("Password");
			   properties.add("UserId");
			   properties.add("UserName");
			   
			   for(int i=0; i< results.getPropertyCount(); i++)
			   {
				   
				   SoapObject user = (SoapObject) results.getProperty(i);
				   
				   List<String> userProperties = new ArrayList<String>();
				   Map<String,String> userProps = new HashMap();
				   
				   for(int j=0; j < user.getPropertyCount(); j++)
				   {
					  //String prop =  user.getPropertyInfo(j, "");
			       
					   userProps.put(properties.get(j), user.getProperty(j).toString());
				   }
				   
				   for(int j=0; j < userProps.size(); j++)
				   {
					   userProperties.add(userProps.get(formFields.get(j)));
				   }
				   users.add(userProperties);
				 
			   
			   }
			   if(update == 1)
			   {
			    formFields.remove(0);
			   }
	        Log.w("service ", users.toString());		   
			   
		   }
		   catch(Exception exception)
		   {
			   exception.printStackTrace();
		   }
	
		return users;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
