package es.cs.rcars.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import es.cs.rcars.R;
import es.cs.rcars.models.Vehicle;

public class VehicleItemAdapter extends RecyclerView.Adapter<VehicleItemAdapter.ViewHolder> {
    List<Vehicle> vehicleList;
    Context context;

    public VehicleItemAdapter(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.LinkItems(vehicleList.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout container;
        LinearLayout containerDetails;
        TextView makeLabel;
        TextView modelLabel;
        TextView yearLabel;
        TextView fuelLabel;
        TextView plateLabel;
        TextView TransmissionLabel;
        TextView purchaseLabel;
        ImageView logoView;
        Map<String, String> fuelMap;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            InitializeItems(itemView);
            InitializeFuelMap();
        }

        private void InitializeItems(@NonNull View itemView) {
            container = itemView.findViewById(R.id.itemVehicles);
            containerDetails = itemView.findViewById(R.id.VehicleDetailedInfo);
            makeLabel = itemView.findViewById(R.id.make);
            modelLabel = itemView.findViewById(R.id.model);
            yearLabel = itemView.findViewById(R.id.yearData);
            fuelLabel = itemView.findViewById(R.id.fuelData);
            logoView = itemView.findViewById(R.id.brandLogo);
            plateLabel = itemView.findViewById(R.id.plateData);
            TransmissionLabel = itemView.findViewById(R.id.transmissionData);
            purchaseLabel = itemView.findViewById(R.id.purchaseDateData);
        }

        public void LinkItems(Vehicle vehicle) {
            makeLabel.setText(vehicle.getMake());
            modelLabel.setText(vehicle.getModel());
            yearLabel.setText(String.valueOf(vehicle.getYear()));
            fuelLabel.setText(fuelMap.get(vehicle.getFuel()));
            plateLabel.setText(vehicle.getNumberPlate());
            TransmissionLabel.setText(vehicle.getTransmission());
//            purchaseLabel.setText(vehicle.getPurchaseDate());
            SetLogoImage(vehicle);

            container.setOnClickListener(v -> containerDetails.setVisibility(AlternateVisibility(containerDetails)));
        }

        private String ConvertDate(Date date) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return dateFormat.format(date);
        }

        private void SetLogoImage(Vehicle vehicle) {
            String imageName = vehicle.getMake().toLowerCase();
            int imageId = context.getResources().getIdentifier(imageName, "drawable",  context.getPackageName());
            logoView.setImageResource(imageId);
        }

        private void InitializeFuelMap() {
            fuelMap = new HashMap<>();
            fuelMap.put("GAS", "Gasolina");
            fuelMap.put("DSL", "Diesel");
            fuelMap.put("HIB", "Híbrido");
            fuelMap.put("ELE", "Eléctrico");
            fuelMap.put("PHEV", "Híbrido Enchifable");
            fuelMap.put("GLC", "Gas Licuado");
            fuelMap.put("HDR", "Hidrógeno");
        }

        private int AlternateVisibility(LinearLayout containerDetails) {
            return containerDetails.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
        }
    }
}
