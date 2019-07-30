package com.example.thudemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

public class TimKiem_activite extends Activity {
	private RadioGroup group;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timkiem_activite);
		
		 group= (RadioGroup) findViewById(R.id.radiohgrouptk);
		group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch(checkedId)
				{
				case R.id.radiobttt:
					startActivity(new Intent(TimKiem_activite.this, TimKiem_khoanthu.class));
		            break;
				case R.id.radiobttc:
					startActivity(new Intent(TimKiem_activite.this, TimKiem_khoanchi.class));
		            break;
				case R.id.radiobkv:
					startActivity(new Intent(TimKiem_activite.this, Timkiem_KhoanVay.class));
		            break;
				case R.id.radiobkn:
					startActivity(new Intent(TimKiem_activite.this, TimKiem_Khoanno.class));
		            break;
		            
		   
				
				}
				
			}

		});
		

		group.clearCheck();	
		
	}

}
