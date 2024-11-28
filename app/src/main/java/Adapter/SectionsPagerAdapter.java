package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.esaturasi.Fragment.InformasiFragment;
import com.esaturasi.Fragment.TerbaruFragment;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    private static final int TAB_TERBARU = 0;
    private static final int TAB_INFORMASI = 1;

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case TAB_TERBARU:
                return new TerbaruFragment(); // Tab pertama
            case TAB_INFORMASI:
                return new InformasiFragment(); // Tab kedua
            default:
                throw new IllegalStateException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Total jumlah tab
    }
}
