package com.frenzydevel.manajerkeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);


        Fragment home = new Home();

        fragmentChange(home);

        BNav = findViewById(R.id.BNav);
        BNav.setOnItemSelectedListener(item -> {
        Fragment selectedFragment = new Home();

            switch (item.getItemId()){
                case R.id.add:
                    selectedFragment = new Add();
                    break;
                case R.id.Setting:
                    selectedFragment = new Setting();
                    break;
            }

            fragmentChange(selectedFragment);

            return true;
        });
    }

    // fungsi untuk mengubah fragment
    protected void fragmentChange(Fragment selectedFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameFragment, selectedFragment).commit();
    }
}