package cz.programujemeproandroid.prvniaplikacejava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void kliknutiNaTlacitko(View sender) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Poznámka");
        builder.setMessage("Android je zde!");
        builder.setPositiveButton("Zavřít", null);

        builder.create().show();

        return;
    }
}