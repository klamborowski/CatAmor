package pl.klamborowski.catamor.section.cat;

import android.content.Context;
import android.content.Intent;

import com.android.databinding.library.baseAdapters.BR;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatActivity;
import pl.klamborowski.catamor.databinding.ActivityCatBinding;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatActivity extends BaseCatActivity<ActivityCatBinding, CatViewModel> {
    public static void startCatActivity(Context context) {
        context.startActivity(new Intent(context, CatActivity.class));
    }

    @Override
    public ViewModelBindingConfig<CatViewModel> getViewModelBindingConfig() {
        return new ViewModelBindingConfig<>(R.layout.activity_cat, CatViewModel.class, BR.viewModel);
    }
}
