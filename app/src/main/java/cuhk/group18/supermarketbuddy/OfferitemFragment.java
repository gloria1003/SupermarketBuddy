package cuhk.group18.supermarketbuddy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import cuhk.group18.supermarketbuddy.model.Coord;
import cuhk.group18.supermarketbuddy.model.Location;
import cuhk.group18.supermarketbuddy.model.Offeritem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnOfferitemSelected}
 * interface.
 */
public class OfferitemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnAddToWishListButtonClicked mButtonClickedListener;
    private CurrentLocationListener locationListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public OfferitemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static OfferitemFragment newInstance(int columnCount) {
        OfferitemFragment fragment = new OfferitemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offeritem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            Location l = new Location();
            l.setCoord(new Coord(0,0));
            recyclerView.setAdapter(new OfferItemRecyclerViewAdapter(l, mButtonClickedListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnAddToWishListButtonClicked){
            mButtonClickedListener = (OnAddToWishListButtonClicked) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddToWishListButtonClicked");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mButtonClickedListener = null;
    }


    public interface OnAddToWishListButtonClicked {
        void OnAddToWishListButtonClicked(Location item);
    }

    public class CurrentLocationListener{
        // refresh the list
        public void onCurrenLocationUpdated(Location lastLocation){
            // update the list

        }
    }
}
