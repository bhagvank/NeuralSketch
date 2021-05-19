package org.archcorner.belfort;

import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archcorner.belfort.telecom.metadata.AppBusinessServiceDelegate;
import org.archcorner.belfort.telecom.metadata.AppConfigManager;
import org.archcorner.belfort.telecom.services.UserServiceClient;

import android.R.color;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class EditActivity extends ActionBarActivity implements OnClickListener {

	private static  List<String> properties = new ArrayList<String>();
	private static  String entityId = null;
	private static  List<String> formFields = new ArrayList<String>();
	private static int method = 0;
	private static String[] updatedProperties;
	private static String formno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_edit);
		
		Bundle bundle = getIntent().getExtras();
		entityId = bundle.getString("entityId");
		formno = bundle.getString("formno");
		
		try
		{
			InputStream inputStream = getAssets().open("belfortappconfig.xml");
			
			AppConfigManager appConfigManager = new AppConfigManager();
			
			formFields = appConfigManager.getUpdateFormFieldsByFormNo(inputStream, formno);
			
			inputStream.close();
			
			InputStream formInputStream = getAssets().open("belfortappconfig.xml");
			
			String formName = appConfigManager.getUpdateFormName(formInputStream, formno);
			
			formInputStream.close();
			
			
			AsyncCallWebService asyncCallWebService = new AsyncCallWebService();
			method = 1;
			asyncCallWebService.execute().get();
			
			
			LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			
			RelativeLayout relativeLayout = new RelativeLayout(this);
			relativeLayout.setPadding(100, 100, 100, 100);
			relativeLayout.setLayoutParams(layoutParams);
			relativeLayout.setId(1111);
			relativeLayout.setBackgroundColor(color.white);
			
			int fieldId = 0;
			
			LayoutParams titleLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			titleLayoutParams.topMargin = 20;
			titleLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			titleLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			//titleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
			
			TextView titleView = new TextView(this);
			titleView.setLayoutParams(titleLayoutParams);
			titleView.setText("Edit "+formName);
			titleView.setId(1000);
			//titleView.setPadding(20, 50, 20, 20);
			
			//relativeLayout.addView(titleView);
			
			int formFieldId = 1;
			for(String formField: formFields)
			{
				LayoutParams textLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				
				if(fieldId > 0)
				{
					textLayoutParams.addRule(RelativeLayout.BELOW,fieldId-2);
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
				editTextLayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,textView.getId());
				
				
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
				editText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(50)});
				editText.setMaxWidth(400);
				editText.setText(properties.get(formFieldId));
				editText.setPadding(20, 20, 20, 20);
				editText.setId(fieldId);
				
				relativeLayout.addView(editText);
				
				fieldId++;
				formFieldId++;
				
			}
			
			LayoutParams submitLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			submitLayoutParams.addRule(RelativeLayout.BELOW, fieldId-1);
			submitLayoutParams.topMargin = 10;
			
			Button submitButton = new Button(this);
			submitButton.setText("Submit");
			submitButton.setLayoutParams(submitLayoutParams);
			submitButton.setId(9000);
			submitButton.setOnClickListener(this);
			relativeLayout.addView(submitButton);
			
			setContentView(relativeLayout);
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
			AppBusinessServiceDelegate serviceDelegate = new AppBusinessServiceDelegate();
			
			
			if(method == 1)
			{
			
			
			properties = serviceDelegate.getEntity(formno, formFields,entityId);
			}
			else if(method == 2)
			{
			 
				serviceDelegate.updateEntity(formno, updatedProperties);
			}
			method = 0;
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
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
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
			
			//toast = Toast.makeText(this, "clicked on Submit", Toast.LENGTH_LONG);
			//toast.setGravity(Gravity.TOP, 25, 400);
			//toast.show();
		    saveData();
		  break;
		
		}
		
		
	}
	private void saveData()
	{
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(1111);
		
		 updatedProperties = new String[properties.size()];
		 
		updatedProperties[0] = entityId;
		
		int editId = 1;
		
		for(int i=0; i< relativeLayout.getChildCount(); i++)
		{
			View child = relativeLayout.getChildAt(i);
			
			if(child instanceof  EditText)
			{
				EditText editText = (EditText) child;
				
				updatedProperties[editId] = editText.getText().toString();
				
				editId++;
			}
		}
		try
		{
		AsyncCallWebService asyncCallWebService = new AsyncCallWebService();
		method = 2;
		asyncCallWebService.execute().get();
		
		Intent intent = new Intent(this,CRUDActivity.class);
		intent.putExtra("formno", formno);
		startActivity(intent);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
