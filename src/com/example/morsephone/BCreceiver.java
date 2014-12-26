package com.example.morsephone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;

public class BCreceiver extends BroadcastReceiver {
	static long sLastClick = 0;
	static int countSC=0;
	static int countDC=0;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())){
        	KeyEvent event = (KeyEvent) intent .getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        	if(event.getAction() == KeyEvent.ACTION_DOWN){
        		Intent i = new Intent(context, MainActivity.class);
        		long time = SystemClock.uptimeMillis();
        		if(time- sLastClick < 400)
        		{
        			countDC++;
        			countSC--;
        			Log.e("Double click", String.valueOf(countDC));
        			sLastClick=0;
        			i.putExtra("Click", 2);
        		}
        		else
        		{
        			countSC++;
        			sLastClick = time;
        			i.putExtra("Click", 1);
        			Log.e("Just one", String.valueOf(countSC));
        		}
	        	Log.e("This is", "Down");
	            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	            context.startActivity(i);
        	}
        }
    }
}