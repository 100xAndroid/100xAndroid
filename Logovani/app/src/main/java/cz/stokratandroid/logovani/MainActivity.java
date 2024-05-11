package cz.stokratandroid.logovani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void log1 (View sender) {
        // logovani urovne DEBUG
        Log.d ("MainActivity", "Test log DEBUG");
    }

    public void log2 (View sender) {
        // logovani urovne ERROR
        Log.e ("MainActivity", "Test log ERROR");
    }

    public void log3 (View sender) {
        // logovani urovne INFO
        Log.i ("MainActivity", "Test log INFO");
    }

    public void stackTrace (View sender) {
        try {
            // zavolame metodu, ktera vyvola vyjimku
            vyvolatVyjimku();
        }
        catch (Exception ex) {
            // vypiseme stacktrace
            ex.printStackTrace();
        }
    }

    // Metoda umele zpusobi chybu
    private void vyvolatVyjimku () throws Exception {
        // vyvolame vyjimku
        throw new Exception("Testovací výjimka");
    }
}