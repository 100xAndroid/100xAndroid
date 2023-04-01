package cz.stokratandroid.tlacitka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.tlacitko1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // kod ktery se provede po stisknuti tlacitka
            }
        });
    }

    public void tlacitko_click (View view) {
        // kod ktery se provede po stisknuti tlacitka
    }
}