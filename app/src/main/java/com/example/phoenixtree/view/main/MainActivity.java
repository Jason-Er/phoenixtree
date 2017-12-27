package com.example.phoenixtree.view.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.phoenixtree.R;

import com.example.phoenixtree.util.commonInterface.NavigationInterface;
import com.example.phoenixtree.util.commonInterface.StagePlayInfo;
import com.example.phoenixtree.view.browse.BrowseFragment;
import com.example.phoenixtree.view.compose.ComposeFragment;
import com.example.phoenixtree.view.drawerNavigation.DrawerNavController;
import com.example.phoenixtree.view.drawerNavigation.SystemUIController;
import com.example.phoenixtree.view.login.LoginFragment;
import com.example.phoenixtree.view.participate.ParticipateFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationInterface,
        HasSupportFragmentInjector {

    private final String TAG = "MainActivity";

    @BindView(R.id.nav_drawer)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    SystemUIController systemUIController;

    final String BROWSE = "browse";
    final String PARTICIPATE = "participate";
    final String COMPOSE = "compose";
    final String LOGIN = "login";
    final int containerId = R.id.main_container;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null)
            navigateToBrowse();

        systemUIController.hide();
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
        getMenuInflater().inflate(R.menu.user_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            navigateToLogin();
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
                navigateToCompose( getCurrentStagePlayId() );
                break;
            case R.id.nav_participate:
                navigateToParticipate( getCurrentStagePlayId() );
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
    @Override
    public void navigateToBrowse() {
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            ((NavigationInterface) drawerLayout.getChildAt(i)).navigateToBrowse();
        }
        currentFragment = new BrowseFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, currentFragment, BROWSE)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void navigateToParticipate(long stagePlayId) {
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            ((NavigationInterface) drawerLayout.getChildAt(i)).navigateToParticipate(stagePlayId);
        }
        currentFragment = ParticipateFragment.create(stagePlayId);
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, currentFragment, PARTICIPATE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void navigateToCompose(long stagePlayId) {
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            ((NavigationInterface) drawerLayout.getChildAt(i)).navigateToCompose(stagePlayId);
        }
        currentFragment = ComposeFragment.create(stagePlayId);
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, currentFragment, COMPOSE + stagePlayId)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void navigateToLogin() {
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            ((NavigationInterface) drawerLayout.getChildAt(i)).navigateToLogin();
        }
        currentFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, currentFragment, LOGIN)
                .commitAllowingStateLoss();
    }

    @Override
    public void navigateToProfile() {
        for (int i = 0; i < drawerLayout.getChildCount(); i++) {
            ((NavigationInterface) drawerLayout.getChildAt(i)).navigateToProfile();
        }
        // TODO: 12/27/2017 need add fragment profile
    }

    long getCurrentStagePlayId() {
        if(currentFragment instanceof StagePlayInfo) {
            return ((StagePlayInfo) currentFragment).getStagePlayID();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @OnClick({R.id.btn_player_control_pre, R.id.btn_player_control_play, R.id.btn_player_control_next,
            R.id.btn_user_login, R.id.btn_drawer_toggle, R.id.btn_nav_up})
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
            case R.id.btn_user_login:
                navigateToLogin();
                break;
            case R.id.btn_drawer_toggle:
                break;
            case R.id.btn_nav_up:
                Log.i(TAG, "click on navigation up");
                break;
        }
    }

}
