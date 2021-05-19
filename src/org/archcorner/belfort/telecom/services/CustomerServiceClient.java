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
public class CustomerServiceClient {


	
	private static final String NAMESPACE = "http://pojo.telecom.teal.archcorner.org";
	
	private static final String METHODNAME = "getAll";
	
	private static final String SOAPACTION = "getAll";
	
	private static final String GETMETHOD = "getCustomerById";

	private static final String GETCUSTOMERBYNAMEMETHOD = "getCustomerByName";
	
	private static final String INSERTCUSTOMERMETHOD = "insertCustomer";
	
	private static final String UPDATEMETHOD = "updateCustomer";
	
	private static final String DELETEMETHOD = "deleteCustomer";
	/**
	 * 
	 */
	public CustomerServiceClient() {
		// TODO Auto-generated constructor stub
	}

	
	public void insertCustomer(String[] customer)
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, INSERTCUSTOMERMETHOD);
			
			StringBuffer customerProperties = new StringBuffer();
			for(int i=0; i< customer.length; i++)
			{
				if(i < customer.length-1)
				{
				 customerProperties.append(customer[i]+"@@");
				}
				else
				{
					customerProperties.append(customer[i]);
				}
			}
			Log.w("webservices", customerProperties.toString());
			PropertyInfo propertyInfo = new PropertyInfo();
			propertyInfo.setName("customerProperties");
			propertyInfo.setValue(customerProperties.toString());
			
	        request.addProperty(propertyInfo);		
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.CUSTOMER_SERVICE_URL);
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
	
    public void updateCustomer(String[] customer)
    {
    	try
    	{
    	  SoapObject request = new SoapObject(NAMESPACE,UPDATEMETHOD);
    	  
    	  StringBuffer customerProperties = new StringBuffer();
    	  for(int i=0; i< customer.length; i++)
    	  {
    		  if(i < customer.length-1)
    		  {
    			  customerProperties.append(customer[i]+"@@");
    		  }
    		  else
    		  {
    			  customerProperties.append(customer[i]);
    		  }
    	  }
    	  
    	  Log.w("webservices", customerProperties.toString());
    	  PropertyInfo propertyInfo  = new PropertyInfo();
    	  propertyInfo.setName("customerProperties");
    	  propertyInfo.setValue(customerProperties.toString());
    
    	  request.addProperty(propertyInfo);
    	  
    	  AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.CUSTOMER_SERVICE_URL);
    	  
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
    public void deleteCustomer(String[] customer)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0; i < customer.length; i++)
    	{
    		if(i < customer.length-1)
    		{
    			stringBuffer.append(customer[i]+"@@");
    		}
    		else
    		{
    			stringBuffer.append(customer[i]);
    		}
    	}
    	try
    	{
    		SoapObject request = new SoapObject(NAMESPACE,DELETEMETHOD);
    		PropertyInfo propertyInfo = new PropertyInfo();
    		propertyInfo.setName("customerProperties");
    		propertyInfo.setValue(stringBuffer.toString());
    		
    		request.addProperty(propertyInfo);
    		
    		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.CUSTOMER_SERVICE_URL);
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
	public List<String> getCustomer(int customerId,List<String> formFields)
	{
		List<String> customer = new ArrayList<String>();
		
		try
		{
			SoapObject request = new SoapObject(NAMESPACE,GETMETHOD);
			
			PropertyInfo propertyInfo = new PropertyInfo();
			   
			   propertyInfo.setName("customerid");
			   propertyInfo.setValue(Integer.valueOf(customerId));
			   
			   request.addProperty(propertyInfo);
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.CUSTOMER_SERVICE_URL);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = false;
			
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport.call(SOAPACTION, envelope);
		
			SoapObject results = (SoapObject) envelope.getResponse();
			
			formFields.add(0,"CustomerId");
			
			 List<String> properties = new ArrayList<String>();
			   properties.add("City");
			   properties.add("ContactNo");
			   properties.add("Country");
			   properties.add("CustomerId");
			   properties.add("CustomerName");
			   properties.add("State");
			   properties.add("StreetAddress");
			   properties.add("ZipCode");
			
			   Map<String,String> propertiesMap = new HashMap<String,String>();
			   
			for(int i=0; i< results.getPropertyCount(); i++)
			{
				//customer.add(results.getProperty(i).toString());
				
				propertiesMap.put(properties.get(i), results.getProperty(i).toString());
			}
			
			for(int i=0; i < propertiesMap.size(); i++)
			{
				customer.add(propertiesMap.get(formFields.get(i)));
			}
			Log.w("service customer", customer.toString());
			
			formFields.remove(0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return customer;
	}
	
	public List<List<String>> getAll(List<String> formFields)
	{
		List<List<String>> customers = new ArrayList<List<String>>();
		try
		   {
			   SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
			   
			   AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.CUSTOMER_SERVICE_URL);
			   
			   
			   
			   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			   
			   envelope.dotNet = false;
			   
			   envelope.setOutputSoapObject(request);
			   
			   androidHttpTransport.call(SOAPACTION, envelope);
			   
			   SoapObject results = (SoapObject) envelope.bodyIn;
			  
			   int update = 0;
	            if(!formFields.contains("CustomerId"))
	            {
			      formFields.add(0, "CustomerId");
			      update = 1;
	            }
			   List<String> properties = new ArrayList<String>();
			   properties.add("City");
			   properties.add("ContactNo");
			   properties.add("Country");
			   properties.add("CustomerId");
			   properties.add("CustomerName");
			   properties.add("State");
			   properties.add("StreetAddress");
			   properties.add("ZipCode");
			   
			   for(int i=0; i< results.getPropertyCount(); i++)
			   {
				   
				   SoapObject customer = (SoapObject) results.getProperty(i);
				   
				   List<String> customerProperties = new ArrayList<String>();
				   Map<String,String> customerProps = new HashMap();
				   
				   for(int j=0; j < customer.getPropertyCount(); j++)
				   {
					  //String prop =  customer.getPropertyInfo(j, "");
			       
					   customerProps.put(properties.get(j), customer.getProperty(j).toString());
				   }
				   
				   for(int j=0; j < customerProps.size(); j++)
				   {
					   customerProperties.add(customerProps.get(formFields.get(j)));
				   }
				   customers.add(customerProperties);
				 
			   
			   }
			   if(update == 1)
			   {
			    formFields.remove(0);
			   }
	        Log.w("service ", customers.toString());		   
			   
		   }
		   catch(Exception exception)
		   {
			   exception.printStackTrace();
		   }
	
		return customers;
	}

}
