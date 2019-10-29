package com.parasoft.stonedoor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parasoft.stonedoor.actionhandlers.CreateAccountActionHandler;
import com.parasoft.stonedoor.util.activitymodels.PsActivity;

public class CreateNewAccountActivity extends AppCompatActivity implements PsActivity {
    private TextView newLogin;
    private TextView newPassword;
    private Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        standardAction();
        setFields();
        addSaveAction();
    }

    @Override
    public void standardAction() {
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public void setFields() {
        this.newLogin = findViewById(R.id.newLogin);
        this.newPassword = findViewById(R.id.newPassword);
        this.saveButton = findViewById(R.id.save);
    }

    private void addSaveAction() {
        CreateNewAccountActivity context = this;
        this.saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String login = context.newLogin.getText().toString();
                        String password = context.newPassword.getText().toString();
                        CreateAccountActionHandler createAccountActionHandler =
                                new CreateAccountActionHandler(context);
                        createAccountActionHandler.insertNewAccount(login, password);
                        startActivity(new Intent(context, MainActivity.class));
                    }
                }
        );
    }
}
