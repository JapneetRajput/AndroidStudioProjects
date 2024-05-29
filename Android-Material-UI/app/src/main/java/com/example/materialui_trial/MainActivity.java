package com.example.materialui_trial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigationrail.NavigationRailView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        NavigationRailView navigationRailView=findViewById(R.id.navigation_rail);

        navigationRailView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.home){
                    loadFragment(new HomeFragment(),true);
                }
                else if(id==R.id.skills){
                    loadFragment(new SkillsFragment(),false);

                }
                else if(id==R.id.photos){
                    loadFragment(new PhotosFragment(),false);

                }
                else if(id==R.id.videos){
                    loadFragment(new VideosFragment(),false);

                }


                return false;
            }
        });

    }

    public void loadFragment(Fragment fragment, boolean flag){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        if(flag){
            ft.add(R.id.frame_layout,fragment);
        }
        else{
            ft.replace(R.id.frame_layout,fragment);
        }

        ft.commit();

    }
}