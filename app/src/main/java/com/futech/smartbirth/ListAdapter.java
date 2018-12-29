package com.futech.smartbirth;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private List<DataModel> dataModelList;


    ListAdapter(List<DataModel> dataModelList){
        this.dataModelList = dataModelList;
    }


    @Override
    public int getItemCount() {
        return dataModelList.size() +1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == dataModelList.size()) ? R.layout.graph : R.layout.cardview_list;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        /*
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_list, viewGroup, false);
        if(viewType == R.layout.cardview_list )
        ListViewHolder listViewHolder = new ListViewHolder(v);
        return listViewHolder;
        */

        View itemView;
        if(viewType == R.layout.cardview_list){
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_list, viewGroup, false);
        }

        else {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.graph, viewGroup, false);
        }

        return new ListViewHolder(itemView);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder,final int pos) {
        if(pos == dataModelList.size()) {



        }
        else {
            listViewHolder.tanggal.setText(dataModelList.get(pos).getTanggal());
            listViewHolder.berat.setText(dataModelList.get(pos).getBerat()+" kg");
            listViewHolder.langkah.setText(dataModelList.get(pos).getLangkah()+ " langkah");
        }
    }




    public static class ListViewHolder extends RecyclerView.ViewHolder{
        TextView tanggal;
        TextView berat, langkah, langkah_main;

        ListViewHolder(View itemView) {
            super(itemView);

            langkah_main = itemView.findViewById(R.id.textViewLangkahMain);
            tanggal = itemView.findViewById(R.id.textViewTanggal);
            berat = itemView.findViewById(R.id.textViewBerat);
            langkah = itemView.findViewById(R.id.textViewLangkah);

        }
    }

}