package com.example.morsephone;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	 private AudioManager mAudioManager;
	 private ComponentName mRemoteControlResponder;
	 private int clickstate;
	 private long lastClick=0;
	 
	 
	 	private char evaluateCode(String str){
	 		if(str.equals("12"))
	 			return 'A';
	 		else if (str.equals("2111"))
	 			return 'B';
	 		else if (str.equals("2121"))
	 			return 'C';
	 		else if (str.equals("211"))
	 			return 'D';
	 		else if (str.equals("1"))
	 			return 'E';
	 		else if (str.equals("1121"))
	 			return 'F';
	 		else if (str.equals("221"))
	 			return 'G';
	 		else if (str.equals("1111"))
	 			return 'H';
	 		else if (str.equals("11"))
	 			return 'I';
	 		else if (str.equals("1222"))
	 			return 'J';
	 		else if (str.equals("212"))
	 			return 'K';
	 		else if (str.equals("1211"))
	 			return 'L';
	 		else if (str.equals("22"))
	 			return 'M';
	 		else if (str.equals("21"))
	 			return 'N';
	 		else if (str.equals("222"))
	 			return 'O';
	 		else if (str.equals("1221"))
	 			return 'P';
	 		else if (str.equals("2212"))
	 			return 'Q';
	 		else if (str.equals("121"))
	 			return 'R';
	 		else if (str.equals("111"))
	 			return 'S';
	 		else if (str.equals("2"))
	 			return 'T';
	 		else if (str.equals("112"))
	 			return 'U';
	 		else if (str.equals("1112"))
	 			return 'V';
	 		else if (str.equals("122"))
	 			return 'W';
	 		else if (str.equals("2112"))
	 			return 'X';
	 		else if (str.equals("2122"))
	 			return 'Y';
	 		else if (str.equals("2211"))
	 			return 'Z';
	 		return '\0';
	 	}
	 	
	 	public void copyText(View view) {
	 		ClipboardManager c = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	 		TextView t = (TextView)findViewById(R.id.Text2);
	 		CharSequence s = t.getText();
	 		ClipData clip = ClipData.newPlainText("MorseCode", s);
	 		c.setPrimaryClip(clip);
	 	}
	 	
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        getApplicationContext();
			mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	        mRemoteControlResponder = new ComponentName(getPackageName(),BCreceiver.class.getName());
	    }
	    
	    @Override
	    public void onResume() {
	        super.onResume();
	        Log.e("Resume", "Hell yeah");
	        mAudioManager.registerMediaButtonEventReceiver(
	                mRemoteControlResponder);
	        TextView t = new TextView(this);
	        t = (TextView)findViewById(R.id.Text1);
	        if(clickstate==1)
	        	t.setText(t.getText() + String.valueOf(clickstate));
	        if(clickstate==2){
	        	String str = (String) t.getText().subSequence(0, t.getText().length()-1);
	        	t.setText(str + String.valueOf(clickstate));
	        }
	        	
	    }
	    
	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	        mAudioManager.unregisterMediaButtonEventReceiver(
	                mRemoteControlResponder);
	    }

	    
	@Override
	public void onUserInteraction(){
		long currentClick = SystemClock.uptimeMillis();
		if(currentClick - lastClick > 1500){
			Log.e("Next!", "Next word");
			TextView t1 = (TextView)findViewById(R.id.Text1);
			TextView t2 = (TextView)findViewById(R.id.Text2);
			String str = (String)t1.getText();
			Log.e("Str", str);
			char a = evaluateCode(str);
			Log.e("a", Character.toString(a));
			t1.setText("");
			t2.setText((String)(t2.getText()) + a);	
		}
		lastClick = currentClick;
	}
	@Override
	protected void onNewIntent(Intent intent){
		Log.e("New", "Intent");
		clickstate = intent.getIntExtra("Click", 0);
		super.onNewIntent(intent);
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
