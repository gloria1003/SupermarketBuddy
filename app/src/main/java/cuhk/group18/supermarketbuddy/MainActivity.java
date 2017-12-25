package cuhk.group18.supermarketbuddy;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import cuhk.group18.supermarketbuddy.dummy.DummyContent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        HomeFragment.OnFragmentInteractionListener,
        MyCouponFragment.OnMyCouponSelectedListener,
        CouponItemFragment.OnCouponListItemSelected,
        CouponItemFragment.OnCollectButtonClicked
{

    private DatabaseReference mDatabaseReference;
//    private CouponListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // set the fragment
        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.Fragment fragment = null;
        if (id == R.id.nav_home ){
           fragment = new HomeFragment();
        }
        else if (id == R.id.nav_coupon) {
            fragment = new MyCouponFragment();

        } else if (id == R.id.nav_wishlist) {


        } else if (id == R.id.nav_productcat) {

        } else if (id == R.id.nav_offer) {

        }
        if (fragment!=null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onMyCouponSelected(Uri uri) {
        Toast toast = Toast.makeText(this, uri.getEncodedFragment(), Toast.LENGTH_SHORT);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // Implement for the Coupon
    @Override
    public void onCouponItemSelected(DummyContent.CouponItem item) {

        Toast toast = Toast.makeText(this, item.content, Toast.LENGTH_SHORT);
    }

    @Override
    public void onCollectCoupon(DummyContent.CouponItem item) {
        showDialog("Coupon Collected!","You have collected the coupon " + item.content);
    }
    // TODO: Show error on screen with an alert dialog
    private void showDialog(String title, String message) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
