package org.archcorner.belfort;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.archcorner.belfort.telecom.metadata.AppBusinessServiceDelegate;
import org.archcorner.belfort.telecom.metadata.AppConfigManager;
import org.archcorner.belfort.telecom.services.UserServiceClient;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private static final List<String> properties = new ArrayList<String>();
	
	private static String formno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		Bundle bundle = getIntent().getExtras();
		formno = bundle.getString("formno");
		try
		{
		InputStream inputStream = getAssets().open("belfortappconfig.xml");
		
		AppConfigManager configManager = new AppConfigManager();
		
		List<String> formFields = configManager.getFormFieldsByFormNo(inputStream, formno);
		
		inputStream.close();
		InputStream formInputStream = getAssets().open("belfortappconfig.xml");
		
		
		String formName = configManager.getFormName(formInputStream, formno);
		
		formInputStream.close();
		 LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		 
		 
		RelativeLayout relativeLayout = new RelativeLayout(this);
		relativeLayout.setPadding(100, 100, 100, 100);
		relativeLayout.setLayoutParams(layoutParams);
		relativeLayout.setId(1111);
		// linearLayout.setOrientation(LinearLayout.VERTICAL);
		 relativeLayout.setBackgroundColor(0000000000);
		
		int fieldId = 0;
		
		LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		titleLayoutParams.topMargin = 20;
		titleLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		//titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		titleLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		TextView titleView = new TextView(this);
		titleView.setLayoutParams(titleLayoutParams);
		titleView.setText("Create "+formName);
		//titleView.setTextAppearance(android.R.attr.textAppearanceLarge);
		titleView.setId(1000);
		//titleView.setPadding(20, 50, 20, 20);
		//titleView.setTop(1000);
		//titleView.setBottom(1000);
		//relativeLayout.addView(titleView);
		
	
		for(String formField: formFields)
		{
			
		LayoutParams textLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		
		 if(fieldId > 0)
		 {
		  textLayoutParams.addRule(RelativeLayout.BELOW, fieldId-2);
		  textLayoutParams.addRule(RelativeLayout.ALIGN_LEFT,fieldId-2);
		  textLayoutParams.topMargin = 70;
		 }
		 else
		 {
			 textLayoutParams.addRule(RelativeLayout.BELOW,titleView.getId());
			 textLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);	
			 textLayoutParams.topMargin = 5;
			 textLayoutParams.leftMargin = 10;
		 }
		 TextView textView = new TextView(this);
		 textView.setLayoutParams(textLayoutParams);
         textView.setText(formField);
         textView.setPadding(20, 20, 20, 20);
         textView.setId(fieldId);
         relativeLayout.addView(textView);
         
         fieldId++;
         
         
         LayoutParams editTextLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
       //  editTextLayoutParams.gravity = Gravity.RIGHT;
        // editTextLayoutParams.setMargins(10, 10, 10, 10);
        
         editTextLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,textView.getId());
        // editTextLayoutParams.addRule(RelativeLayout.le);
         
         if(fieldId > 1)
         {
        	 editTextLayoutParams.addRule(RelativeLayout.ALIGN_LEFT,fieldId-2);
        	 editTextLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT,fieldId-2);
         }
         else
         {
        	 editTextLayoutParams.leftMargin = 200;
        	 editTextLayoutParams.addRule(RelativeLayout.RIGHT_OF,textView.getId());
         }
         EditText editText = new EditText(this);
         editText.setLayoutParams(editTextLayoutParams);
         editText.setText(" ");
         editText.setPadding(20, 20, 20,20);
        // editText.setMinLines(1);
         //editText.setMaxLines(1);
         editText.setId(fieldId);
         relativeLayout.addView(editText);
         fieldId++;
         
         
         
         
         
		}  
		
		LayoutParams submitLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		submitLayoutParams.addRule(RelativeLayout.BELOW,fieldId-1);
		submitLayoutParams.topMargin = 10;
		Button button = new Button(this);
		button.setText("Submit");
		button.setLayoutParams(submitLayoutParams);
		button.setId(9000);
		button.setOnClickListener(this);
		relativeLayout.addView(button);
		
         setContentView(relativeLayout);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast;
		
		switch(v.getId())
		{
		case 9000: 
			//toast = Toast.makeText(this, "Clicked on Submit",Toast.LENGTH_LONG);
			//toast.setGravity(Gravity.TOP, 25, 400);
			//toast.show();
			saveData();
			break;
		}
	}
	
	private void saveData()
	{
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(1111);
		
		Log.w("entity", "saving data");
		for(int i=0; i< relativeLayout.getChildCount(); i++)
		{
			View child = relativeLayout.getChildAt(i);
			if(child instanceof EditText)
			{
				EditText editText = (EditText) child;
				Log.w("ANDROID DYNAMIC VIEWS:",editText.getText().toString());
				//Toast toast = Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_LONG);
				//toast.setGravity(Gravity.TOP, 25, 400);
				//toast.show();
				
				properties.add(editText.getText().toString());
			}
		}
		
		try
		{
        AsyncCallWebService task = new AsyncCallWebService();
		
		task.execute().get();
		
		Intent intent = new Intent(this,CRUDActivity.class);
		intent.putExtra("formno", formno);
		startActivity(intent);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
	}
	private class AsyncCallWebService extends AsyncTask<Void,Void,Void>
	{
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
			
		
		
		
		String[] entity = new String[properties.size()];
		
		for(int i=0; i< properties.size(); i++)
		{
			entity[i] = properties.get(i);
		}
		
		AppBusinessServiceDelegate serviceDelegate = new AppBusinessServiceDelegate();
		serviceDelegate.insertEntity(formno, entity);
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
			}*/
			
	  }
	  private void getAllUsers()
	  {
		  UserServiceClient serviceClient = new UserServiceClient();
		  
			/*List<List<String>> users = serviceClient.getAll();
			
			for(List<String> user: users)
			{
				for(String property: user)
				{
				Log.w("user property values ", property);
				}
			}*/
	  }
	}
	
}
