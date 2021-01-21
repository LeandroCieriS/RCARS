package es.cs.rcars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout btnWide;
    private final String urlRcars ="https://rcarscanarias.com/vehiculos-premium-generales/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializeViewComponents();
    }

    private void initializeViewComponents() {
        btnWide = findViewById(R.id.itemFinalWide);

        btnWide.setOnClickListener(view -> {
            System.out.println("ASASASDFASDFASDF");
            Uri uri = Uri.parse(urlRcars);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }
}