package com.frenzydevel.manajerkeuangan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.frenzydevel.manajerkeuangan.R;
import com.frenzydevel.manajerkeuangan.model.Uang;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Activity activity;
    List<Uang> uang;
    LayoutInflater inflater;


    public Adapter(Activity activity, List<Uang> uang) {
        this.activity = activity;
        this.uang = uang;
    }

    @Override
    public int getCount() {
        return uang.size();
    }

    @Override
    public Object getItem(int i) {
        return uang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null && inflater != null){
            view = inflater.inflate(R.layout.list_uang, null);
        }

        if (view != null){
            TextView tvIncome = (TextView) view.findViewById(R.id.tvIncome);
            TextView tvOutcome = (TextView) view.findViewById(R.id.tvOutcome);

            Uang data = uang.get(i);
//            String income = data.getIncome();
//            String outcome = data.getOutcome();

            tvIncome.setText("Rp. " + data.getIncome() + ",00");
            tvOutcome.setText("Rp. " + data.getOutcome() + ",00");
        }
        return view;
    }
}
