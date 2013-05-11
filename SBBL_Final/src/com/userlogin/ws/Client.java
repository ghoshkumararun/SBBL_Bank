package com.userlogin.ws;
import com.userlogin.ws.LoginStub.Authentication;

public class Client {
 public static void main(String[] args) throws Exception {
     LoginStub stub = new LoginStub();
    
     //Creating the request
     com.userlogin.ws.LoginStub.Authentication  request = new  com.userlogin.ws.LoginStub.Authentication();
     request.setUserName("sayem");
     request.setPassword("3");
   
     //Invoking the service
     com.userlogin.ws.LoginStub.AuthenticationResponse response  = stub.authentication(request);
     System.out.println("Response : " + response.get_return());
     
     /*
     CheckBalanceStub stub2 = new CheckBalanceStub();
     
     //Creating the request
     com.userlogin.ws.CheckBalanceStub.GetBalance  request2 = new  com.userlogin.ws.CheckBalanceStub.GetBalance();
     request2.setAccNum("abc-123-001");
   
     //Invoking the service
     com.userlogin.ws.CheckBalanceStub.GetBalanceResponse response2  = stub2.getBalance(request2);
     System.out.println("Response : " + response2.get_return());
     
     
     TransactionStub ts = new TransactionStub();
     
     com.userlogin.ws.TransactionStub.GetBalance req = new com.userlogin.ws.TransactionStub.GetBalance();
     req.setAccNum("abc-123-001");
     
     com.userlogin.ws.TransactionStub.GetBalanceResponse res = ts.getBalance(req);
     System.out.println("Response : " + res.get_return());
     
     UpdatePasswordStub ups = new UpdatePasswordStub();
     com.userlogin.ws.UpdatePasswordStub.UpdatePassword req2 = new com.userlogin.ws.UpdatePasswordStub.UpdatePassword();
     req2.setUserName("salman");
     
     com.userlogin.ws.UpdatePasswordStub.UpdatePasswordResponse res2 = ups.updatePassword(req2);
     System.out.print("Response : " + res2.get_return());
     
     ExchangeRateStub ecs = new ExchangeRateStub();
     ExchangeRateStub.GetExchangeRate ecsreq = new ExchangeRateStub.GetExchangeRate();
     
     ExchangeRateStub.GetExchangeRateResponse ecsres = ecs.getExchangeRate(ecsreq);
     System.out.println("Response : " + ecsres.get_return());
     
     */
     
     
    }
}
