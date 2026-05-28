package com.example.inmobiliaria;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ShakeDetector implements SensorEventListener {
    private static final float SHAKE_THRESHOLD = 25f;
    private static final int SHAKE_SLOP_TIME_MS = 1500;
    private long lastShakeTime;
    private OnShakeListener listener;

    public interface OnShakeListener {
        void onShake();
    }

    public void setOnShakeListener(OnShakeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

            if (acceleration > SHAKE_THRESHOLD) {
                long currentTime = System.currentTimeMillis();
                if (lastShakeTime + SHAKE_SLOP_TIME_MS > currentTime) {
                    return;
                }
                lastShakeTime = currentTime;

                if (listener != null) {
                    listener.onShake();
                }
            }
        }
    }
}
