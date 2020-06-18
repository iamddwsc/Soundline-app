package com.saber.Soundline.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.saber.Soundline.Fragments.HomeFragment;
import com.saber.Soundline.Fragments.LibraryFragment;
import com.saber.Soundline.Fragments.SearchFragment;
import com.saber.Soundline.R;

public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameHomeContainer,new HomeFragment(), HomeFragment.class.getSimpleName()).commit();
        init();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_home: {
                        Fragment frag_search = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
                        Fragment frag_library = fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName());
                        if (frag_search != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        }
                        if (frag_library != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        }
                        break;
                    }
                    case R.id.item_search: {
                        Fragment frag_search = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
                        Fragment frag_library = fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName());
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        if (frag_search != null && frag_library != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                        } else if (frag_search == null && frag_library != null){
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer, new SearchFragment(), SearchFragment.class.getSimpleName()).commit();
                        } else {
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer, new SearchFragment(), SearchFragment.class.getSimpleName()).commit();
                        }
                        break;
                    }
                    case  R.id.item_library: {
                        Fragment frag_search = fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName());
                        Fragment frag_library = fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName());
                        Fragment frag_home = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();

                        if (frag_search != null && frag_library != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(LibraryFragment.class.getSimpleName())).commit();
                        } else if (frag_search != null && frag_library == null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(SearchFragment.class.getSimpleName())).commit();
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer, new LibraryFragment(), LibraryFragment.class.getSimpleName()).commit();
                        } else {
                            fragmentManager.beginTransaction().add(R.id.frameHomeContainer, new LibraryFragment(), LibraryFragment.class.getSimpleName()).commit();
                        }
                        if (frag_search != null && frag_library != null && frag_home != null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName())).commit();
                        }
                        break;
                    }
                }

                return true;
            }
        });
    }
}