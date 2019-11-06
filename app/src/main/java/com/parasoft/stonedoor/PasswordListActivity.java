package com.parasoft.stonedoor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parasoft.stonedoor.util.activitymodels.PsActivity;

public class PasswordListActivity extends AppCompatActivity implements PsActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        standardAction();
        setFields();
    }

    @Override
    public void standardAction() {
        setContentView(R.layout.activity_password_list);
    }

    @Override
    public void setFields() {
        recyclerView = findViewById(R.id.passwordList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
