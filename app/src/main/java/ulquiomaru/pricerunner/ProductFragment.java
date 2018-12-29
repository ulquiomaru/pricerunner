package ulquiomaru.pricerunner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import ulquiomaru.pricerunner.R;

public class ProductFragment extends Fragment {

    private static final String ARG_BARCODE_NUMBER = "barcode";

    public ProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        TextView tvBarcode = rootView.findViewById(R.id.tvBarcode);
        String barcodeNumber = getArguments().getString(ARG_BARCODE_NUMBER, null);
        tvBarcode.setText("Barcode Number: " + barcodeNumber);




        return rootView;
    }

    public static ProductFragment newInstance(String barcodeNumber) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BARCODE_NUMBER, barcodeNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
