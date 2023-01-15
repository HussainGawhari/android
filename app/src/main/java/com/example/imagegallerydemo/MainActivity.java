package com.example.imagegallerydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

import java.nio.channels.GatheringByteChannel;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

    ListView mLstCar;
    Gallery mImgCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLstCar = (ListView) findViewById(R.id.lstCars);
        mImgCar = (Gallery) findViewById(R.id.imgCars);

        mLstCar.setClickable(true);
        mLstCar.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Integer SelectedCar = Integer.valueOf(i);
        String SelectedCarPos=SelectedCar.toString();

        mImgCar.setAdapter((SpinnerAdapter) new AddImgAdp(this,SelectedCarPos));
    }
}

class AddImgAdp extends BaseAdapter
{
    int GalItemBg;
    private Context cont;
    String positionLast;


    private Integer[] Imgidb = {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4};
    private Integer[] Imgidh = {R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4};
    private Integer[] Imgidm = {R.drawable.m1, R.drawable.m2, R.drawable.m3, R.drawable.m4};

    public AddImgAdp(Context c,String pos)
    {
        cont = c;
        positionLast=pos;

        TypedArray typArray = c.obtainStyledAttributes(R.styleable.GalleryTheme);
        GalItemBg = typArray.getResourceId(0,0);
        //GalItemBg = typArray.getResourceId(R.styleable.GalleryTheme_android_galleryItemBackground, 0);
        typArray.recycle();
    }

    public int getCount()
    {
        if(positionLast.equals("0"))
        {
            return Imgidb.length;
        }
        else if(positionLast.equals("1"))
        {
            return Imgidh.length;
        }
        else if(positionLast.equals("2"))
        {
            return Imgidm.length;
        }
        else
        {
            return Imgidm.length;
        }
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imgView = new ImageView(cont);

        if(positionLast.equals("0"))
        {
            imgView.setImageResource(Imgidb[i]);
        }
        else if(positionLast.equals("1"))
        {
            imgView.setImageResource(Imgidh[i]);
        }
        else if(positionLast.equals("2"))
        {
            imgView.setImageResource(Imgidm[i]);
        }
        else
        {
            imgView.setImageResource(Imgidm[i]);
        }

        // Fixing width & height for image to display
        imgView.setLayoutParams(new Gallery.LayoutParams(200, 200));
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        imgView.setBackgroundResource(GalItemBg);

        return imgView;
    }
}
