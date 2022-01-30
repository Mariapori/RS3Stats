package fi.mariapori.rs3stats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Button button = (Button) findViewById(R.id.btnTallenna);
        EditText nick = findViewById(R.id.txtNickname);
        String accountName = getSharedPreferences("Asetukset",MODE_PRIVATE).getString("accountName", "");
        if(accountName != ""){
            nick.setText(accountName);
        }
        button.setOnClickListener(lol -> {
            getSharedPreferences("Asetukset",MODE_PRIVATE).edit().putString("accountName",nick.getText().toString()).apply();
            Intent switchActivityIntent = new Intent(this, MainActivity.class);
            startActivity(switchActivityIntent);
        });

    }
}