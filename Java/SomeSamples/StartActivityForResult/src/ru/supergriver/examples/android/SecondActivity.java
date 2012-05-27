package ru.supergriver.examples.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent= getIntent();
        String msg = intent.getStringExtra("sampleData");
        msg += ", Added at Second";

        Intent intent2 = new Intent(this, ThirdActivity.class);
        intent2 .putExtra("sampleData", msg);
        startActivityForResult(intent2, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==10){
            String msg = data.getStringExtra("returnedData");
            intent.putExtra("returnedData", msg);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}