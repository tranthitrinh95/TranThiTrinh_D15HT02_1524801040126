package com.example.thudemo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import Database.DataAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class KhoanThu_acitive extends Activity {

	private java.util.Date dateFinish;
	Calendar cal;
	private DataAdapter db;
	Button bttdate,bttthemkt;
	Spinner spintenkt;
	ListView listviekt;
	EditText editdate1,editsotien;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.khoangthu_acitive);
		
		bttdate = (Button) findViewById(R.id.btnkt);
		editdate1 = (EditText) findViewById(R.id.editdatekt);
		editsotien = (EditText) findViewById(R.id.editsotienkt);
		bttthemkt = (Button) findViewById(R.id.btnkhoanthu);
		listviekt = (ListView) findViewById(R.id.listVkt);
		spintenkt = (Spinner) findViewById(R.id.spinnerkt);
		db = new DataAdapter(this);
		db.open();
		Cursor curn = db.getAllkt();
		startManagingCursor(curn);

		String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
		int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };

		SimpleCursorAdapter cursordata = new SimpleCursorAdapter(this, R.layout.row_khoanthu, curn, from, to);
		listviekt.setAdapter(cursordata);
		disAllKT();
		loadSpinnerData();
		loadTabs();
		bttdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDefaultInforkt();
				showDatePickerDialogkt();
			}
		});
		bttthemkt.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub\
			
				// String tkc= spinten.getSelectedItem().toString();
				String tkt = (String)spintenkt.getSelectedItem().toString();
				int st = Integer.parseInt(editsotien.getText().toString());
				String nc = editdate1.getText().toString();
				db.insertkhoanthu(tkt, st, nc);
				 Toast.makeText(getApplicationContext(), "Thêm Thành Công Tên: " + tkt,Toast.LENGTH_LONG).show();
				disAllKT();
				editsotien.setText(null);
				}
		});

	}

	private void disAllKT() {
		db.open();
		try {
			Cursor curn = db.getAllkt();
			startManagingCursor(curn);

			String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
			int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };

			SimpleCursorAdapter cursordata = new SimpleCursorAdapter(this, R.layout.row_khoanthu, curn, from, to);
			listviekt.setAdapter(cursordata);
			
			listviekt.setOnItemClickListener(new OnItemClickListener() {

	
				@Override
				public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
					// TODO Auto-generated method stub

					Cursor cursor = (Cursor) parent.getItemAtPosition(position);

					final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.colktID));
					String item_ten = cursor.getString(cursor.getColumnIndex(DataAdapter.mathloaithu));
					String item_st = cursor.getString(cursor.getColumnIndex(DataAdapter.colsotienkt));
					String item_nt = cursor.getString(cursor.getColumnIndex(DataAdapter.colngaythu));

					AlertDialog.Builder myDialog = new AlertDialog.Builder(KhoanThu_acitive.this);

					myDialog.setTitle("Delete/Edit?");
					TextView dialogTxt_id = new TextView(KhoanThu_acitive.this);

					LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

					dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

					dialogTxt_id.setText("->" + String.valueOf(item_id));

					
					final EditText stkt = new EditText(KhoanThu_acitive.this);
					final EditText nt = new EditText(KhoanThu_acitive.this);
					final EditText tenkt = new EditText(KhoanThu_acitive.this);
					
					LayoutParams tenkt_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					LayoutParams stkt_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					LayoutParams nt_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

					tenkt.setLayoutParams(tenkt_layoupa);
					stkt.setLayoutParams(stkt_layoupa);
					nt.setLayoutParams(nt_layoupa);

					tenkt.setText(item_ten);
					stkt.setText(item_st);
					nt.setText(item_nt);

					LinearLayout layout = new LinearLayout(KhoanThu_acitive.this);

					layout.setOrientation(LinearLayout.VERTICAL);

					layout.addView(dialogTxt_id);

					layout.addView(tenkt);
					layout.addView(stkt);
					layout.addView(nt);

					myDialog.setView(layout);

					myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

						// do something when the button is clicked

						public void onClick(DialogInterface arg0, int arg1) {

							LinearLayout layout = new LinearLayout(KhoanThu_acitive.this);
							AlertDialog.Builder builder = new AlertDialog.Builder(layout.getContext());
							builder.setTitle("Hỏi Xóa");
							builder.setMessage("Bạn Có Muốn Xóa Khoản Thu Này Không?");
							builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated
									// method stub
									db.open();
									db.deletekt(item_id);

									Cursor c = db.getAllkt();
									startManagingCursor(c);
									String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
									int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };

									SimpleCursorAdapter notes = new SimpleCursorAdapter(KhoanThu_acitive.this, R.layout.row_khoanthu, c, from, to);
									listviekt.setAdapter(notes);

									notes.notifyDataSetChanged();
									db.close();
								}
							});
							builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated
									// method stub
									dialog.dismiss();
								}
							});
							builder.create().show();

						}

					});

					myDialog.setNeutralButton("Update", new DialogInterface.OnClickListener() {

						// do something when the button is clicked

						public void onClick(DialogInterface arg0, int arg1) {

							String tkt = tenkt.getText().toString();
							int valstkc = Integer.parseInt(stkt.getText().toString());
							String valkc = nt.getText().toString();
							db.open();
							db.updatekhoanthu(item_id, tkt, valstkc, valkc);

							Cursor c = db.getAllkt();
							startManagingCursor(c);

							String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
							int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };

							SimpleCursorAdapter notes = new SimpleCursorAdapter(KhoanThu_acitive.this, R.layout.row_khoanthu, c, from, to);
							listviekt.setAdapter(notes);
							 Toast.makeText(getApplicationContext(), "Bạn Sửa Thành Công: " + tkt,Toast.LENGTH_LONG).show();
							notes.notifyDataSetChanged();
							db.close();
						}

					});

					myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

						// do something when the button is clicked

						public void onClick(DialogInterface arg0, int arg1) {
						}

					});

					myDialog.show();

				}
			});

		} catch (Exception e) {
			System.out.println(e);
		}
		db.close();
	}
	
	
	 private void loadSpinnerData() {
	        // database handler
	        db.open();
	 
	        // Spinner Drop down elements
	        List<String> lables = db.getAllLabelskt();
	 
	        // Creating adapter for spinner
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	        spintenkt.setAdapter(dataAdapter);
	    }
	@SuppressWarnings("deprecation")
	/*public void dissgetspinnerkt() {
		db.open();
		 
	   ArrayAdapter<CharSequence> spinAdaptor = ArrayAdapter.createFromResource(this, R.array.spinner_array_khoanthu, android.R.layout.simple_spinner_item);
	   spinAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    
       	spintenkt.setAdapter(spinAdaptor);

       	if(spintenkt.isSelected() != true) {
       		spintenkt.setSelection(0);
        }
		db.close();
	}*/

	public void loadTabs() {
		// L?y Tabhost id ra tru?c (cái này c?a built - in android
		final TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
		// g?i l?nh setup
		tab.setup();
		TabHost.TabSpec spec;
		// T?o tab1
		spec = tab.newTabSpec("t1");
		spec.setContent(R.id.tab1kt);
		spec.setIndicator("1-Thêm Khoản Thu");
		tab.addTab(spec);
		// T?o tab2
		spec = tab.newTabSpec("t2");
		spec.setContent(R.id.tab2kt);
		spec.setIndicator("2-Danh Sách Khoản Thu");
		tab.addTab(spec);
		// Thi?t l?p tab m?c d?nh du?c ch?n ban d?u là tab 0
		tab.setCurrentTab(0);
	}

	public void getDefaultInforkt() {
		// l?y ngày hi?n t?i c?a h? th?ng
		cal = Calendar.getInstance();
		SimpleDateFormat dft = null;
		// Ð?nh d?ng ngày / tháng /nam
		dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String strDate = dft.format(cal.getTime());
		// hi?n th? lên giao di?n
		editdate1.setText(strDate);
		// gán cal.getTime() cho ngày hoàn thành và gi? hoàn thành
		dateFinish = cal.getTime();
	}

	public void showDatePickerDialogkt() {
		OnDateSetListener callback = new OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				// M?i l?n thay d?i ngày tháng nam thì c?p nh?t l?i TextView
				// Date
				editdate1.setText((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year);
				// Luu v?t l?i bi?n ngày hoàn thành
				cal.set(year, monthOfYear, dayOfMonth);
				dateFinish = cal.getTime();
			}
		};
		// các l?nh du?i này x? lý ngày gi? trong DatePickerDialog
		// s? gi?ng v?i trên TextView khi m? nó lên
		String s1 = editdate1.getText() + "";
		String strArrtmp1[] = s1.split("/");
		int ngayv = Integer.parseInt(strArrtmp1[0]);
		int thangv = Integer.parseInt(strArrtmp1[1]) - 1;
		int namv = Integer.parseInt(strArrtmp1[2]);
		DatePickerDialog pic1 = new DatePickerDialog(KhoanThu_acitive.this, callback, namv, thangv, ngayv);
		pic1.setTitle("Chọn ngày hoàn thành");
		pic1.show();
	}
}
