package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;



import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

//    final int LAND = getResources().getConfiguration().ORIENTATION_LANDSCAPE;
//    final int PORT = getResources().getConfiguration().ORIENTATION_PORTRAIT;

    int position = -1;

    ButtonsFragment buttonsFragment;
    Fragment fragment;
    Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){

            buttonsFragment = new ButtonsFragment();
            buttonsFragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().add(R.id.land_buttons,buttonsFragment).commit();

        }else{
            buttonsFragment = new ButtonsFragment();
            buttonsFragment.setArguments(savedInstanceState);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,buttonsFragment).commit();
        }

        if(savedInstanceState != null){

            if(savedInstanceState.containsKey("position")){
                position = savedInstanceState.getInt("position");
                if(position == 0 || position == 1){
                    bundle = savedInstanceState;
                }else{
                    bundle = null;
                }

                switch (position){
                    case 0:
                        speechToTextButton(null);
                        break;
                    case 1:
                        textToSpeechButton(null);
                        break;
                    case 2:
                        audioButton(null);
                        break;
                    case 3:
                        videoButton(null);
                        break;
                    case 4:
                        animationButton(null);
                        break;
                }
            }
        }

    }

    public void speechToTextButton(View view){
        position = 0;



        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            SpeechFragment frag = new SpeechFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.land_conteiner, frag).commit();
            fragment = frag;
        }else{
            SpeechFragment frag = new SpeechFragment();

            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, frag);
            fm.addToBackStack(null);
            fm.commit();
            fragment = frag;
        }

    }

    public void textToSpeechButton(View view){
        position = 1;

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            TextFragment frag = new TextFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.land_conteiner, frag).commit();
            fragment = frag;
        }else{
            TextFragment frag = new TextFragment();

            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, frag);
            fm.addToBackStack(null);
            fm.commit();
            fragment = frag;
        }
    }

    public void audioButton(View view){
        position = 2;
        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            AudioFragment frag = new AudioFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.land_conteiner, frag).commit();
            fragment = frag;
        }else{
            AudioFragment frag = new AudioFragment();
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, frag);
            fm.addToBackStack(null);
            fm.commit();
            fragment = frag;
        }
    }

    public void videoButton(View view){
        position = 3;
        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            VideoFragment frag = new VideoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.land_conteiner, frag).commit();
            fragment = frag;
        }else{
            VideoFragment frag = new VideoFragment();
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, frag);
            fm.addToBackStack(null);
            fm.commit();
            fragment = frag;
        }
    }

    public void animationButton(View view){
        position = 4;
        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            AnimationFragment frag = new AnimationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.land_conteiner, frag).commit();
            fragment = frag;
        }else{
            AnimationFragment frag = new AnimationFragment();
            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
            fm.replace(R.id.fragment_container, frag);
            fm.addToBackStack(null);
            fm.commit();
            fragment = frag;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);

    }

    private void setWidth(int orientation, LinearLayout v){
        if(orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            v.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            // or ViewGroup.LayoutParams.WRAP_CONTENT
                            500,
                            // or ViewGroup.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT ) );
        }else if(orientation == getResources().getConfiguration().ORIENTATION_PORTRAIT){
            v.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            // or ViewGroup.LayoutParams.WRAP_CONTENT
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            // or ViewGroup.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT ) );
        }
    }

}
