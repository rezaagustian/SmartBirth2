package com.futech.smartbirth;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {



        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.app_name);

                    //mTextMessage.setText(R.string.title_home);

                    return true;
                case R.id.navigation_news:

                    fragment = new BeritaFragment();// BeritaFragment();
                    loadFragment(fragment);


                    toolbar.setTitle(R.string.title_news);
                    return true;

                case R.id.navigation_profile:


                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_profile);

                    return true;

                case R.id.navigation_stats:

                    fragment = new StatistikFragment();// PerkembanganFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_stats);
                    return true;

                case R.id.navigation_contact:

                    fragment = new KontakFragment();
                    loadFragment(fragment);
                    toolbar.setTitle(R.string.title_contact);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new HomeFragment());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add) {

            startActivity(new Intent(this, OCRActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}