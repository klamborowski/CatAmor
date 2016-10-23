package pl.klamborowski.catamor.section.cat;

import android.content.Context;
import android.content.Intent;

import com.android.databinding.library.baseAdapters.BR;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatFragment;
import pl.klamborowski.catamor.databinding.FragmentCatBinding;

/**
 * Created by Artur on 23.10.2016.
 */

public class CatFragment extends BaseCatFragment<FragmentCatBinding, CatViewModel> {
    public static final String TAG = "CatFragment:TAG";

    public static void startCatActivity(Context context) {
        context.startActivity(new Intent(context, CatFragment.class));
    }

    @Override
    public ViewModelBindingConfig<CatViewModel> getViewModelBindingConfig() {
        return new ViewModelBindingConfig<>(R.layout.fragment_cat, CatViewModel.class, BR.viewModel);
    }
}
