package pl.klamborowski.catamor.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;

/**
 * Created by Artur on 23.10.2016.
 */

public abstract class BaseCatActivity<T extends ViewDataBinding, VM extends BaseCatViewModel<T>>
        extends ViewModelActivity<T, VM> {
    protected Fragment switchFragment(@NonNull Class fragmentClass, @NonNull String tag, int fragmentContainerId,
                                      FragmentAnimationEnum animationEnum, Bundle bundle) {
        return switchFragment(fragmentClass, tag, fragmentContainerId,
                animationEnum == null ? FragmentAnimationEnum.NONE.getEnterAnimationResId() : animationEnum.getEnterAnimationResId(),
                animationEnum == null ? FragmentAnimationEnum.NONE.getExitAnimationResId() : animationEnum.getExitAnimationResId(),
                bundle, false);
    }

    protected Fragment switchFragment(@NonNull Class fragmentClass, @NonNull String tag, int fragmentContainerId,
                                      FragmentAnimationEnum animationEnum, Bundle bundle, boolean addTopBackStack) {
        return switchFragment(fragmentClass, tag, fragmentContainerId,
                animationEnum == null ? FragmentAnimationEnum.NONE.getEnterAnimationResId() : animationEnum.getEnterAnimationResId(),
                animationEnum == null ? FragmentAnimationEnum.NONE.getExitAnimationResId() : animationEnum.getExitAnimationResId(),
                bundle, addTopBackStack);
    }

    protected Fragment switchFragment(@NonNull Class clz, @NonNull String tag, int fragmentContainerId,
                                      int enterAnimationRes, int exitAnimationRes, Bundle bundle,
                                      boolean addTopBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        ft.setCustomAnimations(enterAnimationRes, exitAnimationRes);
        Fragment existingFragment = fm.findFragmentByTag(tag);
        if (existingFragment == null) {
            existingFragment = Fragment.instantiate(this, clz.getName());
        }

        Bundle arguments = existingFragment.getArguments();
        if (arguments == null) {
            existingFragment.setArguments(bundle != null ? bundle : new Bundle());
        } else {
            if (bundle != null) {
                arguments.clear();
                arguments.putAll(bundle);
            }
        }
        ft.replace(fragmentContainerId, existingFragment, tag);
        if (addTopBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
        return existingFragment;
    }


    protected void removeFragment(@NonNull String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragmentById = fm.findFragmentByTag(tag);
        if (fragmentById != null) {
            ft.remove(fragmentById);
            ft.commit();
        }
    }
}
