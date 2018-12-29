package ulquiomaru.pricerunner;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;
    private ViewPager viewPager;
    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.fragmentContainer);
        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_barcode:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.nav_product:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.nav_about:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) prevMenuItem.setChecked(false);
                else bottomNavigationView.getMenu().getItem(0).setChecked(false);
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {  }
        });

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.init();
        viewPager.setAdapter(viewPagerAdapter);

    }

    void newProduct(String barcodeNumber) {
//        viewPager.setCurrentItem(1);
//        loadFragment(ProductFragment.newInstance(barcodeNumber));
        viewPagerAdapter.newProduct(barcodeNumber);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragmentContainer, fragment);
                ft.commit();
            }
        }
    }
}
