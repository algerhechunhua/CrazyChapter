package com.lewa.crazychapter11;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.app.Fragment;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

public class MessageFragment extends Fragment {  
  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View messageLayout = inflater.inflate(R.layout.message_layout, container, false);  
        return messageLayout;  
    }  
  
}