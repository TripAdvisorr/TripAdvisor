package com.example.tripadvisor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripAdvisor extends RecyclerView.Adapter<TripAdvisor.ViewHolder> implements Filterable {
    private ArrayList<TripClass> trips;
    private ArrayList<TripClass> tripListFull;
    private Context context;

    public TripAdvisor(Context context, ArrayList<TripClass> trips) {
        this.context = context;
        this.trips = trips;
        this.tripListFull = new ArrayList<>(trips);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_singletripdesign, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TripClass singleTrip = trips.get(position);
        holder.tvLocation.setText(singleTrip.getLocation());
        holder.ivImage.setImageResource(singleTrip.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context,Detailsoftrips.class);
            intent.putExtra("location", singleTrip.getLocation());
            intent.putExtra("image", singleTrip.getImageResId());
            intent.putExtra("description", singleTrip.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    @Override
    public Filter getFilter() {
        return tripFilter;
    }

    private Filter tripFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<TripClass> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(tripListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (TripClass item : tripListFull) {
                    if (item.getLocation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            trips.clear();
            trips.addAll((ArrayList<TripClass>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivVenueImage);
            tvLocation = itemView.findViewById(R.id.tvVenueName);
        }
    }
}
