package com.example.wwww;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SBBL_AppFinalActivity extends Activity {
	/** Called when the activity is first created. */
	private final String NAMESPACE = "http://ws.userlogin.com";
	private final String URL = "http://10.0.2.2:8080/SBBL_Final/services/Login?wsdl";
	private final String SOAP_ACTION = "http://ws.userlogin.com/authentication";
	private final String METHOD_NAME = "authentication";
	TextView result;
	String user_Name;

	String s="check";
	EditText userName,userPassword;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		userName = (EditText) findViewById(R.id.editText1);
		userName.setText("");
		
	    userPassword = (EditText) findViewById(R.id.editText2);
	    userPassword.setText("");
	     
		Button login = (Button) findViewById(R.id.button1);
		result = (TextView) findViewById(R.id.v1);
		// result.setText("sdfa");
		login.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				loginAction();

			}
		});
	}

	private void loginAction() {

		
		user_Name = userName.getText().toString();
		
		String user_Password = userPassword.getText().toString();

		// Pass value for userName variable of the web service
		PropertyInfo unameProp = new PropertyInfo();
		unameProp.setName("userName");// Define the variable name in the web
										// service method
		unameProp.setValue(user_Name);// set value for userName variable
		unameProp.setType(String.class);// Define the type of the variable

		// Pass value for Password variable of the web service
		PropertyInfo passwordProp = new PropertyInfo();
		passwordProp.setName("password");
		passwordProp.setValue(user_Password);
		passwordProp.setType(String.class);

		// /---------posting data-----------
		HttpPost httpPost = new HttpPost(NAMESPACE, URL, SOAP_ACTION,
				METHOD_NAME);
		s=httpPost.postData(unameProp,passwordProp);
		String []strArr;
		strArr=s.split(",");

		Log.d(s, "siam");
		if (!s.equals("false")) {
			Log.d("here", "siam");
			Intent i = new Intent(this, HomeActivity.class);
			i.putExtra("userName", strArr[0]);
			i.putExtra("accountNumber", strArr[1]);
			startActivity(i);
		}
		else{
			result.setText("Incorrect userName or Password");
			userName.setText("");
			userPassword.setText("");
		}

	}

}