package com.example.menaadel.salesapp.Features.SalesScreen;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.menaadel.salesapp.CustomDialog.ConfirmationDialog;
import com.example.menaadel.salesapp.DataStorage.sharedPreference.DataSharedPreference;
import com.example.menaadel.salesapp.Features.SalesScreen.DailySalesReportFragment.DailySalesReportFragment;
import com.example.menaadel.salesapp.Features.SalesScreen.SalesFragment.SalesScreenFragment;
import com.example.menaadel.salesapp.R;
import com.example.menaadel.salesapp.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,DialogInterface.OnClickListener{

    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    ActionBarDrawerToggle mToggle;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ConfirmationDialog confirmationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.screen_area,new SalesScreenFragment());
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        switch (id){
            case R.id.sales_screen:
                fragment = new SalesScreenFragment();break;
            case R.id.daily_sales_report:
                fragment = new DailySalesReportFragment();break;
            case R.id.logout:
                confirmationDialog = new ConfirmationDialog(this, getString(R.string.logout_alert), this);break;
            default:
                fragment = new SalesScreenFragment();break;
        }

            FragmentManager fragmentManager= getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.screen_area,fragment);
            ft.commit();

        mDrawer.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        confirmationDialog.Dismiss();

        DataSharedPreference preference = new DataSharedPreference(this);
        preference.removeSharedPreference(Constants.FIRST_LOG_IN);
        finish();
    }
}
