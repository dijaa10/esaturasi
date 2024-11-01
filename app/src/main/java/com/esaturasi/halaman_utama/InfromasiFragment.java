package com.esaturasi.halaman_utama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.esaturasi.R;

public class InfromasiFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout fragment
        return inflater.inflate(R.layout.activity_home, container, false);
    }
}

