package org.archcorner.belfort;

import android.support.v7.app.ActionBarActivity;

import java.io.InputStream;

import org.archcorner.belfort.telecom.metadata.AppConfigManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CRUDActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crud);
		
		Bundle bundle = getIntent().getExtras();
		final String formno = bundle.getString("formno");
		
		try
		{
			InputStream inputStream = getAssets().open("belfortappconfig.xml");
		
		AppConfigManager appConfigManager = new AppConfigManager();
		String formName = appConfigManager.getFormName(inputStream, formno);
		 inputStream.close();
		 
		   final Context context = this;
		 
		    Button createButton = (Button) findViewById(R.id.button1);
		    createButton.setText("Create " + formName);
		    
		    createButton.setOnClickListener(new OnClickListener() {
		    	
		    	
		    	public void onClick(View view)
		    	{
		    		Intent intent =new Intent(context,MainActivity.class);
		    		intent.putExtra("formno",formno);
		    		startActivity(intent);
		    		
		    	}
		    	
		    
		    });
		    
		    Button updateButton = (Button) findViewById(R.id.button2);
		    updateButton.setText("Update " + formName);
		    
		    updateButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					Intent intent = new Intent(context,UpdateActivity.class);
					intent.putExtra("formno", formno);
					startActivity(intent);
				}
		    	
		    	
		    	
		    });
		    
		    Button listButton = (Button) findViewById(R.id.button3);
		    listButton.setText(formName + " List");
		    
		    listButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					Intent intent = new Intent(context,ListActivity.class);
					intent.putExtra("formno", formno);
					startActivity(intent);
				}
		    	
		    	
		    	
		    });
		    
		    Button deleteButton = (Button) findViewById(R.id.button4);
		    deleteButton.setText("Delete "+ formName);
		    deleteButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					Intent intent = new Intent(context,DeleteActivity.class);
					intent.putExtra("formno", formno);
					startActivity(intent);
				}
		    	
		    	
		    	
		    });
		 
		    Button homeButton = (Button) findViewById(R.id.button5);
		    homeButton.setText("Home");
		    homeButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
					Intent intent = new Intent(context,BelfortHome.class);
					startActivity(intent);
				}
		    	
		    	
		    	
		    });
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crud, menu);
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
