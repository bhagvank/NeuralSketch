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
public class OrderServiceClient {


	
	private static final String NAMESPACE = "http://pojo.telecom.teal.archcorner.org";
	
	private static final String METHODNAME = "getAll";
	
	private static final String SOAPACTION = "getAll";
	
	private static final String GETMETHOD = "getOrderById";

	private static final String GETORDERBYNAMEMETHOD = "getOrderByName";
	
	private static final String INSERTORDERMETHOD = "insertOrder";
	
	private static final String UPDATEMETHOD = "updateOrder";
	
	private static final String DELETEMETHOD = "deleteOrder";

	/**
	 * 
	 */
	public OrderServiceClient() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void insertOrder(String[] order)
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, INSERTORDERMETHOD);
			
			StringBuffer orderProperties = new StringBuffer();
			for(int i=0; i< order.length; i++)
			{
				if(i < order.length-1)
				{
				 orderProperties.append(order[i]+"@@");
				}
				else
				{
					orderProperties.append(order[i]);
				}
			}
			Log.w("webservices", orderProperties.toString());
			PropertyInfo propertyInfo = new PropertyInfo();
			propertyInfo.setName("orderProperties");
			propertyInfo.setValue(orderProperties.toString());
			
	        request.addProperty(propertyInfo);		
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.ORDER_SERVICE_URL);
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
	
    public void updateOrder(String[] order)
    {
    	try
    	{
    	  SoapObject request = new SoapObject(NAMESPACE,UPDATEMETHOD);
    	  
    	  StringBuffer orderProperties = new StringBuffer();
    	  for(int i=0; i< order.length; i++)
    	  {
    		  if(i < order.length-1)
    		  {
    			  orderProperties.append(order[i]+"@@");
    		  }
    		  else
    		  {
    			  orderProperties.append(order[i]);
    		  }
    	  }
    	  
    	  Log.w("webservices", orderProperties.toString());
    	  PropertyInfo propertyInfo  = new PropertyInfo();
    	  propertyInfo.setName("orderProperties");
    	  propertyInfo.setValue(orderProperties.toString());
    
    	  request.addProperty(propertyInfo);
    	  
    	  AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.ORDER_SERVICE_URL);
    	  
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
    public void deleteOrder(String[] order)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0; i < order.length; i++)
    	{
    		if(i < order.length-1)
    		{
    			stringBuffer.append(order[i]+"@@");
    		}
    		else
    		{
    			stringBuffer.append(order[i]);
    		}
    	}
    	try
    	{
    		SoapObject request = new SoapObject(NAMESPACE,DELETEMETHOD);
    		PropertyInfo propertyInfo = new PropertyInfo();
    		propertyInfo.setName("orderProperties");
    		propertyInfo.setValue(stringBuffer.toString());
    		
    		request.addProperty(propertyInfo);
    		
    		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.ORDER_SERVICE_URL);
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
	public List<String> getOrder(int orderId,List<String> formFields)
	{
		List<String> order = new ArrayList<String>();
		
		try
		{
			SoapObject request = new SoapObject(NAMESPACE,GETMETHOD);
			
			PropertyInfo propertyInfo = new PropertyInfo();
			   
			   propertyInfo.setName("orderid");
			   propertyInfo.setValue(Integer.valueOf(orderId));
			   
			   request.addProperty(propertyInfo);
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.ORDER_SERVICE_URL);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = false;
			
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport.call(SOAPACTION, envelope);
		
			SoapObject results = (SoapObject) envelope.getResponse();
			
			formFields.add(0,"OrderId");
			
			 List<String> properties = new ArrayList<String>();
			   properties.add("ContactNo");
			   properties.add("CustomerName");
			   properties.add("DeliveryDate");
			   properties.add("OrderDate");
			   properties.add("OrderId");
			   properties.add("ProductModel");
			   properties.add("ProductName");
			
			   Map<String,String> propertiesMap = new HashMap<String,String>();
			   
			for(int i=0; i< results.getPropertyCount(); i++)
			{
				//order.add(results.getProperty(i).toString());
				
				propertiesMap.put(properties.get(i), results.getProperty(i).toString());
			}
			
			for(int i=0; i < propertiesMap.size(); i++)
			{
				order.add(propertiesMap.get(formFields.get(i)));
			}
			Log.w("service order", order.toString());
			
			formFields.remove(0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return order;
	}
	
	public List<List<String>> getAll(List<String> formFields)
	{
		List<List<String>> orders = new ArrayList<List<String>>();
		try
		   {
			   SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
			   
			   AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.ORDER_SERVICE_URL);
			   
			   
			   
			   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			   
			   envelope.dotNet = false;
			   
			   envelope.setOutputSoapObject(request);
			   
			   androidHttpTransport.call(SOAPACTION, envelope);
			   
			   SoapObject results = (SoapObject) envelope.bodyIn;
			  
			   int update = 0;
	            if(!formFields.contains("OrderId"))
	            {
			      formFields.add(0, "OrderId");
			      update = 1;
	            }
			   List<String> properties = new ArrayList<String>();
			   properties.add("ContactNo");
			   properties.add("CustomerName");
			   properties.add("DeliveryDate");
			   properties.add("OrderDate");
			   properties.add("OrderId");
			   properties.add("ProductModel");
			   properties.add("ProductName");
			   
			   for(int i=0; i< results.getPropertyCount(); i++)
			   {
				   
				   SoapObject order = (SoapObject) results.getProperty(i);
				   
				   List<String> orderProperties = new ArrayList<String>();
				   Map<String,String> orderProps = new HashMap();
				   
				   for(int j=0; j < order.getPropertyCount(); j++)
				   {
					  //String prop =  order.getPropertyInfo(j, "");
			       
					   orderProps.put(properties.get(j), order.getProperty(j).toString());
				   }
				   
				   for(int j=0; j < orderProps.size(); j++)
				   {
					   orderProperties.add(orderProps.get(formFields.get(j)));
				   }
				   orders.add(orderProperties);
				 
			   
			   }
			   if(update == 1)
			   {
			    formFields.remove(0);
			   }
	        Log.w("service ", orders.toString());		   
			   
		   }
		   catch(Exception exception)
		   {
			   exception.printStackTrace();
		   }
	
		return orders;
	}


}
