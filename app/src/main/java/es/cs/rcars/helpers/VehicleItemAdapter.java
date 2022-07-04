package es.cs.rcars.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
        holder.linkItems(vehicleList.get(position));
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView makeLabel;
        TextView modelLabel;
        ImageView logoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            makeLabel = itemView.findViewById(R.id.make);
            modelLabel = itemView.findViewById(R.id.model);
            logoView = itemView.findViewById(R.id.brandLogo);
        }

        public void linkItems(Vehicle vehicle) {
            makeLabel.setText(vehicle.getMake());
            modelLabel.setText(vehicle.getModel());

            String imageName = vehicle.getMake().toLowerCase();
            int imageId = context.getResources().getIdentifier(imageName, "drawable",  context.getPackageName());
            logoView.setImageResource(imageId);
        }
    }
}
