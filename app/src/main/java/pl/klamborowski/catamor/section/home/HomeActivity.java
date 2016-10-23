package pl.klamborowski.catamor.section.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.BR;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatActivity;
import pl.klamborowski.catamor.base.FragmentAnimationEnum;
import pl.klamborowski.catamor.databinding.ActivityHomeBinding;

/**
 * Created by Artur on 23.10.2016.
 */

public class HomeActivity extends BaseCatActivity<ActivityHomeBinding, HomeViewModel> {
    public static void startHomeActivity(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.activity_home, HomeViewModel.class, BR.viewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setupNavigationDrawer(this);
    }

    public Fragment switchMainFragment(@NonNull Class fragmentClass, @NonNull String tag) {
        return switchFragment(fragmentClass, tag, R.id.menu_fragment_container,
                FragmentAnimationEnum.FROM_BOTTOM, null, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            toggleDrawer();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void toggleDrawer() {
        if (drawerIsOpen()) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    protected boolean drawerIsOpen() {
        return getBinding().homeDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeDrawer() {
        getBinding().homeDrawerLayout.closeDrawer(GravityCompat.START);
    }

    protected void openDrawer() {
        getBinding().homeDrawerLayout.openDrawer(GravityCompat.START);
    }
}
