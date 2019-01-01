package com.atulvinod.moneytrack;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;

public class Recents_Scroll_Detect extends RecyclerView.OnScrollListener {
        RapidFloatingActionLayout layout;
        RapidFloatingActionButton btn;
        public Recents_Scroll_Detect(RapidFloatingActionLayout layout, RapidFloatingActionButton btn){
        this.layout = layout;
        this.btn = btn;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch(newState){
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    btn.setVisibility(View.INVISIBLE);

                    layout.setVisibility(View.INVISIBLE);

                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    btn.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    break;
                case RecyclerView.SCROLL_STATE_IDLE:
                    btn.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    break;
            }
        }

}
