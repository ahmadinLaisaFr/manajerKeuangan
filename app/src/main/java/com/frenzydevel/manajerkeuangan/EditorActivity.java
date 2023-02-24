package com.frenzydevel.manajerkeuangan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frenzydevel.manajerkeuangan.helper.UangHelper;

import java.util.HashMap;

public class EditorActivity extends AppCompatActivity {

    TextView etProfile_id, etIncome, etOutcome;
    Button btnSave;
    UangHelper tbluang = new UangHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        btnSave = findViewById(R.id.btnSave);
        etProfile_id = findViewById(R.id.etProfileId);
        etIncome = findViewById(R.id.etIncome);
        etOutcome = findViewById(R.id.etOutcome);

        btnSave.setOnClickListener(view -> {
            try {
                save();
            }catch (Exception e){
                Log.e("Saving", e.getMessage());
            }
        });
    }

    public void save(){
        String profile_id = etProfile_id.getText().toString();
        String income = etIncome.getText().toString();
        String outcome = etOutcome.getText().toString();
        if (profile_id.equals("") || income.equals("") || outcome.equals("")){
            Toast.makeText(this, "Data tidak boleh kosong !", Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, String> data = new HashMap<>();
            data.put("profile_id", profile_id);
            data.put("income", income);
            data.put("outcome", outcome);

            // Toast.makeText(this, data.get("income"), Toast.LENGTH_SHORT).show();
            try {
                tbluang.insert(data, EditorActivity.this);
                finish();
            }catch (Exception e){
                Toast.makeText(this, e.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        }
    }
}