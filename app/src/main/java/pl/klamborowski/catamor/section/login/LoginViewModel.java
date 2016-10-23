package pl.klamborowski.catamor.section.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pl.klamborowski.catamor.R;
import pl.klamborowski.catamor.base.BaseCatViewModel;
import pl.klamborowski.catamor.databinding.ActivityLoginBinding;
import pl.klamborowski.catamor.model.Account;
import pl.klamborowski.catamor.preferences.AccountManager;
import pl.klamborowski.catamor.section.cat.CatActivity;
import pl.klamborowski.catamor.util.DialogHelper;

/**
 * Created by Artur on 23.10.2016.
 */

public class LoginViewModel extends BaseCatViewModel<ActivityLoginBinding> {
    private static final int GP_CONNECTION_FAILURE = 12501;
    private static final int RC_GOOGLE_SIGN_IN     = 125;

    private CallbackManager     facbookCallbackManager;
    //Google sign in
    private GoogleApiClient     mGoogleApiClient;
    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    private GoogleSignInOptions gso;

    @Override
    public void onViewModelCreated() {
        super.onViewModelCreated();
        if (AccountManager.getInstance().isLoggedIn()) {
            startNextActivity();
        } else {
            setupSocialLogin();
        }
    }

    private void setupSocialLogin() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestServerAuthCode(getString(R.string.default_web_client_id))
                .build();
        createGoogleApiClient();
        setupFacebookLogin();
    }

    private void createGoogleApiClient() {
        Activity activity = getActivity();
        if (!(activity instanceof FragmentActivity)) {
            return;
        } else {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .enableAutoManage((FragmentActivity) activity, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {
                            DialogHelper.showDialog(getContext(),
                                    String.format("%s: %s", getString(R.string.error_occurred),
                                            connectionResult.getErrorMessage()),
                                    SweetAlertDialog.ERROR_TYPE);
                        }
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }

    private void setupFacebookLogin() {

        facbookCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(facbookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Logger.d(response.toString());
                                        try {
                                            String id = object.getString("id");
                                            String name = object.getString("name");
                                            saveAccountAndStartNextActivity(id, name);
                                        } catch (JSONException e) {
                                            Logger.e(e, "");
                                            Toast.makeText(getContext(), getString(R.string.fb_error), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getContext(), getString(R.string.fb_cancelled_by_user), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Logger.d(error.getMessage());
                        Toast.makeText(getContext(), getString(R.string.fb_error), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RC_GOOGLE_SIGN_IN:
                    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
                    GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    handleGoogleSignInResult(result);
                    break;
                default:
                    facbookCallbackManager.onActivityResult(requestCode, resultCode, data);
                    break;
            }
        }
    }

    private void handleGoogleSignInResult(GoogleSignInResult result) {
        DialogHelper.showProgress(getContext(), R.string.log_in_with_gplus);
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            saveAccountAndStartNextActivity(acct.getId(), acct.getDisplayName());

        } else if (result.getStatus().getStatusCode() == GP_CONNECTION_FAILURE) {
            DialogHelper.changeProgressDialog(getContext(), R.string.log_in_with_google_network_failed,
                    SweetAlertDialog.WARNING_TYPE);
        } else {
            DialogHelper.changeProgressDialog(getContext(), R.string.log_in_with_google_failed,
                    SweetAlertDialog.WARNING_TYPE);
        }
    }

    private void saveAccountAndStartNextActivity(String id, String name) {
        AccountManager.getInstance().saveAccount(
                new Account(id,
                        name));
        startNextActivity();
    }

    private void startNextActivity() {
        CatActivity.startCatActivity(getContext());
        getActivity().finish();
    }


    public void onFacebookClicked(View v) {
        LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));
    }

    public void onGoogleClicked(View v) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        getActivity().startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }
}
