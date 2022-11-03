package com.example.android_asyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Button button;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = this.findViewById(R.id.editText);
        button = this.findViewById(R.id.button);
        output = this.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task(MainActivity.this, new onPostTask() {
                    @Override
                    public void onFinish(Long factorial) {
                        output.setText(factorial + "");
                    }
                });

                task.execute(Integer.valueOf(input.getText().toString()));
            }
        });

    }

}