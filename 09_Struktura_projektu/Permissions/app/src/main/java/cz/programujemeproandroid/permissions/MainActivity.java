package cz.programujemeproandroid.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cz.programujemeproandroid.permissions.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static final int OPRAVNENI_REQUEST = 1;

    // metoda volana kliknutim na tlacitko Test opravneni
    public void testOpravneni(View view) {
        // test, jestli uzivatel opravneni na odesilani SMS jiz drive udelil
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // test, jestli muzeme uzivatele o opravneni pozadat
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                Toast.makeText(this, "Oprávnění nebylo uděleno.", Toast.LENGTH_LONG).show();
            } else {
                // pozadame uzivatele o udeleni opravneni odesilat SMS
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, OPRAVNENI_REQUEST);
            }
        }
        else {
            Toast.makeText(this,"Oprávnění je k dispozici.", Toast.LENGTH_LONG).show();
        }
    }
}
