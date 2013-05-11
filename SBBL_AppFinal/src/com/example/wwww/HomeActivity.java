package com.example.wwww;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends Activity{
	TextView balance,transaction_history,pin,logout,welcome,transaction;
	String accNum,userName;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        String accountNumber;
        welcome = (TextView) findViewById(R.id.welcome);
        final Bundle extras=getIntent().getExtras();
        if(extras==null)return;
        else {
        	userName=extras.getString("userName");
        	accountNumber=extras.getString("accountNumber");
        	welcome.setText("welcome "+userName +"\n Account no:"+accountNumber);
        }
        accNum=extras.getString("accountNumber");
        balance = (TextView) findViewById(R.id.balance);
        transaction_history = (TextView) findViewById(R.id.transaction_history);
        transaction=(TextView)findViewById(R.id.new_transaction);
        pin = (TextView) findViewById(R.id.pin);
        logout = (TextView) findViewById(R.id.logout);
        
       // result.setText("sdfa");
       
        balance.setOnClickListener(new View.OnClickListener() {
    
		   public void onClick(View arg0) {
		    balance(accNum);
		     
		   }
		  });
        
        // result.setText("sdfa");
        transaction_history.setOnClickListener(new View.OnClickListener() {
     
 		   public void onClick(View arg0) {
 		    
 		     transactionHistory(accNum);
 		   }
 		  });
         
         // result.setText("sdfa");
          pin.setOnClickListener(new View.OnClickListener() {
      
  		   public void onClick(View arg0) {
  			   pinChange();
  		     
  		   }
  		  });
         
          // result.setText("sdfa");
           logout.setOnClickListener(new View.OnClickListener() {
       
   		   public void onClick(View arg0) {
   		   
   		     logout();
   		   }
   		  });
           transaction.setOnClickListener(new View.OnClickListener() {
               
       		   public void onClick(View arg0) {
       		   
       		        makeNewTransaction(accNum);
       		   }

			
       		  });
    }
	public void makeNewTransaction(String accNum) {
		// TODO Auto-generated method stub
		Intent i=new Intent(this,TransactionActivity.class);
		i.putExtra("userName", userName);
		i.putExtra("accountNumber", accNum);
		startActivity(i);
		
	}
	public void balance(String accNum){
		
		Intent i=new Intent(this,BalanceActivity.class);
		i.putExtra("accountNumber", accNum);
		startActivity(i);
	}
	public void transactionHistory(String accNum){
		Intent i=new Intent(this,TransactionHistoryActivity.class);
		i.putExtra("accountNumber", accNum);
		startActivity(i);
		
	}
	public void pinChange(){
		Intent i=new Intent(this,ChangePinActivity.class);
		i.putExtra("userName",userName);
		startActivity(i);
		
	}
	public void logout(){
		Intent i=new Intent(this,SBBL_AppFinalActivity.class);
		startActivity(i);
		
	}
     

}
