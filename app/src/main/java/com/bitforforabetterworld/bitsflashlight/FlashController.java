package com.bitforforabetterworld.bitsflashlight;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class FlashController {
    private Camera camera;
    private final Context context;
    private final Toaster toaster;
    private final boolean hasCamera;

    public FlashController(Context context) {
        this.context = context;
        this.toaster = new Toaster(context);
        this.hasCamera = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public synchronized void enableFlash() {
        if (!hasCamera) {
            toaster.toast("No camera hardware detected.");
            return;
        }
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
        if (!hasCamera) {
            return;
        }
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
        return this.hasCamera;
    }
}
