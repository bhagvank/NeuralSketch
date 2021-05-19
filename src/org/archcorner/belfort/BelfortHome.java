package org.archcorner.belfort;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import org.archcorner.belfort.telecom.services.UserServiceClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BelfortHome extends ActionBarActivity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_belfort_home);
		
	    createButtonListeners();	
		
		
	}
	
	private class AsyncCallWebService extends AsyncTask<Void,Void,Void>
	{
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
			
		UserServiceClient serviceClient = new UserServiceClient();
		
		String[] user = new String[3];
		
		user[0] ="4";
		user[1] ="technician";
		user[2] = "workspace";
		
		serviceClient.deleteUser(user);
		
		return null;
	}
	
	@Override
	 protected void onPostExecute(Void result)
	 {
		Log.i("Async", "PostExecute");
	 }
	
	 @Override
	  protected void onPreExecute()
	  {
		 Log.i("Async", "PreExecute");
	  }
	 
	 @Override
	  protected void onProgressUpdate(Void... values)
	  {
		 Log.i("Async", "onProgressUpdate");
	  }
	 
	  private void getUser()
	  {
			/*UserServiceClient serviceClient = new UserServiceClient();
			
			List<String> user = serviceClient.getUser(2);
			
			for(String property: user)
			{
				Log.w("user property value", property);
			}
			*/
	  }
	  private void getAllUsers()
	  {
		  UserServiceClient serviceClient = new UserServiceClient();
		  
		/*	
		 List<List<String>> users = serviceClient.getAll();
			
			for(List<String> user: users)
			{
				for(String property: user)
				{
				Log.w("user property values ", property);
				}
			}*/
	  }
	}
	
	private void createButtonListeners()
	{
	final Context context = this;
		
		Button disastermgmtButton = (Button) findViewById(R.id.button1);
		
		
		disastermgmtButton.setOnClickListener(new OnClickListener()
				
				{
			      public void onClick(View view)
			      {
			    	  Intent intent = new Intent(context,CRUDActivity.class);
			    	  intent.putExtra("formno","2" );
			    	  startActivity(intent);
			    	  
			      }
				}
				
				
				
				
				
				
				);
      Button customermgmtButton = (Button) findViewById(R.id.button2);
		
		
		customermgmtButton.setOnClickListener(new OnClickListener()
				
				{
			      public void onClick(View view)
			      {
			    	  Intent intent = new Intent(context,CRUDActivity.class);
			    	  intent.putExtra("formno","3" );
			    	  startActivity(intent);
			    	  
			      }
				}
				
				
				
				
				
				
				);
       Button ordermgmtButton = (Button) findViewById(R.id.button3);
		
		
		ordermgmtButton.setOnClickListener(new OnClickListener()
				
				{
			      public void onClick(View view)
			      {
			    	  Intent intent = new Intent(context,CRUDActivity.class);
			    	  intent.putExtra("formno","4" );
			    	  startActivity(intent);
			    	  
			      }
				}
				
				
				
				
				
				
				);
         Button productmgmtButton = (Button) findViewById(R.id.button4);
		
		
		productmgmtButton.setOnClickListener(new OnClickListener()
				
				{
			      public void onClick(View view)
			      {
			    	  Intent intent = new Intent(context,CRUDActivity.class);
			    	  intent.putExtra("formno","5" );
			    	  startActivity(intent);
			    	  
			      }
				}
				
				
				
				
				
				
				);
       Button resourcemgmtButton = (Button) findViewById(R.id.button5);
		
		
		resourcemgmtButton.setOnClickListener(new OnClickListener()
				
				{
			      public void onClick(View view)
			      {
			    	  Intent intent = new Intent(context,CRUDActivity.class);
			    	  intent.putExtra("formno","6" );
			    	  startActivity(intent);
			    	  
			      }
				}
				
				
				
				
				
				
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.belfort_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
