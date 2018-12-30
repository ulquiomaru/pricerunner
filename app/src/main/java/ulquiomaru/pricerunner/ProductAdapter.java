package ulquiomaru.pricerunner;

import android.content.Context;
import android.support.annotation.NonNull;
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

        TextView mTextView;

        ProductViewHolder(TextView v) {
            super(v);
            mTextView = v;
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
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        // ...
        return new ProductViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, final int position) {
        holder.mTextView.setText(mDataset.get(position).toString());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
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
