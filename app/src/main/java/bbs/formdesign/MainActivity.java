package bbs.formdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    MaterialButtonToggleGroup tg;
    SwitchMaterial isVolunteer;
    TextInputEditText dateOfBirth;
    RadioButton yes,no;
    MaterialButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateOfBirth = findViewById(R.id.date_of_birth);
        isVolunteer = findViewById(R.id.is_volunteer);
        yes = findViewById(R.id.radio_yes);
        no = findViewById(R.id.radio_no);
        tg = findViewById(R.id.is_resident);
        isVolunteer = findViewById(R.id.is_volunteer);
        next = findViewById(R.id.next_second_page);

        //initiate a date picker dialog for picking from the calender
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //set current date into the calender and
                // after user click on a date it will replace the text of the button with the new date.
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                String myFormat="MM/dd/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                dateOfBirth.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        //end of initialization
        //After clicking button it will show date and user can pick any date
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //end of date button

        tg.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                //Toast.makeText(MainActivity.this, String.valueOf(checkedId).concat(String.valueOf(isChecked)),Toast.LENGTH_SHORT ).show();
                switch (checkedId){
                    case R.id.yes:
                        if (isChecked)
                            Toast.makeText(MainActivity.this, "yes",Toast.LENGTH_SHORT ).show();
                        break;
                    case R.id.no:
                        if (isChecked)
                            Toast.makeText(MainActivity.this, "no",Toast.LENGTH_SHORT ).show();
                        break;
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        isVolunteer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_yes:
                if (checked)
                    Toast.makeText(this, R.string.label_male, Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radio_no:
                if (checked)
                    Toast.makeText(this, R.string.label_female, Toast.LENGTH_SHORT).show();
                    break;
        }
    }
}