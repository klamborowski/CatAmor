package pl.klamborowski.catamor.section.home;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatViewModel;
import pl.klamborowski.catamor.databinding.ActivityHomeBinding;
import pl.klamborowski.catamor.preferences.AccountManager;
import pl.klamborowski.catamor.section.cat.CatFragment;
import pl.klamborowski.catamor.section.favourites.FavouritesFragment;
import pl.klamborowski.catamor.section.login.LoginActivity;

/**
 * Created by Artur on 23.10.2016.
 */

public class HomeViewModel extends BaseCatViewModel<ActivityHomeBinding>
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onViewAttached(boolean firstAttachment) {
        super.onViewAttached(firstAttachment);
        openHome();
    }

    public void setupNavigationDrawer(AppCompatActivity activity) {
        activity.setSupportActionBar(getBinding().homeToolbar);
        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBarDrawerToggle = new ActionBarDrawerToggle(activity, getBinding().homeDrawerLayout,
                    R.string.drawer_open, R.string.drawer_close) {

                @Override
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }
            };
        }
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        getBinding().homeDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getBinding().menuNavView.getMenu().findItem(R.id.menu_home).setChecked(true);
        getBinding().menuNavView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        getBinding().menuNavView.getMenu().findItem(id).setChecked(true);
        switch (id) {
            case R.id.menu_home:
                openHome();
                break;
            case R.id.menu_fav:
                openFavourites();
                break;
            case R.id.menu_logout:
                logout();
                break;
            default:
                break;
        }
        closeDrawer();
        return false;
    }

    private void openHome() {
        ((HomeActivity) getActivity()).switchMainFragment(CatFragment.class, CatFragment.TAG);
    }

    private void openFavourites() {
        ((HomeActivity) getActivity()).switchMainFragment(FavouritesFragment.class, FavouritesFragment.TAG);
    }

    private void logout() {
        AccountManager.getInstance().flushAccount();
        LoginActivity.startLoginActivity(getContext());
        getActivity().finish();

    }

    private void closeDrawer() {
        getBinding().homeDrawerLayout.closeDrawer(GravityCompat.START);
    }
}
