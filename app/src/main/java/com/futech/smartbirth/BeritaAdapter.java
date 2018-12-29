package com.futech.smartbirth;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>{

    private List<BeritaDataModel> beritaDataModelList;


    BeritaAdapter(List<BeritaDataModel> beritaDataModelList){
        this.beritaDataModelList = beritaDataModelList;
    }


    @Override
    public int getItemCount() {
        return beritaDataModelList.size();
    }


    @Override
    public BeritaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_berita, viewGroup, false);
        BeritaViewHolder beritaViewHolder = new BeritaViewHolder(v);
        return beritaViewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(final BeritaViewHolder beritaViewHolder,final int i) {
        beritaViewHolder.judulBerita.setText(beritaDataModelList.get(i).getJudulBerita());
        beritaViewHolder.deskripsiBerita.setText(beritaDataModelList.get(i).getDeskripsiBerita());
        beritaViewHolder.tanggalBerita.setText(beritaDataModelList.get(i).getTanggalBerita());
        beritaViewHolder.tanggalBerita2.setText(beritaDataModelList.get(i).getTanggalBerita2());

    }


    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        TextView judulBerita;
        TextView deskripsiBerita;
        TextView tanggalBerita;
        TextView tanggalBerita2;
        CardView cardViewBerita;
        Context context;

        BeritaViewHolder(View itemView) {
            super(itemView);
            cardViewBerita = itemView.findViewById(R.id.card_view_berita);
            judulBerita = itemView.findViewById(R.id.textViewJudul);
            deskripsiBerita = itemView.findViewById(R.id.textViewDeskripsi);
            tanggalBerita = itemView.findViewById(R.id.textViewTanggal);
            tanggalBerita2 = itemView.findViewById(R.id.textViewTanggal2);
            context = itemView.getContext();


            cardViewBerita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailBeritaActivity.class);
                    i.putExtra("id", beritaDataModelList.get(getAdapterPosition()).getId());
                    i.putExtra("judul", beritaDataModelList.get(getAdapterPosition()).getJudulBerita());
                    i.putExtra("isi", beritaDataModelList.get(getAdapterPosition()).getDeskripsiBerita());
                    context.startActivity(i);
                }
            });
        }
    }

}