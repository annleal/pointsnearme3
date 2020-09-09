package com.annleal34.pointsnearme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.os.ConfigurationCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.google.android.libraries.places.api.model.Place.Type.GAS_STATION;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView tvCoordinate;
    GoogleApiClient mGoogleApiClient;
    FusedLocationProviderClient fusedLocationClient;
    AddressResultReceiver retornoReciver;
    GoogleMap mMap;
    PlacesClient placesClient;
    AutocompleteSessionToken token;
    Context context;
    String country;
    Locale locale;



    String Query = TelaPrincipal.pesquisa;

    SparseArray mErrorString;
    private final int REQUEST_PERMISSION_PHONE_STATE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvCoordinate = (TextView) findViewById(R.id.tv_coordinate);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        retornoReciver = new AddressResultReceiver(null);
        String apiKey = Constants.API_PLACES_KEY;
        Places.initialize(getApplicationContext(), apiKey);
        placesClient = Places.createClient(this);
        token = AutocompleteSessionToken.newInstance();
       locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);


        mErrorString = new SparseArray();



            }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(20.0f);


        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


    }


    @SuppressLint("NewApi")
    protected void onResume() {
        super.onResume();




        /**verificando se Play Services está Atualizado Inicio*/
        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        switch (errorCode) {
            case ConnectionResult.SERVICE_MISSING:
                ;
            case ConnectionResult.SERVICE_DISABLED:
                ;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                ;
                Log.d("errorCode", "show dialog");
                GoogleApiAvailability.getInstance().getErrorDialog(this, errorCode, 0,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                finish();
                            }
                        }).show();
                break;

            case ConnectionResult.SUCCESS:
                Log.d("errorCode", "Google Play Services Update esta Atualizado");
                break;

        }
        /**verificando se Play Services está Atualizado Fim*/

        /**Buscar a Primeira localização Conhecida Incio*/

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            /*return;*/

                if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION))
                {
                    Toast.makeText(this,"Para o correto funcionamento você deve dar as permissão de Localização do seu telefone.",Toast.LENGTH_SHORT).show();
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION},REQUEST_PERMISSION_PHONE_STATE);
            }


        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            Log.i("location", "Localização " + location.getLatitude() + " " + location.getLongitude());


                            // Add a marker in Sydney and move the camera
                            LatLng origem = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(origem).title("I'm Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origem, 15));
                            mMap.getUiSettings().setZoomControlsEnabled(true);
                            mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                                @Override
                                public void onCameraIdle() {
                                    FindAutocompletePredictionsRequest predictionsRequest = FindAutocompletePredictionsRequest.builder()
                                            /*.setCountry("BR")*/
                                            .setTypeFilter(TypeFilter.ESTABLISHMENT)
                                            .setSessionToken(token)
                                            .setLocationRestriction(RectangularBounds.newInstance(
                                                    mMap.getProjection().getVisibleRegion().latLngBounds
                                            ))
                                            .setQuery(Query)
                                            .build();

                                    placesClient.findAutocompletePredictions(predictionsRequest)
                                            .addOnCompleteListener(new OnCompleteListener<FindAutocompletePredictionsResponse>() {
                                                @Override
                                                public void onComplete(@NonNull Task<FindAutocompletePredictionsResponse> task) {
                                                    if (task.isSuccessful()) {
                                                        FindAutocompletePredictionsResponse result = task.getResult();
                                                        if (result != null) {
                                                            List<AutocompletePrediction> predictions = result.getAutocompletePredictions();
                                                            for (AutocompletePrediction p : predictions) {
                                                              /*
                                                                List<Place.Type> placeType =p.getPlaceTypes();
                                                                for (Place.Type type : placeType)
                                                                {
                                                                    Log.i("task","type " + type.name());
                                                                }

                                                               */
                                                                Log.i("task", p.getPlaceId());
                                                                Log.i("task", p.getFullText(null).toString());

                                                                final List<Place.Field> field = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG,Place.Field.ADDRESS,Place.Field.PHONE_NUMBER);
                                                                FetchPlaceRequest request = FetchPlaceRequest.builder(p.getPlaceId(), field)
                                                                        .setSessionToken(token)
                                                                        .build();
                                                                placesClient.fetchPlace(request)
                                                                        .addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
                                                                            @Override
                                                                            public void onSuccess(FetchPlaceResponse response) {
                                                                                Place place = response.getPlace();
                                                                                LatLng latLng = place.getLatLng();
                                                                                mMap.addMarker(new MarkerOptions().position(latLng).title(place.getName()));
                                                                                Log.i("Lugar" ,place.getName());
                                                                              /*  textView.append("Place Found:" + place.getName());
                                                                                textView.append("Place Found:" + place.getPhoneNumber());*/

                                                                            }
                                                                        });


                                                            }
                                                        }

                                                    } else {
                                                        Log.i("task", task.getException().getMessage());


                                                    }
                                                }


                                            });
                                }
                            });


                            if (!Geocoder.isPresent()) {
                                Log.i("location", "Gecoder Indisponivel");
                                return;
                            }
                        } else {
                            Log.i("location", "Localização  nao foi encotrada");

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });
        /**Buscar a Primeira localização Conhecida Fim*/

        /**Intervalo de Busca de Posicao e uso de bateria Incio*/
        final LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(15 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        /**Intervalo de Busca de Posicao e uso de bateria Fim*/

        /**Checando posição e atualuzando Incio*/
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        /**Checando WIFI para performace se Fail usuario resolve Incio*/
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(builder.build())
                .addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i("WFI", String.valueOf
                                (locationSettingsResponse.getLocationSettingsStates().isNetworkLocationPresent()));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ResolvableApiException) {
                            ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                            try {
                                resolvableApiException.startResolutionForResult(MainActivity.this, 10);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });

        /**Checando WIFI para performace se Fail usuario resolve Fim*/

        /**Update Localizacao incio*/

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.i("Upd", "localizaco nao encontrada");
                    return;

                }

                for (Location location : locationResult.getLocations()) {
                    Log.i("Upd", "localizaco " + location.getLatitude() + " " + location.getLongitude());

                    if (!Geocoder.isPresent()) {
                        return;
                    }
                    // startIntentService(location);
                }

            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                Log.i("Upd", locationAvailability.isLocationAvailable() + " ");
            }

        };
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        /**Update Localizacao Fim*/

        /**Checando posição e atualuzando Fim*/
    }

    private void startIntentService(Location location) {
        Intent intent = new Intent(this, FetchAdressService.class);
        intent.putExtra(Constants.RECEIVER, retornoReciver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }


    private class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         * Inicio da Devolucao do FetchAdress
         */


        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null) return;
            final String addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);

            if (addressOutput != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, addressOutput, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }
    /**Fim da Devolucao do FetchAdress*/




}

