package edu.ucdavis.cs.mobile.cop.benchmark;


import android.app.Service;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;
import edu.ucdavis.cs.mobile.cop.benchmark.R;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import edu.ucdavis.cs.mobile.cop.benchmark.Judgement;
import edu.ucdavis.cs.mobile.cop.benchmark.SleepLayer;
import edu.ucdavis.cs.mobile.cop.benchmark.WorkLayer;
import edu.ucdavis.cs.mobile.cop.benchmark.BatteryLayer;
import edu.ucdavis.cs.mobile.cop.benchmark.OutLayer;

public class FirstService extends Service{
	
	private static String calendarEventURL = "content://com.android.calendar/events";
	
	private int battery_int = 25;
	private int battery_light_int = 120;
	private int battery_voice_int = 3;
	private int battery_ring_int = 0;
	private int battery_wifi_int = 0;
	private int battery_sync_int = 0;
	private int battery_bluetooth_int = 0;
	
	private int work_light_int = 120;
	private int work_voice_int = 3;
	private int work_ring_int = 0;
	private int work_wifi_int = 0;
	private int work_sync_int = 0;
	private int work_bluetooth_int = 0;
	
	private int sleep_light_int = 120;
	private int sleep_voice_int = 3;
	private int sleep_ring_int = 0;
	private int sleep_wifi_int = 0;
	private int sleep_sync_int = 0;
	private int sleep_bluetooth_int = 0;
	
	private int out_light_int = 120;
	private int out_voice_int = 3;
	private int out_ring_int = 0;
	private int out_wifi_int = 0;
	private int out_sync_int = 0;
	private int out_bluetooth_int = 0;
	
	private int battery_status = 0;
	private int work_status = 0;
	private int sleep_status = 0;
	private int out_status = 0;
	
	private AudioManager am = null;
	private WifiManager wifim = null;
	//private Cursor eventCursor = null;
	
	private SleepLayer sleeplayer = null;
	private WorkLayer worklayer = null;
	private BatteryLayer batterylayer = null;
	private OutLayer outlayer = null;
	
