package com.lewa.crazychapter11;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

public class Fragment2 extends Fragment {  
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  
        return inflater.inflate(R.layout.fragment2, container, false);  
    }  
  
}