package com.android.rupiah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView tvName,tvEmail,tvId;
    private ImageView ivPhoto;
    private Button btnKeluar;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    private BottomNavigationView bottom_navigation_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        tvId = findViewById(R.id.tv_id);
        ivPhoto = findViewById(R.id.iv_photo);
        btnKeluar = findViewById(R.id.btn_keluar);
        btnKeluar.setOnClickListener(this);*/

        //Google SignIn Start
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        /*
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Glide.with(this).load(String.valueOf(personPhoto)).into(ivPhoto);

            tvName.setText(personName);
            tvEmail.setText(personEmail);
            tvId.setText(personId);
        }*/
        //Google SignIn End

        bottom_navigation_bar = findViewById(R.id.bottom_navigation_bar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BerandaFragment()).commit();

        bottom_navigation_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.beranda_menu:
                        fragment = new BerandaFragment();
                        break;

                    case R.id.buat_menu:
                        fragment = new BuatFragment();
                        break;

                    case R.id.gabung_menu:
                        fragment = new GabungFragment();
                        break;

                    case R.id.riwayat_menu:
                        fragment = new RiwayatFragment();
                        break;

                    case R.id.saya_menu:
                        fragment = new SayaFragment();
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

                return true;
            }
        });
    }

    /*
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_keluar:
                signOut();
                break;
            // ...
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this,"Anda telah keluar",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }*/
}
