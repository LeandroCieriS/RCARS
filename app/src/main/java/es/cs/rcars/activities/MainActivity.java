package es.cs.rcars.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.cs.rcars.R;
import es.cs.rcars.io.APIAdapter;
import es.cs.rcars.models.Customer;
import es.cs.rcars.models.Dealer;
import es.cs.rcars.models.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String urlRCars ="https://rcarscanarias.com/vehiculos-premium-generales/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        String userEmail = getIntent().getExtras().getString("UserId");
        getLoggedUser(userEmail);
        getDealers();
        initializeViewComponents();
    }

    private void getLoggedUser(String userEmail) {
        Call<Customer> customerCall = APIAdapter.getApiService().getCustomerFromUser(userEmail);
        customerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(@NotNull Call<Customer> call, @NotNull Response<Customer> response) {
                setWelcomeName(response.body().getFirstName(), response.body().getLastName());
            }

            @Override
            public void onFailure(@NotNull Call<Customer> call, @NotNull Throwable t) {
                Customer loggedCustomer = getTestCustomerJsonConverted();
                setWelcomeName(loggedCustomer.getFirstName(), loggedCustomer.getLastName());
            }
        });
    }

    private void setWelcomeName(String firstName, String lastName){
        String fullName = firstName + " " + lastName;
        TextView nameLabel = findViewById(R.id.name_tv);
        nameLabel.setText(fullName.toUpperCase());
    }

    private void getDealers() {
        Call<ArrayList<Dealer>> dealersCall = APIAdapter.getApiService().getDealers();
        dealersCall.enqueue(new Callback<ArrayList<Dealer>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<Dealer>> call, @NotNull Response<ArrayList<Dealer>> response) {
                setDealersInfo(response.body().get(0));
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Dealer>> call, @NotNull Throwable t) {
                setDealersInfo(getTestJsonConverted().get(0));
            }
        });
    }

    private void initializeViewComponents() {

        ConstraintLayout btnWide = findViewById(R.id.itemFinalWide);
        LinearLayout btnOffices = findViewById(R.id.itemOffices);
        LinearLayout btnVehicles = findViewById(R.id.itemVehicles);
        LinearLayout btnWorkshops = findViewById(R.id.itemWorkshop);
        LinearLayout btnAppointment = findViewById(R.id.itemAppointment);
        LinearLayout dealersInfo = findViewById(R.id.row2point5);

        btnWorkshops.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("Show","Workshops");
            startActivity(intent);
        });

        btnVehicles.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VehiclesActivity.class);
            intent.putExtra("Show","Vehicles");
            startActivity(intent);
        });

        btnOffices.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("Show","Offices");
            startActivity(intent);
        });

        btnAppointment.setOnClickListener(v -> alternateVisibility(dealersInfo));

        btnWide.setOnClickListener(v -> {
            Uri uri = Uri.parse(urlRCars);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });
    }

    private void setDealersInfo(Dealer dealer) {
        TextView phoneLbl = findViewById(R.id.phoneLbl);
        TextView addressLbl = findViewById(R.id.addressLbl);
        TextView islandLbl = findViewById(R.id.islandLbl);

        phoneLbl.setText(formatPhoneNumber(dealer.getPhoneNumber()));
        islandLbl.setText(dealer.getIsland());
        addressLbl.setText(dealer.getAddress());
    }

    @NotNull
    private String formatPhoneNumber(String phoneNumber) {
        return phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1 $2 $3");
    }

    private void alternateVisibility(LinearLayout dealersInfo) {
        dealersInfo.setVisibility(dealersInfo.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }

    private ArrayList<Dealer> getTestJsonConverted() {
        String testJson = "[\n" +
                "  {\n" +
                "    \"dealerId\": 1,\n" +
                "    \"address\": \"Av. Juan de Bethencourt, 12, Puerto del Rosario\",\n" +
                "    \"island\": \"Fuerteventura\",\n" +
                "    \"dealerName\": \"RCARS Fuerteventura\",\n" +
                "    \"phoneNumber\": \"928114200\"\n" +
                "  },\n" +
                "]";
        Type collectionType = new TypeToken<List<Dealer>>() {}.getType();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(testJson, collectionType);
    }

    private Customer getTestCustomerJsonConverted() {
        String testJson =
                "  {\n" +
                "    \"id\": \"2830D638-073F-49D8-9F95-19D41ADE427E\",\n" +
                "    \"firstName\": \"Carla\",\n" +
                "    \"lastName\": \"Llorens\",\n" +
                "    \"idNumber\": \"12345678A\",\n" +
                "    \"isCompany\": false,\n" +
                "    \"email\": \"realemail1@forsure.com\",\n" +
                "    \"address\": \"Fake Street 123\",\n" +
                "    \"phoneNumber\": \"654321987\"\n" +
                "  }\n";
        Type collectionType = new TypeToken<Customer>() {}.getType();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(testJson, collectionType);
    }
}