package com.designthepresent.samuvelp.loctoctask;

import android.app.ProgressDialog;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public  String tempKey ="oVQST5D6NT";
    public String permKey;
    private ApiServiceTemp mApiserviceTemp;
    private ApiServicePerm mApiservicePerm;
    private TextView mResponseTv;
    private String[] latitute= new String[2];
    private String[] longitude = new String[2];
    private static double startLat=0,startLong=0,endLat=0,endLong=0;
     ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BTN_calculate = (Button) findViewById(R.id.UI_BTN_calculate);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        mApiserviceTemp = ApiUtilsTemp.getAPIService();
        mApiservicePerm = ApiUtilsPerm.getAPIService();

        BTN_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading..");
                progressDialog.show();

                PostTemp postTemp = new PostTemp(tempKey);

                sendPost(postTemp);

                //double res =distance(latitute,longitude);
                //Toast.makeText(MainActivity.this, Double.toString(res), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void postPermKey(PostPerm postPerm, final int i) {
        mApiservicePerm.postPermKey(postPerm).enqueue(new Callback<PostPerm>() {
            @Override
            public void onResponse(Call<PostPerm> call, Response<PostPerm> response) {
                if(response.isSuccessful()){
                    latitute[i]=response.body().getLatitude();
                    longitude[i]=response.body().getLongitude();

                   if(i==0){
                       startLat = Double.parseDouble(latitute[0]);
                       startLong = Double.parseDouble(longitude[0]);
                   }
                    if(i==1){
                        endLat = Double.parseDouble(latitute[1]);
                        endLong = Double.parseDouble(longitude[1]);
                       // MainActivity mainActivity = new MainActivity();
                         double res =distance();
                        int finalRes = (int) Math.round(res);
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, Integer.toString(finalRes)+" Km", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<PostPerm> call, Throwable t) {

            }
        });

    }


    private void sendPost(PostTemp postTemp) {


        mApiserviceTemp.savePost(postTemp).enqueue(new Callback<PostTemp>() {
            @Override
            public void onResponse(Call<PostTemp> call, Response<PostTemp> response) {

                if(response.isSuccessful()) {
                   // Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    permKey = response.body().getPermKey();
                    showResponse(permKey);
                    //getting lat and lon using permKey
                    int i=0;
                    while(i<2) {
                        PostPerm postPerm = new PostPerm(permKey);
                        postPermKey(postPerm,i);
                        i++;
                    }


                }
                else Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PostTemp> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }


    public static double distance() {
        //double startLat = Double.parseDouble(lat[0]);
        //double startLong =Double.parseDouble(lon[0]);
       // double endLat=Double.parseDouble(lat[1]);
        //double endLong=Double.parseDouble(lon[1]);
        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- distance

    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }


}
