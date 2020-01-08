package com.temwa.tech.trendy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.temwa.tech.trendy.model.AppNotification;
import com.temwa.tech.trendy.model.TrendyDeal;
import com.temwa.tech.trendy.model.User;
import com.temwa.tech.trendy.util.PreferenceKeys;

public class MainActivity extends AppCompatActivity implements IMainActivity, BottomNavigationViewEx.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        switch (menuItem.getItemId()) {


            case R.id.bottom_nav_home: {
                Log.d(TAG, "onNavigationItemSelected: HomeFragment.");
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, homeFragment, getString(R.string.tag_fragment_home));
                transaction.addToBackStack(getString(R.string.tag_fragment_home));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }

            case R.id.bottom_nav_search: {
                Log.d(TAG, "onNavigationItemSelected: SearchFragment.");
                SearchFragment searchDealsFragment = new SearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, searchDealsFragment, getString(R.string.tag_fragment_search_deals));
                transaction.addToBackStack(getString(R.string.tag_fragment_search_deals));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }

            case R.id.bottom_nav_notification: {
                Log.d(TAG, "onNavigationItemSelected: NotificationsFragment.");
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, notificationsFragment, getString(R.string.tag_fragment_notifications));
                transaction.addToBackStack(getString(R.string.tag_fragment_notifications));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }

            case R.id.bottom_nav_account: {
                Log.d(TAG, "onNavigationItemSelected: UserAccountFragment.");
                ViewProfileFragment userProfileFragment = new ViewProfileFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, userProfileFragment, getString(R.string.tag_fragment_view_profile));
                transaction.addToBackStack(getString(R.string.tag_fragment_view_profile));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
        }
        return false;
    }

    //widgets
    private BottomNavigationViewEx mBottomNavigationViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        //NavigationView navigationView = findViewById(R.id.navigation_view);
        //View headerView = navigationView.getHeaderView(0);
        //mHeaderImage = headerView.findViewById(R.id.header_image);
        //mDrawerLayout = findViewById(R.id.drawer_layout);

        isFirstLogin();
        //setHeaderImage();
        initBottomNavigationView();
        //setNavigationViewListener();
        init();
    }


    private void init(){
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, homeFragment, getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();
    }


    private void initBottomNavigationView() {
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view.");

        mBottomNavigationViewEx.enableAnimation(false);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }



    private void isFirstLogin() {
        Log.d(TAG, "isFirstLogin: checking if this is the first login.");

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);

        if (isFirstLogin) {
            Log.d(TAG, "isFirstLogin: launching alert dialog.");

            // launch the info dialog for first-time-users
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.first_time_user_message));
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d(TAG, "onClick: closing dialog");
                    // now that the user has logged in, save it to shared preferences so the dialog won't
                    // pop up again
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false);
                    editor.apply();
                    dialogInterface.dismiss();
                }
            });
            alertDialogBuilder.setTitle(" ");
            alertDialogBuilder.setIcon(R.drawable.trendy_logo);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void inflateViewProfileFragment(User user) {

        ViewProfileFragment fragment = new ViewProfileFragment();

        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_user), user);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, fragment, getString(R.string.tag_fragment_view_profile));
        transaction.addToBackStack(getString(R.string.tag_fragment_view_profile));
        transaction.commit();
    }

    @Override
    public void inflateViewTrendyDealFragment(TrendyDeal deal) {

        ViewTrendyDealFragment fragment = new ViewTrendyDealFragment();

        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_deal), deal);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, fragment, getString(R.string.tag_fragment_view_deal));
        transaction.addToBackStack(getString(R.string.tag_fragment_view_deal));
        transaction.commit();
    }

    @Override
    public void onNotificationSelected(AppNotification message) {
        ViewTrendyDealFragment fragment = new ViewTrendyDealFragment();

        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_message), message);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, fragment, getString(R.string.tag_fragment_chat));
        transaction.addToBackStack(getString(R.string.tag_fragment_chat));
        transaction.commit();
    }

    @Override
    public void onNotificationSelected(TrendyDeal trendyDeal) {

    }


}
