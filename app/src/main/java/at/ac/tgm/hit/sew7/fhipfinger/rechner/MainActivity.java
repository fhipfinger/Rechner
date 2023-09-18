package at.ac.tgm.hit.sew7.fhipfinger.rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    }
    public void calc(View v) {
        EditText input1 = findViewById(R.id.editTextNumber);
        int number1 = Integer.parseInt(input1.getText().toString());
        EditText input2 = findViewById(R.id.editTextNumber2);
        int number2 = Integer.parseInt(input2.getText().toString());
        int result;
        RadioGroup rg = findViewById(R.id.radioGroup);
        TextView output = findViewById(R.id.textView);
        if(rg.getCheckedRadioButtonId() == R.id.radioButton) {
            result = number1+number2;
        } else if(rg.getCheckedRadioButtonId() == R.id.radioButton2) {
            result = number1 - number2;
        } else if(rg.getCheckedRadioButtonId() == R.id.radioButton3) {
            result = number1 * number2;
        } else if(rg.getCheckedRadioButtonId() == R.id.radioButton4) {
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
        RadioGroup rg = findViewById(R.id.radioGroup);
        if(rg.getCheckedRadioButtonId() == R.id.radioButton) {
            c = "+";
        } else if(rg.getCheckedRadioButtonId() == R.id.radioButton2) {
            c = "-";
        } else if(rg.getCheckedRadioButtonId() == R.id.radioButton3) {
            c = "*";
        } else if(rg.getCheckedRadioButtonId() == R.id.radioButton4) {
            c = "/";
        }
        else {
            c = " ";
        }
        SharedPreferences sp = this.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("rechentyp", c);
        editor.commit();
        Toast.makeText(this, "Der Rechentyp wurde erfolgreich gespeichert", Toast.LENGTH_SHORT).show();
    }

    public void memoryCallButton(View v) {
        SharedPreferences sp = this.getPreferences(MODE_PRIVATE);
        String rechentyp = sp.getString("rechentyp", "+");
        RadioButton rb1 = findViewById(R.id.radioButton);
        RadioButton rb2 = findViewById(R.id.radioButton2);
        RadioButton rb3 = findViewById(R.id.radioButton3);
        RadioButton rb4 = findViewById(R.id.radioButton4);
        if(rechentyp.equals("+")) {
            rb1.setChecked(true);
        } else if(rechentyp.equals("-")) {
            rb2.setChecked(true);
        } else if(rechentyp.equals("*")) {
            rb3.setChecked(true);
        } else if(rechentyp.equals("/")) {
            rb4.setChecked(true);
        }
    }
}