package fi.mariapori.rs3stats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class StatsAdapter extends ArrayAdapter<StatsObject> {

    private int resourceLayout;
    private Context mContext;

    public StatsAdapter(Context context, int resource, List<StatsObject> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        StatsObject p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.lblSkill);
            TextView tt2 = (TextView) v.findViewById(R.id.lblLevel);
            TextView tt3 = (TextView) v.findViewById(R.id.lblExp);
            if (tt1 != null) {
                tt1.setText(p.getSkill());
            }

            if (tt2 != null) {
                tt2.setText(String.valueOf(p.getLevel()));
            }

            if (tt3 != null) {
                tt3.setText(String.valueOf(p.getExp()));
            }

        }

        return v;
    }

}
