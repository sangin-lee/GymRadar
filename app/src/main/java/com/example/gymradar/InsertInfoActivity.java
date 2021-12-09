package com.example.gymradar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapPoint;

public class InsertInfoActivity extends AppCompatActivity implements MapReverseGeoCoder.ReverseGeoCodingResultListener {

    private MapReverseGeoCoder geoCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_info);
        Intent intent = getIntent();
        double latitude = intent.getExtras().getDouble("latitude");
        double longitude = intent.getExtras().getDouble("longitude");

        ((TextView) findViewById(R.id.insert_latitude)).setText(Double.toString(latitude));
        ((TextView) findViewById(R.id.insert_longitude)).setText(Double.toString(longitude));

        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        Log.d("latitude", Double.toString(mapPoint.getMapPointGeoCoord().latitude));
        Log.d("longitude", Double.toString(mapPoint.getMapPointGeoCoord().longitude));

        geoCoder = new MapReverseGeoCoder("2971a5084716a57c9ee71443b72bc20b", mapPoint,
                InsertInfoActivity.this, InsertInfoActivity.this);
        geoCoder.startFindingAddress();

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        onFinishReverseGeoCoding(s);
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {
        onFinishReverseGeoCoding("Fail");
    }

    private void onFinishReverseGeoCoding(String result) {
        ((TextView) findViewById(R.id.insert_address)).setText(result);
    }
}