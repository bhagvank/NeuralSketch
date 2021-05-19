package org.archcorner.belfort;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.archcorner.belfort.telecom.metadata.AppBusinessServiceDelegate;
import org.archcorner.belfort.telecom.metadata.AppConfigManager;
import org.archcorner.belfort.telecom.services.UserServiceClient;

import android.R.color;
import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DeleteActivity extends ActionBarActivity implements OnClickListener {
	private List<List<String>> entities = new ArrayList();
	private String formName;
	private String formSearchField;
	private List<String> formFields;
	private String formno;
	private static int method = 0;
	private static  List<String> properties = new ArrayList<String>();
	private static  String entityId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
setContentView(R.layout.activity_update);
		
		Bundle bundle = getIntent().getExtras();
		 formno = bundle.getString("formno");
		Log.w("Delete formno", formno);
		try
		{
			InputStream inputStream = getAssets().open("belfortappconfig.xml");
			
			AppConfigManager appConfigManager = new AppConfigManager();
			formName = appConfigManager.getFormName(inputStream, formno);
			
			Log.w("delete formname",formName);
			inputStream.close();
			
			InputStream formInputStream = getAssets().open("belfortappconfig.xml");
			
			formSearchField = appConfigManager.getDeleteSearchFormField(formInputStream, formno);
			
			formInputStream.close();
			
			InputStream formFieldsInputStream = getAssets().open("belfortappconfig.xml");
			
			formFields = appConfigManager.getDeleteFormFieldsByFormNo(formFieldsInputStream, formno);
			
			formFieldsInputStream.close();
			
			final Context context = this;
			
			
			
			AsyncCallWebService asyncCallWebService = new AsyncCallWebService();
			
			method = 3;
			asyncCallWebService.execute().get();
			
			createTableLayout(this);
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete, menu);
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
	
	private int getSearchFormFieldIndex(String searchField, List<String> fields)
	{
		int j=0;
		
		for(int i=0; i < fields.size(); i++)
		{
		   if(searchField.equals(fields.get(i)))
		   {
			   j=i;
		   }
		}
		
		return j;
	}
	private void createTableLayout(Context context)
	{
		//TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		
		TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
		tableLayoutParams.setMargins(1, 1, 1, 1);
		//ScrollView scrollView = new ScrollView(context);
		//TableLayout tableLayout = new TableLayout(context);
		//tableLayout.setBackgroundColor(color.black);
		
		TableLayout tableLayout = (TableLayout) findViewById(R.id.main_table);
		
		Log.w("Delete",tableLayout.toString());
		//HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
		
		
		TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
		tableRowParams.setMargins(1,1,1,1);
		tableRowParams.weight = 1;
		int index = getSearchFormFieldIndex(formSearchField,formFields);
		
		TableRow headerRow = new TableRow(context);
		headerRow.setBackgroundColor(color.black);
		
		TextView label = new TextView(context);
		label.setText(formSearchField);
		label.setBackgroundColor(color.white);
		label.setGravity(Gravity.CENTER);
		
		headerRow.addView(label, tableRowParams);
		
		TextView updateLabel = new TextView(context);
		updateLabel.setText("Delete " + formName);
		updateLabel.setBackgroundColor(color.white);
		updateLabel.setGravity(Gravity.CENTER);
		
		headerRow.addView(updateLabel,tableRowParams);
		
		tableLayout.addView(headerRow,tableLayoutParams);
		
		for(int i=0; i< entities.size(); i++)
		{
			TableRow tableRow = new TableRow(context);
			tableRow.setBackgroundColor(color.black);
			
			//for(int j=0; j < 2; j++)
			//{
				TextView textView = new TextView(context);
				textView.setText(entities.get(i).get(index+1));
				textView.setBackgroundColor(color.white);
				textView.setGravity(Gravity.CENTER);
				
				tableRow.addView(textView, tableRowParams);
				
				Button deleteButton = new Button(context);
				deleteButton.setText("Delete");
				deleteButton.setId(Integer.parseInt(entities.get(i).get(0)));
				deleteButton.setOnClickListener(this);
				
				tableRow.addView(deleteButton,tableRowParams);
				
				tableLayout.addView(tableRow,tableLayoutParams);
			//}
		}
		
		TableRow tableRow = new TableRow(context);
		tableRow.setBackgroundColor(color.black);
		Button backButton = new Button(context);
		backButton.setText("Home ");
		backButton.setId(8888);
		backButton.setOnClickListener(this);
		
		tableRow.addView(backButton,tableRowParams);
		tableLayout.addView(tableRow,tableLayoutParams);
		//horizontalScrollView.addView(tableLayout);
		//scrollView.addView(horizontalScrollView);

		
	}
	private class AsyncCallWebService extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			Log.w("Delete", "calling service");
			AppBusinessServiceDelegate serviceDelegate = new AppBusinessServiceDelegate();
			
			if(method == 1)
			{
			   
			   
			   properties = serviceDelegate.getEntity(formno, formFields,entityId);
			}
			else if(method == 2)
			{
				String[] entityProperties = new String[properties.size()];
				for(int i=0; i< properties.size(); i++)
				{
					entityProperties[i] = properties.get(i);
					
				}
				
				serviceDelegate.deleteEntity(formno,entityProperties);
			}
			else if(method == 3)
			{
				entities = serviceDelegate.getAll(formno, formFields);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == 8888)
		{
			Intent intent = new Intent(this,CRUDActivity.class);
			intent.putExtra("formno", formno);
			startActivity(intent);
		}
		else
		{
		entityId = String.valueOf(v.getId());
		//toast = Toast.makeText(this, "clicked on Submit", Toast.LENGTH_LONG);
		//toast.setGravity(Gravity.TOP, 25, 400);
		//toast.show();
	      saveData();
		}
		
		
	}
	private void saveData()
	{
		
		try
		{
		AsyncCallWebService asyncCallWebService = new AsyncCallWebService();
		method = 1;
		asyncCallWebService.execute().get();
		
		AsyncCallWebService asyncWebService = new AsyncCallWebService();
		method = 2;
		asyncWebService.execute().get();
		
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
