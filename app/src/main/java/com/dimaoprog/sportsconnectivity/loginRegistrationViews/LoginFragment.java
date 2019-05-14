package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    OnPressedButtonListener pressedEvent;

    public void setOnPressedButtonListener(OnPressedButtonListener pressedEvent) {
        this.pressedEvent = pressedEvent;
    }

    public interface OnPressedButtonListener {
        void onLoginClick();

        void onRegisterClick();
    }

    public static LoginFragment newInstance(OnPressedButtonListener pressedEvent) {
        LoginFragment fragment = new LoginFragment();
        fragment.setOnPressedButtonListener(pressedEvent);
        return fragment;
    }

    LoginViewModel lViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentLoginBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_login, container, false);
        View v = binding.getRoot();
        lViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        lViewModel.setOnPressedButtonListener(pressedEvent);
        binding.setLoginmodel(lViewModel);
        binding.btnLogin.setOnClickListener(__ -> {
            if (lViewModel.checkUser()) {
                pressedEvent.onLoginClick();
            } else {
                if (lViewModel.isEmailOk()) {
                    binding.etEMail.setError("invalid e-mail");
                }
                if (lViewModel.isPassOk()) {
                    binding.etPassword.setError("invalid password");
                }
            }
        });
        return v;
    }
}
