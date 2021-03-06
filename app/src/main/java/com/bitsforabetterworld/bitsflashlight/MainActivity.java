package com.bitsforabetterworld.bitsflashlight;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
    protected void onResume() {
        super.onResume();
        Switch powerSwitch = (Switch) findViewById(R.id.power_switch);
        powerSwitch.setChecked(true);
        flashController.enableFlash();
    }

    @Override
    protected void onDestroy() {
        flashController.disableFlash();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Switch powerSwitch = (Switch) findViewById(R.id.power_switch);
        powerSwitch.setChecked(false);
        flashController.disableFlash();
        super.onPause();
    }

    private void onPowerChanged(View view) {
        Switch theSwitch = (Switch)view;

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
