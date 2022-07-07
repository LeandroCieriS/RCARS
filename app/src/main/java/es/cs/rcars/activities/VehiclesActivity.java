package es.cs.rcars.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.cs.rcars.R;
import es.cs.rcars.helpers.VehicleItemAdapter;
import es.cs.rcars.io.APIAdapter;
import es.cs.rcars.models.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehiclesActivity extends AppCompatActivity implements Callback<ArrayList<Vehicle>> {

    List<Vehicle> vehicleList;
    RecyclerView recycler;
    private Call<ArrayList<Vehicle>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(es.cs.rcars.R.layout.activity_vehicles);
        initializeViewComponents();
        call = APIAdapter.getApiService().getVehicles();
        call.enqueue(this);

        recycler = findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initializeViewComponents() {
    }

    @Override
    public void onResponse(@NotNull Call<ArrayList<Vehicle>> call, Response<ArrayList<Vehicle>> response) {
        vehicleList = response.body();
        VehicleItemAdapter adapter = new VehicleItemAdapter(vehicleList);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onFailure(@NotNull Call<ArrayList<Vehicle>> call, @NotNull Throwable t) {
        vehicleList = getTestJsonConverted();
        VehicleItemAdapter adapter = new VehicleItemAdapter(vehicleList);
        recycler.setAdapter(adapter);
    }

    @NotNull
    private List<Vehicle> getTestJsonConverted() {
        String testJson =  "[\n" +
                "  {\n" +
                "    \"numberPlate\": \"4567KDF\",\n" +
                "    \"make\": \"Toyota\",\n" +
                "    \"model\": \"Yaris\",\n" +
                "    \"year\": 2019,\n" +
                "    \"purchaseKms\": 18500,\n" +
                "    \"fuel\": \"HIB\",\n" +
                "    \"transmission\": \"A\",\n" +
                "    \"inspectionDate\": \"2022-05-21T00:00:00\",\n" +
                "    \"titleOwner\": \"CL\",\n" +
                "    \"sellingPrice\": 13990,\n" +
                "    \"purchasePrice\": 11500,\n" +
                "    \"purchaseDate\": \"2022-05-20T00:00:00\",\n" +
                "    \"secondKey\": true,\n" +
                "    \"manual\": true,\n" +
                "    \"serviceCost\": 130,\n" +
                "    \"bodyworkCost\": 65,\n" +
                "    \"cleaningCost\": 50,\n" +
                "    \"totalCost\": 0\n" +
                "  },\n" +
                "  {\n" +
                "    \"numberPlate\": \"1548HKP\",\n" +
                "    \"make\": \"Subaru\",\n" +
                "    \"model\": \"Forester\",\n" +
                "    \"year\": 2013,\n" +
                "    \"purchaseKms\": 132000,\n" +
                "    \"fuel\": \"DSL\",\n" +
                "    \"transmission\": \"M\",\n" +
                "    \"inspectionDate\": \"2022-06-26T00:00:00\",\n" +
                "    \"titleOwner\": \"AF\",\n" +
                "    \"sellingPrice\": 14500,\n" +
                "    \"purchasePrice\": 11800,\n" +
                "    \"purchaseDate\": \"2022-06-24T00:00:00\",\n" +
                "    \"secondKey\": false,\n" +
                "    \"manual\": true,\n" +
                "    \"serviceCost\": 160,\n" +
                "    \"bodyworkCost\": 250,\n" +
                "    \"cleaningCost\": 60,\n" +
                "    \"totalCost\": 0\n" +
                "  }\n" +
                "]";
        Type collectionType = new TypeToken<List<Vehicle>>(){}.getType();
        return new Gson().fromJson(testJson, collectionType);
    }
}