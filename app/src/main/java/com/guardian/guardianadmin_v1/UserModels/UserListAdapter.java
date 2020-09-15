package com.guardian.guardianadmin_v1.UserModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guardian.guardianadmin_v1.R;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> implements Filterable {

    private ArrayList<UserList> data;
    private LayoutInflater inflater;
    private OnItemListener mOnItemListener;
    private ArrayList<UserList> dataFiltered;


    public UserListAdapter(Context context, ArrayList<UserList> data, OnItemListener onItemListener) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.dataFiltered = data;
        this.mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.driver_row, parent, false), mOnItemListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTextView;
        TextView phoneNumberTextView;
        TextView speedTextView;
        TextView averageTextView;

        OnItemListener onItemListener;

        ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);

            this.onItemListener = onItemListener;

            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            speedTextView = itemView.findViewById(R.id.speedTextView);
            averageTextView = itemView.findViewById(R.id.averageTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if(constraint == null | constraint.length() == 0) {
                    filterResults.count = dataFiltered.size();
                    filterResults.values = dataFiltered;
                } else {
                    String searchChar = constraint.toString().toLowerCase();
                    ArrayList<UserList> resultData = new ArrayList<>();

                    for (UserList user : dataFiltered) {
                        if(user.getName().toLowerCase().contains(searchChar)) {
                            resultData.add(user);
                        } else if(user.getPhoneNumber().toLowerCase().contains(searchChar)) {
                            resultData.add(user);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (ArrayList<UserList>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
