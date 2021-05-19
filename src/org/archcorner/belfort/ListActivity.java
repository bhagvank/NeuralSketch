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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListActivity extends ActionBarActivity implements OnClickListener {
	private List<List<String>> entities = new ArrayList();
	private String formName;
	private List<String> formFields;
	private String formno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		Bundle bundle = getIntent().getExtras();
		 formno = bundle.getString("formno");
		Log.w("list formno", formno);
		try
		{
			InputStream inputStream = getAssets().open("belfortappconfig.xml");
			
			AppConfigManager appConfigManager = new AppConfigManager();
			formName = appConfigManager.getFormName(inputStream, formno);
			
			Log.w("list formname",formName);
			inputStream.close();
			
			
			InputStream formFieldsInputStream = getAssets().open("belfortappconfig.xml");
			
			formFields = appConfigManager.getViewFieldsByViewNo(formFieldsInputStream, formno);
			
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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
	private void createTableLayout(Context context)
	{
		//TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		
		TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
		tableLayoutParams.setMargins(1, 1, 1, 1);
		//ScrollView scrollView = new ScrollView(context);
		//TableLayout tableLayout = new TableLayout(context);
		//tableLayout.setBackgroundColor(color.black);
		
		TableLayout tableLayout = (TableLayout) findViewById(R.id.main_table);
		
		Log.w("list",tableLayout.toString());
		//HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
		
		
		TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
		tableRowParams.setMargins(1,1,1,1);
		tableRowParams.weight = 1;
		
		TableRow headerRow = new TableRow(context);
		headerRow.setBackgroundColor(color.black);
	
		for(String formField: formFields)
		{
		TextView label = new TextView(context);
		label.setText(formField);
		label.setBackgroundColor(color.white);
		label.setGravity(Gravity.CENTER);
		
		headerRow.addView(label, tableRowParams);
	
		
		
		}
		tableLayout.addView(headerRow,tableLayoutParams);
		for(int i=0; i< entities.size(); i++)
		{
			TableRow tableRow = new TableRow(context);
			tableRow.setBackgroundColor(color.black);
			
			for(int j=0; j< formFields.size(); j++)
			{
				TextView textView = new TextView(context);
				textView.setText(entities.get(i).get(j));
				textView.setBackgroundColor(color.white);
				textView.setGravity(Gravity.CENTER);
				
				tableRow.addView(textView, tableRowParams);
				
				
				
			}
			
			tableLayout.addView(tableRow,tableLayoutParams);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		
		case 8888:
			
			//toast = Toast.makeText(this, "clicked on Submit", Toast.LENGTH_LONG);
			//toast.setGravity(Gravity.TOP, 25, 400);
			//toast.show();
			Intent intent = new Intent(this,CRUDActivity.class);
			intent.putExtra("formno", formno);
			startActivity(intent);
		  break;
		
		}
		
	}

}
