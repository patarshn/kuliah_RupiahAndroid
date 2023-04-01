package com.android.rupiah;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.rupiah.adapter.SlideAdapter;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment implements View.OnClickListener {

    private ImageButton btnPemasukan;
    private ImageButton btnPengeluaran;

    ViewPager viewPager;
    int currentPageCounter = 0;
    int images[] = {R.drawable.carousel1, R.drawable.carousel2, R.drawable.carousel3};

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SlideAdapter(images, getActivity()));

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPageCounter == images.length) {
                    currentPageCounter = 0;
                }
                viewPager.setCurrentItem(currentPageCounter++, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 2500, 2500);


        btnPemasukan = view.findViewById(R.id.btn_pemasukan);
        btnPemasukan.setOnClickListener(this);
        btnPengeluaran = view.findViewById(R.id.btn_pengeluaran);
        btnPengeluaran.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pemasukan:
                Intent intentPemasukan = new Intent(getActivity(),PemasukanActivity.class);
                startActivity(intentPemasukan);
                break;

            case R.id.btn_pengeluaran:
                Intent intentPengeluaran = new Intent(getActivity(),PengeluaranActivity.class);
                startActivity(intentPengeluaran);
                break;
        }
    }
}
