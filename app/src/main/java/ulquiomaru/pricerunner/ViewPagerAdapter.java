package ulquiomaru.pricerunner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

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

    void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    void newProduct(ProductFragment productFragment) {
        fragmentList.set(1, productFragment);
    }

}