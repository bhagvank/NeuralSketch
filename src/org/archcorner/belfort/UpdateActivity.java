package org.archcorner.belfort;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.ActionBarOverlayLayout.LayoutParams;
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
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class UpdateActivity extends ActionBarActivity implements OnClickListener{

	private List<List<String>> entities = new ArrayList();
	private String formName;
	private String formSearchField;
	private List<String> formFields;
	private String formno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		
		Bundle bundle = getIntent().getExtras();
		 formno = bundle.getString("formno");
		Log.w("update formno", formno);
		try
		{
			InputStream inputStream = getAssets().open("belfortappconfig.xml");
			
			AppConfigManager appConfigManager = new AppConfigManager();
			formName = appConfigManager.getFormName(inputStream, formno);
			
			Log.w("update formname",formName);
			inputStream.close();
			
			InputStream formInputStream = getAssets().open("belfortappconfig.xml");
			
			formSearchField = appConfigManager.getUpdateSearchFormField(formInputStream, formno);
			
			formInputStream.close();
			
			InputStream formFieldsInputStream = getAssets().open("belfortappconfig.xml");
			
			formFields = appConfigManager.getUpdateFormFieldsByFormNo(formFieldsInputStream, formno);
			
			formFieldsInputStream.close();
			
			final Context context = this;
			
			
			
			AsyncCallWebService asyncCallWebService = new AsyncCallWebService();
			
			asyncCallWebService.execute().get();
			
			createTableLayout(this);
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
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
		
		Log.w("update",tableLayout.toString());
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
		updateLabel.setText("Update " + formName);
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
				
				Button updateButton = new Button(context);
				updateButton.setText("Update");
				updateButton.setId(Integer.parseInt(entities.get(i).get(0)));
				updateButton.setOnClickListener(this);
				
				tableRow.addView(updateButton,tableRowParams);
				
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
			
			Log.w("update", "calling service");
			
			
			AppBusinessServiceDelegate serviceDelegate = new AppBusinessServiceDelegate();
			entities = serviceDelegate.getAll(formno, formFields);
			
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
		getMenuInflater().inflate(R.menu.update, menu);
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
		
		if(v.getId() == 8888)
		{
			Intent intent = new Intent(this,CRUDActivity.class);
			intent.putExtra("formno", formno);
			startActivity(intent);
		}
		else
		{
			Intent intent = new Intent(this,EditActivity.class);
			intent.putExtra("entityId",String.valueOf(v.getId()));
			intent.putExtra("formno", formno);
			startActivity(intent);
		}
	}
}
