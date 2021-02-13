package es.cs.rcars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout btnWide;
    private LinearLayout btnOffices;
    private LinearLayout btnWorkshops;
    private final String urlRCars ="https://rcarscanarias.com/vehiculos-premium-generales/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initializeViewComponents();
    }

    private void initializeViewComponents() {
        btnWide = findViewById(R.id.itemFinalWide);
        btnOffices = findViewById(R.id.item6);
        btnWorkshops = findViewById(R.id.item5);

        btnWorkshops.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("Show","Workshops");
            startActivity(intent);
        });

        btnOffices.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("Show","Offices");
            startActivity(intent);
        });

        btnWide.setOnClickListener(v -> {
            Uri uri = Uri.parse(urlRCars);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
    }
}