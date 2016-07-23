package edu.ucsb.cs.cs190i.pazspm.cameraroll;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    static int SCREN_WIDTH = 0, nextImage = 0;

    ArrayList<ImageView> ar = new ArrayList<ImageView>();

    File folder;

    public RecyclerView mRecyclerView;
    public MyAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar("CameraRoll");
        setFab();

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        SCREN_WIDTH = metrics.widthPixels;

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        restoreLastInstance();


    }

    public void restoreLastInstance() {
        if (isExternalStorageReadable() && isExternalStorageWritable()) {
            folder = getAlbumStorageDir("/CS190IPics");
            System.out.println("HA-1");
            while (true) {

                File f = new File(folder, nextImage + ".png");
                System.out.println("HA-2 " + nextImage);
                if (f.exists()) {
                    System.out.println("HA-3");
                    Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                    mAdapter.add(bitmap);
                    nextImage++;
                } else {
                    break;
                }
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all) {
            mAdapter.deleteAll();
            for (int x = 0; x < nextImage; x++) {
                File f = new File(folder, x + ".png");
                f.delete();
            }

            nextImage = 0;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setActionBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
    }

    public void setFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[][] states = new int[][]{
                        new int[]{-android.R.attr.state_enabled}
                };

                int[] colors = new int[]{
                        Color.BLACK
                };

                fab.setBackgroundTintList(new ColorStateList(states, colors));

                dispatchTakePictureIntent();

            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            mAdapter.add(imageBitmap);
            saveBitmapToJPG(imageBitmap, (nextImage) + ".png");
            nextImage++;
            //addCardToLayout(createCard(imageBitmap));
        }
    }

    public void saveBitmapToJPG(Bitmap bitmap, String str) {
        //create a file to write bitmap data
        File f = new File(folder, str);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(Environment.getExternalStorageDirectory(), albumName);

        if (file.exists()) {
            System.out.println("Existent folder");
        } else if (!file.mkdir()) {
            System.out.println("Couldn't create folder");
        } else {
            System.out.println("Folder Created");
        }

        return file;
    }

//    public int dp(float dp) {
//        Context context = getApplicationContext();
//        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
//    }

//    public void addCardToLayout(ImageView iv) {
//        if(!ar.isEmpty()){
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ar.get(ar.size() - 1).getLayoutParams();
//            lp.setMargins(dp(6), dp(6), dp(6), dp(0));
//        }
//
//        LinearLayout ll = (LinearLayout) findViewById(R.id.c_main);
//        ll.addView(iv);
//        ar.add(iv);
//    }

//    public ImageView createCard(Bitmap imageBitmap) {
//        ImageView iv = new ImageView(this);
//        iv.setImageBitmap(imageBitmap);
//        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        lp.setMargins(dp(6), dp(6), dp(6), dp(6));
//        lp.width = dp(SCREN_WIDTH / 2) - lp.leftMargin - lp.rightMargin;
//        lp.height = dp(200);
//
//        iv.setLayoutParams(lp);
//        return iv;
//    }
}
