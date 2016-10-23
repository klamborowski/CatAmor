package pl.klamborowski.catamor.base;

import android.databinding.ViewDataBinding;

import cz.kinst.jakub.viewmodelbinding.ViewModelActivity;

/**
 * Created by Artur on 23.10.2016.
 */

public abstract class BaseCatActivity<T extends ViewDataBinding, VM extends BaseCatViewModel<T>>
        extends ViewModelActivity<T, VM> {
}
