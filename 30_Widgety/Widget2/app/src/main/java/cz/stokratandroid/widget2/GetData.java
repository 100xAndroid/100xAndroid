package cz.stokratandroid.widget2;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetData extends AsyncTask<Void, Void, Void> {

    String mesto = "Karlovy Vary";
    String data = "";
    Context context;

    public GetData (Context cont, String nazevMesta) {
        context=cont;
        mesto = nazevMesta.replace(" ", "%20"); // nahradit pripadne mezery
    }

    // operace ktera bude spustena na pozadi
    @Override
    protected Void doInBackground(Void... voids) {
        try
        {
            URL url= new URL("https://api.openweathermap.org/data/2.5/weather?q="
                    + mesto
                    + "&units=metric&lang=cz&appid=df15c19897e64a504b06ce36ae0dce85");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");   // pro predani dat pouzita metoda GET
            conn.setReadTimeout(10000);     // timeout pro cteni, v milisekundach
            conn.setConnectTimeout(15000);  // timeout pro pripojeni, v milisekundach
            conn.setDoOutput(false);        // priznak - telo zpravy neodesilat

            InputStream stream = conn.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(stream));

            // nacteni dat po jednotlivych radcich
            String radka = "";
            while (radka != null)
            {
                radka = buff. readLine();
                data = data + radka;
            }
        }
        catch(Exception e) {
            WidgetProvider.zobrazitText(context, e.getMessage());
            return null;
        }

        return null;
    }

    // metoda se zavola po skonceni operace
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        String dekodovanaData = dekodovatJson (data);
        WidgetProvider.zobrazitText(context, dekodovanaData);
    }

    // rozparsovani dat z formatu JSON
    private String dekodovatJson(String data) {
        try {
            // parsovani teploty
            JSONObject jObj = new JSONObject(data);
            jObj = new JSONObject(jObj.getString("main"));
            String teplota = jObj.getString("temp");
            // prevod na cislo, abychom mohli nastavit potrebny pocet desetinnych mist
            double teplotaNum = Double.parseDouble(teplota);

            // parsovani popisu pocasi
            jObj = new JSONObject(data);
            JSONArray jArray = new JSONArray(jObj.getString("weather"));
            jObj = new JSONObject(jArray.getString(0));
            String text = jObj.getString("description");

            return String.format("%s, %.1f°C", text, teplotaNum);
        } catch (JSONException e) {
            return e.getMessage();
        }
    }
}
