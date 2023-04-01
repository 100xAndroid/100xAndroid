package cz.stokratandroid.otiskyprstu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.Executor;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private TextView txtVysledek;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVysledek = findViewById(R.id.txtVysledek);

        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback(){
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                txtVysledek.setText("Nepodařilo se ověřit");
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                txtVysledek.setText("Ověřeno");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                txtVysledek.setText("Chyba - neověřeno");
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Otisk prstu")
                .setNegativeButtonText("Zrušit")
                .setConfirmationRequired(false)
                .build();
    }

    public void testCtecky (View view) {

        BiometricManager biometricManager=BiometricManager.from(this);

        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
            Toast.makeText(this, "Zařízení neobsahuje čtečku otisku prstu.", Toast.LENGTH_LONG).show();
            return;
        }

        if (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
            Toast.makeText(this, "Zamykání pomocí otisku prstu není v Nastaveních aktivované.", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Čtečka otisku prstu je připravena.", Toast.LENGTH_LONG).show();
    }


    public void overeniOtisku(View view) {
        biometricPrompt.authenticate(promptInfo);
    }

}