package com.logicshore.ksoapservices;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {
    private static String NAMESPACE = "http://183.82.9.6:8080/";


    private static String METHOD_NAME = "getIDProofMaster";
    private static String SOAP_ACTION = NAMESPACE+METHOD_NAME;
    private static String URL = "http://183.82.9.6:8080/HydPettyCaseService/services/PettyCaseServiceImpl?wsdl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


new Details().execute();

        // Get the SoapResult from the envelope body.








    }

    private class Details extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids) {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//            request.addProperty("pidCd", ET_userID);
//            request.addProperty("password", ET_password);
//            request.addProperty("IMEI", IMEI);
//            request.addProperty("sim_No", sim_No);
            //Use this to add parameters
            //request.addProperty("Parameter","Value");

            //Declare the version of the SOAP request
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            //Needed to make the internet call
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                //this is the actual part that will call the webservice
                androidHttpTransport.call(SOAP_ACTION, envelope);
                Object result =envelope.getResponse();
                Log.d("response",result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
