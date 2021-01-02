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

import com.guardian.guardianadmin_v1.EncodeDecode;
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
        holder.phoneNumberTextView.setText(englishToArabic(userList.getPhoneNumber()));
        String status = EncodeDecode.calculateStatusAlgorithm(userList.getAverage());
        if(userList.getAverage()<0) {
            holder.speedTextView.setText(String.valueOf(userList.getSpeed()));
            holder.averageTextView.setText(String.valueOf(userList.getAverage()));
            holder.averageTextView2.setText("اطلاعات ناموجود");
            holder.speedTextView2.setText("");
        } else {
            holder.speedTextView.setText(String.valueOf(englishToArabic(String.valueOf(userList.getSpeed()))));
            holder.averageTextView.setText(String.valueOf(userList.getAverage()) + "%");// + "\n" + status);
            holder.averageTextView2.setText(String.valueOf(EncodeDecode.calculateStatusAlgorithm(userList.getAverage())));
            holder.speedTextView2.setText("km/h");
        }
    }

    private String englishToArabic(String str) {
        char[] arabicChars = {'٠','١','٢','٣','٤','٥','٦','٧','٨','٩'};
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                builder.append(arabicChars[(int)(str.charAt(i))-48]);
            }
            else
            {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
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
        TextView averageTextView2;
        TextView speedTextView2;

        OnItemListener onItemListener;

        ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);

            this.onItemListener = onItemListener;

            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            speedTextView = itemView.findViewById(R.id.speedTextView);
            averageTextView = itemView.findViewById(R.id.averageTextView);
            averageTextView2 = itemView.findViewById(R.id.averageTextView2);
            speedTextView2 = itemView.findViewById(R.id.speedTextView2);

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

    public void updateData(ArrayList<UserList> newD) {
        this.data.clear();
        this.data.addAll(newD);
        UserList.getAll().clear();
        UserList.getAll().addAll(newD);
        notifyDataSetChanged();
    }

    public ArrayList<UserList> getData() {
        return data;
    }
}
