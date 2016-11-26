package iram.radioplayer.activities;

import android.animation.ArgbEvaluator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

import iram.radioplayer.CallbackToActivity;
import iram.radioplayer.R;
import iram.radioplayer.adapters.Radio_PagerAdapter;
import iram.radioplayer.fragments.Radio_Fragment;

public class MainActivity extends AppCompatActivity implements CallbackToActivity, MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {


    TextView mTextDescription;
    TextView mTextName;
    Radio_Fragment rfragment;
    DisplayMetrics metrics;
    Toolbar mToolbar;
    ViewPager mViewPager;
    Radio_PagerAdapter mPagerAdapter;
    ArgbEvaluator argbEvaluator;
    boolean firstTime = true;
    MediaPlayer player = null;
    AudioManager volume;
    SeekBar volumeBar;
    String stream;

    @Override
    public void colorFetched(int position, int vibrant, int mutedColor) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextDescription = (TextView) findViewById(R.id.RadioDescription);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_minimize);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent minimize = new Intent(Intent.ACTION_MAIN);
                    minimize.addCategory(Intent.CATEGORY_HOME);
                    minimize.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle animate = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.push_down_in, R.anim.push_down_out).toBundle();
                    startActivity(minimize, animate);
                }
            });
        }
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toolbar, null);
        mTextName = (TextView) view.findViewById(R.id.radio_name);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = (metrics.widthPixels / 12);

        volume = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        argbEvaluator = new ArgbEvaluator();

        mPagerAdapter = new Radio_PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setPageMargin(-pageMargin);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 1:
                        setRadioName("IFM");
                        setRadioDescription("La 1ère Radio du Rire en Tunisie");
                        setRadioLink("http://5.135.138.182:8000/direct");
                        break;
                    case 0:
                        setRadioName("Mosaique FM");
                        setRadioDescription("وصل صوتك");
                        setRadioLink("http://radio.mosaiquefm.net:8000/mosalive");
                        break;
                    case 2:
                        setRadioName("Cap FM");
                        setRadioDescription("La 1ère radio du Cap Bon");
                        setRadioLink("http://stream8.tanitweb.com/capfm");
                        break;
                    case 3:
                        setRadioName("Jawhara FM");
                        setRadioDescription("Nous sommes là où vous êtes");
                        setRadioLink("http://streaming2.toutech.net:8000/jawharafm");
                        break;
                    case 4:
                        setRadioName("Radio Med");
                        setRadioDescription("La nouvelle radio généraliste du Cap Bon");
                        setRadioLink("http://stream6.tanitweb.com/radiomed");
                        break;
                    case 5:
                        setRadioName("Shems FM");
                        setRadioDescription("La radio qui fait du bien à vos oreilles");
                        setRadioLink("http://stream6.tanitweb.com/shems");
                        break;
                    case 6:
                        setRadioName("Zitouna FM");
                        setRadioDescription("Du cœur à cœur من القلب إلى القلب");
                        setRadioLink("http://stream8.tanitweb.com/zitounafm");
                        break;
                    case 7:
                        setRadioName("Sabra FM");
                        setRadioDescription("la première radio de Kairouan");
                        setRadioLink("http://stream6.tanitweb.com/sabrafm");
                        break;
                    case 8:
                        setRadioDescription();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setRadioName(String name) {
        this.mTextName.setText(name);
    }

    public void setRadioDescription(String description) {
        this.mTextDescription.setText(description);
    }

    public void setRadioLink(String link) {
        this.stream = link;
    }

    public void playerInit(String link) {
        try {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnBufferingUpdateListener(this);
            player.setDataSource(link);
            player.setOnPreparedListener(this);
            player.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        player.stop();
        player.reset();
        player.release();
    }

    @Override
    protected void onResume() {
        super.onResume();

        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

    @Override
    protected void onStop() {
        super.onStop();

        player.reset();
        player.stop();
        player.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.reset();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
