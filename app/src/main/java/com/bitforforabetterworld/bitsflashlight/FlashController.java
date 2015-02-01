package com.bitforforabetterworld.bitsflashlight;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class FlashController {
    private Camera camera;
    private final Context context;
    private final Toaster toaster;

    public FlashController(Context context) {
        this.context = context;
        this.toaster = new Toaster(context);
    }

    public synchronized void enableFlash() {
        try {
            if (this.camera == null) {
                this.camera = Camera.open();
            }
            if (this.camera != null) {
                Camera.Parameters parameters = this.camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        }
        catch (Exception exp) {
            toaster.toast("Error: "+exp.getMessage());
        }
    }

    public synchronized void disableFlash() {
        try {
            if (this.camera == null) {
                this.camera = Camera.open();
            }
            if (this.camera != null) {
                this.camera.stopPreview();
                this.camera.release();
                this.camera = null;
            }
        }
        catch (Exception exp) {
            toaster.toast("Error: "+exp.getMessage());
        }
    }

    public boolean hasCameraHardware() {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
