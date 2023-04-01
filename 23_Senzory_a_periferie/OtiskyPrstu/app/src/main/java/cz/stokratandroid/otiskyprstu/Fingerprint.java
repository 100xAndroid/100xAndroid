package cz.stokratandroid.otiskyprstu;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

public class Fingerprint extends FingerprintManager.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private Context context;

    public Fingerprint(Context ctxt) {
        context = ctxt;
    }

    // zahajeni overovani otisku prstu
    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        // spusteni senzoru
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    // chyba pri overovani, operace je ukoncena (dalsi zpetna volani zrusena)
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(context, "Chyba při ověřování:\n" + errString, Toast.LENGTH_LONG).show();
    }

    // napoveda pri napravitelne chybe zarizeni (napr. spinavy senzor)
    @Override
    public void onAuthenticationHelp(int helpMsgId,  CharSequence helpString) {
        Toast.makeText(context, "Nápověda:\n" + helpString, Toast.LENGTH_LONG).show();
    }

    // otisk je uspesne nacteny, nebyl ale rozpoznan (dalsi zpetna volani povolena)
    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(context, "Neznámý otisk.", Toast.LENGTH_SHORT).show();
    }

    // otisk je uspesne nacteny a rozpoznany, operace ukoncena (dalsi zpetna volani zrusena)
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(context, "Otisk je ověřen.", Toast.LENGTH_SHORT).show();
    }
}