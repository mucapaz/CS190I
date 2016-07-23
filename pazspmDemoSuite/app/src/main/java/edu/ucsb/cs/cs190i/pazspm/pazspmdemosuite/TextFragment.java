package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samuel on 2/10/2016.
 */
public class TextFragment extends Fragment{
    View rootView;
    Button button;

    boolean ready = false;
    TextToSpeech tts;
    ArrayList<String> ar;
    public EditText tv;
    static String texto =null;
    //TextView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.text_layout, container, false);
         tv = (EditText) rootView.findViewById(R.id.edit_text);
        if(texto != null){
            tv.setText(texto);
        }

        ar = new ArrayList<String>();
        tts = new TextToSpeech(this.getContext(), new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status){
                ready = true;
            }
        });


        button = (Button) rootView.findViewById(R.id.text_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText et = (EditText) getActivity().findViewById(R.id.edit_text);
                speak(et.getText().toString());
                texto = et.getText().toString();
            }
        });

        return rootView;
    }

    public void speak(String str){
        if(!ready){
            ar.add(str);
        }else{
            while(ar.size()!=0){
                tts.speak(ar.get(0), TextToSpeech.QUEUE_ADD, null);
                ar.remove(0);
            }
            tts.speak(str,  TextToSpeech.QUEUE_ADD, null);
        }
    }
}
