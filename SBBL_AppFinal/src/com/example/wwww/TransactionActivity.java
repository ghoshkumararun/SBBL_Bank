package com.example.wwww;

import org.ksoap2.serialization.PropertyInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TransactionActivity extends Activity {
	String userName,accNum;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);
	    Button b=(Button)findViewById(R.id.transfer);
	    
	    Bundle extras=getIntent().getExtras();
	    if(extras==null)return;
	    else {
	    	userName=extras.getString("userName");
	    	accNum=extras.getString("accountNumber");
	    }
	    b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doTransfer();
				
			}
		});
	
	}
	protected void doTransfer() {
		// TODO Auto-generated method stub
		 final String NAMESPACE = "http://ws.userlogin.com";
		 final String URL = "http://10.0.2.2:8080/SBBL_Final/services/MakeTransaction?wsdl";
		 final String SOAP_ACTION = "http://ws.userlogin.com/make";
		 final String METHOD_NAME = "make";
		 String toAcc=((EditText)findViewById(R.id.toAccount)).getText().toString();
		 String amount=((EditText)findViewById(R.id.amount)).getText().toString();
		 
		 TextView tv=(TextView)findViewById(R.id.transfer_status);
		 
		HttpPost httpPost=new HttpPost(NAMESPACE, URL, SOAP_ACTION, METHOD_NAME);
		PropertyInfo to=new PropertyInfo();
		to.setName("toAccNum");
		to.setValue(toAcc);
		
		PropertyInfo from=new PropertyInfo();
		from.setName("fromAccNum");
		from.setValue(accNum);
		
		PropertyInfo amnt=new PropertyInfo();
		amnt.setName("amount");
		amnt.setValue(Integer.parseInt(amount));
		String s=httpPost.postData(to,from,amnt);
		if(s.equals("true")){
			tv.setText("Successfully transferred");
		}
		else{
			tv.setText(" "+s);
		}
		
	}

}
