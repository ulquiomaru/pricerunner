package ulquiomaru.pricerunner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> mDataset;
    private Context mContext;

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mLayout;

        ProductViewHolder(ConstraintLayout layout) {
            super(layout);
            mLayout = layout;
        }
    }

    ProductAdapter(ArrayList<Product> myDataset, Context context) {
        mDataset = myDataset;
        mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ProductViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        Product product = mDataset.get(position);
        ((TextView)holder.mLayout.getViewById(R.id.tvName)).setText(String.format(Locale.getDefault(), "Product: %s", product.getName()));
        ((TextView)holder.mLayout.getViewById(R.id.tvPrice)).setText(String.format(Locale.getDefault(), "Price: %s", product.getPrice()));
        ((TextView)holder.mLayout.getViewById(R.id.tvBarcode)).setText(String.format(Locale.getDefault(), "Barcode: %s", product.getBarcode()));
        ((TextView)holder.mLayout.getViewById(R.id.tvSource)).setText(String.format(Locale.getDefault(), "Source: %s", product.getSource()));
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity) {
                    ((MainActivity) mContext).searchProduct(mDataset.get(holder.getAdapterPosition()).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
