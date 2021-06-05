package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation= findViewById(R.id.btm_nav);


        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_precaution));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_news));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment= null;
                switch (item.getId()){
                    case 1:
                        fragment= new HomeFragment();
                        break;
                    case 2:
                        fragment = new PrecautionFragment();
                        break;
                    case 3:
                        fragment = new NewsFragment();
                        break;
                }

                loadFragment(fragment);
            }

        });

        bottomNavigation.setCount(1," ");
        bottomNavigation.show(2,false);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext()
                ,"Page " +item.getId()
                ,Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext()
                ," " +item.getId()
                ,Toast.LENGTH_SHORT).show();
            }
        });

}

    private void loadFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

}

// {
//
//        HomeFragment fragment= new HomeFragment();
//        FragmentManager manager= getSupportFragmentManager();
//        manager.beginTransaction().add(R.id.mainLayout,fragment).commit();
//
//    }








