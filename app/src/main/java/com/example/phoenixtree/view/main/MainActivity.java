package com.example.phoenixtree.view.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.phoenixtree.R;

import com.example.phoenixtree.view.drawerNavigation.NavigationController;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HasSupportFragmentInjector {

    private final String TAG = "MainActivity";

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.app_centre_layout)
    CoordinatorLayout coordinatorLayout;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    NavigationController navigationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationController.setNavigationView(navigationView);
        navigationController.setCoordinatorLayout(coordinatorLayout);

        if(savedInstanceState == null)
            navigateToBrowse();

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
        getMenuInflater().inflate(R.menu.participate, menu);
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

        switch (id) {
            case R.id.nav_browse:
                navigateToBrowse();
                break;
            case R.id.nav_compose:
                navigateToCompose( navigationController.getCurrentStagePlayId() );
                break;
            case R.id.nav_participate:
                navigateToParticipate( navigationController.getCurrentStagePlayId() );
                break;
            case R.id.nav_profile:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    // navigation to other fragments
    public void navigateToBrowse() {
        navigationController.navigateToBrowse();
    }

    public void navigateToParticipate(long stagePlayId) {
        navigationController.navigateToParticipate(stagePlayId);
    }

    public void navigateToCompose(long stagePlayId) {
        navigationController.navigateToCompose(stagePlayId);
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    /*
    @OnClick({R.id.btn_player_control_pre, R.id.btn_player_control_play, R.id.btn_player_control_next})
    public void responseButtonOnClick(ImageButton button) {
        switch (button.getId()) {
            case R.id.btn_player_control_pre:
                Log.i(TAG, "click on pre");
                break;
            case R.id.btn_player_control_play:
                Log.i(TAG, "click on play");
                break;
            case R.id.btn_player_control_next:
                Log.i(TAG, "click on next");
                break;
        }
    }
    */
}
