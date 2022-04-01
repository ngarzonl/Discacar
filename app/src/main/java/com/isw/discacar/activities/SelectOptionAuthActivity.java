package com.isw.discacar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.isw.discacar.R;
import com.isw.discacar.activities.client.RegisterActivity;
import com.isw.discacar.activities.driver.RegisterDriverActivity;
import com.isw.discacar.includes.MyToolbar;

public class SelectOptionAuthActivity extends AppCompatActivity {

    Button mButtonGoToLogin;
    Button mButtonGoToRegister;

    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        MyToolbar.show(this, "Seleccionar opcion", true);

        mButtonGoToLogin = findViewById(R.id.btnGoToLogin);
        mButtonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });

        mButtonGoToRegister = findViewById(R.id.btnGoToRegister);
        mButtonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

    }

    private void goToLogin() {
        Intent intent = new Intent(SelectOptionAuthActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void goToRegister() {
        String typeUser = mPref.getString("user","");
        if (typeUser.equals("client")) {
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(SelectOptionAuthActivity.this, RegisterDriverActivity.class);
            startActivity(intent);
        }

    }
}