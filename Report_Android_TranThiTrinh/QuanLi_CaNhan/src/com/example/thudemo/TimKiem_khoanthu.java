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

public class TimKiem_khoanthu extends Activity {
	 private ListView lv;
	    ArrayAdapter<String> adapter;
	    private SimpleCursorAdapter cursoradata;
	    private DataAdapter db;
	    EditText edino;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.timkiem_khoanthu);
		       db=new DataAdapter(this);
		       db.open();
		       edino = (EditText) findViewById(R.id.etSearchthu);	
	    	   
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
		       Cursor cursor = db.getAllkt();
		       
		       // The desired columns to be bound
		       String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
				int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };
		      
		       // create the adapter using the cursor pointing to the desired data 
		       //as well as the layout information
		       cursoradata = new SimpleCursorAdapter(this, R.layout.row_khoanthu,  cursor, from, to,0);
		      
		      lv = (ListView) findViewById(R.id.listthu);
		       // Assign adapter to ListView
		       lv.setAdapter(cursoradata);

		       cursoradata.setFilterQueryProvider(new FilterQueryProvider() {
		              public Cursor runQuery(CharSequence constraint) {
		                  return db.seachkhoanthu(constraint.toString());
		              }
		          });
		       lv.setOnItemClickListener(new OnItemClickListener() {

					@SuppressLint("NewApi")
					@Override
					public void onItemClick(AdapterView<?> parent, View arg1,int position, long arg3) {
						// TODO Auto-generated method stub
						Cursor cursor = (Cursor) parent.getItemAtPosition(position);

					final int item_id = cursor.getInt(cursor.getColumnIndex(DataAdapter.colktID));

					String item_tkv = cursor.getString(cursor.getColumnIndex(DataAdapter.mathloaithu));
					String item_st= cursor.getString(cursor.getColumnIndex(DataAdapter.colsotienkt));
					String item_nt = cursor.getString(cursor.getColumnIndex(DataAdapter.colngaythu));

					AlertDialog.Builder myDialog = new AlertDialog.Builder(TimKiem_khoanthu.this);

					myDialog.setTitle("Delete/Edit?");
					TextView dialogTxt_id = new TextView(TimKiem_khoanthu.this);

					LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

					dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

					dialogTxt_id.setText("->" + String.valueOf(item_id));

					final EditText tenkv = new EditText(TimKiem_khoanthu.this);
					final EditText st = new EditText(TimKiem_khoanthu.this);
					final EditText nt = new EditText(TimKiem_khoanthu.this);

					LayoutParams tenkv_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					LayoutParams st_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					LayoutParams ls_layoupa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					LayoutParams nv_layopa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
					LayoutParams nt_layopa = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

					tenkv.setLayoutParams(tenkv_layoupa);
					st.setLayoutParams(st_layoupa);
					nt.setLayoutParams(nt_layopa);

					tenkv.setText(item_tkv);
					st.setText(item_st);
					nt.setText(item_nt);

					 LinearLayout layout = new LinearLayout(TimKiem_khoanthu.this);

					layout.setOrientation(LinearLayout.VERTICAL);

					layout.addView(dialogTxt_id);

					layout.addView(tenkv);
					layout.addView(st);
					layout.addView(nt);

					myDialog.setView(layout);
					 myDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

			                // do something when the button is clicked

			                public void onClick(DialogInterface arg0, int arg1) {
			                	
			                	LinearLayout layout = new LinearLayout(TimKiem_khoanthu.this);
			                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
			                	builder.setTitle("Hỏi Xóa");
			                	builder.setMessage("Bạn Có Muốn Xóa Khoản Thu Này Không?");
			                	builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										db.open();
										 db.deletekt(item_id);
						                 
						                 Cursor c = db.getAllkt();
											startManagingCursor(c);
											String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
											int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };


								    		cursoradata = new SimpleCursorAdapter(TimKiem_khoanthu.this,R.layout.row_khoanthu, c, from, to,0);
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

								String valt = tenkv.getText().toString();
								int valst = Integer.parseInt(st.getText().toString());
								String valnt = nt.getText().toString();
								db.open();
								db.updatekhoanthu(item_id, valt, valst, valnt);
									    									
								Cursor c = db.getAllkt();
								startManagingCursor(c);

								String[] from = new String[] { DataAdapter.mathloaithu, DataAdapter.colsotienkt, DataAdapter.colngaythu };
								int[] to = new int[] { R.id.txttenkt, R.id.txtsotienkt, R.id.txtngaytrakt };
								
					    		cursoradata = new SimpleCursorAdapter(TimKiem_khoanthu.this,R.layout.row_khoanthu, c, from, to,0);
								lv.setAdapter(cursoradata);

								cursoradata.notifyDataSetChanged();

								LinearLayout layout = new LinearLayout(TimKiem_khoanthu.this);
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
