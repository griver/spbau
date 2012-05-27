package ru.supergriver.examples.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import ru.supergriver.examples.android.R;

public class FirstActivity extends Activity {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView)this.findViewById(R.id.textView);

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("sampleData", "This is Sample Data");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==1){
            String msg = data.getStringExtra("returnedData");
            textView.setText(msg);
        }
    }
}