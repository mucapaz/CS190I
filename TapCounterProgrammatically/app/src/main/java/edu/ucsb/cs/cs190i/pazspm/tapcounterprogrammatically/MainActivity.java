package edu.ucsb.cs.cs190i.pazspm.tapcounterprogrammatically;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = new TextView(this);
        text.setText("You tapped 0 times.");
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);

        final Button button = new Button(this);
        button.setText("TAP ME!");

        View.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){
                increaseCounter();
                text.setText("You tapped "+ counter + " times.");
            }
        };

        button.setOnClickListener(listener);

        text.setId(1);
        button.setId(2);

        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                ,RelativeLayout.LayoutParams.WRAP_CONTENT);
        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);


        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                ,RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        buttonParams.addRule(RelativeLayout.BELOW, text.getId());

        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(button, buttonParams);
        layout.addView(text, textParams);


        setContentView(layout);
    }

    public void increaseCounter(){
        counter++;
    }
}
