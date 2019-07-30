package com.example.thudemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import Database.DataAdapter;
import android.animation.AnimatorSet.Builder;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TheLoaiChi extends Activity {

	EditText inputContent1;
	Button buttonAdd, buttonDeleteAll;
	private DataAdapter db;
	ListView listContent;
	SimpleCursorAdapter cursorAdapter;

	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theloaichi);

		inputContent1 = (EditText) findViewById(R.id.content1);
		buttonAdd = (Button) findViewById(R.id.add);
		listContent = (ListView) findViewById(R.id.contentlist);
		db = new DataAdapter(this);
		db.open();
		Cursor c = db.getAlltlc();
		startManagingCursor(c);

		String[] from = new String[] { DataAdapter.coltentheloaic };
		int[] to = new int[] { R.id.text1 };

		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.row, c, from, to);
		listContent.setAdapter(notes);
		notes.notifyDataSetChanged();
		fillData();
		try {
			String destPath = "/data/data/" + getPackageName()
					+ "/databases/QuanLi_TaiChinh";
			File f = new File(destPath);
			if (!f.exists()) {
				CopyDB(getBaseContext().getAssets().open("QuanLi_TaiChinh"),
						new FileOutputStream(destPath));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ---add a contact---
		buttonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String tb = inputContent1.getText().toString();
				db.inserttlc(tb);
				Toast.makeText(getApplicationContext(), "Thêm thành công thể loại chi : " + tb,Toast.LENGTH_LONG).show();
				fillData();
				inputContent1.setText(null);
			}
		});
	}
	public void CopyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}
	@SuppressLint("NewApi")
	private void fillData() {
		Cursor c = db.getAlltlc();
		startManagingCursor(c);

		String[] from = new String[] { DataAdapter.coltentheloaic };
		int[] to = new int[] { R.id.text1 };

		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.row, c, from, to);
		listContent.setAdapter(notes);
		notes.notifyDataSetChanged();
        c.requery();
		

		listContent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Cursor cursor = (Cursor) parent.getItemAtPosition(position);

				final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.coltlcID));

				String item_content1 = cursor.getString(cursor.getColumnIndex(DataAdapter.coltentheloaic));

				AlertDialog.Builder myDialog = new AlertDialog.Builder(TheLoaiChi.this);

				myDialog.setTitle("Delete/Edit?");
				TextView dialogTxt_id = new TextView(TheLoaiChi.this);

				LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

				dialogTxt_id.setText("->" + String.valueOf(item_id));

				final EditText dialogC1_id = new EditText(TheLoaiChi.this);

				LayoutParams dialogC1_idLayoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

				dialogC1_id.setLayoutParams(dialogC1_idLayoutParams);

				dialogC1_id.setText(item_content1);

				 LinearLayout layout = new LinearLayout(TheLoaiChi.this);

				layout.setOrientation(LinearLayout.VERTICAL);

				layout.addView(dialogTxt_id);

				layout.addView(dialogC1_id);

				myDialog.setView(layout);
				
				 myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

		                // do something when the button is clicked

		                public void onClick(DialogInterface arg0, int arg1) {
		                	
		                	LinearLayout layout = new LinearLayout(TheLoaiChi.this);
		                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
		                	builder.setTitle("Hỏi Xóa");
		                	builder.setMessage("Bạn Có Muốn Xóa Thể Loại Chi Này Không?");
		                	builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									 db.deletetlc(item_id);
					                 
					                 Cursor c = db.getAlltlc();
										startManagingCursor(c);

										String[] from = new String[] { DataAdapter.coltentheloaic };
										int[] to = new int[] { R.id.text1 };

										SimpleCursorAdapter notes = new SimpleCursorAdapter(TheLoaiChi.this, R.layout.row, c, from, to);
										listContent.setAdapter(notes);
										
										notes.notifyDataSetChanged();
									
								}
							});
		                	builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.dismiss();
								}
							});
		                	builder.create().show();

		                 }

		                });
				
				myDialog.setNeutralButton("Update",new DialogInterface.OnClickListener() {

							// do something when the button is clicked

							public void onClick(DialogInterface arg0, int arg1) {

								String value1 = dialogC1_id.getText().toString();

								db.updatetlc(item_id, value1);

//								updateList();
								
								
								Cursor c = db.getAlltlc();
								startManagingCursor(c);

								String[] from = new String[] { DataAdapter.coltentheloaic };
								int[] to = new int[] { R.id.text1 };

								SimpleCursorAdapter notes = new SimpleCursorAdapter(TheLoaiChi.this, R.layout.row, c, from, to);
								listContent.setAdapter(notes);
								Toast.makeText(getApplicationContext(), "Sửa thành công thể loại chi : " + value1,Toast.LENGTH_LONG).show();
								notes.notifyDataSetChanged();

							}

						});

				myDialog.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {

							// do something when the button is clicked

							public void onClick(DialogInterface arg0, int arg1) {
							}

						});

				myDialog.show();

			}
		});

	}

	@Override
	protected void onDestroy() {

		// TODO Auto-generated method stub

		super.onDestroy();

		db.close();

	}

	private void updateList() {

		cursor.requery();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
