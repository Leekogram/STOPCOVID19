package com.aldroids.stopcovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    Uri uri1,uri2,uri3,uri4,uri5,uri6,uri7;
    LinearLayout share1,share2,share3,share4, share5;
    TextView dont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setUpWindowAnimations();

        share1 = findViewById(R.id.Share1);
        share2 = findViewById(R.id.Share2);
        share3 = findViewById(R.id.Share3);
        share4 = findViewById(R.id.Share4);
        share5 = findViewById(R.id.Share5);

        uri1 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image1");
        uri2 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image2");
        uri3 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image3");
        uri4 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image4");
        uri5 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image5");
        uri6 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image6");
        uri7 = Uri.parse("android.resource://com.aldroids.stopcovid19/drawable/image7");

        dont = findViewById(R.id.dontText);
        final String text = dont.getText().toString().trim();
        //final InputStream stream1 = getContentResolver().openInputStream(uri1);
        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareImage1Intent = new Intent();
                shareImage1Intent.setAction(Intent.ACTION_SEND);
                shareImage1Intent.putExtra(Intent.EXTRA_STREAM,uri1);
                shareImage1Intent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareImage1Intent,"Share image to..."));
            }
        });

        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Uri> imageUri = new ArrayList<Uri>();
                imageUri.add(uri4);
                imageUri.add(uri1);
                Intent shareImage2Intent = new Intent();
                shareImage2Intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                shareImage2Intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,imageUri);
                shareImage2Intent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareImage2Intent,"Share images to..."));
            }
        });

        share3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareImage1Intent = new Intent();
                shareImage1Intent.setAction(Intent.ACTION_SEND);
                shareImage1Intent.putExtra(Intent.EXTRA_STREAM,uri6);
                shareImage1Intent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareImage1Intent,"Share image to..."));
            }
        });

        share4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareImage1Intent = new Intent();
                shareImage1Intent.setAction(Intent.ACTION_SEND);
                shareImage1Intent.putExtra(Intent.EXTRA_STREAM,uri7);
                shareImage1Intent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareImage1Intent,"Share image to..."));
            }
        });

        share5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareDontIntent = new Intent();
                shareDontIntent.setAction(Intent.ACTION_SEND);
                shareDontIntent.putExtra(Intent.EXTRA_TEXT,text);
                shareDontIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(shareDontIntent, null);
                startActivity(shareIntent);
            }
        });
    }
    private void setUpWindowAnimations(){


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade enter_fade = new Fade();
            enter_fade.setDuration(2000);
            getWindow().setEnterTransition(enter_fade);

            Slide return_slide = new Slide();
            return_slide.setDuration(2000);
            getWindow().setReturnTransition(return_slide);


        }
    }
}
