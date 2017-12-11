package com.example.vincent.eip.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vincent.eip.Interfaces.LoginCallback;
import com.example.vincent.eip.Network.SendData;
import com.example.vincent.eip.Network.login.LoginResult;
import com.example.vincent.eip.Network.login.LoginRetrofit;
import com.example.vincent.eip.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;

/**
 * Created by niels on 15/02/2016.
 */
public class LoginActivity  extends AppCompatActivity{

    CircularProgressButton connect;
    EditText txtLogin, txtPassword, txtChambre;
    private SendData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_write_date_layout);

        TextView smartText=(TextView)findViewById(R.id.smart_text);
        TextView groomText=(TextView)findViewById(R.id.groom_text);
        Typeface typeFace=Typeface.createFromAsset(getAssets(), "fonts/adam_cg_pro.ttf");
        smartText.setTypeface(typeFace);
        groomText.setTypeface(typeFace);


        data = new SendData();

        final LoginRetrofit log = new LoginRetrofit();


        txtLogin = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        txtChambre = (EditText) findViewById(R.id.roomNumber);
        //progress = (ProgressBar) findViewById(R.id.progressBar1);
       // progress.setVisibility(View.GONE);
        connect = (CircularProgressButton) findViewById(R.id.btnConnect);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect.startAnimation();
                String username = txtLogin.getText().toString();
                username = username.replaceAll(" ", "");
                String password = txtPassword.getText().toString();
                password = password.replaceAll(" ", "");
                String chambre = txtChambre.getText().toString();
                chambre = chambre.replaceAll(" ", "");
                log.login(LoginActivity.this, username, password, chambre, new LoginCallback() {
                    @Override
                    public void OnLoginAchevied(boolean isFailed) {
                        if (!isFailed){
                            connect.revertAnimation(new OnAnimationEndListener() {
                                @Override
                                public void onAnimationEnd() {
                                    connect.setBackgroundResource(R.drawable.rounded_border_login_button_blue);
                                }
                            });
                            Toast.makeText(LoginActivity.this, "erreur lors du Login", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }
        });

    }
}
