package cuhk.group18.supermarketbuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.gson.Gson;
import com.google.gson.internal.bind.DateTypeAdapter;

import cuhk.group18.supermarketbuddy.MyCouponFragment.OnMyCouponItemSelected;
import cuhk.group18.supermarketbuddy.model.Coupon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Coupon} and makes a call to the
 * specified {@link OnMyCouponItemSelected}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCouponRecyclerViewAdapter extends RecyclerView.Adapter<MyCouponRecyclerViewAdapter.ViewHolder> {


    final SimpleDateFormat DATE_JSON_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
    final SimpleDateFormat DATE_SIMPLE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private List<Coupon> mValues;
    private final OnMyCouponItemSelected mListener;
    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            mValues.add(dataSnapshot.getValue(Coupon.class));
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public MyCouponRecyclerViewAdapter(Query items, OnMyCouponItemSelected listener) {
        mValues = new ArrayList<Coupon>();
        items.addChildEventListener(childEventListener);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mycoupon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mDetailView.setText(mValues.get(position).getDetails());

        String format = mValues.get(position).getExpirydate();
        if (format!=null){
            format = format.substring(0,10);
        }
        holder.mExpiryDate.setText(format);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onMyCouponItemSelected(holder.mItem);
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
        public final TextView mDetailView;
        public final TextView mExpiryDate;
        public Coupon mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mDetailView = (TextView) view.findViewById(R.id.coupon_detail);
            mExpiryDate = (TextView) view.findViewById(R.id.coupon_expiry);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mExpiryDate.getText() + "'";
        }
    }
}
