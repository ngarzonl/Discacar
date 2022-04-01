package com.isw.discacar.activities.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.isw.discacar.R;
import com.isw.discacar.activities.client.RegisterActivity;
import com.isw.discacar.includes.MyToolbar;
import com.isw.discacar.models.Client;
import com.isw.discacar.models.Driver;
import com.isw.discacar.providers.AuthProvider;
import com.isw.discacar.providers.ClientProvider;
import com.isw.discacar.providers.DriverProvider;

public class RegisterDriverActivity extends AppCompatActivity {

    AuthProvider mAuthProvider;
    DriverProvider mDriverProvider;

    //VIEWS
    Button mButtonRegister;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputVehicleBrand;
    TextInputEditText mTextInputVehiclePlate;
    TextInputEditText mTextInputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);

        MyToolbar.show(this, "Registro de conductor", true);

        mAuthProvider = new AuthProvider();
        mDriverProvider = new DriverProvider();

        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputVehicleBrand = findViewById(R.id.textInputVehicleBrand);
        mTextInputVehiclePlate = findViewById(R.id.textInputVehiclePlate);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        //Toast.makeText(this, "Usted es " + selectedUser, Toast.LENGTH_SHORT).show();



        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegister();
            }
        });
    }

    void clickRegister() {
        String name = mTextInputName.getText().toString();
        String email = mTextInputEmail.getText().toString();
        String vehicleBrand = mTextInputVehicleBrand.getText().toString();
        String vehiclePlate = mTextInputVehiclePlate.getText().toString();
        String password = mTextInputPassword.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !vehicleBrand.isEmpty() && !vehiclePlate.isEmpty()) {
            if (password.length() >= 6) {
                register(name, email, password, vehicleBrand, vehiclePlate);
            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        }

    }

    void register(String name, String email, String password, String vehicleBrand, String vehiclePlate) {

        mAuthProvider.register(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Driver driver = new Driver(id, name, email, vehicleBrand, vehiclePlate);
                    create(driver);
                }
                else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(Driver driver) {
        mDriverProvider.create(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterDriverActivity.this, "El registro se realizo exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterDriverActivity.this, MapDriverActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}