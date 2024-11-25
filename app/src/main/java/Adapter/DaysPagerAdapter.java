package Adapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.esaturasi.Fragment.ScheduleFragment;
import com.esaturasi.Model.ScheduleItem;

import java.util.List;

public class DaysPagerAdapter extends FragmentStateAdapter {

    private final List<List<ScheduleItem>> weeklySchedule;

    public DaysPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<List<ScheduleItem>> weeklySchedule) {
        super(fragmentActivity);
        this.weeklySchedule = weeklySchedule;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new ScheduleFragment(weeklySchedule.get(position));
    }

    @Override
    public int getItemCount() {
        return weeklySchedule.size(); // Jumlah hari (misalnya, 5 untuk Senin - Jumat)
    }
}

