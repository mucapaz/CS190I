package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Samuel on 2/9/2016.
 */
public class SpeechFragment extends Fragment{
    final static int SPEECH_REQ = 1011;

    View rootView;
    ImageButton ib;
    public TextView tv;
    static String texto = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.speech_layout, container, false);

        tv = (TextView) rootView.findViewById(R.id.speech_text);
        if(texto != null){
            tv.setText(texto);
        }


        ib = (ImageButton) rootView.findViewById(R.id.button_speech);
        ib.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createSpeechIntent();
            }
        });

        return rootView;
    }


    public void createSpeechIntent(){
        Intent intent = new
                Intent(		RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,	"Say something");
        startActivityForResult(intent, SPEECH_REQ);
    }


    @Override
    public void onActivityResult(int requestCode,
                                       int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == SPEECH_REQ){
            if(intent != null) {
                ArrayList<String> list = intent.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);

                String res = list.get(0);

                tv.setText(res);
                texto = res;
            }
        }
    }

}
