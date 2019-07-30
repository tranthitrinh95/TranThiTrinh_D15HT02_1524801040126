package com.example.thudemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import Database.DataAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.FilterQueryProvider;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Timkiem_KhoanVay extends Activity{
    private ListView lv;
    ArrayAdapter<String> adapter;
    private SimpleCursorAdapter cursoradata;
    private DataAdapter db;
    EditText myFilter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_itemkv);
	       db=new DataAdapter(this);
	       db.open();
	       myFilter = (EditText) findViewById(R.id.etSearch);	
    	   
	       myFilter.addTextChangedListener(new TextWatcher()
	       {
	        public void afterTextChanged(Editable s) {
	        	
	        	cursoradata.getFilter().filter(s.toString());
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
	       Cursor cursor = db.getAllkv();
	       
	       // The desired columns to be bound
	       String[] columns = new String[] {DataAdapter.tenkhoanvay,DataAdapter.sotienv,DataAdapter.laisuatv, DataAdapter.ngayvay,DataAdapter.ngaymuon,};
	      
	       // the XML defined views which the data will be bound to
	       int[] to = new int[] { 
	         R.id.txtten,
	         R.id.txtsotien,
	         R.id.txtlaisuat,
	         R.id.txtngayvay,
	         R.id.txttra,
	       };
	      
	       // create the adapter using the cursor pointing to the desired data 
	       //as well as the layout information
	       cursoradata = new SimpleCursorAdapter(this, R.layout.row_khoanvay,  cursor, columns, to,0);
	      
	      lv = (ListView) findViewById(R.id.listResults);
	      
	       // Assign adapter to ListView
	       lv.setAdapter(cursoradata);

	       cursoradata.setFilterQueryProvider(new FilterQueryProvider() {
	              public Cursor runQuery(CharSequence constraint) {
	                  return db.fetchCountriesByName(constraint.toString());
	              }
	          });
	       if(myFilter.getText().toString()!=null)
	       {
	    	   myFilter.addTextChangedListener(new TextWatcher()
		       {
		      
		        public void afterTextChanged(Editable s) {
		      
		        cursoradata.getFilter().filter(s.toString());
		        }
		      
		        public void beforeTextChanged(CharSequence s, int start, 
		          int count, int after) {
		        	cursoradata.getFilter().filter(s.toString());
		        }
		      
		        public void onTextChanged(CharSequence s, int start, 
		          int before, int count) {
		         cursoradata.getFilter().filter(s.toString());
		        }
		       });
	       }
	       else
	       {
	       lv.setOnItemClickListener(new OnItemClickListener() {

				@SuppressLint("NewApi")
				@Override
				public void onItemClick(AdapterView<?> parent, View arg1,int position, long arg3) {
					// TODO Auto-generated method stub
					Cursor cursor = (Cursor) parent.getItemAtPosition(position);

				final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.colkvID));

				String item_tkv = cursor.getString(cursor.getColumnIndex(DataAdapter.tenkhoanvay));
				String item_st= cursor.getString(cursor.getColumnIndex(DataAdapter.sotienv));
				String item_ls = cursor.getString(cursor.getColumnIndex(DataAdapter.laisuatv));
				String item_nv = cursor.getString(cursor.getColumnIndex(DataAdapter.ngayvay));
				String item_nt = cursor.getString(cursor.getColumnIndex(DataAdapter.ngaymuon));

				AlertDialog.Builder myDialog = new AlertDialog.Builder(Timkiem_KhoanVay.this);

				myDialog.setTitle("Delete/Edit?");
				TextView dialogTxt_id = new TextView(Timkiem_KhoanVay.this);

				LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

				dialogTxt_id.setText("->" + String.valueOf(item_id));

				final EditText tenkv = new EditText(Timkiem_KhoanVay.this);
				final EditText st = new EditText(Timkiem_KhoanVay.this);
				final EditText ls= new EditText(Timkiem_KhoanVay.this);
				final EditText nv = new EditText(Timkiem_KhoanVay.this);
				final EditText nt = new EditText(Timkiem_KhoanVay.this);

				LayoutParams tenkv_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams st_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams ls_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams nv_layopa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams nt_layopa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

				tenkv.setLayoutParams(tenkv_layoupa);
				st.setLayoutParams(st_layoupa);
				ls.setLayoutParams(ls_layoupa);
				nv.setLayoutParams(nv_layopa);
				nt.setLayoutParams(nt_layopa);

				tenkv.setText(item_tkv);
				st.setText(item_st);
				ls.setText(item_ls);
				nv.setText(item_nv);
				nt.setText(item_nt);

				 LinearLayout layout = new LinearLayout(Timkiem_KhoanVay.this);

				layout.setOrientation(LinearLayout.VERTICAL);

				layout.addView(dialogTxt_id);

				layout.addView(tenkv);
				layout.addView(st);
				layout.addView(ls);
				layout.addView(nv);
				layout.addView(nt);

				myDialog.setView(layout);
				 myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

		                // do something when the button is clicked

		                public void onClick(DialogInterface arg0, int arg1) {
		                	
		                	LinearLayout layout = new LinearLayout(Timkiem_KhoanVay.this);
		                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
		                	builder.setTitle("Hỏi Xóa");
		                	builder.setMessage("Bạn Có Muốn Xóa Khoản vay Này Không?");
		                	builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									db.open();
									 db.deletetkv(item_id);
									    Loadlist();
					           
										 myFilter.setText(null);
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

							String valt = tenkv.getText().toString();
							int valst = Integer.parseInt(st.getText().toString());
							int valls = Integer.parseInt(ls.getText().toString());
							String valnv = nv.getText().toString();
							String valnt = nt.getText().toString();
							db.open();
							db.updatekhoanvay(item_id, valt, valst, valls, valnv, valnt);
						     	Loadlist();
							 myFilter.setText(null);						
							
							 myFilter.addTextChangedListener(new TextWatcher()
						       {
						      
						        public void afterTextChanged(Editable s) {
						      
						        cursoradata.getFilter().filter(s.toString());
						        }
						      
						        public void beforeTextChanged(CharSequence s, int start, 
						          int count, int after) {
						        }
						      
						        public void onTextChanged(CharSequence s, int start, 
						          int before, int count) {
						         cursoradata.getFilter().filter(s.toString());
						        }
						       });
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
	       }  
	    
	}
	@SuppressLint("NewApi")
	public void Loadlist()
	{
		Cursor c = db.getAllkv();
		startManagingCursor(c);

		String[] from = new String[] {DataAdapter.tenkhoanvay,DataAdapter.sotienv,DataAdapter.laisuatv,DataAdapter.ngayvay,DataAdapter.ngaymuon};
		int[] to = new int[] { R.id.txtten,R.id.txtsotien,R.id.txtlaisuat,R.id.txtngayvay,R.id.txtngaytra };

		cursoradata = new SimpleCursorAdapter(Timkiem_KhoanVay.this,R.layout.row_khoanvay, c, from, to,0);
		lv.setAdapter(cursoradata);
	}
}
