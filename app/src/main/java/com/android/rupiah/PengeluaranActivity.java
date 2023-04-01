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

public class PengeluaranActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference database;
    private EditText etSaldoPengeluaran,etKeteranganPengeluaran;
    private Button btnCatatPengeluaran;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);

        database = FirebaseDatabase.getInstance().getReference();

        etSaldoPengeluaran = findViewById(R.id.et_saldo_pengeluaran);
        etKeteranganPengeluaran = findViewById(R.id.et_keterangan_pengeluaran);
        btnCatatPengeluaran = findViewById(R.id.btn_catat_pengeluaran);
        btnCatatPengeluaran.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String saldo = etSaldoPengeluaran.getText().toString();
        String keterangan = etKeteranganPengeluaran.getText().toString();
        if(saldo.equals("") || keterangan.equals("")){
            Toast.makeText(PengeluaranActivity.this, "Semua field harus diisi",Toast.LENGTH_SHORT).show();
        }
        else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String tanggal = simpleDateFormat.format(new Date());
            int saldoint = Integer.parseInt(saldo);
            addPengeluaran(new RiwayatModel(
                            saldoint,
                            keterangan,
                            tanggal,
                            "Pengeluaran"
                    )
            );
        }
    }

    private void addPengeluaran(RiwayatModel riwayatModel){
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intentlogin = new Intent(PengeluaranActivity.this, LoginActivity.class);
            startActivity(intentlogin);
        }
        else{
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            database.child("Users")
                    .child(uid)
                    .child("riwayat")
                    .push()
                    .setValue(riwayatModel).addOnCompleteListener(PengeluaranActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(PengeluaranActivity.this, "Gagal Mencatat",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(PengeluaranActivity.this, "Pengeluaran berhasil dicatat",Toast.LENGTH_SHORT).show();
                        etKeteranganPengeluaran.setText("");
                        etSaldoPengeluaran.setText("");
                    }
                }
            });
        }
    }
}
