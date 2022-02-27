package bbs.formdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Spinner division_spinner;
    AutoCompleteTextView district_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //spinner view for list of divisions. Will show a list of divisions and user can select any from them.
        division_spinner = findViewById(R.id.division_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.divisions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        division_spinner.setAdapter(adapter);
        division_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SecondActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SecondActivity.this, "no item selected", Toast.LENGTH_SHORT).show();
            }
        });
        //end of spinner view.

        district_spinner = findViewById(R.id.auto);
        List<String> districts = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, districts);
        district_spinner.setAdapter(adapter1);
    }
}