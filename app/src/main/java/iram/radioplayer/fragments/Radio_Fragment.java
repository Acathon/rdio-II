package iram.radioplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import iram.radioplayer.CallbackToActivity;
import iram.radioplayer.R;

/**
 * Created by musta on 16/11/2016.
 */

public class Radio_Fragment extends Fragment {
    View view;
    ImageView rdImage;
    TextView txtDescription;
    LinearLayout fragmentContainer;
    CallbackToActivity obj;

    int defaultColor = 0x000000;
    int vibrantColor = -1, mutedColor = -1;
    int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout, container, false);
        obj = (CallbackToActivity) getActivity();

        rdImage = (ImageView) view.findViewById(R.id.radio_image);
        fragmentContainer = (LinearLayout) view.findViewById(R.id.container);
        setUpFragmentData();

        return view;
    }

    public void setUpFragmentData() {
        switch (position) {
            case 0:
                setRadioImage(R.mipmap.mosaique);
                break;
            case 1:
                setRadioImage(R.mipmap.ifm);
                break;
            case 2:
                setRadioImage(R.mipmap.cap);
                break;
            case 3:
                setRadioImage(R.mipmap.jawhara);
                break;
            case 4:
                setRadioImage(R.mipmap.shems);
                break;
            case 5:
                setRadioImage(R.mipmap.med);
                break;
            case 6:
                setRadioImage(R.mipmap.zitouna);
                break;
            case 7:
                setRadioImage(R.mipmap.ambiance);
                break;
            case 8:
                setRadioImage(R.mipmap.sabra);
                break;
            case 9:
                setRadioImage(R.mipmap.kelma);
                break;
            case 10:
                setRadioImage(R.mipmap.diwan);
                break;
            case 11:
                setRadioImage(R.mipmap.express);
                break;
            case 12:
                setRadioImage(R.mipmap.knooz);
                break;
            case 13:
                setRadioImage(R.mipmap.mfm);
                break;
            case 14:
                setRadioImage(R.mipmap.national);
                break;
            case 15:
                setRadioImage(R.mipmap.moja);
                break;
            case 16:
                setRadioImage(R.mipmap.nejma);
                break;
            case 17:
                setRadioImage(R.mipmap.oasis);
                break;
            case 18:
                setRadioImage(R.mipmap.oxygene);
                break;
            case 19:
                setRadioImage(R.mipmap.ribat);
                break;
            case 20:
                setRadioImage(R.mipmap.radio6);
                break;
            case 21:
                setRadioImage(R.mipmap.saraha);
                break;
        }
    }

    public void setRadioImage(int image) {
        rdImage.setImageResource(image);
    }

    public Fragment setFragmentPosition(int position) {
        this.position = position;
        return this;
    }
}
