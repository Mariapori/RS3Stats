package fi.mariapori.rs3stats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<StatsObject> stats = new ArrayList<>();
    ListView statsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button asetukset = (Button) findViewById(R.id.btnAsetukset);
        asetukset.setOnClickListener(v -> {
            Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(switchActivityIntent);
        });
        String accountName = getSharedPreferences("Asetukset",MODE_PRIVATE).getString("accountName", "");
        if(accountName == null || accountName == ""){
            Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(switchActivityIntent);
        }else{
            statsList = (ListView) findViewById(R.id.statsList);
            StatsAdapter adapter = new StatsAdapter(this,R.layout.stat,stats);
            statsList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Thread newThread = new Thread(() -> {
                try {
                    URL url = new URL("https://secure.runescape.com/m=hiscore/index_lite.ws?player=" + accountName);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader br = null;
                    if (con.getResponseCode() == 200) {
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String strCurrentLine;
                        int counter = 0;
                        while ((strCurrentLine = br.readLine()) != null) {
                            try {
                                String lvl = strCurrentLine.split(",")[1];
                                StatsObject uus = new StatsObject(RunescapeStats.values()[counter], Integer.getInteger(lvl));
                                stats.add(uus);
                                counter++;
                            } catch (Exception ex1) {
                            }
                        }
                    }
                }catch (Exception ex2){
                    Toast.makeText(this,"Can't update", Toast.LENGTH_LONG);
                }
            });
            newThread.start();

    }

}
}