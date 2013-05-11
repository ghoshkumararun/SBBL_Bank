package com.example.wwww;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class HttpPost {
	private final String NAMESPACE ;
    private final String URL;
    private final String SOAP_ACTION;
    private final String METHOD_NAME ; 
    private SoapObject request;
    String s ;
    
    public HttpPost(String NAMESPACE,String URL,String SOAP_ACTION,String METHOD_NAME){
    	
    	this.NAMESPACE=NAMESPACE;
    	this.URL=URL;
    	this.SOAP_ACTION=SOAP_ACTION;
    	this.METHOD_NAME=METHOD_NAME;
    }
    
    public String postData(final PropertyInfo... props){
    	Thread t=new Thread(new Runnable() {
    	 
    	
			
			@Override
			public void run() {
				request = new SoapObject(NAMESPACE, METHOD_NAME);
		    	for(PropertyInfo i:props){
		    		request.addProperty(i);
		    	}
				// TODO Auto-generated method stub
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		        envelope.setOutputSoapObject(request);
		        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		        
		         
		        try{
		            androidHttpTransport.call(SOAP_ACTION, envelope);
		            SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
		                 
		               
		               s = response.toString();
		               
		              
		              // Log.d("sayem","das"+ response.toString());
		           
		        }
		        catch(Exception e){
		        	
		        	//result.setText("error");
		        }
				
			}
		});
    	t.start();
    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return s;
	
    }

}
