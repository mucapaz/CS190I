package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Samuel on 2/11/2016.
 */
public class AudioFragment extends Fragment{

    View rootView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.audio_layout, container, false);
        button = (Button) rootView.findViewById(R.id.audio_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer	mp = MediaPlayer.create(getContext(), R.raw.fanfare);
                mp.start();
            }
        });
        return rootView;
    }
}
