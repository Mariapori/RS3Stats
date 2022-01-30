package fi.mariapori.rs3stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

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
            StatsObject attack = new StatsObject("Attack",99);
            stats.add(attack);
            StatsAdapter adapter = new StatsAdapter(this,R.layout.stat,stats);
            statsList.setAdapter(adapter);
        }
    }
}