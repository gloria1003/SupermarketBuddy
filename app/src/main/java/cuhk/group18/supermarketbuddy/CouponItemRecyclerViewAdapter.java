package cuhk.group18.supermarketbuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import cuhk.group18.supermarketbuddy.dummy.DummyContent.CouponItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CouponItem} and makes a call to the
 * specified {@link CouponItemFragment.OnCouponListItemSelected}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CouponItemRecyclerViewAdapter extends RecyclerView.Adapter<CouponItemRecyclerViewAdapter.ViewHolder> {

    private final List<CouponItem> mValues;
    private final CouponItemFragment.OnCouponListItemSelected mListener;
    private final CouponItemFragment.OnCollectButtonClicked mCollectButtonClickedListener;
    public static int VIEW_TYPE_COLLECT = 0;
    public static int VIEW_TYPE_DISPLAY = 1 ;

    public CouponItemRecyclerViewAdapter(List<CouponItem> items, CouponItemFragment.OnCouponListItemSelected listener,
                                         CouponItemFragment.OnCollectButtonClicked collectButtonClickedListener) {
        mValues = items;
        mListener = listener;
        mCollectButtonClickedListener = collectButtonClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_couponitem, parent, false);
        // Set the different display according to the view type



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onCouponItemSelected(holder.mItem);
                }
            }
        });
        holder.mButtonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mCollectButtonClickedListener){
                    mCollectButtonClickedListener.onCollectCoupon(holder.mItem);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageButton mButtonCollect;
        public CouponItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mButtonCollect = (ImageButton) view.findViewById(R.id.button_collect);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
