package cz.programujemeproandroid.tabhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import cz.programujemeproandroid.tabhost.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // najdeme kontejner TabHost
        // metodu setup je nutne zavolat pred vkladanim zalozek
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        // 1. zalozka (Android)
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Tab1");
        tabSpec.setIndicator("Android");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        // 2. zalozka (Verze)
        tabSpec = tabHost.newTabSpec("Tab2");
        tabSpec.setIndicator("Verze");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        // 3. zalozka (API)
        tabSpec = tabHost.newTabSpec("Tab3");
        tabSpec.setIndicator("API");
        tabSpec.setContent(R.id.tab3);
        tabHost.addTab(tabSpec);
    }
}
