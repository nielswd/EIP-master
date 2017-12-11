package com.example.vincent.eip.Activity;

import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.example.vincent.eip.Interfaces.LoginCallback;
import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.R;
import com.google.zxing.aztec.decoder.Decoder;

import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

/**
 * Created by iNfecteD on 12/04/2017.
 */

public class DecoderActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    private ProgressBar progressBar;
    private QRCodeReaderView qrCodeReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decoder_layout);

        TextView smartText = (TextView) findViewById(R.id.smart_text);
        TextView groomText = (TextView) findViewById(R.id.groom_text);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/adam_cg_pro.ttf");
        smartText.setTypeface(typeFace);
        groomText.setTypeface(typeFace);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        qrCodeReaderView.setQRDecodingEnabled(true);

        qrCodeReaderView.setAutofocusInterval(2000L);

        qrCodeReaderView.setTorchEnabled(true);

        qrCodeReaderView.setFrontCamera();

        qrCodeReaderView.setBackCamera();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrCodeReaderView.stopCamera();
        if (text != null){
            String[] credentials = text.split("[\\r\\n]+");
            Log.d("text", text);
            Log.d("splitted", credentials.toString());
            if (credentials.length < 3) {
            } else {
                SendData data = new SendData();
                data.login(DecoderActivity.this, credentials[0], credentials[1], credentials[2], new LoginCallback() {
                    @Override
                    public void OnLoginAchevied(boolean isFailed) {
                        if (!isFailed) {

                        } else {
                            Intent i = new Intent(DecoderActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}