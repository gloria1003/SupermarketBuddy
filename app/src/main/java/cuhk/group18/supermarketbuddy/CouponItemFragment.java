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

import cuhk.group18.supermarketbuddy.dummy.DummyContent;
import cuhk.group18.supermarketbuddy.dummy.DummyContent.CouponItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnCouponListItemSelected}
 * interface.
 */
public class CouponItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnCouponListItemSelected mListener;
    private OnCollectButtonClicked mCollectButtonClickedListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CouponItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CouponItemFragment newInstance(int columnCount) {
        CouponItemFragment fragment = new CouponItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_couponitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new CouponItemRecyclerViewAdapter(DummyContent.ITEMS, mListener,mCollectButtonClickedListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCouponListItemSelected) {
            mListener = (OnCouponListItemSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCouponListItemSelected");
        }
        if (context instanceof  OnCollectButtonClicked){
            mCollectButtonClickedListener = (OnCollectButtonClicked) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnCollectButtonClicked");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mCollectButtonClickedListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCouponListItemSelected {
        // TODO: Update argument type and name
        void onCouponItemSelected(CouponItem item);
    }

    public interface OnCollectButtonClicked{
        void onCollectCoupon(CouponItem item);
    }
}
