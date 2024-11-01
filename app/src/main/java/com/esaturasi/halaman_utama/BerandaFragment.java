package com.esaturasi.halaman_utama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.esaturasi.R;

/**
 * Created by tutlane on 09-01-2018.
 */

public class BerandaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_halaman, container, false);
    }
}
