package cz.stokratandroid.explicitniintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        // nacteni intentu
        Intent mujIntent = getIntent();
        // nacteni prvniho, textoveho, parametru
        String parametr1 = mujIntent.getStringExtra("param1");
        // nacteni druheho, ciselneho, parametru
        Integer parametr2 = mujIntent.getIntExtra("param2", 0);

        // zapis nactenych aprametru do textovych poli
        TextView txtView1 = (TextView)findViewById(R.id.textView1);
        txtView1.setText(parametr1);
        TextView txtView2 = (TextView)findViewById(R.id.textView2);
        txtView2.setText(parametr2.toString());
    }

    // zavrit aktivitu a vratit se na predchozi
    public void zpet (View view) {
        finish();
    }
}