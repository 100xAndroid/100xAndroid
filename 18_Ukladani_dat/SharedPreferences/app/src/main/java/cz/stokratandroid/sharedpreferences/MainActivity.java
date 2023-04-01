package cz.stokratandroid.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nacistData(null);
    }

    public void ulozitData(View sender)
    {
        // nacteme hodnoty z formulare
        EditText editText1 = (EditText)findViewById(R.id.editText1);
        String inputText = editText1.getText().toString();

        EditText editText2 = (EditText)findViewById(R.id.editText2);
        String text2 = editText2.getText().toString();
        int inputInt = Integer.parseInt(text2);

        // otevreme soubor pro zapis preferenci
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        // vytvorime objekt editor preferenci
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // do editoru ulozime klic a hodnotu z formulare
        editor.putString("text1", inputText);
        editor.putInt("text2", inputInt);
        // data ulozime
        editor.commit();
    }

    public void nacistData(View sender)
    {
        // otevreme soubor pro cteni preferenci
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        // nacteme ulozenou hodnotu
        String text1 = sharedPreferences.getString("text1", "default");
        String text2 = Integer.toString(sharedPreferences.getInt("text2", 0));

        // ulozime hodnoty do formulare
        EditText editText1 = (EditText)findViewById(R.id.editText1);
        editText1.setText(text1);

        EditText editText2 = (EditText)findViewById(R.id.editText2);
        editText2.setText(text2);
    }
}
