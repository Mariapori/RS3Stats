package fi.mariapori.rs3stats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<StatsObject> stats = new ArrayList<>();
    ListView statsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button asetukset = findViewById(R.id.btnAsetukset);
        asetukset.setOnClickListener(v -> {
            Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(switchActivityIntent);
        });
        String accountName = getSharedPreferences("Asetukset",MODE_PRIVATE).getString("accountName", "");
        if(accountName.isEmpty()){
            Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(switchActivityIntent);
        }else{
            statsList = findViewById(R.id.statsList);
            statsList.setAdapter(null);
            StatsAdapter adapter = new StatsAdapter(this,R.layout.stat,stats);
            statsList.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://secure.runescape.com/m=hiscore/index_lite.ws?player=" + accountName;
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        try{
                            stats.clear(); // Siivotaan lista ettei tule duplikaatteja
                            HandleResponse(response,adapter);
                        }catch (Exception ex1){
                        }
                    }, error -> {

            });

            queue.add(stringRequest);
        }

}

    @Override
    protected void onResume() {
        super.onResume();

        setContentView(R.layout.activity_main);
        Button asetukset = findViewById(R.id.btnAsetukset);
        asetukset.setOnClickListener(v -> {
            Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(switchActivityIntent);
        });
        String accountName = getSharedPreferences("Asetukset",MODE_PRIVATE).getString("accountName", "");
        if(accountName.isEmpty()){
            Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
            startActivity(switchActivityIntent);
        }else{
            statsList = findViewById(R.id.statsList);
            statsList.setAdapter(null);
            stats.clear(); // Siivotaan lista ettei tule duplikaatteja
            StatsAdapter adapter = new StatsAdapter(this,R.layout.stat,stats);
            statsList.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://secure.runescape.com/m=hiscore/index_lite.ws?player=" + accountName;
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        try{
                            stats.clear(); // Siivotaan lista ettei tule duplikaatteja
                            HandleResponse(response,adapter);
                        }catch (Exception ex1){
                        }
                    }, error -> {

            });

            queue.add(stringRequest);
        }
    }
    void HandleResponse(String response, StatsAdapter adapter) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(response));
        String strCurrentLine;
        int counter = 0;
        while ((strCurrentLine = br.readLine()) != null) {
            try {
                int lvl = Integer.parseInt(strCurrentLine.split(",")[1]);
                long exp = Integer.parseInt(strCurrentLine.split(",")[2]);
                StatsObject uus = new StatsObject(RunescapeStats.values()[counter],lvl,exp);
                counter++;
                stats.add(uus);
                adapter.notifyDataSetChanged();
            }catch(Exception ex2) {

            }
        }
    }
}