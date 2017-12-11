package com.example.vincent.eip.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 12/07/2017.
 */

public class RequestAServiceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        String s = getIntent().getStringExtra("SECTOR_ID");
    }
}
