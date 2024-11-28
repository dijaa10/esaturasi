package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.esaturasi.R;

public class CalendarAdapter extends BaseAdapter {

    private final Context context;
    private final String[] days;
    private final boolean[] isToday;

    public CalendarAdapter(Context context, String[] days, boolean[] isToday) {
        this.context = context;
        this.days = days;
        this.isToday = isToday;
    }

    @Override
    public int getCount() {
        return days.length;
    }

    @Override
    public Object getItem(int position) {
        return days[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.calendar_day_item, parent, false);
        }

        TextView dayTextView = view.findViewById(R.id.day_text);
        String day = days[position];

        if (day != null && !day.isEmpty()) {  // Pastikan day tidak null dan tidak kosong
            dayTextView.setText(day);

            // Jika hari ini, beri warna latar belakang khusus
            if (isToday[position]) {
                dayTextView.setBackgroundColor(context.getResources().getColor(R.color.blue));
                dayTextView.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                dayTextView.setBackgroundColor(context.getResources().getColor(R.color.white));
                dayTextView.setTextColor(context.getResources().getColor(R.color.black));
            }
        } else {
            dayTextView.setText(""); // Jika day null atau kosong, jangan tampilkan teks
        }

        return view;
    }
}

