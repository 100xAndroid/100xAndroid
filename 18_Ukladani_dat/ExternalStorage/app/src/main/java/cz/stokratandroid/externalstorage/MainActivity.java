package cz.stokratandroid.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ulozitData(View sender)
    {
        // overit, jestli je mozne externi uloziste pouzit
        String stavExternihoMedia = kontrolaStavuExternihoMedia();
        if(stavExternihoMedia.length()>0) {
            Toast.makeText(MainActivity.this, stavExternihoMedia, Toast.LENGTH_SHORT).show();
            return;
        }

        // nacteni hodnot z formulare
        EditText editText1 = (EditText)findViewById(R.id.editText1);
        String inputText = editText1.getText().toString();

        // nazev souboru a jeho umisteni
        String NAZEV_SOUBORU = "test.txt";
        String adresar = Environment.getExternalStorageDirectory().getAbsolutePath();

        // zapis dat do souboru
        File file = new File(adresar, NAZEV_SOUBORU);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(inputText);
            writer.close();
        } catch (IOException e) {
            String txtChyba = "Data se nepodařilo zapsat. " + e.getMessage();
            Toast.makeText(MainActivity.this, txtChyba, Toast.LENGTH_SHORT).show();
            return;
        }

        // zjistit misto ulozeni souboru
        String umisteni = "Uloženo: " + adresar + "/" + NAZEV_SOUBORU;
        // pro informaci zaobrazit umisteni souboru
        Toast.makeText(MainActivity.this, umisteni, Toast.LENGTH_SHORT).show();
    }

    public void nacistData(View sender)
    {
        // overit, jestli je mozne externi uloziste pouzit
        String stavExternihoMedia = kontrolaStavuExternihoMedia();
        if(stavExternihoMedia.length()>0) {
            Toast.makeText(MainActivity.this, stavExternihoMedia,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // nazev souboru a jeho umisteni
        String NAZEV_SOUBORU = "test.txt";
        String adresar = Environment.getExternalStorageDirectory()
                .getAbsolutePath();

        try {
            // otevrit soubor pro cteni
            File soubor = new File(adresar, NAZEV_SOUBORU);
            FileInputStream inStream = new FileInputStream(soubor);

            // vytvorit instanci bufferu
            StringBuffer nactenyText = new StringBuffer();

            // soubor postupne, po znacich, nacist do bufferu
            int znak;
            while ((znak = inStream.read()) != -1) {
                nactenyText.append((char)znak);
            }

            // ulozit text do formulare
            EditText editText1 = (EditText)findViewById(R.id.editText1);
            editText1.setText(nactenyText);
        }
        catch (IOException e) {
            // zobrazit informaci o chybe
            String textChyby = "Nedaří se načíst..\n" + e.getMessage();
            Toast.makeText(MainActivity.this, textChyby,Toast.LENGTH_SHORT).show();
        }
    }

    // metoda zjistí, jestli je mozne externi zarizeni pouzit ke cteni/zapisu
    private String kontrolaStavuExternihoMedia() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // data lze cist a zapisovat
            return "";
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return "Data lze z externího média pouze číst.";
        } else {
            return "Data nelze na externí médium zapisovat ani z něj číst.";
        }
    }
}