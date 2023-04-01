package cz.stokratandroid.internet1;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetData extends AsyncTask<Void, Void, Void> {

    String nactenaData = "";

    // operace ktera bude spustena na pozadi
    @Override
    protected Void doInBackground(Void... voids) {
        try
        {
            URL url= new URL("https://jsonkeeper.com/b/XFIM");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            InputStream stream = conn.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));

            String radka = "";
            do {
                nactenaData = nactenaData + radka;
                radka = buff.readLine();
            } while (radka != null);
        }
        catch(Exception e) {
            // MainActivity.jsonTextView.setText(e.getMessage());
            nactenaData = "Chyba: " + e.getMessage();
            return null;
        }

        return null;
    }

    // metoda se zavola po skonceni operace
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.jsonTextView.setText(nactenaData);
    }

}

