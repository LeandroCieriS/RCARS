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
import java.util.Calendar;
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
        LinearLayout container, containerDetails;
        TextView makeLabel, modelLabel, nextInspectionLabel, fuelLabel, plateLabel, yearLabel;
        TextView TransmissionLabel, purchaseLabel, inspectionLabel, secondKeyLabel, manualLabel;
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
            inspectionLabel = itemView.findViewById(R.id.inspectionDateData);
            nextInspectionLabel = itemView.findViewById(R.id.nextInspectionDateData);
            secondKeyLabel = itemView.findViewById(R.id.secondKeyData);
            manualLabel = itemView.findViewById(R.id.manualData);
        }

        public void LinkItems(Vehicle vehicle) {
            makeLabel.setText(vehicle.getMake());
            modelLabel.setText(vehicle.getModel());
            yearLabel.setText(String.valueOf(vehicle.getYear()));
            fuelLabel.setText(fuelMap.get(vehicle.getFuel()));
            plateLabel.setText(vehicle.getNumberPlate());
            String transmission = vehicle.getTransmission().equals("A") ? "Automático" : "Manual";
            TransmissionLabel.setText(transmission);
            purchaseLabel.setText(ConvertDate(vehicle.getPurchaseDate()));
            inspectionLabel.setText(ConvertDate(vehicle.getInspectionDate()));
            nextInspectionLabel.setText(getNextInspectionDate(vehicle));
            secondKeyLabel.setText(vehicle.hasSecondKey() ? "SI" : "NO");
            manualLabel.setText(vehicle.hasManual() ? "SI" : "NO");
            SetLogoImage(vehicle);

            container.setOnClickListener(v -> AlternateVisibility(containerDetails));
        }

        private String getNextInspectionDate(Vehicle vehicle) {
            Calendar c = Calendar.getInstance();
            c.setTime(vehicle.getInspectionDate());
            c.add(Calendar.YEAR, 1);
            return ConvertDate(c.getTime());
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

        private void AlternateVisibility(LinearLayout containerDetails) {
            containerDetails.setVisibility(containerDetails.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
    }
}
