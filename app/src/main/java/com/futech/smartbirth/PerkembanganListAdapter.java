package com.futech.smartbirth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PerkembanganListAdapter extends RecyclerView.Adapter<PerkembanganListAdapter.PerkembanganViewHolder> {

    class PerkembanganViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewBerat;

        private PerkembanganViewHolder(View itemView) {
            super(itemView);
            textViewBerat = itemView.findViewById(R.id.textViewBerat);
        }
    }

    private final LayoutInflater mInflater;
    private List<Data> dataList; // Cached copy of words

    PerkembanganListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PerkembanganViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cardview_list, parent, false);
        return new PerkembanganViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PerkembanganViewHolder holder, int position) {
        if (dataList != null) {
            Data current = dataList.get(position);
            holder.textViewBerat.setText(current.getBerat().toString());
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewBerat.setText("No Word");
        }
    }

    void setDataList(List<Data> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (dataList != null)
            return dataList.size();
        else return 0;
    }
}
