package com.example.thudemo;

import Database.DataAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class TimKiem_Khoanno extends Activity {
    private ListView lv;
    ArrayAdapter<String> adapter;
    private SimpleCursorAdapter cursoradata;
    private DataAdapter db;
    EditText edino;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timkiem_khoanno);
	       db=new DataAdapter(this);
	       db.open();
	       edino = (EditText) findViewById(R.id.etSearchno);	
    	   
	       edino.addTextChangedListener(new TextWatcher()
	       {
	        public void afterTextChanged(Editable s) {
	        }
	      
	        public void beforeTextChanged(CharSequence s, int start, 
	          int count, int after) {
	        }
	      
	        public void onTextChanged(CharSequence s, int start, 
	          int before, int count) {
	         cursoradata.getFilter().filter(s.toString());
	        }
	       });
	       //Generate ListView from SQLite Database
	       displayListView();
	}
	@SuppressLint("NewApi")
	private void displayListView() {
		db.open();	
	       Cursor cursor = db.getAllkn();
	       
	       // The desired columns to be bound
	       String[] from = new String[] {DataAdapter.tenkhoanno,DataAdapter.sotienn,DataAdapter.laisuatn,DataAdapter.ngayno,DataAdapter.ngaytrano};
   		     int[] to = new int[] { R.id.txtten,R.id.txtsotienkn,R.id.txtlaisuatkn,R.id.txtngayvaykn,R.id.txtngaytrakn };
	      
	       // create the adapter using the cursor pointing to the desired data 
	       //as well as the layout information
	       cursoradata = new SimpleCursorAdapter(this, R.layout.row_khoanno,  cursor, from, to,0);
	      
	      lv = (ListView) findViewById(R.id.listno);
	       // Assign adapter to ListView
	       lv.setAdapter(cursoradata);

	       cursoradata.setFilterQueryProvider(new FilterQueryProvider() {
	              public Cursor runQuery(CharSequence constraint) {
	                  return db.seachkhoanno(constraint.toString());
	              }
	          });
	       lv.setOnItemClickListener(new OnItemClickListener() {

				@SuppressLint("NewApi")
				@Override
				public void onItemClick(AdapterView<?> parent, View arg1,int position, long arg3) {
					// TODO Auto-generated method stub
					Cursor cursor = (Cursor) parent.getItemAtPosition(position);

				final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.colknID));

				String item_tkv = cursor.getString(cursor.getColumnIndex(DataAdapter.tenkhoanno));
				String item_st= cursor.getString(cursor.getColumnIndex(DataAdapter.sotienn));
				String item_ls = cursor.getString(cursor.getColumnIndex(DataAdapter.laisuatn));
				String item_nv = cursor.getString(cursor.getColumnIndex(DataAdapter.ngayno));
				String item_nt = cursor.getString(cursor.getColumnIndex(DataAdapter.ngaytrano));

				AlertDialog.Builder myDialog = new AlertDialog.Builder(TimKiem_Khoanno.this);

				myDialog.setTitle("Delete/Edit?");
				TextView dialogTxt_id = new TextView(TimKiem_Khoanno.this);

				LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

				dialogTxt_id.setText("->" + String.valueOf(item_id));

				final EditText tenkn = new EditText(TimKiem_Khoanno.this);
				final EditText st = new EditText(TimKiem_Khoanno.this);
				final EditText ls= new EditText(TimKiem_Khoanno.this);
				final EditText nn = new EditText(TimKiem_Khoanno.this);
				final EditText nt = new EditText(TimKiem_Khoanno.this);

				LayoutParams tenkv_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams st_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams ls_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams nv_layopa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams nt_layopa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

				tenkn.setLayoutParams(tenkv_layoupa);
				st.setLayoutParams(st_layoupa);
				ls.setLayoutParams(ls_layoupa);
				nn.setLayoutParams(nv_layopa);
				nt.setLayoutParams(nt_layopa);

				tenkn.setText(item_tkv);
				st.setText(item_st);
				ls.setText(item_ls);
				nn.setText(item_nv);
				nt.setText(item_nt);

				 LinearLayout layout = new LinearLayout(TimKiem_Khoanno.this);

				layout.setOrientation(LinearLayout.VERTICAL);

				layout.addView(dialogTxt_id);

				layout.addView(tenkn);
				layout.addView(st);
				layout.addView(ls);
				layout.addView(nn);
				layout.addView(nt);

				myDialog.setView(layout);
				 myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

		                // do something when the button is clicked

		                public void onClick(DialogInterface arg0, int arg1) {
		                	
		                	LinearLayout layout = new LinearLayout(TimKiem_Khoanno.this);
		                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
		                	builder.setTitle("Hỏi Xóa");
		                	builder.setMessage("Bạn Có Muốn Xóa Khoản Nợ Này Không?");
		                	builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									db.open();
									 db.deletetkn(item_id);
					                 
					                 Cursor c = db.getAllkn();
										startManagingCursor(c);
										String[] from = new String[] {DataAdapter.tenkhoanno,DataAdapter.sotienn,DataAdapter.laisuatn,DataAdapter.ngayno,DataAdapter.ngaytrano};
							    		int[] to = new int[] { R.id.txtten,R.id.txtsotienkn,R.id.txtlaisuatkn,R.id.txtngayvaykn,R.id.txtngaytrakn };


							    		cursoradata = new SimpleCursorAdapter(TimKiem_Khoanno.this,R.layout.row_khoanno, c, from, to,0);
										lv.setAdapter(cursoradata);
										
										cursoradata.notifyDataSetChanged();
									db.close();
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

							String valt = tenkn.getText().toString();
							int valst = Integer.parseInt(st.getText().toString());
							int valls = Integer.parseInt(ls.getText().toString());
							String valnn = nn.getText().toString();
							String valnt = nt.getText().toString();
							db.open();
							db.updatekhoanno(item_id, valt, valst, valls, valnn, valnt);
								    									
							Cursor c = db.getAllkn();
							startManagingCursor(c);

							String[] from = new String[] {DataAdapter.tenkhoanno,DataAdapter.sotienn,DataAdapter.laisuatn,DataAdapter.ngayno,DataAdapter.ngaytrano};
				    		int[] to = new int[] { R.id.txtten,R.id.txtsotienkn,R.id.txtlaisuatkn,R.id.txtngayvaykn,R.id.txtngaytrakn };

				    		cursoradata = new SimpleCursorAdapter(TimKiem_Khoanno.this,R.layout.row_khoanno, c, from, to,0);
							lv.setAdapter(cursoradata);

							cursoradata.notifyDataSetChanged();

							LinearLayout layout = new LinearLayout(TimKiem_Khoanno.this);
		                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
							builder.setMessage("Sửa Thành Công" + valt);
						       db.close();
						}

					});

			myDialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						// do something when the button is clicked

						public void onClick(DialogInterface arg0, int arg1) {
						}

					});
			myDialog.show();
		
			db.close();
		}
				
	});
  	   
	       edino.addTextChangedListener(new TextWatcher()
	       {
	      
	        public void afterTextChanged(Editable s) {
	        }
	      
	        public void beforeTextChanged(CharSequence s, int start, 
	          int count, int after) {
	        }
	      
	        public void onTextChanged(CharSequence s, int start, 
	          int before, int count) {
	         cursoradata.getFilter().filter(s.toString());
	        }
	       });
	}

}
