package com.lewa.crazychapter11;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

public class NewsFragment extends Fragment {  
  
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View newsLayout = inflater.inflate(R.layout.news_layout, container,  
                false);  
        return newsLayout;  
    }  
  
}  