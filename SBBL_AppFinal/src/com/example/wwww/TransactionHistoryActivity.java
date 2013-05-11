package com.example.wwww;

import org.ksoap2.serialization.PropertyInfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionHistoryActivity extends Activity {
	String accNum;
	String s;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_history);

		Bundle extras = getIntent().getExtras();
		if (extras == null)
			return;
		else
			accNum = extras.getString("accountNumber");
		// -----------http data retrieve-------
		final String NAMESPACE = "http://ws.userlogin.com";
		final String URL = "http://10.0.2.2:8080/SBBL_Final/services/Transaction?wsdl";
		final String SOAP_ACTION = "http://ws.userlogin.com/getBalance";
		final String METHOD_NAME = "getBalance";

		PropertyInfo accNo = new PropertyInfo();
		// TextView tv=(TextView)findViewById(R.id.accNo);
		// tv.setText("ACCOUNT NO: "+accNum);
		accNo.setName("accNum");
		accNo.setValue(accNum);

		HttpPost httpPost = new HttpPost(NAMESPACE, URL, SOAP_ACTION,
				METHOD_NAME);
		s = httpPost.postData(accNo);

		ListView listView = (ListView) findViewById(R.id.mylist);
		String[] values = s.split(",");

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);
	}

}
