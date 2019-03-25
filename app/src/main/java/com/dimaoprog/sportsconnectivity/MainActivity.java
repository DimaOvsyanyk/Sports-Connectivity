package com.dimaoprog.sportsconnectivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btnHello, btnQuiz, btnBye;
    public TextView txtSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHello = findViewById(R.id.btnHello);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnBye = findViewById(R.id.btnBye);
        txtSpeech = findViewById(R.id.txtSpeech);
        btnHello.setOnClickListener(this);
        btnQuiz.setOnClickListener(this);
        btnBye.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHello:
                txtSpeech.setText(getString(R.string.helloText));
                txtSpeech.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case R.id.btnQuiz:
                txtSpeech.setText(getString(R.string.quizText));
                txtSpeech.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case R.id.btnBye:
                txtSpeech.setText(getString(R.string.byeText));
                txtSpeech.setBackgroundColor(getResources().getColor(R.color.red));
                break;
        }
    }
}
