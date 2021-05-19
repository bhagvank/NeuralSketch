/**
 * 
 */
package org.archcorner.belfort.telecom.services.util;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * @author bhagvan_kommadi
 *
 */
public class ArraySerializer implements KvmSerializable {

	public Parameter[] parameters;
	
	
	public void setParameter(int index, Parameter parameter)
	{
		parameters[index] = parameter;
	}
	/* (non-Javadoc)
	 * @see org.ksoap2.serialization.KvmSerializable#getProperty(int)
	 */
	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0)
		{
		case 0: return parameters;
		
		default: 
		
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.ksoap2.serialization.KvmSerializable#getPropertyCount()
	 */
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	/* (non-Javadoc)
	 * @see org.ksoap2.serialization.KvmSerializable#getPropertyInfo(int, java.util.Hashtable, org.ksoap2.serialization.PropertyInfo)
	 */
	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) 
	{
		// TODO Auto-generated method stub

		switch(arg0)
		{
		  case 0:
			 arg2.type = Parameter.class;
			 arg2.name = "parameters";
			 break;
			 
		default: break;
		}
	}

	/* (non-Javadoc)
	 * @see org.ksoap2.serialization.KvmSerializable#setProperty(int, java.lang.Object)
	 */
	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub

		switch(arg0)
		{
		case 0: 
			parameters = (Parameter[]) arg1;
			break;
			
		default: break;	
			
		}
		
	}

}
