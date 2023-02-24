package com.frenzydevel.manajerkeuangan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.frenzydevel.manajerkeuangan.helper.UangHelper;
import com.frenzydevel.manajerkeuangan.model.Uang;
import com.frenzydevel.manajerkeuangan.adapter.Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView incomeTotal, outcomeTotal;
    ListView lv;
    Button btnTambah;
    AlertDialog.Builder alert;
    List<Uang> uang = new ArrayList<>();
    Adapter adapter;
    UangHelper tbluang = new UangHelper(getContext());
    int incomeSum, outcomeSum;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inisiasi objek tbluang
        tbluang = new UangHelper(getContext());

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        // inisiasi button tambah (sementara)
        btnTambah = v.findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editor = new Intent(getContext(), EditorActivity.class);
                startActivity(editor);
            }
        });

        lv = v.findViewById(R.id.listt);

        incomeTotal = v.findViewById(R.id.incomeTotal);
        outcomeTotal = v.findViewById(R.id.outcomeTotal);

        setIncomeOutcome();

        adapter = new Adapter(getActivity(),uang);
        lv.setAdapter(adapter);
        getData();

        return v;
    }

    public void getData(){
        ArrayList<HashMap<String, String>> rows = tbluang.getData();

        for (int i = 0; i < rows.size(); i++){
            String id = rows.get(i).get("id");
            String profile_id = rows.get(i).get("profile_id");
            String income = rows.get(i).get("income");
            String outcome = rows.get(i).get("outcome");

            Uang moneys = new Uang();
            moneys.setId(id);
            moneys.setProfile_id(profile_id);
            moneys.setIncome(income);
            moneys.setOutcome(outcome);

            this.uang.add(moneys);
        }
        adapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setIncomeOutcome (){
        if (incomeSum > 0 || outcomeSum > 0){
            this.incomeSum = 0;
            this.outcomeSum = 0;
        }

        ArrayList<HashMap<String, String>> rows = tbluang.getData();
        ArrayList<Integer> IncomeData = new ArrayList<>();
        ArrayList<Integer> OutcomeData = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++){
            String in = rows.get(i).get("income");
            String out = rows.get(i).get("outcome");

            assert in != null;
            IncomeData.add(Integer.parseInt(in));
            assert out != null;
            OutcomeData.add(Integer.parseInt(out));
        }

        incomeSum = IncomeData.stream().mapToInt(Integer::intValue).sum();
        outcomeSum = OutcomeData.stream().mapToInt(Integer::intValue).sum();

        incomeTotal.setText(incomeSum+"");
        outcomeTotal.setText(outcomeSum+"");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();
        incomeTotal.setText(incomeSum+"");
        outcomeTotal.setText(outcomeSum+"");
//        reset variabel income outcome
        setIncomeOutcome();


        uang.clear();
        getData();
    }
}