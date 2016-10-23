package pl.klamborowski.catamor.section.cat;

import com.android.databinding.library.baseAdapters.BR;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatActivity;
import pl.klamborowski.catamor.databinding.ActivityCatBinding;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatActivity extends BaseCatActivity<ActivityCatBinding, CatViewModel> {
    @Override
    public ViewModelBindingConfig<CatViewModel> getViewModelBindingConfig() {
        return new ViewModelBindingConfig<>(R.layout.activity_cat, CatViewModel.class, BR.viewModel);
    }
}
