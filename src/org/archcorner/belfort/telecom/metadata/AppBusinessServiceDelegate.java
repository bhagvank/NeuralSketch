/**
 * 
 */
package org.archcorner.belfort.telecom.metadata;

import java.util.ArrayList;
import java.util.List;

import org.archcorner.belfort.telecom.services.CustomerServiceClient;
import org.archcorner.belfort.telecom.services.DisasterServiceClient;
import org.archcorner.belfort.telecom.services.OrderServiceClient;
import org.archcorner.belfort.telecom.services.ProductServiceClient;
import org.archcorner.belfort.telecom.services.ResourceServiceClient;
import org.archcorner.belfort.telecom.services.UserServiceClient;

/**
 * @author bhagvan_kommadi
 *
 */
public class AppBusinessServiceDelegate {

	/**
	 * 
	 */
	public AppBusinessServiceDelegate() {
		// TODO Auto-generated constructor stub
	}

	public void insertEntity(String formno,String[] entity)
	{
		int formNumber = Integer.parseInt(formno);
		if(formNumber == 1)
		{
			UserServiceClient userServiceClient = new UserServiceClient();	
		    userServiceClient.insertUser(entity);
		}
		else if(formNumber == 2)
		{
			DisasterServiceClient disasterServiceClient = new DisasterServiceClient();	
		    disasterServiceClient.insertDisaster(entity);
		}
		else if(formNumber == 3)
		{
			CustomerServiceClient customerServiceClient = new CustomerServiceClient();	
		    customerServiceClient.insertCustomer(entity);
		}
		else if(formNumber == 4)
		{
			OrderServiceClient orderServiceClient = new OrderServiceClient();	
		    orderServiceClient.insertOrder(entity);
		}
		else if(formNumber == 5)
		{
			ProductServiceClient productServiceClient = new ProductServiceClient();	
		    productServiceClient.insertProduct(entity);
		}
		else if(formNumber == 6)
		{
			ResourceServiceClient resourceServiceClient = new ResourceServiceClient();	
		    resourceServiceClient.insertResource(entity);
		}
	}
	public void updateEntity(String formno,String[] entity)
	{
		int formNumber = Integer.parseInt(formno);
		if(formNumber == 1)
		{
			UserServiceClient userServiceClient = new UserServiceClient();	
		    userServiceClient.updateUser(entity);
		}
		else if(formNumber == 2)
		{
			DisasterServiceClient disasterServiceClient = new DisasterServiceClient();	
		    disasterServiceClient.updateDisaster(entity);
		}
		else if(formNumber == 3)
		{
			CustomerServiceClient customerServiceClient = new CustomerServiceClient();	
		    customerServiceClient.updateCustomer(entity);
		}
		else if(formNumber == 4)
		{
			OrderServiceClient orderServiceClient = new OrderServiceClient();	
		    orderServiceClient.updateOrder(entity);
		}
		else if(formNumber == 5)
		{
			ProductServiceClient productServiceClient = new ProductServiceClient();	
		    productServiceClient.updateProduct(entity);
		}
		else if(formNumber == 6)
		{
			ResourceServiceClient resourceServiceClient = new ResourceServiceClient();	
		    resourceServiceClient.updateResource(entity);
		}
	}
	public void deleteEntity(String formno,String[] entity)
	{
		int formNumber = Integer.parseInt(formno);
		if(formNumber == 1)
		{
			UserServiceClient userServiceClient = new UserServiceClient();	
		    userServiceClient.deleteUser(entity);
		}
		else if(formNumber == 2)
		{
			DisasterServiceClient disasterServiceClient = new DisasterServiceClient();	
		    disasterServiceClient.deleteDisaster(entity);
		}
		else if(formNumber == 3)
		{
			CustomerServiceClient customerServiceClient = new CustomerServiceClient();	
		    customerServiceClient.deleteCustomer(entity);
		}
		else if(formNumber == 4)
		{
			OrderServiceClient orderServiceClient = new OrderServiceClient();	
		    orderServiceClient.deleteOrder(entity);
		}
		else if(formNumber == 5)
		{
			ProductServiceClient productServiceClient = new ProductServiceClient();	
		    productServiceClient.deleteProduct(entity);
		}
		else if(formNumber == 6)
		{
			ResourceServiceClient resourceServiceClient = new ResourceServiceClient();	
		    resourceServiceClient.deleteResource(entity);
		}
	}
	public List<List<String>> getAll(String formno,List<String> formFields)
	{
		List<List<String>> entities = new ArrayList();
		
		int formNumber = Integer.parseInt(formno);
		if(formNumber == 1)
		{
			UserServiceClient userServiceClient = new UserServiceClient();	
		    entities = userServiceClient.getAll(formFields);
		}
		else if(formNumber == 2)
		{
			DisasterServiceClient disasterServiceClient = new DisasterServiceClient();	
			 entities = disasterServiceClient.getAll(formFields);
		}
		else if(formNumber == 3)
		{
			CustomerServiceClient customerServiceClient = new CustomerServiceClient();	
			 entities = customerServiceClient.getAll(formFields);
		}
		else if(formNumber == 4)
		{
			OrderServiceClient orderServiceClient = new OrderServiceClient();	
			 entities = orderServiceClient.getAll(formFields);
		}
		else if(formNumber == 5)
		{
			ProductServiceClient productServiceClient = new ProductServiceClient();	
			 entities = productServiceClient.getAll(formFields);
		}
		else if(formNumber == 6)
		{
			ResourceServiceClient resourceServiceClient = new ResourceServiceClient();	
			 entities = resourceServiceClient.getAll(formFields);
		}
		
		return entities;
	}
	public List<String> getEntity(String formno,List<String> formFields,String entityIdString)
	{
		List<String> entity = new ArrayList();
		
		int entityId = Integer.parseInt(entityIdString);
		int formNumber = Integer.parseInt(formno);
		if(formNumber == 1)
		{
			UserServiceClient userServiceClient = new UserServiceClient();	
		    entity = userServiceClient.getUser(entityId,formFields);
		}
		else if(formNumber == 2)
		{
			DisasterServiceClient disasterServiceClient = new DisasterServiceClient();	
			 entity = disasterServiceClient.getDisaster(entityId,formFields);
		}
		else if(formNumber == 3)
		{
			CustomerServiceClient customerServiceClient = new CustomerServiceClient();	
			 entity = customerServiceClient.getCustomer(entityId,formFields);
		}
		else if(formNumber == 4)
		{
			OrderServiceClient orderServiceClient = new OrderServiceClient();	
			 entity = orderServiceClient.getOrder(entityId,formFields);
		}
		else if(formNumber == 5)
		{
			ProductServiceClient productServiceClient = new ProductServiceClient();	
			 entity = productServiceClient.getProduct(entityId,formFields);
		}
		else if(formNumber == 6)
		{
			ResourceServiceClient resourceServiceClient = new ResourceServiceClient();	
			 entity = resourceServiceClient.getResource(entityId,formFields);
		}
		
		return entity;
	}
}
