package com.designthepresent.samuvelp.loctoctask;

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
    //public  String tempKey ="oVQST5D6NT";
    private ApiServiceTemp mApiserviceTemp;
    private TextView mResponseTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BTN_calculate = (Button) findViewById(R.id.UI_BTN_calculate);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        mApiserviceTemp = ApiUtilsTemp.getAPIService();
        BTN_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* double lat1,long1,lat2,long2;

                lat1 =0;
                long1 =0 ;
                lat2=0;
                long2 =0;
                double res = distance(lat1,long1,lat2,long2);*/


               // Toast.makeText(MainActivity.this,Double.toString(res),Toast.LENGTH_LONG).show();
                sendPost("oVQST5D6NT");
            }
        });
    }

    private void sendPost(String tempKey) {
        mApiserviceTemp.savePost(tempKey).enqueue(new Callback<PostTemp>() {
            @Override
            public void onResponse(Call<PostTemp> call, Response<PostTemp> response) {
                if(response.isSuccessful()) {
                    showResponse(response.body().getTempKey());

                }

            }

            @Override
            public void onFailure(Call<PostTemp> call, Throwable t) {

            }
        });
    }

    private void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }


    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

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
