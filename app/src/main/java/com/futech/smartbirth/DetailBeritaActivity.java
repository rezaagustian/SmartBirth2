package com.futech.smartbirth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailBeritaActivity extends AppCompatActivity {

    private TextView textViewJudul, textViewIsi, textViewTanggal;
    private String id, judul, isi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getStringExtra("id");
        judul = getIntent().getStringExtra("judul");
        isi = getIntent().getStringExtra("isi");

        textViewJudul = findViewById(R.id.textViewJudul);
        textViewIsi = findViewById(R.id.textViewIsi);

        textViewJudul.setText(judul);
        textViewIsi.setText(isi);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
