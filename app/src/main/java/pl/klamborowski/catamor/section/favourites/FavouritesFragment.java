package pl.klamborowski.catamor.section.favourites;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.BR;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatFragment;
import pl.klamborowski.catamor.databinding.FragmentFavourtiesBinding;

/**
 * Created by Artur on 24.10.2016.
 */

public class FavouritesFragment extends BaseCatFragment<FragmentFavourtiesBinding, FavouritesViewModel> {
    public static final String TAG = "FavouritesFragment:TAG";

    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_favourties, FavouritesViewModel.class, BR.viewModel);
    }
}
