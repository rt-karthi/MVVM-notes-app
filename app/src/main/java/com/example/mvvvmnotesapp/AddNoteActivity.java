package com.example.mvvvmnotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mTitleEdit, mDescEdit;
    private Button mSaveNoteBtn;
    private NumberPicker numberPicker;

    public static final String EXTRA_ID = "com.example.mvvvmnotesapp.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.mvvvmnotesapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.mvvvmnotesapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.mvvvmnotesapp.EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mTitleEdit = findViewById(R.id.editTextTitle);
        mDescEdit = findViewById(R.id.editTextDesc);
        mSaveNoteBtn = findViewById(R.id.saveBtn);
        numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent editIntent = getIntent();
        if (editIntent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            mTitleEdit.setText(editIntent.getStringExtra(EXTRA_TITLE));
            mDescEdit.setText(editIntent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(editIntent.getIntExtra(EXTRA_PRIORITY, 1));
        }

        setTitle("Add Note");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background));


        mSaveNoteBtn.setOnClickListener(v -> {
            String title = mTitleEdit.getText().toString();
            String desc = mDescEdit.getText().toString();
            int priority = numberPicker.getValue();

            if (title.trim().isEmpty() || desc.trim().isEmpty()) {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent dataIntent = new Intent();
            dataIntent.putExtra(EXTRA_TITLE, title);
            dataIntent.putExtra(EXTRA_DESCRIPTION, desc);
            dataIntent.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                dataIntent.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, dataIntent);

            finish();
        });
    }
}