package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.FragmentNaviManager;
import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dagger.AppComponentBuild;
import com.dimaoprog.sportsconnectivity.databinding.FragmentLoginBinding;
import com.dimaoprog.sportsconnectivity.profileViews.ProfileFragment;

import javax.inject.Inject;

public class LoginFragment extends Fragment {

    @Inject
    FragmentNaviManager navigation;
    private LoginViewModel lViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentLoginBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_login, container, false);
        AppComponentBuild.getComponent().inject(this);
        lViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginmodel(lViewModel);
        binding.btnRegister.setOnClickListener(v -> navigation.showNewFragment(new RegistrationFragment()));
        binding.btnLogin.setOnClickListener(__ -> {
            if (lViewModel.checkUser()) {
                navigation.showNewFragment(new ProfileFragment());

            } else {
                if (lViewModel.isEmailOk()) {
                    binding.etEMail.setError("invalid e-mail");
                }
                if (lViewModel.isPassOk()) {
                    binding.etPassword.setError("invalid password");
                }
            }
        });
        return binding.getRoot();
    }
}
