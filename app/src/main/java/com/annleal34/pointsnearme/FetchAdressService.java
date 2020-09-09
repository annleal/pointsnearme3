package com.annleal34.pointsnearme;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Servico para buscar os enderencos em determinadas latitudes*/

public class FetchAdressService extends JobIntentService {
    protected ResultReceiver receiver;




    public FetchAdressService ()
     {
         super();
     }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
         if (intent == null) return;


        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
        receiver = intent.getParcelableExtra(Constants.RECEIVER);

        List<Address> addresses = null;

        try {
            geocoder.getFromLocation( location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Nlocation","servico indisponivel", e);
        } catch (IllegalArgumentException e)
                {
                    Log.e("Nlocation","Latitude e Longitude Invalidos", e);
                }


        if(addresses ==null || addresses.isEmpty())
        {
            Log.e("Nlocation","Endereco nao Encontrado");
            returnReciver(Constants.FAILURE_RESULT,"Endereco nao Encontrado");
        }
        else
            {
            Address address = addresses.get(0);
            List<String> addressArray=new ArrayList<String>();

            for (int i=0; i<= address.getMaxAddressLineIndex(); i++)
                {
                    addressArray.add(address.getAddressLine(i));
                }
                returnReciver(Constants.SUCESS_RESULT, TextUtils.join("|",addressArray));
            }
    }

    private void returnReciver (int resultCode,String message)
    {
        Bundle bundle = new Bundle();
        message = TelaPrincipal.idFilter;
        bundle.putString(Constants.RESULT_DATA_KEY,message);
        receiver.send(resultCode,bundle);
    }

}
