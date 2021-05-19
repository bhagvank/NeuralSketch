package org.archcorner.belfort.telecom.services;

public interface ServiceClientConstants 
{
	public static final String URL = "http://192.168.1.9:8080/";
	public static final String DISASTER_SERVICE_URL = URL+"Teal/services/DisasterService?wsdl";

	public static final String CUSTOMER_SERVICE_URL = URL+"Teal/services/CustomerService?wsdl";

	public static final String ORDER_SERVICE_URL = URL+"Teal/services/OrderService?wsdl";

	public static final String PRODUCT_SERVICE_URL = URL+"Teal/services/ProductService?wsdl";

	public static final String RESOURCE_SERVICE_URL = URL+"Teal/services/ResourceService?wsdl";

	public static final String USER_SERVICE_URL = URL+"Teal/services/UserService?wsdl";

}
