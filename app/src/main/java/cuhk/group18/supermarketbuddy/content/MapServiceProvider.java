package cuhk.group18.supermarketbuddy.content;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import cuhk.group18.supermarketbuddy.model.Location;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MapServiceProvider {

    public static final String TAG = "MapServiceProvider";
    private static DatabaseReference mDatabaseReference;


    public static Query getSpecialOffers(Location currentLocation) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        Query query = mDatabaseReference.child("locations").child("type").equalTo("special-offer");
        Query query = mDatabaseReference.child("locations").orderByChild("type").equalTo("special-offer");

        return query;
    }





}
