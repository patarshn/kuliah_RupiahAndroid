package com.android.rupiah;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.rupiah.sayafragmentcontent.FaqActivity;
import com.android.rupiah.sayafragmentcontent.KontakActivity;
import com.android.rupiah.sayafragmentcontent.TentangActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class SayaFragment extends Fragment  {

    TextView tvName,tvEmail,tvId;
    ImageView ivPhoto;

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    public SayaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saya, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_email);
        tvId = view.findViewById(R.id.tv_id);
        ivPhoto = view.findViewById(R.id.iv_photo);

        //Google DataSignIn Start
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this.getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Glide.with(this).load(String.valueOf(personPhoto)).into(ivPhoto);
            tvName.setText(personName);
            tvEmail.setText(personEmail);
            tvId.setText(personId);
        }
        //Google DataSignIn End


        final String[] menuItem = {
                "F A Q",
                "Kontak",
                "Tentang",
                "Keluar"
        };
        int menuImage= R.drawable.ic_keyboard_arrow_right_black_24dp;


        ListView listView = (ListView)view.findViewById(R.id.lv_saya);


        final ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItem);

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Intent intent = new Intent(getActivity(), SayaDetailActivity.class);
                intent.putExtra("varEmployee", menuItem[i]);
                startActivity(intent);*/
                listViewControl(i);
            }
        });

        return view;

    }

    public void listViewControl(int i){
        switch (i){
            case 0 :
                Intent intent1 = new Intent(getActivity(), FaqActivity.class);
                startActivity(intent1);
                break;
            case 1 :
                Intent intent2 = new Intent(getActivity(), KontakActivity.class);
                startActivity(intent2);
                break;
            case 2 :
                Intent intent3 = new Intent(getActivity(), TentangActivity.class);
                startActivity(intent3);
                break;
            case 3 :
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getActivity(),"Anda telah keluar",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
                break;
        }
    }
}