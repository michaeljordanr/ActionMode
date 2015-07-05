package com.example.michael.actionmode;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnLongClickListener, ActionMode.Callback {

    private TextView view;
    private int count = 0;
    private boolean actionModeActive = false;

    private MainActivity getActivity() {
        return this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView view = (TextView) findViewById(R.id.txt_number);
        view.setText("0");

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!actionModeActive) {
                    startActionMode(getActivity());
                    actionModeActive = true;
                }

                return true;
            }
        });
    }



    @Override
    public boolean onLongClick(View v) {
        if(!actionModeActive) {
            startActionMode(this);
            actionModeActive = true;
        }

        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        view = (TextView) findViewById(R.id.txt_number);
        if(item.getItemId() == R.id.act_add){
            count++;
            view.setText(String.valueOf(count));
            mode.finish();
            return true;
        }

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        actionModeActive = false;
    }
}
