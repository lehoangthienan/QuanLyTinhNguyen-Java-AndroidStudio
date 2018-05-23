package bigc.uit.quanlytinhnguyen;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model.DiaDiem;

public class WeatherActivity extends AppCompatActivity {

    TextView txtTenTP , txtTenQG , txtGio ,txtDoAm , txtMay , txtNgayCapNhap , txtNhietDo , txtTrangThai;
    ImageView imgIcon ;
    String MATN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addControls();
        addEvents();
    }

    private void addEvents() {
        String urlGetViTri = "http://quanlyhoatdongtinhnguyen.000webhostapp.com/getdiadiem.php?MATN=" + MATN;
        getViTri(urlGetViTri);
    }

    private void addControls() {
        txtTenTP = (TextView) findViewById(R.id.txtTenTP);
        txtTenQG = (TextView) findViewById(R.id.txtTenQG);
        txtGio = (TextView) findViewById(R.id.txtGio);
        txtDoAm = (TextView) findViewById(R.id.txtDoAm);
        txtMay = (TextView) findViewById(R.id.txtMay);
        txtNgayCapNhap = (TextView) findViewById(R.id.txtNgayCapNhap);
        txtNhietDo = (TextView) findViewById(R.id.txtNhietDo);
        txtTrangThai = (TextView) findViewById(R.id.txtTrangThai);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);

        Intent intent = getIntent();
        MATN = intent.getStringExtra("MATN");


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
                                DiaDiem diadiem = new Gson().fromJson(String.valueOf(object), DiaDiem.class);
                                String kinhDo=diadiem.getKinhdo();
                                String viDo=diadiem.getVido();
                                String url="http://api.openweathermap.org/data/2.5/weather?lat="+kinhDo+"&lon="+viDo+"&units=metric&appid=b84fdc1366fe2246d3f71562a38f6578";
                                {
                                    RequestQueue requestQueue1 = Volley.newRequestQueue(WeatherActivity.this);
                                    StringRequest stringRequest1 = new StringRequest(
                                            Request.Method.GET,
                                            url,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(response);
                                                        String day = jsonObject.getString("dt");
                                                        String name = jsonObject.getString("name");
                                                        txtTenTP.setText("Thành Phố : "+name);
                                                        long l = Long.valueOf(day);
                                                        Date date = new Date(l*1000);
                                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                                                        String Day = simpleDateFormat.format(date);
                                                        txtNgayCapNhap.setText(Day);

                                                        JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                                                        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                                                        String trangThai = jsonObjectWeather.getString("main");
                                                        String icon = jsonObjectWeather.getString("icon");
                                                        Picasso .with(WeatherActivity.this).load("http://openweathermap.org/img/w/"+icon+".png").into(imgIcon);
                                                        txtTrangThai.setText(trangThai);

                                                        JSONObject jsonObjectMain =jsonObject.getJSONObject("main");
                                                        String nhietDo = jsonObjectMain.getString("temp");
                                                        String doAm = jsonObjectMain.getString("humidity");
                                                        Double a = Double.valueOf(nhietDo);
                                                        String NhietDo = String.valueOf(a.intValue());
                                                        txtNhietDo.setText(NhietDo +" Độ C");
                                                        txtDoAm.setText(doAm +"%");

                                                        JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                                                        String gio = jsonObjectWind.getString("speed");
                                                        txtGio.setText(gio+"m/s");

                                                        JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                                                        String may = jsonObjectCloud.getString("all");
                                                        txtMay.setText(may+"%");

                                                        JSONObject jsonObjectsys = jsonObject.getJSONObject("sys");
                                                        String qg = jsonObjectsys.getString("country");
                                                        txtTenQG.setText("Quốc Gia : "+qg);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            }
                                    );
                                    requestQueue1.add(stringRequest1);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(WeatherActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
