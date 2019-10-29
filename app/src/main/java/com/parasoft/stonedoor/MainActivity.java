package com.parasoft.stonedoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parasoft.stonedoor.util.activitymodels.PsActivity;

public class MainActivity extends AppCompatActivity implements PsActivity {
    private TextView loginField;
    private TextView passwordField;
    private Button loginButton;
    private Button addAccountButton;
    private MainActivityHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        standardAction();
        setFields();
        checkToShowAddAccountButton();
        addButtonActions();
    }

    @Override
    public void standardAction() {
        setContentView(R.layout.activity_main);
        helper = new MainActivityHelper(this);
    }

    @Override
    public void setFields() {
        this.loginField = findViewById(R.id.login);
        this.passwordField = findViewById(R.id.password);
        this.loginButton = findViewById(R.id.signIn);
        this.addAccountButton = findViewById(R.id.addNewAccount);
    }

    private void checkToShowAddAccountButton() {
        if (!helper.checkIfAnyUserExists()) {
            this.addAccountButton.setEnabled(false);
        }
    }

    private void addButtonActions() {
        createNewAccountAction();
    }

    private void createNewAccountAction() {
        MainActivity context = this;
        this.addAccountButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, CreateNewAccountActivity.class));
                    }
                }
        );
    }
}
