package cuhk.group18.supermarketbuddy.content;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import cuhk.group18.supermarketbuddy.model.Coupon;
import cuhk.group18.supermarketbuddy.model.Location;
import cuhk.group18.supermarketbuddy.model.Offeritem;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MapServiceProvider {

    public static final String TAG = "MapServiceProvider";



    MapServiceProvider(){

    }

    public static Query getSpecialOffers(Location currentLocation) {
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //        Query query = mDatabaseReference.child("locations").child("type").equalTo("special-offer");
        Query query = mDatabaseReference.child("locations").orderByChild("type").equalTo("special-offer");

        return query;
    }
    public static Query getMyCoupons() {
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = mDatabaseReference.child("user_coupons").equalTo("currentUser");
        return query;
    }

    public static void saveCoupons(Coupon item){
        // get the auth user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("user_coupons").child(currentUser.getUid()).setValue(item);
    }

    public static void addToMyWishList(Location offerItemLocation){

    }


}
