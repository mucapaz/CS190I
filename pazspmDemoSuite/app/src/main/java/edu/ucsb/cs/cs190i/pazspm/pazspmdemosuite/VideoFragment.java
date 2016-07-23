package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;

import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * Created by Samuel on 2/11/2016.
 */
public class VideoFragment extends Fragment {
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.video_layout, container, false);

        getActivity().getWindow().setFormat(PixelFormat.TRANSLUCENT);
        VideoView videoHolder = (VideoView) rootView.findViewById(R.id.video);

        Uri video = Uri.parse("android.resource://" + getActivity().getPackageName() + "/"
                + R.raw.bigbuck);

        videoHolder.setVideoURI(video);
        videoHolder.start();
        return rootView;
    }
}
