package com.ariyalion.immersivetest;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
            ((MainActivity)getActivity()).hideSystemUI();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
            params.width = getView().getWidth();
            params.height = getView().getHeight();
            mView.setLayoutParams(params);
            mView.requestLayout();
            isSmall = false;
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
    }
}
