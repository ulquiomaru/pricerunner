package ulquiomaru.pricerunner;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

//    private final BarcodeFragment barcodeFragment = new BarcodeFragment();
//    private final AboutFragment aboutFragment = new AboutFragment();

    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 1:
//                return ProductFragment.newInstance();
//                break;
//            case 2:
//                return aboutFragment;
//                break;
//            default: // also case 0
//                return barcodeFragment;
//                break;
//        }
//    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    void init() {
        fragmentList.add(new BarcodeFragment());
        fragmentList.add(ProductFragment.newInstance(null));
        fragmentList.add(new AboutFragment());
    }

    void newProduct(String barcodeNumber) {
        fragmentList.set(1, ProductFragment.newInstance(barcodeNumber));
    }

}