package com.example.wwww;

import org.ksoap2.serialization.PropertyInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BalanceActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);
       
        
        String b=getBalance();
        
       // result.setText("sdfa");
        TextView balance=(TextView) findViewById(R.id.balanceField);
        balance.setText("Your current account balance is: "+b);
    }
	public String getBalance(){
		String accNum=null;
		Bundle extras=getIntent().getExtras();
		if(extras==null)return null;
		else accNum=extras.getString("accountNumber");
		String b=null;
		 final String NAMESPACE = "http://ws.userlogin.com";
		 final String URL = "http://10.0.2.2:8080/SBBL_Final/services/checkBalance?wsdl";
		 final String SOAP_ACTION = "http://ws.userlogin.com/getBalance";
		 final String METHOD_NAME = "getBalance";
		 
		 PropertyInfo accNo=new PropertyInfo();
		 accNo.setName("accNum");
		 accNo.setValue(accNum);
		 
		 HttpPost httpPost=new HttpPost(NAMESPACE, URL, SOAP_ACTION, METHOD_NAME);
		 b=httpPost.postData(accNo);
		 
		return b;
	}

}
