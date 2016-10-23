package pl.klamborowski.catamor.section.login;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.BR;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatActivity;
import pl.klamborowski.catamor.databinding.ActivityLoginBinding;

/**
 * Created by Artur on 23.10.2016.
 */

public class LoginActivity extends BaseCatActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig<>(R.layout.activity_login, LoginViewModel.class, BR.viewModel);
    }
}
