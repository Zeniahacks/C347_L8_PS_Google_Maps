package sg.edu.rp.c346.id19023702.c347_l8_ps_google_maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner sp;
    ArrayAdapter<String> aa;
    ArrayList<String> areas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });


                LatLng poi_orchard = new LatLng(1.3039326, 103.8297309);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_orchard,
                        15));
                Marker orchard = map.addMarker(new
                        MarkerOptions()

                        .position(poi_orchard)
                        .title("HQ Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_admiralty = new LatLng(1.4565088, 103.7973508);
                Marker rp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_admiralty)
                        .title("HQ North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_round)));


                LatLng poi_tampines = new LatLng(1.3526609, 103.9430563);
                Marker tampines = map.addMarker(new
                        MarkerOptions()

                        .position(poi_tampines)
                        .title("HQ East")
                        .snippet("Block 555, Tampines Ave 3, 287788 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

            }
        });


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        sp = (Spinner) findViewById(R.id.spinner);

        areas = new ArrayList<String>();
        areas.add(new String("NORTH"));
        areas.add(new String("CENTRAL"));
        areas.add(new String("EAST"));

        final LatLng poi_admiralty = new LatLng(1.4565088, 103.7973508);
        final LatLng poi_orchard = new LatLng(1.3039326, 103.8297309);
        final LatLng poi_tampines = new LatLng(1.3526609, 103.9430563);


        aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, areas);
        sp.setAdapter(aa);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_orchard = new LatLng(1.3039326, 103.8297309);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_orchard,
                            15));
                }
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker arg0) {
                        // TODO Auto-generated method stub

                        Toast.makeText(getApplicationContext(), "HQ Central", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_admiralty = new LatLng(1.4565088, 103.7973508);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_admiralty,
                            15));
                }
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker arg0) {
                        // TODO Auto-generated method stub

                        Toast.makeText(getApplicationContext(), "HQ North", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng poi_tampines = new LatLng(1.3526609, 103.9430563);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_tampines,
                            15));
                }
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker arg0) {
                        // TODO Auto-generated method stub

                        Toast.makeText(getApplicationContext(), "HQ East", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String getArea = sp.getItemAtPosition(position).toString();
                if (getArea.equals("NORTH") && map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_admiralty, 15));
                } else if (getArea.equals("CENTRAL") && map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_orchard, 15));
                } else if (getArea.equals("EAST") && map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_tampines, 15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }
}