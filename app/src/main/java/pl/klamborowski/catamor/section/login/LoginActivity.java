package pl.klamborowski.catamor.section.login;

import android.content.Context;
import android.content.Intent;

import cz.kinst.jakub.viewmodelbinding.ViewModelBindingConfig;
import pl.klamborowski.catamor.BR;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatActivity;
import pl.klamborowski.catamor.databinding.ActivityLoginBinding;

/**
 * Created by Artur on 23.10.2016.
 */

public class LoginActivity extends BaseCatActivity<ActivityLoginBinding, LoginViewModel> {
    public static void startLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig<>(R.layout.activity_login, LoginViewModel.class, BR.viewModel);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getViewModel().onActivityResult(requestCode, resultCode, data);

    }
}
