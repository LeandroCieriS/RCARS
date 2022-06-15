package es.cs.rcars.io;

import java.util.ArrayList;

import es.cs.rcars.io.responses.VehiclesResponse;
import es.cs.rcars.models.Purchase;
import es.cs.rcars.models.Vehicle;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    @GET("vehicles")
    Call<ArrayList<Vehicle>> getVehicles();
}
