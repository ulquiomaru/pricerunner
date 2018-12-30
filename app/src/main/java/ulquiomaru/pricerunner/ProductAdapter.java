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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private ArrayList<Product> mDataset;
    private Context mContext;

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mLayout;
//        TextView tvName;
//        TextView tvPrice;
//        TextView tvBarcode;
//        TextView tvSource;

        ProductViewHolder(ConstraintLayout layout) {
            super(layout);
            mLayout = layout;
//            tvName = (TextView) layout.getViewById(R.id.tvName);
//            tvPrice = (TextView) layout.getViewById(R.id.tvPrice);
//            tvBarcode = (TextView) layout.getViewById(R.id.tvBarcode);
//            tvSource = (TextView) layout.getViewById(R.id.tvSource);
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
        // ...
        return new ProductViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        Product product = mDataset.get(position);
        ((TextView)holder.mLayout.getViewById(R.id.tvName)).setText(product.getName());
        ((TextView)holder.mLayout.getViewById(R.id.tvPrice)).setText(product.getPrice());
        ((TextView)holder.mLayout.getViewById(R.id.tvBarcode)).setText(product.getBarcode());
        ((TextView)holder.mLayout.getViewById(R.id.tvSource)).setText(product.getSource());
//        holder.tvName.setText(product.getName());
//        holder.tvPrice.setText(product.getPrice());
//        holder.tvBarcode.setText(product.getBarcode());
//        holder.tvSource.setText(product.getSource());
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity)
                    ((MainActivity) mContext).searchProduct(mDataset.get(holder.getAdapterPosition()).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
