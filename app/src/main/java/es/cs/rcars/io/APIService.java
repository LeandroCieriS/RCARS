package es.cs.rcars.io;

import java.util.ArrayList;

import es.cs.rcars.io.responses.VehiclesResponse;
import es.cs.rcars.models.Customer;
import es.cs.rcars.models.Dealer;
import es.cs.rcars.models.Purchase;
import es.cs.rcars.models.Vehicle;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("vehicles")
    Call<ArrayList<Vehicle>> getVehicles();

    @GET("dealers")
    Call<ArrayList<Dealer>> getDealers();

    @GET("Customer/{userEmail}")
    Call<Customer> getCustomerFromUser(@Path("userEmail") String userEmail);

}
