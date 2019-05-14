package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {

    RegistrationCompleteListener registrationCompleteListener;

    public void setRegistrationCompleteListener(RegistrationCompleteListener registrationCompleteListener) {
        this.registrationCompleteListener = registrationCompleteListener;
    }

    public interface RegistrationCompleteListener {
        void openLoginFragment();
    }

    public static RegistrationFragment newInstance(RegistrationCompleteListener registrationCompleteListener) {
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setRegistrationCompleteListener(registrationCompleteListener);
        return fragment;
    }

    RegistrationViewModel rViewModel;
    FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false);
        View v = binding.getRoot();
        rViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        binding.setRegmodel(rViewModel);
        binding.btnConfirm.setOnClickListener(__ -> {
            if (rViewModel.isInPutOK()) {
                if (rViewModel.addUserToBD()) {
                    registrationCompleteListener.openLoginFragment();
                } else {
                    binding.etEMail.setError("This e-mail already exist");
                }
            } else {
                Toast.makeText(getContext(), "Incorrect entries", Toast.LENGTH_SHORT).show();
                checkVisibilityIncorrectText();
            }
        });
        return v;
    }

    public void checkVisibilityIncorrectText() {
        binding.textIncorrectFirst.setVisibility(!rViewModel.isFirstNameOK() ? View.VISIBLE : View.INVISIBLE);
        binding.textIncorrectSecond.setVisibility(!rViewModel.isSecondNameOK() ? View.VISIBLE : View.INVISIBLE);
        binding.textIncorrectGender.setVisibility(!rViewModel.isGenderOk() ? View.VISIBLE : View.INVISIBLE);
        binding.textIncorrectDateOfBirth.setVisibility(!rViewModel.isBirthDayOk() ? View.VISIBLE : View.INVISIBLE);
        binding.textIncorrectMail.setVisibility(!rViewModel.isEmailOK() ? View.VISIBLE : View.INVISIBLE);
        binding.textIncorrectPassword.setVisibility(!rViewModel.isPasswordOK() ? View.VISIBLE : View.INVISIBLE);
    }
}