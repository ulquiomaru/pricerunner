package ulquiomaru.pricerunner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class BarcodeFragment extends Fragment {

    private MainActivity activity;

    public BarcodeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_barcode, container, false);

        activity = (MainActivity) getActivity();
        AppCompatButton btnScan = rootView.findViewById(R.id.btnScan);
        AppCompatButton btnInput = rootView.findViewById(R.id.btnInput);

        btnInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getContext();
                LayoutInflater li = LayoutInflater.from(context);
                View viewBarcodeInput = li.inflate(R.layout.dialog_barcode_input, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(viewBarcodeInput);

                final EditText etBarcodeInput = viewBarcodeInput.findViewById(R.id.etBarcodeInput);
                alertDialogBuilder
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.newProduct(etBarcodeInput.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });





        return rootView;
    }

}
