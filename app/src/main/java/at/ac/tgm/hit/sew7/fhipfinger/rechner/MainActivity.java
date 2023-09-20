package at.ac.tgm.hit.sew7.fhipfinger.rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView output = findViewById(R.id.textView);
        output.setOnTouchListener((view, motionEvent) -> {
            output.setText("0");
            output.setTextColor(Color.WHITE);

            return true;
        } );
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText input1 = findViewById(R.id.editTextNumber);
        EditText input2 = findViewById(R.id.editTextNumber2);
        Spinner spinner = findViewById(R.id.spinner);
        TextView result = findViewById(R.id.textView);

        int id = item.getItemId();

        if(id == R.id.action_reset) {
            input1.setText("");
            input2.setText("");
            result.setText("0");
            spinner.setSelection(0);
            return true;
        } else if(id == R.id.action_info) {
            Toast.makeText(this, "Version: 1.0, Author: Florian Hipfinger", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void calc(View v) {
        EditText input1 = findViewById(R.id.editTextNumber);
        int number1 = Integer.parseInt(input1.getText().toString());
        EditText input2 = findViewById(R.id.editTextNumber2);
        int number2 = Integer.parseInt(input2.getText().toString());
        int result;
        TextView output = findViewById(R.id.textView);

        Spinner spinner = findViewById(R.id.spinner);
        String rechenart = spinner.getSelectedItem().toString();

        if(rechenart.equals("+")) {
            result = number1+number2;
        } else if(rechenart.equals("-")) {
            result = number1 - number2;
        } else if(rechenart.equals("*")) {
            result = number1 * number2;
        } else if(rechenart.equals("/")) {
            result = number1 / number2;
        }
        else {
            result = 0;
        }
        if(result < 0) {
            output.setText(String.valueOf(result));
            output.setTextColor(Color.RED);
        } else if(result > 0){
            output.setText(String.valueOf(result));
            output.setTextColor(Color.BLACK);
        } else {
            output.setText(String.valueOf(result));
            output.setTextColor(Color.WHITE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Button button = findViewById(R.id.button2);
            button.setBackgroundColor(Color.GREEN);
    }


    public void saveCalcType(View v) {
        String c;
        Spinner spinner = findViewById(R.id.spinner);
        String rechenart = spinner.getSelectedItem().toString();
        SharedPreferences sp = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("rechentyp", rechenart);
        editor.commit();
        Toast.makeText(this, "Der Rechentyp wurde erfolgreich gespeichert", Toast.LENGTH_SHORT).show();
    }

    public void memoryCallButton(View v) {
        SharedPreferences sp = this.getPreferences(MODE_PRIVATE);
        Spinner spinner = findViewById(R.id.spinner);
        String rechentyp = sp.getString("rechentyp", "+");
        if(rechentyp.equals("+")) {
            spinner.setSelection(0);
        } else if(rechentyp.equals("-")) {
            spinner.setSelection(1);
        } else if(rechentyp.equals("*")) {
            spinner.setSelection(2);
        } else if(rechentyp.equals("/")) {
            spinner.setSelection(3);
        }
    }
}