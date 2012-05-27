package ru.supergriver.examples.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        String msg = intent.getStringExtra("sampleData");
        msg += ", Added at Third";
        intent.putExtra("returnedData", msg);
        setResult(RESULT_OK, intent);
        finish();
    }
}