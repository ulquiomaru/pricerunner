package ulquiomaru.pricerunner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Locale;

public class ProductFragment extends Fragment {

    private static final String ARG_BARCODE_NUMBER = "barcode";

    TextView tvBarcode;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        tvBarcode = rootView.findViewById(R.id.tvBarcode);
        Bundle args = getArguments();
        if (args != null) {
            String barcodeNumber = args.getString(ARG_BARCODE_NUMBER, null);
            if (barcodeNumber != null) {
                tvBarcode.setText(String.format("Barcode Number: %s", barcodeNumber));

                if (!checkNetworkConnection()) {
                    Toast.makeText(requireActivity(), "INTERNET unavailable!", Toast.LENGTH_SHORT).show();
                }
                else {
                    tvBarcode.append("\nSearching.."); // debug
//                    new HTTPAsyncTask().execute(String.format(Locale.getDefault(), Util.urlBarkodoku, barcodeNumber));
                    new jSOUPAsyncTask().execute(String.format(Locale.getDefault(), Util.urlBarkodoku, barcodeNumber));
                }
            }
        }

        return rootView;
    }

    public static ProductFragment newInstance(String barcodeNumber) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BARCODE_NUMBER, barcodeNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private boolean checkNetworkConnection() {
        ConnectivityManager connMgr = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return Util.HttpGet(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid." + urls[0];
            }
        }

        @Override
        protected void onPostExecute(String result) {
//            try { if (isAdded()) JSONParser(result); }
//            catch (JSONException e) { e.printStackTrace(); }

            try { tvBarcode.setText(result.length()); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

    private class jSOUPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
                return doc.text();
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid." + urls[0];
            }
        }

        @Override
        protected void onPostExecute(String result) {
//            try { if (isAdded()) JSONParser(result); }
//            catch (JSONException e) { e.printStackTrace(); }

            try { tvBarcode.setText(result); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
