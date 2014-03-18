package edu.ucdavis.cs.mobile.cop.benchmark;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Main extends Activity {

	private static final int OTHER_battery = 1;
	private static final int OTHER_work = 2;
	private static final int OTHER_sleep = 3;
	private static final int OTHER_out = 4;
	
	private Button batterybt = null;
	private Button workbt = null;
	private Button sleepbt = null;
	private Button outbt = null;
	private Button startbt = null;
	private Button endbt = null;
	
	private RadioGroup battery_rg = null;
	private RadioGroup work_rg = null;
	private RadioGroup sleep_rg = null;
	private RadioGroup out_rg = null;
	
	private TextView tv = null;
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		batterybt = (Button)this.findViewById(R.id.app_battery_bt);
		workbt = (Button)this.findViewById(R.id.app_work_bt);
		sleepbt = (Button)this.findViewById(R.id.app_sleep_bt);
		outbt = (Button)this.findViewById(R.id.app_out_bt);
		startbt = (Button)this.findViewById(R.id.start);
		endbt = (Button)this.findViewById(R.id.end);
		
		battery_rg = (RadioGroup)this.findViewById(R.id.app_battery_rg);
		work_rg = (RadioGroup)this.findViewById(R.id.app_work_rg);
		sleep_rg = (RadioGroup)this.findViewById(R.id.app_sleep_rg);
		out_rg = (RadioGroup)this.findViewById(R.id.app_out_rg);
		
		tv = (TextView)this.findViewById(R.id.tv);
		
		batterybt.setOnClickListener(new OnClickListener(){

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, AnotherActivity.class);
				startActivityForResult(intent, OTHER_battery);
			}
			
		});
		
		workbt.setOnClickListener(new OnClickListener(){

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, OtherActivity.class);
				startActivityForResult(intent, OTHER_work);
			}
			
		});
		
		sleepbt.setOnClickListener(new OnClickListener(){

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, OtherActivity.class);
				startActivityForResult(intent, OTHER_sleep);
			}
			
		});
		
		outbt.setOnClickListener(new OnClickListener(){

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this, OtherActivity.class);
				startActivityForResult(intent, OTHER_out);
			}
			
		});
		
		battery_rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			//@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == R.id.app_battery_on){
					battery_status = 0;
				}else{
					battery_status = 1;
				}
			}
			
		});
		
		work_rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			//@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == R.id.app_work_on){
					work_status = 0;
				}else{
					work_status = 1;
				}
			}
			
		});
		
		sleep_rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			//@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == R.id.app_sleep_on){
					sleep_status = 0;
				}else{
					sleep_status = 1;
				}
			}
			
		});
		
		out_rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			//@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg1 == R.id.app_out_on){
					out_status = 0;
				}else{
					out_status = 1;
				}
			}
			
		});
		
		startbt.setOnClickListener(new OnClickListener(){

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				
				intent.putExtra("battery_int", battery_int);
				intent.putExtra("battery_status", battery_status);
				intent.putExtra("battery_light_int", battery_light_int);
				intent.putExtra("battery_voice_int", battery_voice_int);
				intent.putExtra("battery_ring_int", battery_ring_int);
				intent.putExtra("battery_wifi_int", battery_wifi_int);
				intent.putExtra("battery_sync_int", battery_sync_int);
				intent.putExtra("battery_bluetooth_int", battery_bluetooth_int);
				
				intent.putExtra("work_status", work_status);
				intent.putExtra("work_light_int", work_light_int);
				intent.putExtra("work_voice_int", work_voice_int);
				intent.putExtra("work_ring_int", work_ring_int);
				intent.putExtra("work_wifi_int", work_wifi_int);
				intent.putExtra("work_sync_int", work_sync_int);
				intent.putExtra("work_bluetooth_int", work_bluetooth_int);
				
				intent.putExtra("sleep_status", sleep_status);
				intent.putExtra("sleep_light_int", sleep_light_int);
				intent.putExtra("sleep_voice_int", sleep_voice_int);
				intent.putExtra("sleep_ring_int", sleep_ring_int);
				intent.putExtra("sleep_wifi_int", sleep_wifi_int);
				intent.putExtra("sleep_sync_int", sleep_sync_int);
				intent.putExtra("sleep_bluetooth_int", sleep_bluetooth_int);
				
				intent.putExtra("out_status", out_status);
				intent.putExtra("out_light_int", out_light_int);
				intent.putExtra("out_voice_int", out_voice_int);
				intent.putExtra("out_ring_int", out_ring_int);
				intent.putExtra("out_wifi_int", out_wifi_int);
				intent.putExtra("out_sync_int", out_sync_int);
				intent.putExtra("out_bluetooth_int", out_bluetooth_int);
				
				intent.setClass(Main.this, FirstService.class);
				startService(intent);
				
				//传递数据
			}
			
		});
		
		endbt.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, FirstService.class);
				stopService(intent);
			}
		});
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case OTHER_battery:
				
				switch (resultCode) {
					case RESULT_OK:
						battery_int = data.getIntExtra("battery_int", 25);
						battery_light_int = data.getIntExtra("light_int", 120);
						battery_voice_int = data.getIntExtra("voice_int", 3);
						battery_ring_int = data.getIntExtra("ring_int", 0);
						battery_wifi_int = data.getIntExtra("wifi_int", 0);
						battery_sync_int = data.getIntExtra("sync_int", 0);
						battery_bluetooth_int = data.getIntExtra("bluetooth_int", 0);
						tv.setText("battery");
						break;

					default:
						break;
				}

				break;
				
			case OTHER_work:
				switch (resultCode) {
					case RESULT_OK:
						work_light_int = data.getIntExtra("light_int", 120);
						work_voice_int = data.getIntExtra("voice_int", 3);
						work_ring_int = data.getIntExtra("ring_int", 0);
						work_wifi_int = data.getIntExtra("wifi_int", 0);
						work_sync_int = data.getIntExtra("sync_int", 0);
						work_bluetooth_int = data.getIntExtra("bluetooth_int", 0);
						tv.setText("work");
						break;
					default:
						break;
					}
				break;
				
			case OTHER_sleep:
				switch (resultCode) {
					case RESULT_OK:
						sleep_light_int = data.getIntExtra("light_int", 120);
						sleep_voice_int = data.getIntExtra("voice_int", 3);
						sleep_ring_int = data.getIntExtra("ring_int", 0);
						sleep_wifi_int = data.getIntExtra("wifi_int", 0);
						sleep_sync_int = data.getIntExtra("sync_int", 0);
						sleep_bluetooth_int = data.getIntExtra("bluetooth_int", 0);
						tv.setText("sleep");					
						break;
					default:
						break;
					}
				break;
				
			case OTHER_out:
				switch (resultCode) {
					case RESULT_OK:
						out_light_int = data.getIntExtra("light_int", 120);
						out_voice_int = data.getIntExtra("voice_int", 3);
						out_ring_int = data.getIntExtra("ring_int", 0);
						out_wifi_int = data.getIntExtra("wifi_int", 0);
						out_sync_int = data.getIntExtra("sync_int", 0);
						out_bluetooth_int = data.getIntExtra("bluetooth_int", 0);
						tv.setText("out");	
						break;
					default:
						break;
					}
				break;
			default:
				break;
				
			
		}
	}

}