package es.cs.rcars;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import es.cs.rcars.db.DataBase;

public class LoginActivity extends AppCompatActivity {

    private EditText user_tv, password_tv;
    private Button login_button, register_button;
    private TextView failedLogin_tv, ornament;
    private ImageView mainLogo, logo;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initializeViewComponents();
        showLogo();

        dataBase = new DataBase(LoginActivity.this);

        // Valida los datos al presionar el boton de iniciar sesion
        login_button.setOnClickListener(v ->
                validate(user_tv.getText().toString(), password_tv.getText().toString()));
        
        //Borra el mensaje de contraseña erronea
        password_tv.setOnClickListener(v -> failedLogin_tv.setVisibility(View.GONE));
    }

    // llama a la actividad correspondiente o
    // muestra mensaje de contraseña erronea
    private void validate(String user, String password){
        Cursor c = dataBase.checkLogin(user.toLowerCase(), password);

        if(c.getCount()==1 && c.moveToFirst()){
            Toast.makeText(this, "Bienvenido " + user , Toast.LENGTH_SHORT).show();
        } else {
            password_tv.setText("");
            failedLogin_tv.setVisibility(View.VISIBLE);
        }
    }

    private void initializeViewComponents() {
        register_button = findViewById(R.id.registerButton);
        failedLogin_tv = findViewById(R.id.tv_loginfailed);
        password_tv = findViewById(R.id.editTextPassword);
        login_button = findViewById(R.id.loginButton);
        user_tv = findViewById(R.id.editTextUser);
        mainLogo = findViewById(R.id.iv_mainlogo);
        ornament = findViewById(R.id.textView);
        logo = findViewById(R.id.imageView);
    }

    private void showLogo() {
        new Handler().postDelayed(() -> {
            mainLogo.setVisibility(View.GONE);
            user_tv.setVisibility(View.VISIBLE);
            password_tv.setVisibility(View.VISIBLE);
            login_button.setVisibility(View.VISIBLE);
            register_button.setVisibility(View.VISIBLE);
            ornament.setVisibility(View.VISIBLE);
            logo.setVisibility(View.VISIBLE);
        }, 3000);
    }
}