package ulquiomaru.pricerunner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ProductFragment extends Fragment {

    private static final String ARG_BARCODE_NUMBER = "barcode";
    private static final String urlBarkodoku = "https://www.barkodoku.com/%s";
    private static final String idResultCount = "ContentPlaceHolder1_UcBarkodGetir_lblCount";
    private static final String classElement = "col-xs-24 col-sm-24 col-md-9 excerpet";
    private static final String hrefName = "a[href]";
    private static final String classBarcode = "fa fa-barcode";
    private static final String classPrice = "glyphicon glyphicon-calendar";
    private static final String classTimeValidated = "glyphicon glyphicon-time";
    private static final String classSource = "glyphicon glyphicon-tags";
    private static final String idOrigin = "ContentPlaceHolder1_UcBarkodGetir_lblMensei";
    private static final String idProducerCode = "ContentPlaceHolder1_UcBarkodGetir_lblUretici";
    private static final String idProductCode = "ContentPlaceHolder1_UcBarkodGetir_lblUrun";

    TextView tvBarcode;
    RecyclerView rvProducts;

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
                tvBarcode.setText(String.format(Locale.getDefault(),"Barcode Number: %s", barcodeNumber));

                if (!checkNetworkConnection()) {
                    Toast.makeText(requireActivity(), "INTERNET unavailable!", Toast.LENGTH_SHORT).show();
                }
                else {
                    rvProducts = rootView.findViewById(R.id.rvProducts);
                    Toast.makeText(requireActivity(), String.format(Locale.getDefault(),"Searching Barcode %s", barcodeNumber), Toast.LENGTH_SHORT).show();
//                    tvBarcode.append("\nSearching.."); // debug
                    new jSOUPAsyncTask().execute(String.format(Locale.getDefault(), urlBarkodoku, barcodeNumber));
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

    private class jSOUPAsyncTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... urls) {
            try {
                return Jsoup.connect(urls[0]).get();
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Document doc) {
            parseDocument(doc);
        }
    }

    private void parseDocument(Document document) { // barkodoku.com parser
        if (document == null) {
            Toast.makeText(requireActivity(), "Unable to retrieve search results.", Toast.LENGTH_LONG).show();
        }
        else if (isAdded()) {
            Element noResults = document.getElementById(idResultCount);
            if (noResults == null || noResults.text().equals("0")) {
                Toast.makeText(requireActivity(), "No products found in the database.", Toast.LENGTH_LONG).show();
            }
            else {
                Elements productResults = document.getElementsByClass(classElement);
                ArrayList<Product> productList = new ArrayList<>();
                for (Element result : productResults) {
                    Elements e = result.getElementsByTag("p");
                    if (!result.children().first().children().isEmpty()) { // Product Info
                        Product product = new Product();

                        product.setName(result.select(hrefName).first().text());
                        product.setBarcode(e.get(0).getElementsByTag("span").first().text());
                        product.setTimeValidated(e.get(1).getElementsByTag("span").first().text());
                        product.setPrice(e.get(2).getElementsByTag("span").first().text());
                        product.setSource(e.get(3).getElementsByTag("span").first().text());

                        productList.add(product);
                    }
                    else { // Barcode Info for the Last Product
                        Product product = productList.get(productList.size() - 1);

                        product.setOrigin(e.get(0).getElementsByTag("span").first().text());
                        product.setProducerCode(e.get(1).getElementsByTag("span").first().text());
                        product.setProductCode(e.get(2).getElementsByTag("span").first().text());
                    }
                }

                rvProducts.setLayoutManager(new LinearLayoutManager(requireActivity()));
                rvProducts.setAdapter(new ProductAdapter(productList));
            }
        }
    }
}
