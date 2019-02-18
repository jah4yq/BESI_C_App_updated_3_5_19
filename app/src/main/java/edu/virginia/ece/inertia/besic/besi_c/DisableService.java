package edu.virginia.ece.inertia.besic.besi_c;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DisableService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        ClockfaceActivity.DISABLE =1;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
