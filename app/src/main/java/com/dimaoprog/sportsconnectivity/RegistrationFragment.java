package com.dimaoprog.sportsconnectivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.dbUsers.App;
import com.dimaoprog.sportsconnectivity.dbUsers.User;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDao;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDatabase;

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

    EditText txtFirstName;
    EditText txtSecondName;
    EditText txtEMail;
    EditText txtPassword;
    Button btnConfirm;
    boolean firstNameOK;
    boolean secondNameOK;
    boolean eMailOK ;
    boolean passwordOK;
    TextView incorrectFirst;
    TextView incorrectSecond;
    TextView incorrectEMail;
    TextView incorrectPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        initView(v);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inPutIsOK()) {
                    if (addUserToBD()) {
                        registrationCompleteListener.openLoginFragment();
                    }
                } else {
                    Toast.makeText(getContext(), "Incorrect entries", Toast.LENGTH_SHORT).show();
                }
                checkVisibilityIncorrectText();
            }
        });
        return v;
    }

    private boolean addUserToBD() {
        UserDatabase db = App.getInstance().getDatabase();
        UserDao userDao = db.userDao();
        User userWantToRegister = new User();
        userWantToRegister.firstName = txtFirstName.getText().toString().trim();
        userWantToRegister.secondName = txtSecondName.getText().toString().trim();
        userWantToRegister.eMail = txtEMail.getText().toString().trim();
        userWantToRegister.password = txtPassword.getText().toString().trim();
        userWantToRegister.stayInSystem = User.NOTSTAY;
        long i = userDao.insert(userWantToRegister);
        Toast.makeText(getContext(), "User with e-mail " + userWantToRegister.eMail + " added on place " + i, Toast.LENGTH_SHORT).show();
        return true;
    }

    private void initView(View v) {
        txtEMail = v.findViewById(R.id.et_e_mail);
        txtPassword = v.findViewById(R.id.et_password);
        txtFirstName = v.findViewById(R.id.et_first_name);
        txtSecondName = v.findViewById(R.id.et_second_name);
        btnConfirm = v.findViewById(R.id.btn_confirm);
        incorrectFirst = v.findViewById(R.id.text_incorrect_first);
        incorrectSecond = v.findViewById(R.id.text_incorrect_second);
        incorrectEMail = v.findViewById(R.id.text_incorrect_mail);
        incorrectPassword = v.findViewById(R.id.text_incorrect_password);
        firstNameOK = false;
        secondNameOK = false;
        eMailOK = false;
        passwordOK = false;
        setInvisibleIncorrectText();
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
        }else {
            incorrectEMail.setVisibility(View.INVISIBLE);
        }
        if (!passwordOK) {
            incorrectPassword.setVisibility(View.VISIBLE);
        } else {
            incorrectPassword.setVisibility(View.INVISIBLE);
        }
    }

    private void setInvisibleIncorrectText() {
        incorrectFirst.setVisibility(View.INVISIBLE);
        incorrectSecond.setVisibility(View.INVISIBLE);
        incorrectEMail.setVisibility(View.INVISIBLE);
        incorrectPassword.setVisibility(View.INVISIBLE);
    }

}
