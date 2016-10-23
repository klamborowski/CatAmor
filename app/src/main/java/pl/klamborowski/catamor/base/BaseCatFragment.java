package pl.klamborowski.catamor.base;

import android.databinding.ViewDataBinding;

import cz.kinst.jakub.viewmodelbinding.ViewModelFragment;

/**
 * Created by Artur on 23.10.2016.
 */

public abstract class BaseCatFragment<T extends ViewDataBinding, VM extends BaseCatViewModel<T>>
        extends ViewModelFragment<T, VM> {
}
