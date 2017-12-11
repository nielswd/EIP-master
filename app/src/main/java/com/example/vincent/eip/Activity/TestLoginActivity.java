package com.example.vincent.eip.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.vincent.eip.R;

/**
 * Created by iNfecteD on 12/04/2017.
 */

public class TestLoginActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_USE_CAMERA = 42;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        TextView smartText = (TextView) findViewById(R.id.smart_text);
        TextView groomText = (TextView) findViewById(R.id.groom_text);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/adam_cg_pro.ttf");
        smartText.setTypeface(typeFace);
        groomText.setTypeface(typeFace);

        FloatingActionButton writeLogin = (FloatingActionButton) findViewById(R.id.image_first);
        writeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLogin = new Intent(TestLoginActivity.this, LoginActivity.class);
                startActivity(goToLogin);
                finish();
            }
        });

        FloatingActionButton qrCode = (FloatingActionButton) findViewById(R.id.image_first2);
        qrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCameraPermissions();
            }
        });
    }
        public void checkCameraPermissions() {
        if (ContextCompat.checkSelfPermission(TestLoginActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(TestLoginActivity.this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(TestLoginActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_USE_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            goToDecoderScan();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_USE_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    goToDecoderScan();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void goToDecoderScan(){
        Intent goToQRCodeDecoder = new Intent(TestLoginActivity.this, DecoderActivity.class);
        startActivity(goToQRCodeDecoder);
        finish();
    }
}
