package com.example.unitconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.unitconverter.Fragments.LengthFragment;
import com.example.unitconverter.Fragments.MassFragment;
import com.example.unitconverter.Fragments.SpeedFragment;
import com.example.unitconverter.Fragments.TemperatureFragment;
import com.example.unitconverter.Fragments.TimeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;
    SpeedFragment speedFragment;
    LengthFragment lengthFragment;
    MassFragment massFragment;
    TemperatureFragment temperatureFragment;
    TimeFragment timeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavBar = findViewById(R.id.bottomNavigationView);

        speedFragment = new SpeedFragment();
        lengthFragment = new LengthFragment();
        massFragment = new MassFragment();
        temperatureFragment= new TemperatureFragment();
        timeFragment = new TimeFragment();

        bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()){

                    case R.id.speed:
                        setCurrentFragment(speedFragment);
                        break;
                    case R.id.length:
                        setCurrentFragment(lengthFragment);
                        break;
                    case R.id.mass:
                        setCurrentFragment(massFragment);
                        break;
                    case R.id.temperature:
                        setCurrentFragment(temperatureFragment);
                        break;
                    case R.id.time:
                        setCurrentFragment(timeFragment);
                        break;

                }
                return true;
            }
        });

       /* bottomNavBar.setOnItemSelectedListener {
            when(it.itemId){

                R.id1.speed->setCurrentFragment(speedFragment)
                R.id.length->setCurrentFragment(lengthFragment)
                R.id.mass->setCurrentFragment(massFragment)
                R.id.temperature -> setCurrentFragment(temperatureFragment)
                R.id.time -> setCurrentFragment(timeFragment)

            }
            true
        }*/

    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}