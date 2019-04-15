package com.dimaoprog.sportsconnectivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.dimaoprog.sportsconnectivity.dbUsers.App;
import com.dimaoprog.sportsconnectivity.dbUsers.User;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDao;
import com.dimaoprog.sportsconnectivity.dbUsers.UserDatabase;

public class LoginFragment extends Fragment implements View.OnClickListener {

    OnPressedButtonListener pressedEvent;

    public void setOnPressedButtonListener(OnPressedButtonListener pressedEvent) {
        this.pressedEvent = pressedEvent;
    }

    public interface OnPressedButtonListener {
        void openNewsListFragment(User currentUser);
        void openRegistrationFragment();

    }

    User userToLogin;

    public static LoginFragment newInstance(OnPressedButtonListener pressedEvent) {
        LoginFragment fragment = new LoginFragment();
        fragment.setOnPressedButtonListener(pressedEvent);
        return fragment;
    }

    EditText txtEMail;
    EditText txtPassword;
    Button btnLogin;
    Button btnRegister;
    CheckBox checkBoxStayIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initView(v);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        return v;
    }

    private void initView(View v) {
        txtEMail = v.findViewById(R.id.et_e_mail);
        txtPassword = v.findViewById(R.id.et_password);
        btnLogin = v.findViewById(R.id.btn_login);
        btnRegister = v.findViewById(R.id.btn_register);
        checkBoxStayIn = v.findViewById(R.id.check_stay_in);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkUser()) {
                    pressedEvent.openNewsListFragment(userToLogin);
                }
                break;
            case R.id.btn_register:
                pressedEvent.openRegistrationFragment();
                break;
        }
    }

    private boolean checkUser() {
        UserDatabase db = App.getInstance().getDatabase();
        UserDao userDao = db.userDao();
        User userToCheck = userDao.getByEmail(txtEMail.getText().toString());
        if (userToCheck == null) {
            Toast.makeText(getContext(), "This e-mail is not in the database", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!userToCheck.password.equals(txtPassword.getText().toString())) {
            Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (checkBoxStayIn.isChecked()) {
                userToCheck.stayInSystem = User.STAY;
                userDao.update(userToCheck);
            }
            userToLogin = userToCheck;
            return true;
        }
    }

}
