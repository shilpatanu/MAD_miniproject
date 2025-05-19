package com.example.miniproject06;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText editTextFileName, editTextInput;
    Button btnSave, btnView;
    TextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFileName = findViewById(R.id.editTextFileName);
        editTextInput = findViewById(R.id.editTextInput);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        textViewOutput = findViewById(R.id.textViewOutput);

        // Save Button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editTextFileName.getText().toString().trim();
                String text = editTextInput.getText().toString();

                if (fileName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter file name", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    FileOutputStream fos = openFileOutput(fileName + ".txt", MODE_PRIVATE);
                    fos.write(text.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "Text saved to " + fileName + ".txt", Toast.LENGTH_SHORT).show();
                    editTextInput.setText(""); // Clear input
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error saving file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // View Button
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editTextFileName.getText().toString().trim();

                if (fileName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter file name", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    FileInputStream fis = openFileInput(fileName + ".txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    isr.close();
                    fis.close();
                    textViewOutput.setText(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    textViewOutput.setText("File not found or error reading file.");
                }
            }
        });
    }
}
