package bbs.formdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    MaterialButtonToggleGroup tg;
    SwitchMaterial isVolunteer;
    TextInputEditText dateOfBirth;
    RadioButton yes,no;

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

        //initiate a date picker dialog for picking from the calender
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dateOfBirth.setText(dateFormat.format(myCalendar.getTime()));
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