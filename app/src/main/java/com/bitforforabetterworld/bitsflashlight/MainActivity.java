package com.bitforforabetterworld.bitsflashlight;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends ActionBarActivity {

    private FlashController flashController;
    private Toaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.flashController = new FlashController(getApplicationContext());
        this.toaster = new Toaster(getApplicationContext());

        Switch powerSwitch = (Switch) findViewById(R.id.power_switch);
        powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onPowerChanged(buttonView);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void onPowerChanged(View view) {
        Switch theSwitch = (Switch)view;
        if (!flashController.hasCameraHardware()) {
            toaster.toast("No camera hardware detected.");
            return;
        }
        if (theSwitch.isChecked()) {
            toaster.toast("Power ON");
            flashController.enableFlash();
        }
        else {
            toaster.toast("Power OFF");
            flashController.disableFlash();
        }
    }
}
