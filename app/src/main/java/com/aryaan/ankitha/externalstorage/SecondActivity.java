package com.aryaan.ankitha.externalstorage;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    EditText displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        displayText = (EditText)findViewById(R.id.editText2);
    }

    public void loadInternalCache(View view){
        File folder = getCacheDir();
        File myFile = new File(folder,"mydata1.txt");
        String data = readData(myFile);
        if (data != null){
            displayText.setText(data);
        }else{
            displayText.setText("No data found");
        }
    }

    public void loadExternalCache(View view){
        File folder = getExternalCacheDir();
        File myFile = new File(folder,"mydata2.txt");
        String data = readData(myFile);
        if (data != null){
            displayText.setText(data);
        }else{
            displayText.setText("No data found");
        }
    }

    public void loadExternalPrivate(View view){
        File folder = getExternalFilesDir("AryaanS");
        File myFile = new File(folder,"mydata3.txt");
        String data = readData(myFile);
        if (data != null){
            displayText.setText(data);
        }else{
            displayText.setText("No data found");
        }
    }

    public void loadExternalPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(folder,"mydata4.txt");
        String data = readData(myFile);
        if (data != null){
            displayText.setText(data);
        }else{
            displayText.setText("No data found");
        }
    }

    private String readData(File myFile){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);
            int read = -1;
            StringBuffer stringBuffer = new StringBuffer();
            //changed the condition from == to !=
            while((read=fileInputStream.read()) != -1){
                stringBuffer.append((char)read);
            }
            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void previous(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
