package com.guardian.guardianadmin_v1.UserModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guardian.guardianadmin_v1.R;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private ArrayList<UserList> data;
    private LayoutInflater inflater;


    public UserListAdapter(Context context, ArrayList<UserList> data) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.driver_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserList userList = data.get(position);
        holder.nameTextView.setText(userList.getName());
        holder.phoneNumberTextView.setText(userList.getPhoneNumber());
        holder.speedTextView.setText(String.valueOf(userList.getSpeed()));
        holder.averageTextView.setText(String.valueOf(userList.getAverage()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneNumberTextView;
        TextView speedTextView;
        TextView averageTextView;

        ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            speedTextView = itemView.findViewById(R.id.speedTextView);
            averageTextView = itemView.findViewById(R.id.averageTextView);
        }
    }

}
