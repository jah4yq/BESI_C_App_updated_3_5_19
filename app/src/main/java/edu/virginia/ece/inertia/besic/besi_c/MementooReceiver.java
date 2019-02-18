package edu.virginia.ece.inertia.besic.besi_c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import edu.virginia.ece.inertia.besic.besi_c.utils.FedConstants;

public class MementooReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            context.startService(new Intent(context, ControlService.class).putExtra(FedConstants.BOOT, FedConstants.BOOT));

        }else  if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            context.startService(new Intent(context, ControlService.class).putExtra(FedConstants.BOOT, FedConstants.BOOT));

        }else  if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            context.startService(new Intent(context, ControlService.class).putExtra(FedConstants.BOOT, FedConstants.BOOT));

        }
    }
}




//context.startService(new Intent(context, BLEService.class).putExtra(FedConstants.BOOT, FedConstants.BOOT));