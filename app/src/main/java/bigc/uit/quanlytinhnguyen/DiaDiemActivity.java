package bigc.uit.quanlytinhnguyen;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DiaDiem;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TenTruong;
import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.TinhNguyen1;


public class DiaDiemActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<String> dsType;
    ArrayAdapter<String> adapterType;
    String MATN, kinhDo, viDo;
    TextView txtKinhDo, txtViDo;
    float kd, vd;
    ProgressDialog progressDialog;
    DiaDiem diadiem = new DiaDiem();
    ImageButton btnThoiTiet ;

    GoogleMap.OnMyLocationChangeListener listener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(),
                    location.getLongitude());
            Marker mMarker = mMap.addMarker(new
                    MarkerOptions().position(loc));
//            if (mMap != null) {
//
//                mMap.animateCamera(
//                        CameraUpdateFactory.newLatLngZoom(loc, 12.0f));
//            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_diem);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addControls();
        addEvents();

    }

    private void addEvents() {
        btnThoiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyWeather();
            }
        });
    }

    private void xulyWeather() {
        Intent i = new Intent(DiaDiemActivity.this, WeatherActivity.class);
        i.putExtra("MATN" , MATN);
        this.startActivity(i);
    }

//    private void xuLyDoiCheDoHienThi(int i) {
//        switch (i) {
//            case 0:
//                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                break;
//            case 1:
//                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//                break;
//            case 2:
//                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//                break;
//            case 3:
//                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                break;
//            case 4:
//                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
//                break;
//        }
//    }

    private void addControls() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


        progressDialog = new ProgressDialog(DiaDiemActivity.this);
        progressDialog.setTitle("Thông Báo");
        progressDialog.setMessage("Đang tải map , vui lòng chờ trong giây lát ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        txtKinhDo = (TextView) findViewById(R.id.txtKinhDo);
        txtViDo = (TextView) findViewById(R.id.txtViDo);
        btnThoiTiet = (ImageButton) findViewById(R.id.btnXemThoiTiet);


        Intent intent = getIntent();
        MATN = intent.getStringExtra("MATN");

        String urlGetViTri = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getdiadiem.php?MATN=" + MATN;

        getViTri(urlGetViTri);


//        kinhDo = txtKinhDo.getText().toString();
//        viDo= txtViDo.getText().toString();


    }

    private void getViTri(String urlGetViTri) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, urlGetViTri, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                diadiem = new Gson().fromJson(String.valueOf(object), DiaDiem.class);
                                txtKinhDo.setText(diadiem.getKinhdo().toString());
                                txtViDo.setText(diadiem.getVido().toString());
                                kinhDo=diadiem.getKinhdo();
                                viDo=diadiem.getVido();
                                kd = Float.parseFloat(kinhDo);
                                vd  = Float.parseFloat(viDo);
                                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                                    @Override
                                    public void onMapLoaded() {
                                        progressDialog.dismiss();
                                        LatLng sydney = new LatLng(kd, vd);
                                        mMap.addMarker(new MarkerOptions().position(sydney).title("Địa Điểm Tình Nguyện").snippet("Tình Nguyện Là Tuổi Trẻ"));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DiaDiemActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
      mMap=googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        mMap.setOnMyLocationChangeListener(listener);
    }
}