package com.example.wwww;

import org.ksoap2.serialization.PropertyInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePinActivity extends Activity{
	String accNum,userName;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin);
        Bundle extras=getIntent().getExtras();
        if(extras==null)return;
        else{
        	accNum=extras.getString("accountNumber");
        	userName=extras.getString("userName");
        }
        Button b=(Button)findViewById(R.id.change_password);
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				changePin(accNum);
			}
		});
       
	}
	private void changePin(String accNum2) {
		// TODO Auto-generated method stub
		TextView isPasswordChanged=(TextView)findViewById(R.id.is_password_changed);
		EditText oldPassword=(EditText)findViewById(R.id.old_password);
		EditText newPassword=(EditText)findViewById(R.id.new_password);
		EditText confirmPassword=(EditText)findViewById(R.id.confirm_password);
		String newPass=newPassword.getText().toString();
		String conPass=confirmPassword.getText().toString();
		String oldPass=oldPassword.getText().toString();
		
		if(!newPass.equals(conPass)){
			isPasswordChanged.setText("comfirm Password mitchmatched");
			
		}
		else{
			 final String NAMESPACE = "http://ws.userlogin.com";
			 final String URL = "http://10.0.2.2:8080/SBBL_Final/services/UpdatePassword?wsdl";
			 final String SOAP_ACTION = "http://ws.userlogin.com/updatePassword";
			 final String METHOD_NAME = "updatePassword";
			HttpPost httpPost=new HttpPost(NAMESPACE, URL, SOAP_ACTION, METHOD_NAME);
			 
			PropertyInfo user=new PropertyInfo();
			user.setName("userName");
			user.setValue(userName);
			PropertyInfo pass=new PropertyInfo();
			pass.setName("password");
			pass.setValue(conPass);
			
			PropertyInfo odPassword=new PropertyInfo();
			odPassword.setName("oldPassword");
			odPassword.setValue(oldPass);
			
			String i=httpPost.postData(user,pass,odPassword);
			if(i.equals("true"))isPasswordChanged.setText("Password changed successfully");
			else isPasswordChanged.setText("Password did not changed");
			
			
		}
		
		
	}

}