	private BatteryReceiver br = null;
	/*
	
	*/
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);//null;
		wifim = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		//eventCursor = getContentResolver().query(Uri.parse(calendarEventURL), null, null, null, null);
		
		br = new BatteryReceiver();
		
		worklayer = new WorkLayer();
		outlayer = new OutLayer();
		sleeplayer = new SleepLayer();
		batterylayer = new BatteryLayer();
		
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(br, filter);
		
		handler.post(ad);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		battery_int = intent.getIntExtra("battery_int", 25); 
	    battery_light_int = intent.getIntExtra("battery_light_int", 120);
	    battery_voice_int = intent.getIntExtra("battery_voice_int", 3);
	    battery_ring_int = intent.getIntExtra("battery_ring_int", 0);
	    battery_wifi_int = intent.getIntExtra("battery_wifi_int", 0);
	    battery_sync_int = intent.getIntExtra("battery_sync_int", 0);
	    battery_bluetooth_int = intent.getIntExtra("battery_bluetooth_int", 0);
	
	    work_light_int = intent.getIntExtra("work_light_int", 120);
	    work_voice_int = intent.getIntExtra("work_voice_int", 3);
	    work_ring_int = intent.getIntExtra("work_ring_int", 0);
	    work_wifi_int = intent.getIntExtra("work_wifi_int", 0);
	    work_sync_int = intent.getIntExtra("work_sync_int", 0);
	    work_bluetooth_int = intent.getIntExtra("work_bluetooth_int", 0);
	
	    sleep_light_int = intent.getIntExtra("sleep_light_int", 120);
	    sleep_voice_int = intent.getIntExtra("sleep_voice_int", 3);
	    sleep_ring_int = intent.getIntExtra("sleep_ring_int", 0);
	    sleep_wifi_int = intent.getIntExtra("sleep_wifi_int", 0);
	    sleep_sync_int = intent.getIntExtra("sleep_sync_int", 0);
	    sleep_bluetooth_int = intent.getIntExtra("sleep_bluetooth_int", 0);
	
	    out_light_int = intent.getIntExtra("out_light_int", 120);
	    out_voice_int = intent.getIntExtra("out_voice_int", 3);
	    out_ring_int = intent.getIntExtra("out_ring_int", 0);
	    out_wifi_int = intent.getIntExtra("out_wifi_int", 0);
	    out_sync_int = intent.getIntExtra("out_sync_int", 0);
	    out_bluetooth_int = intent.getIntExtra("out_bluetooth_int", 0);
	
	    battery_status = intent.getIntExtra("battery_status", 0);
	    work_status = intent.getIntExtra("work_status", 0);
	    sleep_status = intent.getIntExtra("sleep_status", 0);
	    out_status = intent.getIntExtra("out_status", 0);

		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		handler.removeCallbacks(ad);
		super.onDestroy();
		
	}
	
	Handler handler = new Handler();
	
	Runnable ad = new Runnable(){

		public void run() {
			// TODO Auto-generated method stub
			Cursor eventCursor = getContentResolver().query(Uri.parse(calendarEventURL), null, null, null, null);
			if(eventCursor.getCount() > 0){  
				
				
				eventCursor.moveToFirst();  
						
				long starttime = Calendar.getInstance().getTime().getTime();
				long eventstarttime, eventendtime;
				int in = 0;
				for(int i=0; i<eventCursor.getCount(); i++){
					eventstarttime = eventCursor.getLong(eventCursor.getColumnIndex("dtstart"));
					eventendtime = eventCursor.getLong(eventCursor.getColumnIndex("dtend"));
					if (eventstarttime <= starttime && eventendtime >= starttime){
						in = 1;
						break;
					}
					eventCursor.move(1);
				}
				if (in == 1){
					String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title")); 
					switch(Judgement.check(eventTitle)){
						case 0:
							if (sleep_status == 0){
								with(sleeplayer)
									setall();
							}
							break;
						case 1:
							if (out_status == 0){
								with(outlayer)
									setall();
							}
							break;
						case 2:
							if(work_status == 0){
								with(worklayer)
									setall();
							}
							break;
						default:
							setall();
							break;
					}
				}
				else{
					setall();
				}
				
			}
			else{
				setall();
			}
			handler.postDelayed(ad, 60000);
		}
		
		
	};
	
	layer SleepLayer{
		public void setvoice() {
			am.setStreamVolume(AudioManager.STREAM_SYSTEM, sleep_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_RING, sleep_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_MUSIC, sleep_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_ALARM, sleep_voice_int, 0);
		}
	
		public void setlight() {
			setManualScreenModel();
			Settings.System.putInt(getContentResolver(),  Settings.System.SCREEN_BRIGHTNESS, sleep_light_int);		
		}
		
		public void setring() {
			if(sleep_ring_int == 0){
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}else if(sleep_ring_int == 1){
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}else{
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
		}
		
		public void setwifi(){
			if (sleep_wifi_int == 0){
				wifim.setWifiEnabled(true);
			}else{
				wifim.setWifiEnabled(false);
			}
		}
		
		public void setsync(){
			if(sleep_sync_int == 0){
				ContentResolver.setMasterSyncAutomatically(true);
			}else{
				ContentResolver.setMasterSyncAutomatically(false);
			}
		}
		
		public void setbluetooth(){
		}
	}
	
	layer WorkLayer{
		public void setvoice() {
			am.setStreamVolume(AudioManager.STREAM_SYSTEM, work_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_RING, work_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_MUSIC, work_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_ALARM, work_voice_int, 0);
		}
	
		public void setlight() {
			setManualScreenModel();
			Settings.System.putInt(getContentResolver(),  Settings.System.SCREEN_BRIGHTNESS, work_light_int);		
		}
		
		public void setring() {
			if(work_ring_int == 0){
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}else if(work_ring_int == 1){
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}else{
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
		}
		
		public void setwifi(){
			if (work_wifi_int == 0){
				wifim.setWifiEnabled(true);
			}else{
				wifim.setWifiEnabled(false);
			}
		}
		
		public void setsync(){
			if(work_sync_int == 0){
				ContentResolver.setMasterSyncAutomatically(true);
			}else{
				ContentResolver.setMasterSyncAutomatically(false);
			}
		}
		
		public void setbluetooth(){
		}
	}
	
	layer OutLayer{
		public void setvoice() {
			am.setStreamVolume(AudioManager.STREAM_SYSTEM, out_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_RING, out_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_MUSIC, out_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_ALARM, out_voice_int, 0);
		}
	
		public void setlight() {
			setManualScreenModel();
			Settings.System.putInt(getContentResolver(),  Settings.System.SCREEN_BRIGHTNESS, out_light_int);		
		}
		
		public void setring() {
			if(out_ring_int == 0){
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}else if(out_ring_int == 1){
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}else{
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
		}
		
		public void setwifi(){
			if (out_wifi_int == 0){
				wifim.setWifiEnabled(true);
			}else{
				wifim.setWifiEnabled(false);
			}
		}
		
		public void setsync(){
			if(out_sync_int == 0){
				ContentResolver.setMasterSyncAutomatically(true);
			}else{
				ContentResolver.setMasterSyncAutomatically(false);
			}
		}
		
		public void setbluetooth(){
		}
	}
	
	layer BatteryLayer{
		public void setvoice() {
			am.setStreamVolume(AudioManager.STREAM_SYSTEM, battery_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_RING, battery_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_MUSIC, battery_voice_int, 0);
			am.setStreamVolume(AudioManager.STREAM_ALARM, battery_voice_int, 0);
		}
	
		public void setlight() {
			setManualScreenModel();
			Settings.System.putInt(getContentResolver(),  Settings.System.SCREEN_BRIGHTNESS, battery_light_int);		
		}
		
		public void setring() {
			if(battery_ring_int == 0){
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}else if(battery_ring_int == 1){
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			}else{
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			}
		}
		
		public void setwifi(){
			if (battery_wifi_int == 0){
				wifim.setWifiEnabled(true);
			}else{
				wifim.setWifiEnabled(false);
			}
		}
		
		public void setsync(){
			if(battery_sync_int == 0){
				ContentResolver.setMasterSyncAutomatically(true);
			}else{
				ContentResolver.setMasterSyncAutomatically(false);
			}
		}
		
		public void setbluetooth(){
		}
	}
	
	//set the voice
	public void setvoice(){
	}
	
	//set the light
	public void setlight(){
	}
	
	//set wifi
	public void setwifi(){
	}
	
	//set gps
	public void setbluetooth(){
	}
	
	//set sync
	public void setsync(){
	}
	
	//set model
	public void setring(){
	}
	
	//set all
	public void setall(){
		setvoice();
		setlight();
		setwifi();
		setsync();
		setbluetooth();
		setring();
	}
	
	public void setManualScreenModel(){
		try {
			if (getScreenModel() == 1)
				Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
		} 
		catch (Exception localException) {
			localException.printStackTrace();
		}
	}
	
	public int getScreenModel(){
		int screenMode = 0;
		try {
			screenMode = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
		} 
		catch (Exception localException) {
			
		}
		return screenMode;
	}
	
	public int getScreenLight(){
		int screenBrightness = 255;
		try {
			screenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
		} 
		catch (Exception localException) {
	
		}
		return screenBrightness;
	}	
	
	
	private class BatteryReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			int current = arg1.getExtras().getInt("level");
			int total = arg1.getExtras().getInt("scale");
			int percent = current*100/total;
			if (percent <= battery_int && battery_status == 0){
				with(batterylayer)
					setall();
			}
		}
		
	}
	
}
