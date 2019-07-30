package com.example.thudemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Database.DataAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TheLoaiT_acitivity extends Activity {
	
	EditText edtlt;
	Button buttonAdd, buttonDeleteAll;
	private DataAdapter db;
	ListView listContent;
	SimpleCursorAdapter cursorAdapter;

	Cursor cursor;

	@Override
    protected void onCreate(Bundle savedInstanceState)
			{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloaithu_acitivite);
        
        edtlt=(EditText) findViewById(R.id.editlt);
		buttonAdd = (Button) findViewById(R.id.addtlt);
		listContent = (ListView) findViewById(R.id.contetlt);
		db = new DataAdapter(this);
		db.open();
		Cursor c = db.getAlltlt();
		startManagingCursor(c);

		String[] from = new String[] { DataAdapter.tentlt };
		int[] to = new int[] { R.id.text1 };

		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.row, c, from, to);
		listContent.setAdapter(notes);
		notes.notifyDataSetChanged();
		
		fillData();
		// ---add a contact---
		buttonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String tlt= edtlt.getText().toString();
			db.inserttlt(tlt);
		Toast.makeText(getApplicationContext(), "Thêm thành công thể loại thu : " + tlt,Toast.LENGTH_LONG).show();
			fillData();
			edtlt.setText(null);
			
			}
		});
	}

	/*private void dataid() {
		
		final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.id));
		Cursor c = db.gettlt(item_id);
		startManagingCursor(c);

		String[] from = new String[] { DataAdapter.tentlt };
		int[] to = new int[] { R.id.text1 };

		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.row, c, from, to);
		listContent.setAdapter(notes);
	}*/
	@SuppressLint("NewApi")
	private void fillData() {
		Cursor c = db.getAlltlt();
		startManagingCursor(c);

		String[] from = new String[] { DataAdapter.tentlt };
		int[] to = new int[] { R.id.text1 };

		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, R.layout.row, c, from, to);
		listContent.setAdapter(notes);
		
		listContent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Cursor cursor = (Cursor) parent.getItemAtPosition(position);

				final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.id));

				String item_content1 = cursor.getString(cursor.getColumnIndex(DataAdapter.tentlt));

				AlertDialog.Builder myDialog = new AlertDialog.Builder(TheLoaiT_acitivity.this);

				myDialog.setTitle("Delete/Edit?");
				TextView dialogTxt_id = new TextView(TheLoaiT_acitivity.this);

				LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

				dialogTxt_id.setText("->" + String.valueOf(item_id));

				final EditText dialogC1_id = new EditText(TheLoaiT_acitivity.this);

				LayoutParams dialogC1_idLayoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

				dialogC1_id.setLayoutParams(dialogC1_idLayoutParams);

				dialogC1_id.setText(item_content1);

				 LinearLayout layout = new LinearLayout(TheLoaiT_acitivity.this);

				layout.setOrientation(LinearLayout.VERTICAL);

				layout.addView(dialogTxt_id);

				layout.addView(dialogC1_id);

				myDialog.setView(layout);
				
				 myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

		                // do something when the button is clicked

		                public void onClick(DialogInterface arg0, int arg1) {
		                	
		                	LinearLayout layout = new LinearLayout(TheLoaiT_acitivity.this);
		                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
		                	builder.setTitle("Hỏi Xóa");
		                	builder.setMessage("Bạn Có Muốn Xóa Thể Loại Thu Này Không?");
		                	builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									 db.deletetlt(item_id);
					                 
					                 Cursor c = db.getAlltlt();
										startManagingCursor(c);

										String[] from = new String[] {DataAdapter.tentlt};
										int[] to = new int[] { R.id.text1 };

										SimpleCursorAdapter notes = new SimpleCursorAdapter(TheLoaiT_acitivity.this, R.layout.row, c, from, to);
										listContent.setAdapter(notes);
										notes.notifyDataSetChanged();
										//updateList();
									
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

								db.updatetlt(item_id, value1);

//								updateList();
								
								
								Cursor c = db.getAlltlt();
								startManagingCursor(c);

								String[] from = new String[] { DataAdapter.tentlt};
								int[] to = new int[] { R.id.text1 };

								SimpleCursorAdapter notes = new SimpleCursorAdapter(TheLoaiT_acitivity.this, R.layout.row, c, from, to);
								listContent.setAdapter(notes);
								Toast.makeText(getApplicationContext(), "Sửa thành công thể loại thu : " + value1,Toast.LENGTH_LONG).show();
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
