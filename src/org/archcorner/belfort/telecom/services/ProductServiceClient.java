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
public class ProductServiceClient {


	
	private static final String NAMESPACE = "http://pojo.telecom.teal.archcorner.org";
	
	private static final String METHODNAME = "getAll";
	
	private static final String SOAPACTION = "getAll";
	
	private static final String GETMETHOD = "getProductById";

	private static final String GETPRODUCTBYNAMEMETHOD = "getProductByName";
	
	private static final String INSERTPRODUCTMETHOD = "insertProduct";
	
	private static final String UPDATEMETHOD = "updateProduct";
	
	private static final String DELETEMETHOD = "deleteProduct";

	/**
	 * 
	 */
	public ProductServiceClient() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertProduct(String[] product)
	{
		try
		{
			SoapObject request = new SoapObject(NAMESPACE, INSERTPRODUCTMETHOD);
			
			StringBuffer productProperties = new StringBuffer();
			for(int i=0; i< product.length; i++)
			{
				if(i < product.length-1)
				{
				 productProperties.append(product[i]+"@@");
				}
				else
				{
					productProperties.append(product[i]);
				}
			}
			Log.w("webservices", productProperties.toString());
			PropertyInfo propertyInfo = new PropertyInfo();
			propertyInfo.setName("productProperties");
			propertyInfo.setValue(productProperties.toString());
			
	        request.addProperty(propertyInfo);		
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.PRODUCT_SERVICE_URL);
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
	
    public void updateProduct(String[] product)
    {
    	try
    	{
    	  SoapObject request = new SoapObject(NAMESPACE,UPDATEMETHOD);
    	  
    	  StringBuffer productProperties = new StringBuffer();
    	  for(int i=0; i< product.length; i++)
    	  {
    		  if(i < product.length-1)
    		  {
    			  productProperties.append(product[i]+"@@");
    		  }
    		  else
    		  {
    			  productProperties.append(product[i]);
    		  }
    	  }
    	  
    	  Log.w("webservices", productProperties.toString());
    	  PropertyInfo propertyInfo  = new PropertyInfo();
    	  propertyInfo.setName("productProperties");
    	  propertyInfo.setValue(productProperties.toString());
    
    	  request.addProperty(propertyInfo);
    	  
    	  AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.PRODUCT_SERVICE_URL);
    	  
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
    public void deleteProduct(String[] product)
    {
    	StringBuffer stringBuffer = new StringBuffer();
    	for(int i=0; i < product.length; i++)
    	{
    		if(i < product.length-1)
    		{
    			stringBuffer.append(product[i]+"@@");
    		}
    		else
    		{
    			stringBuffer.append(product[i]);
    		}
    	}
    	try
    	{
    		SoapObject request = new SoapObject(NAMESPACE,DELETEMETHOD);
    		PropertyInfo propertyInfo = new PropertyInfo();
    		propertyInfo.setName("productProperties");
    		propertyInfo.setValue(stringBuffer.toString());
    		
    		request.addProperty(propertyInfo);
    		
    		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.PRODUCT_SERVICE_URL);
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
	public List<String> getProduct(int productId,List<String> formFields)
	{
		List<String> product = new ArrayList<String>();
		
		try
		{
			SoapObject request = new SoapObject(NAMESPACE,GETMETHOD);
			
			PropertyInfo propertyInfo = new PropertyInfo();
			   
			   propertyInfo.setName("productid");
			   propertyInfo.setValue(Integer.valueOf(productId));
			   
			   request.addProperty(propertyInfo);
			
			AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.PRODUCT_SERVICE_URL);
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			
			envelope.dotNet = false;
			
			envelope.setOutputSoapObject(request);
			
			androidHttpTransport.call(SOAPACTION, envelope);
		
			SoapObject results = (SoapObject) envelope.getResponse();
			
			formFields.add(0,"ProductId");
			
			 List<String> properties = new ArrayList<String>();
			   properties.add("Description");
			   properties.add("ProductId");
			   properties.add("ProductModel");
			   properties.add("ProductName");
			
			   Map<String,String> propertiesMap = new HashMap<String,String>();
			   
			for(int i=0; i< results.getPropertyCount(); i++)
			{
				//product.add(results.getProperty(i).toString());
				
				propertiesMap.put(properties.get(i), results.getProperty(i).toString());
			}
			
			for(int i=0; i < propertiesMap.size(); i++)
			{
				product.add(propertiesMap.get(formFields.get(i)));
			}
			Log.w("service product", product.toString());
			
			formFields.remove(0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return product;
	}
	
	public List<List<String>> getAll(List<String> formFields)
	{
		List<List<String>> products = new ArrayList<List<String>>();
		try
		   {
			   SoapObject request = new SoapObject(NAMESPACE,METHODNAME);
			   
			   AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(ServiceClientConstants.PRODUCT_SERVICE_URL);
			   
			   
			   
			   SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			   
			   envelope.dotNet = false;
			   
			   envelope.setOutputSoapObject(request);
			   
			   androidHttpTransport.call(SOAPACTION, envelope);
			   
			   SoapObject results = (SoapObject) envelope.bodyIn;
			  
			   int update = 0;
	            if(!formFields.contains("ProductId"))
	            {
			      formFields.add(0, "ProductId");
			      update = 1;
	            }
			   List<String> properties = new ArrayList<String>();
			   properties.add("Description");
			   properties.add("ProductId");
			   properties.add("ProductModel");
			   properties.add("ProductName");
			   
			   for(int i=0; i< results.getPropertyCount(); i++)
			   {
				   
				   SoapObject product = (SoapObject) results.getProperty(i);
				   
				   List<String> productProperties = new ArrayList<String>();
				   Map<String,String> productProps = new HashMap();
				   
				   for(int j=0; j < product.getPropertyCount(); j++)
				   {
					  //String prop =  product.getPropertyInfo(j, "");
			       
					   productProps.put(properties.get(j), product.getProperty(j).toString());
				   }
				   
				   for(int j=0; j < productProps.size(); j++)
				   {
					   productProperties.add(productProps.get(formFields.get(j)));
				   }
				   products.add(productProperties);
				 
			   
			   }
			   if(update == 1)
			   {
			    formFields.remove(0);
			   }
	        Log.w("service ", products.toString());		   
			   
		   }
		   catch(Exception exception)
		   {
			   exception.printStackTrace();
		   }
	
		return products;
	}

}
