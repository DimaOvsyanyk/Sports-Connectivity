package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbWorkouts.UserDao;
import com.dimaoprog.sportsconnectivity.dbWorkouts.AppDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    private Unbinder unbinder;

    @BindView(R.id.et_e_mail)
    EditText txtEMail;
    @BindView(R.id.et_password)
    EditText txtPassword;
    @BindView(R.id.et_first_name)
    EditText txtFirstName;
    @BindView(R.id.et_second_name)
    EditText txtSecondName;
    @BindView(R.id.text_incorrect_first)
    TextView incorrectFirst;
    @BindView(R.id.text_incorrect_second)
    TextView incorrectSecond;
    @BindView(R.id.text_incorrect_mail)
    TextView incorrectEMail;
    @BindView(R.id.text_incorrect_password)
    TextView incorrectPassword;
    boolean firstNameOK;
    boolean secondNameOK;
    boolean eMailOK;
    boolean passwordOK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        unbinder = ButterKnife.bind(this, v);
        firstNameOK = false;
        secondNameOK = false;
        eMailOK = false;
        passwordOK = false;
        setInvisibleIncorrectText();
        return v;
    }

    @OnClick(R.id.btn_confirm)
    void onConfirmClick() {
        if (inPutIsOK()) {
            if (addUserToBD()) {
                registrationCompleteListener.openLoginFragment();
            } else {
                Toast.makeText(getContext(), "This e-mail already exist", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Incorrect entries", Toast.LENGTH_SHORT).show();
        }
        checkVisibilityIncorrectText();
    }

    private boolean addUserToBD() {
        AppDatabase db = AppDatabase.getInstance(getActivity());
        UserDao userDao = db.userDao();
        if (userDao.getByEmail(txtEMail.getText().toString().trim()) != null) {
            return false;
        }
        User userWantToRegister = new User(txtFirstName.getText().toString().trim(),
                txtSecondName.getText().toString().trim(),
                txtEMail.getText().toString().trim(),
                txtPassword.getText().toString().trim(),
                User.NOTSTAY);
        long i = userDao.insert(userWantToRegister);
        Toast.makeText(getContext(), "User with e-mail " + userWantToRegister.getEMail() + " added on place " + i, Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean inPutIsOK() {
        firstNameOK = txtFirstName.getText().toString().trim().length() > 0;
        secondNameOK = txtSecondName.getText().toString().trim().length() > 0;
        passwordOK = txtPassword.getText().toString().trim().length() > 4;
        eMailOK = checkEMail();
        return (firstNameOK & secondNameOK & eMailOK & passwordOK);
    }

    private boolean checkEMail() {
        String email = txtEMail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return (email.matches(emailPattern));
    }

    private void setInvisibleIncorrectText() {
        incorrectFirst.setVisibility(View.INVISIBLE);
        incorrectSecond.setVisibility(View.INVISIBLE);
        incorrectEMail.setVisibility(View.INVISIBLE);
        incorrectPassword.setVisibility(View.INVISIBLE);
    }

    public void checkVisibilityIncorrectText() {
        if (!firstNameOK) {
            incorrectFirst.setVisibility(View.VISIBLE);
        } else {
            incorrectFirst.setVisibility(View.INVISIBLE);
        }
        if (!secondNameOK) {
            incorrectSecond.setVisibility(View.VISIBLE);
        } else {
            incorrectSecond.setVisibility(View.INVISIBLE);
        }
        if (!eMailOK) {
            incorrectEMail.setVisibility(View.VISIBLE);
        } else {
            incorrectEMail.setVisibility(View.INVISIBLE);
        }
        if (!passwordOK) {
            incorrectPassword.setVisibility(View.VISIBLE);
        } else {
            incorrectPassword.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
