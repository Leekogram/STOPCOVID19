package com.aldroids.stopcovid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class More extends AppCompatActivity {
    TextView contactWho, coronaNews, Faq;
    LinearLayout feedback, about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        setUpWindowAnimations();

        contactWho = findViewById(R.id.contactWho);
        coronaNews = findViewById(R.id.newsAboutcorona);
        Faq = findViewById(R.id.faq);
        feedback = findViewById(R.id.feedback);
        about = findViewById(R.id.about);

        Spanned contact = Html.fromHtml(
                "<a href='https://www.who.int/about/who-we-are/contact-us'>Contact World Health Organization (WHO)</a>");
        Spanned news = Html.fromHtml(
                "<a href='https://www.who.int/emergencies/diseases/novel-coronavirus-2019'>WHO Latest News on Coronavirus</a>");
        Spanned faqText = Html.fromHtml(
                "<a href='https://www.who.int/news-room/q-a-detail/q-a-coronaviruses'>FAQ on coronavirus</a>");
        contactWho.setMovementMethod(LinkMovementMethod.getInstance());
        contactWho.setText(contact);

        coronaNews.setMovementMethod(LinkMovementMethod.getInstance());
        coronaNews.setText(news);

        Faq.setMovementMethod(LinkMovementMethod.getInstance());
        Faq.setText(faqText);


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackintent = new Intent(Intent.ACTION_SENDTO);
                feedbackintent.setData(Uri.parse("mailto:Leekogram@gmail.com"));
                feedbackintent.putExtra(Intent.EXTRA_EMAIL, "Leekogram@gmail.com");
                feedbackintent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from STOPCOVID-19 App");
                feedbackintent.putExtra(Intent.EXTRA_TEXT,  "");


                try {
                    startActivity(Intent.createChooser(feedbackintent, "Send email using..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(More.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"STOPCOVID-19 VERSION 1.0.0.",Toast.LENGTH_LONG).show();
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.About:
                        Intent a = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.Settings:
                        Intent s = new Intent(getApplicationContext(), Settings.class);
                        startActivity(s);
                        break;
                    case R.id.More:
                        Intent m = new Intent(getApplicationContext(), More.class);
                        startActivity(m);




                }
                return true;
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

            Slide exit_slide = new Slide();
            exit_slide.setDuration(2000);
            getWindow().setExitTransition(exit_slide);
        }
    }
}
