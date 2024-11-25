package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.esaturasi.Fragment.AllTasksFragment;
import com.esaturasi.Fragment.LateTasksFragment;
import com.esaturasi.Fragment.PendingTasksFragment;
import com.esaturasi.Fragment.SubmittedTasksFragment;

public class TasksPagerAdapter extends FragmentStateAdapter {

    public TasksPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AllTasksFragment(); // Tidak menggunakan parameter
            case 1:
                return new PendingTasksFragment(); // Tidak menggunakan parameter
            case 2:
                return new SubmittedTasksFragment(); // Tidak menggunakan parameter
            case 3:
                return new LateTasksFragment(); // Tidak menggunakan parameter
            default:
                return new AllTasksFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Jumlah tab
    }
}
