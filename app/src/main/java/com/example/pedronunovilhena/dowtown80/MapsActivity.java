package com.example.pedronunovilhena.dowtown80;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public String actor;
    public int etapa_nr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_maps);
        Intent intent = getIntent();
        actor = intent.getStringExtra("actor");
        etapa_nr = intent.getIntExtra("etapa_nr",3);
        //Toast.makeText(getApplicationContext(), "Actor:"+actor+"__etapa nr:"+etapa_nr, Toast.LENGTH_SHORT).show();
        setUpMapIfNeeded();

    }

    public void showMenu( View v){
        Toast.makeText(getApplicationContext(), "Nothing to do yet.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main_Menu.class);
        startActivity(intent);
    }


    private void closeThisActivity(){
        finish();
        System.exit(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        getMissionLoc();
        getAtualLoc();
    }


    public void getMissionLoc(){
        if(actor.equalsIgnoreCase("Fixx") && (etapa_nr==1)){

            LatLng reitoria = new LatLng(41.1466882, -8.6239753); // reitoria por enquanto
            mMap.addMarker(new MarkerOptions().position(reitoria).title("Marker").snippet("Reitoria"));
        }
        if(actor.equalsIgnoreCase("Fixx") && (etapa_nr==2)){

            LatLng porto = new LatLng(41.1785734, -8.5962233); // FEUP
            mMap.addMarker(new MarkerOptions().position(porto).title("Marker").snippet("Best faculty"));
        }
        if(actor.equalsIgnoreCase("Fixx") && (etapa_nr==3)){

            LatLng porto = new LatLng(41.1608, -8.5819); // Estádio do Dragãoo
            mMap.addMarker(new MarkerOptions().position(porto).title("Best(?) Stadium"));
        }

    }

    public void onSearch(View view)
    {
        EditText location_tf = (EditText)findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if(location != null || !location.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location , 1);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude() , address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }


    public void getAtualLoc(){
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //makeUseOfNewLocation(location);
                LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Your loc.").snippet("Your Loc."));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, locationListener);

    }


    public void onZoom(View view)
    {

        if(view.getId() == R.id.Bzoomin)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());


        }
        if(view.getId() == R.id.Bzoomout)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void changeType(View view)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        getAtualLoc();
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        getAtualLoc();
        getMissionLoc();
        mMap.setMyLocationEnabled(true);

    }
}

