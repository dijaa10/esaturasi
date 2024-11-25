package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.esaturasi.Fragment.InformasiFragment;
import com.esaturasi.Fragment.TerbaruFragment;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Mengembalikan fragment yang sesuai berdasarkan posisi
        switch (position) {
            case 0:
                return new TerbaruFragment(); // Fragment untuk tab pertama
            case 1:
                return new InformasiFragment(); // Fragment untuk tab kedua
            default:
                return new TerbaruFragment(); // Default fragment jika posisi tidak dikenal
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Total jumlah tab atau halaman
    }
}

