package es.cs.rcars.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.cs.rcars.R;
import es.cs.rcars.models.Vehicle;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehicleFragment extends Fragment {

    private static String make;

    public VehicleFragment() {
    }

    public static VehicleFragment newInstance(Vehicle vehicle) {
        VehicleFragment fragment = new VehicleFragment();
        Bundle args = new Bundle();
        args.putString("make", vehicle.getMake());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            make = getArguments().getString("make");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        TextView makeLbl = view.findViewById(R.id.make);
        makeLbl.setText(make);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle, container, false);
    }
}