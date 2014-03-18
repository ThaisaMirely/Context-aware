package edu.ucdavis.cs.mobile.cop.benchmark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class AnotherActivity extends Activity {

	private int battery_int = 25;
	private int light_int = 120;
	private int voice_int = 3;
	private int ring_int = 0;
	private int wifi_int = 0;
	private int sync_int = 0;
	private int bluetooth_int = 0;
	
	private SeekBar batterybar = null;
	private SeekBar lightbar = null;
	private SeekBar voicebar = null;
	private Spinner ringsp = null;
	private CheckBox wificb = null;
	private CheckBox synccb = null;
	private Button backbutton = null;
	private TextView tv = null;
	
	private ArrayAdapter<String> adapter;  
	private static final String[] m={"ÁåÉù","Õð¶¯","ÎÞÉù"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);

		batterybar = (SeekBar)this.findViewById(R.id.another_battery_sb);
		lightbar = (SeekBar)this.findViewById(R.id.another_light_sb);
		voicebar = (SeekBar)this.findViewById(R.id.another_voice_sb);
		ringsp = (Spinner)this.findViewById(R.id.another_ring_sp);
		wificb = (CheckBox)this.findViewById(R.id.another_wifi_cb);
		synccb = (CheckBox)this.findViewById(R.id.another_sync_cb);
		backbutton = (Button)this.findViewById(R.id.another_jump);
		tv = (TextView)this.findViewById(R.id.another_tv);
	
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
        ringsp.setAdapter(adapter);  
           
        ringsp.setOnItemSelectedListener(new OnItemSelectedListener(){

			//@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				ring_int = arg2;
				tv.setText("" + m[arg2]);
				
			}

			//@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });   
        ringsp.setVisibility(View.VISIBLE);
	
		wificb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        	
        	//@Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
					wifi_int = 0;
                    tv.setText("on");
                }else{
					wifi_int = 1;
                	tv.setText("off");
                }
        	}
        });
		
		synccb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        	
        	//@Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
					sync_int = 0;
                    tv.setText("on");
                }else{
					sync_int = 1;
                	tv.setText("off");
                }
        	}
        });
	
		batterybar.setMax(100);
		batterybar.setProgress(25);
		batterybar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			//@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}

			//@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			//@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				 tv.setText(String.valueOf(batterybar.getProgress()));
	             battery_int = batterybar.getProgress();
			}
			
		});
		
		lightbar.setMax(255);
		lightbar.setProgress(120);
        lightbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            //@Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            //@Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //@Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(String.valueOf(lightbar.getProgress()));
                light_int = lightbar.getProgress();
            }
        });
        
        voicebar.setMax(7);
		voicebar.setProgress(3);
        voicebar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            //@Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            //@Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //@Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(String.valueOf(voicebar.getProgress()));
                voice_int = voicebar.getProgress();
            }
        });
    
		backbutton.setOnClickListener(new OnClickListener(){

			//@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("battery_int", battery_int);
				intent.putExtra("light_int", light_int);
				intent.putExtra("voice_int", voice_int);
				intent.putExtra("ring_int", ring_int);
				intent.putExtra("wifi_int", wifi_int);
				intent.putExtra("sync_int", sync_int);
				intent.putExtra("bluetooth_int", bluetooth_int);
				AnotherActivity.this.setResult(RESULT_OK, intent);

				AnotherActivity.this.finish();// ¹Ø±Õactivity
			}
			
		});
	}

}
