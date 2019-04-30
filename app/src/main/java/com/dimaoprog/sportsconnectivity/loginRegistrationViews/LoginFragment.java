package com.dimaoprog.sportsconnectivity.loginRegistrationViews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.dbEntities.User;
import com.dimaoprog.sportsconnectivity.dbRepos.UserDao;
import com.dimaoprog.sportsconnectivity.dbRepos.AppDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends Fragment {

    OnPressedButtonListener pressedEvent;

    public void setOnPressedButtonListener(OnPressedButtonListener pressedEvent) {
        this.pressedEvent = pressedEvent;
    }

    public interface OnPressedButtonListener {
        void openForWorkoutsActivity();

        void openRegistrationFragment();
    }

    public static LoginFragment newInstance(OnPressedButtonListener pressedEvent) {
        LoginFragment fragment = new LoginFragment();
        fragment.setOnPressedButtonListener(pressedEvent);
        return fragment;
    }

    private Unbinder unbinder;

    @BindView(R.id.et_e_mail)
    EditText txtEMail;
    @BindView(R.id.et_password)
    EditText txtPassword;
    @BindView(R.id.check_stay_in)
    CheckBox checkBoxStayIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkUser()) {
                    pressedEvent.openForWorkoutsActivity();
                }
                break;
            case R.id.btn_register:
                pressedEvent.openRegistrationFragment();
                break;
        }
    }

    private boolean checkUser() {
        AppDatabase db = AppDatabase.getInstance(getActivity());
        UserDao userDao = db.userDao();
        User userToCheck = userDao.getByEmail(txtEMail.getText().toString());
        if (userToCheck == null) {
            Toast.makeText(getContext(), "This e-mail is not in the database", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!userToCheck.getPassword().equals(txtPassword.getText().toString())) {
            Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (checkBoxStayIn.isChecked()) {
                userToCheck.setStayInSystem(User.STAY);
                userDao.update(userToCheck);
            }
            User.setACTIVEUSER(userToCheck);
            return true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
