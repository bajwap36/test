package com.example.briskdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import android.os.Bundle;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager() . findFragmentById(R.id.map);
        smf.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;

       // LatLng maharashtra = new LatLng(19.169257 , 73.341601);
        //LatLng maharashtra = new LatLng();
        LatLng AandW = new LatLng(49.15720004535516, -122.7792510947267);
        LatLng BeerCreek = new LatLng(49.15898943056099, -122.84081529405523);
        LatLng BellaItalia = new LatLng(49.26182771809949, -123.09793758426957);
        LatLng ChaTime = new LatLng(49.18927384689662, -122.84640100147121);
        LatLng Chipotle = new LatLng(49.1887774563446, -122.80218625728963);
        LatLng DairyQueen = new LatLng(49.16873522166418, -122.80093411496293);
        LatLng DonairDude = new LatLng(49.176312983148044, -122.86698340147166);
        LatLng FreshSlice = new LatLng(49.189170331065405, -122.84751317448918);
        LatLng hyackSushi = new LatLng(49.20253679128073, -122.9125874014708);
        LatLng IceAndSpice = new LatLng(49.15834339005957, -122.85758950147213);
        LatLng Jaipurindian = new LatLng(49.16409279684822, -122.88962529961782);
        LatLng MacDonald = new LatLng(49.159478387004725, -122.88982284379945);
        LatLng Nandos = new LatLng(49.13653334875055, -122.88909854380019);
        LatLng Panago = new LatLng(49.136476468365515, -122.88973043030911);
        LatLng PinceOfSpice = new LatLng(49.160922879127625, -122.89094347078158);
        LatLng Pizza85 = new LatLng(49.158302655657124, -122.85757707263572);
        LatLng Seven11 = new LatLng(49.133103258296025, -122.846057514964);
        LatLng Subway = new LatLng(49.13405455541972, -122.85531651496393);
        LatLng TimHorton = new LatLng(49.13506403135762, -122.84516898798181);
        LatLng UncleFatih = new LatLng(49.18973313407431, -122.8478564437985);
        LatLng WhiteSpot = new LatLng(49.1872073123566, -122.80132444379866);
        LatLng Zorbas = new LatLng(49.1529669244347, -122.87934878612693);



        map.addMarker(new MarkerOptions().position(AandW).title("A and W"));
        map.addMarker(new MarkerOptions().position(BeerCreek).title("Beer Creek"));
        map.addMarker(new MarkerOptions().position(BellaItalia).title("Bella Italia"));
        map.addMarker(new MarkerOptions().position(ChaTime).title("Cha Time"));
        map.addMarker(new MarkerOptions().position(Chipotle).title("Chipotle"));
        map.addMarker(new MarkerOptions().position(DairyQueen).title("Dairy Queen"));
        map.addMarker(new MarkerOptions().position(DonairDude).title("Donair Dude"));
        map.addMarker(new MarkerOptions().position(FreshSlice).title("Fresh Slice"));
        map.addMarker(new MarkerOptions().position(hyackSushi).title("Hyack Sushi"));
        map.addMarker(new MarkerOptions().position(IceAndSpice).title("Ice And Spice"));
        map.addMarker(new MarkerOptions().position(Jaipurindian).title("Jaipur Indian"));
        map.addMarker(new MarkerOptions().position(MacDonald).title("MacDonald"));
        map.addMarker(new MarkerOptions().position(Nandos).title("Nandos"));
        map.addMarker(new MarkerOptions().position(Panago).title("Panago"));
        map.addMarker(new MarkerOptions().position(PinceOfSpice).title("Pince Of Spice"));
        map.addMarker(new MarkerOptions().position(Pizza85).title("Pizza85"));
        map.addMarker(new MarkerOptions().position(Seven11).title("Seven11"));
        map.addMarker(new MarkerOptions().position(Subway).title("Subway"));
        map.addMarker(new MarkerOptions().position(TimHorton).title("TimHorton"));
        map.addMarker(new MarkerOptions().position(UncleFatih).title("UncleFatih"));
        map.addMarker(new MarkerOptions().position(WhiteSpot).title("WhiteSpot"));
        map.addMarker(new MarkerOptions().position(Zorbas).title("Zorbas"));



        map.moveCamera(CameraUpdateFactory.newLatLng(IceAndSpice));
       // map.getMaxZoomLevel();
        map.setMinZoomPreference(11.43f);
     //  map.animateCamera(CameraUpdate.class);

    }
}