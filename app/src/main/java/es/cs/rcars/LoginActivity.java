package es.cs.rcars;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.cs.rcars.db.DataBase;

public class LoginActivity extends AppCompatActivity {

    private EditText user_tv, password_tv;
    private Button login_button, register_button;
    private TextView failedLogin_tv;
    private ImageView mainLogo;
    private ConstraintLayout mainLayout;
    private DataBase dataBase;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=\\S+$).{6,12}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initializeViewComponents();
        showLogo();
    }

    private void validateLogIn(String email, String password){
        if(validateEmailAndPass(email, password)) {
            //TODO acceder base de datos
//            Cursor c = dataBase.checkLogin(email, password);
//            if (c.getCount() == 1 && c.moveToFirst()) {
//                Toast.makeText(this, "Bienvenido " + email, Toast.LENGTH_SHORT).show();
//                return;
//            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        loginFailed();
    }

    private void loginFailed() {
        password_tv.setText("");
        failedLogin_tv.setVisibility(View.VISIBLE);
    }

    public static boolean validateEmailAndPass(String emailStr, String passwordStr) {
        Matcher matcherEmail = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        Matcher matcherPass = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcherEmail.find() && matcherPass.find();
    }


    private void initializeViewComponents() {
        dataBase = new DataBase(LoginActivity.this);

        mainLayout = findViewById(R.id.constraintLayout);
        register_button = findViewById(R.id.registerButton);
        failedLogin_tv = findViewById(R.id.tv_loginfailed);
        password_tv = findViewById(R.id.editTextPassword);
        login_button = findViewById(R.id.loginButton);
        user_tv = findViewById(R.id.editTextUser);
        mainLogo = findViewById(R.id.iv_mainlogo);

        login_button.setOnClickListener(v ->
                validateLogIn(user_tv.getText().toString(), password_tv.getText().toString()));
        password_tv.setOnClickListener(v -> failedLogin_tv.setVisibility(View.GONE));
        user_tv.setOnClickListener(v -> failedLogin_tv.setVisibility(View.GONE));
    }

    private void showLogo() {
        new Handler().postDelayed(() -> {

            ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(mainLogo, "scaleX", 0.7f);
            ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(mainLogo, "scaleY", 0.7f);
            scaleDownX.setDuration(600);
            scaleDownY.setDuration(600);

            AnimatorSet scaleDown = new AnimatorSet();
            scaleDown.play(scaleDownX).with(scaleDownY);
            scaleDown.start();

            ObjectAnimator animation = ObjectAnimator.ofFloat(mainLogo, "translationY", -500f);
            animation.setDuration(600);
            animation.start();

        }, 3000); //time logo is shown

        new Handler().postDelayed(() -> {
            Animation fadeIn = new AlphaAnimation(0, 1);
            fadeIn.setInterpolator(new DecelerateInterpolator());
            fadeIn.setDuration(1000);

            mainLayout.setVisibility(View.VISIBLE);
            mainLayout.startAnimation(fadeIn);
        }, 3500); //time it takes to the logo to disappear
    }

}