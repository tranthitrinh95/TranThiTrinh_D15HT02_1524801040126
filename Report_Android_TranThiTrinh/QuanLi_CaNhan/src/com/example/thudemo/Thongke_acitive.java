package com.example.thudemo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;


public class Thongke_acitive extends Activity {
	private RadioGroup group;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thongke_acitive);
		 group= (RadioGroup) findViewById(R.id.radiohgrouptk);
			group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					switch(checkedId)
					{
					case R.id.radiobttt:
						Barthu barthu=new Barthu();
						Intent litethu=barthu.getInter(getApplicationContext());
						startActivity(litethu);
			            break;
					case R.id.radiobttc:
						Barchi barchi =new Barchi();
						Intent litechi = barchi.getInter(getApplicationContext());
						startActivity(litechi);
			            break;
					case R.id.radiobkv:
						BarVay barvay =new BarVay();
						Intent litevay = barvay.getInter(getApplicationContext());
						startActivity(litevay);
			            break;
					case R.id.radiobkn:
						Barmuon barmuon =new Barmuon();
						Intent litemuon = barmuon.getInter(getApplicationContext());
						startActivity(litemuon);
			            break;
			            
			   
					
					}
					
				}

			});
			group.clearCheck();	
	}

}
