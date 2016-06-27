package com.ariyalion.immersivetest;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by bavan on 24/06/16.
 */
public class SecondaryFragment extends Fragment {

    boolean isSmall = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondary_fragment, container, false);
        ((Button)(view.findViewById(R.id.button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resize(getActivity().findViewById(R.id.second_container));
            }
        });
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        return view;
    }

    void resize(View mView){
        if(!isSmall){
            ((MainActivity)getActivity()).showSystemUI();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
            params.width = 400;
            params.height = 400;
            mView.setLayoutParams(params);
            mView.requestLayout();
            isSmall = true;
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            goFullScreen(mView);
        }
    }

    void goFullScreen(View mView){
        ((MainActivity)getActivity()).hideSystemUI();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
        mView.setLayoutParams(params);
        mView.requestLayout();
        isSmall = false;
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(!isSmall){
            goFullScreen(getActivity().findViewById(R.id.second_container));
        }
    }
}
