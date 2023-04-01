package com.android.rupiah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.rupiah.model.RiwayatModel;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PemasukanActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference database;
    private EditText etSaldoPemasukan,etKeteranganPemasukan;
    private Button btnCatatPemasukan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        database = FirebaseDatabase.getInstance().getReference();

        etSaldoPemasukan = findViewById(R.id.et_saldo_pemasukan);
        etKeteranganPemasukan = findViewById(R.id.et_keterangan_pemasukan);
        btnCatatPemasukan = findViewById(R.id.btn_catat_pemasukan);
        btnCatatPemasukan.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String saldo = etSaldoPemasukan.getText().toString();
        String keterangan = etKeteranganPemasukan.getText().toString();
        if(saldo.equals("") || keterangan.equals("")){
            Toast.makeText(PemasukanActivity.this, "Semua field harus diisi",Toast.LENGTH_SHORT).show();
        }
        else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String tanggal = simpleDateFormat.format(new Date());
            int saldoint = Integer.parseInt(saldo);
            addPemasukan(new RiwayatModel(
                    saldoint,
                    keterangan,
                    tanggal,
                    "Pemasukan"
                    )
            );
        }
    }

    private void addPemasukan(RiwayatModel riwayatModel){
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intentlogin = new Intent(PemasukanActivity.this, LoginActivity.class);
            startActivity(intentlogin);
        }
        else{
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            database.child("Users")
                    .child(uid)
                    .child("riwayat")
                    .push()
                    .setValue(riwayatModel).addOnCompleteListener(PemasukanActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(PemasukanActivity.this, "Gagal Mencatat",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(PemasukanActivity.this, "Pemasukan berhasil dicatat",Toast.LENGTH_SHORT).show();
                        etKeteranganPemasukan.setText("");
                        etSaldoPemasukan.setText("");
                    }
                }
            });
        }
    }

}

