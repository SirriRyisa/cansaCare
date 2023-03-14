package com.eagle.cansacare;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {


    private final List<ServicesList> servicesLists;


    public ServicesAdapter(List<ServicesList> servicesLists) {
        this.servicesLists = servicesLists;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item_card,parent,false);
       return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesViewHolder holder, int position) {

        ServicesList servicesList = servicesLists.get(position);

        holder.serviceName.setText(servicesList.getDisplayName());
        holder.serviceTitle.setText(servicesList.getTitle());

//Setting a click listener to move to the service details page
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(),BookingServiceAppointment.class);

//            getting the data fir the specfic item selected to move to the
            intent.putExtra("DisplayName",servicesList.getDisplayName());
            intent.putExtra("title",servicesList.getTitle());
            intent.putExtra("schedule",servicesList.getSchedule());

            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return servicesLists.size();
    }


    public static class ServicesViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView serviceName;
        public TextView serviceTitle;


        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceName = itemView.findViewById(R.id.serviceName);
            serviceTitle = itemView.findViewById(R.id.serviceTitle);


        }
    }
}
