package com.ariyalion.immersivetest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by bavan on 24/06/16.
 */
public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.main_fragment, container, false);
        (view.findViewById(R.id.add_fragment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().add(R.id.frameLayout_fragment_main, new SubMainFragment(), "").addToBackStack("SubMainFragment").commit();
            }
        });
        (view.findViewById(R.id.launch_second)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).hideSystemUI();
                (getActivity().findViewById(R.id.frameLayout_fragment_secondary)).setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().add(R.id.frameLayout_fragment_secondary, new SecondaryFragment(), "").commit();
            }
        });
        return view;
    }
}
