package cuhk.group18.supermarketbuddy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import cuhk.group18.supermarketbuddy.content.SupermarketServiceProvider;
import cuhk.group18.supermarketbuddy.model.Location;
import cuhk.group18.supermarketbuddy.model.Offeritem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Offeritem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class OfferItemRecyclerViewAdapter extends RecyclerView.Adapter<OfferItemRecyclerViewAdapter.ViewHolder> {

    public static final String TAG = "OfferItemRecycler";

    private final List<Location> mValues;

    private final OfferitemFragment.OnAddToWishListButtonClicked mCollectButtonClickedListener;
    public static int VIEW_TYPE_COLLECT = 0;
    public static int VIEW_TYPE_DISPLAY = 1 ;

     private ChildEventListener mChildEventListener =
         new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 mValues.add(dataSnapshot.getValue(Location.class));
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


    public OfferItemRecyclerViewAdapter(Location currentLocation,
                                        OfferitemFragment.OnAddToWishListButtonClicked collectButtonClickedListener) {
        mValues = new ArrayList<Location>();

        Query specialOffers = SupermarketServiceProvider.getSpecialOffers(currentLocation);
        specialOffers.addChildEventListener(mChildEventListener);


        mCollectButtonClickedListener = collectButtonClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_offeritem, parent, false);
        // Set the different display according to the view type



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mDetailView.setText(mValues.get(position).get);
        Offeritem item = holder.mItem.getOfferitem();
        holder.mContentView.setText(item.getDetails());


        holder.mButtonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mCollectButtonClickedListener){
                    mCollectButtonClickedListener.OnAddToWishListButtonClicked(holder.mItem);

                }
            }
        });

//        URL url = null;
//        Bitmap bmp = null;
//        try {
//            Log.d(TAG,item.getImageUrl());
//            url = new URL(item.getImageUrl());
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (MalformedURLException e) {
//            Log.e(TAG, "error",e);
//        } catch (IOException e) {
//            Log.e(TAG, "error",e);
//        }
//        if (bmp!=null) {
//            holder.offerImage.setImageBitmap(bmp);
//        }


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
        public final ImageView offerImage;
        public Location mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mButtonCollect = (ImageButton) view.findViewById(R.id.button_collect);
            offerImage = (ImageView) view.findViewById(R.id.offer_item_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
