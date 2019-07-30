package com.example.thudemo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HuongDan_khoanthu extends Activity {
	
	TextView txtdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.huongdan_acitive);
		txtdata=(TextView) findViewById(R.id.txtrawkt);
		readdingfile();
		}
	public void readdingfile()
	{
		String data;
		InputStream in=getResources().openRawResource(R.drawable.khoanthu1);
		InputStreamReader inread= new InputStreamReader(in);
		BufferedReader bufread=new BufferedReader(inread);
		StringBuilder buder=new StringBuilder();
		if(in!=null)
		{
			try
			{
				while((data=bufread.readLine())!=null)
				{
					buder.append(data);
				}
				in.close();
				txtdata.setText(buder.toString());
			}
			catch(IOException ex)
			{
				Log.e("Error", ex.getMessage());
			}
		}
	}
	public void readdingfilekc()
	{
		String data;
		InputStream in=getResources().openRawResource(R.drawable.khoanchi);
		InputStreamReader inread= new InputStreamReader(in);
		BufferedReader bufread=new BufferedReader(inread);
		StringBuilder buder=new StringBuilder();
		if(in!=null)
		{
			try
			{
				while((data=bufread.readLine())!=null)
				{
					buder.append(data);
				}
				in.close();
				txtdata.setText(buder.toString());
			}
			catch(IOException ex)
			{
				Log.e("Error", ex.getMessage());
			}
		}
	}
}
