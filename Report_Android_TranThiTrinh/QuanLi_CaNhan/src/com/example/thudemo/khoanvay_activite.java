package com.example.thudemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Database.DataAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ActionBar.LayoutParams;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class khoanvay_activite extends Activity {
	
	private Date dateFinish;
	private DataAdapter data;
	Calendar cal;
	Button Btdate1,Btdate2,Btthem;
	EditText edittenv,editsotienv,editlaisuatv,editdate1,editdate2;
	ListView listtab;
	SimpleCursorAdapter cursordata ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.khoangvay_activite);
		
		editdate1=(EditText) findViewById(R.id.txtdate);
		editdate2=(EditText) findViewById(R.id.txttra);
		Btdate1=(Button) findViewById(R.id.btnvay);
		Btdate2=(Button) findViewById(R.id.btntra);
		 Btthem=(Button) findViewById(R.id.btncongviec);
		 edittenv=(EditText) findViewById(R.id.editcongviec);
		 editsotienv=(EditText) findViewById(R.id.editsotien);
		 editlaisuatv=(EditText) findViewById(R.id.editlaisuat);
		 listtab=(ListView) findViewById(R.id.listkhoanvay);
			data = new DataAdapter(this);
			//  System.out.println("bool1");
			data.open();	
			Cursor curn = data.getAllkv();
    		startManagingCursor(curn);
    		
    		String[] from = new String[] {DataAdapter.tenkhoanvay,DataAdapter.sotienv,DataAdapter.laisuatv,DataAdapter.ngayvay,DataAdapter.ngaymuon};
    		int[] to = new int[] { R.id.txtten,R.id.txtsotien,R.id.txtlaisuat,R.id.txtngayvay,R.id.txtngaytra };

			cursordata = new SimpleCursorAdapter(this,R.layout.row_khoanvay, curn, from, to);
    	      listtab.setAdapter(cursordata);
		    loadTabs();
		    disAll();
		 
		  try {
				String destPath = "/data/data/" + getPackageName()+ "/databases/QuanLiTaiChinh";
				File f = new File(destPath);
				if (!f.exists()) {
					CopyDB(getBaseContext().getAssets().open("QuanLiTaiChinh"),
							new FileOutputStream(destPath));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		Btdate1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDefaultInforkv();
				showDatePickerDialogkv();
				
			}
		});
        Btdate2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getDefaultInforkt();
				showDatePickerDialogkt();
				
			}
		});
        Btthem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			      String 	nv = sdf.format(new Date());
			      String	nm = sdf.format(new Date());*/
			      
				String tkv= edittenv.getText().toString();
				int st= Integer.parseInt(editsotienv.getText().toString());
				int ls=Integer.parseInt(editlaisuatv.getText().toString());
			    String nv= editdate1.getText().toString();
				String nm=editdate2.getText().toString();
				
				data.insertkhoanvay(tkv, st, ls, nv, nm);
				 Toast.makeText(getApplicationContext(), "Bạn Thêm Thành Công: " + tkv,Toast.LENGTH_LONG).show();
				disAll();
				
				edittenv.setText(null);
				editsotienv.setText(null);
				editlaisuatv.setText(null);
			}
		});
		
	}
		private void disAll(){
	    	data.open();
	        try{
	        	Cursor curn = data.getAllkv();
	    		startManagingCursor(curn);
	    		
	    		String[] from = new String[] {DataAdapter.tenkhoanvay,DataAdapter.sotienv,DataAdapter.laisuatv,DataAdapter.ngayvay,DataAdapter.ngaymuon};
	    		int[] to = new int[] { R.id.txtten,R.id.txtsotien,R.id.txtlaisuat,R.id.txtngayvay,R.id.txtngaytra };

				cursordata = new SimpleCursorAdapter(this,R.layout.row_khoanvay, curn, from, to);
	    	      listtab.setAdapter(cursordata);
	    	      
	    	      listtab.setOnItemClickListener(new OnItemClickListener() {

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

	    					AlertDialog.Builder myDialog = new AlertDialog.Builder(khoanvay_activite.this);

	    					myDialog.setTitle("Delete/Edit?");
	    					TextView dialogTxt_id = new TextView(khoanvay_activite.this);

	    					LayoutParams dialogTxt_idLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	    					dialogTxt_id.setLayoutParams(dialogTxt_idLayoutParams);

	    					dialogTxt_id.setText("->" + String.valueOf(item_id));

	    					final EditText tenkv = new EditText(khoanvay_activite.this);
	    					final EditText st = new EditText(khoanvay_activite.this);
	    					final EditText ls= new EditText(khoanvay_activite.this);
	    					final EditText nv = new EditText(khoanvay_activite.this);
	    					final EditText nt = new EditText(khoanvay_activite.this);

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

	    					 LinearLayout layout = new LinearLayout(khoanvay_activite.this);

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
	    			                	
	    			                	LinearLayout layout = new LinearLayout(khoanvay_activite.this);
	    			                	AlertDialog.Builder builder=new AlertDialog.Builder(layout.getContext());
	    			                	builder.setTitle("Hỏi Xóa");
	    			                	builder.setMessage("Bạn Có Muốn Xóa Khoản vay Này Không?");
	    			                	builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
	    									
	    									@Override
	    									public void onClick(DialogInterface dialog, int which) {
	    										// TODO Auto-generated method stub
	    										data.open();
	    										 data.deletetkv(item_id);
	    						                 
	    						                 Cursor c = data.getAllkv();
	    											startManagingCursor(c);
	    											String[] from = new String[] {DataAdapter.tenkhoanvay,DataAdapter.sotienv,DataAdapter.laisuatv,DataAdapter.ngayvay,DataAdapter.ngaymuon};
	    								    		int[] to = new int[] { R.id.txtten,R.id.txtsotien,R.id.txtlaisuat,R.id.txtngayvay,R.id.txtngaytra };


	    											SimpleCursorAdapter notes = new SimpleCursorAdapter(khoanvay_activite.this,R.layout.row_khoanvay, c, from, to);
	    											listtab.setAdapter(notes);
	    											
	    											notes.notifyDataSetChanged();
	    										data.close();
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
	    									String valnv = editdate1.getText().toString();
	    									String valnt = editdate2.getText().toString();
	    									data.open();
	    									data.updatekhoanvay(item_id, valt, valst, valls, valnv, valnt);
	    										    									
	    									Cursor c = data.getAllkv();
	    									startManagingCursor(c);

	    									String[] from = new String[] {DataAdapter.tenkhoanvay,DataAdapter.sotienv,DataAdapter.laisuatv,DataAdapter.ngayvay,DataAdapter.ngaymuon};
	    						    		int[] to = new int[] { R.id.txtten,R.id.txtsotien,R.id.txtlaisuat,R.id.txtngayvay,R.id.txtngaytra };

	    									SimpleCursorAdapter notes = new SimpleCursorAdapter(khoanvay_activite.this,R.layout.row_khoanvay, c, from, to);
	    									listtab.setAdapter(notes);
	    									 Toast.makeText(getApplicationContext(), "Bạn Sửa Thành Công: " + valt,Toast.LENGTH_LONG).show();
	    									notes.notifyDataSetChanged();
	    									data.close();
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
	    	catch(Exception e){
	        	System.out.println(e);
	        }
	        data.close();
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

	public void loadTabs()
	 {
	 //Lấy Tabhost id ra trước (cái này của built - in android
	 final TabHost tab=(TabHost) findViewById(android.R.id.tabhost);
	 //gọi lệnh setup
	 tab.setup();
	 TabHost.TabSpec spec;
	 //Tạo tab1
	 spec=tab.newTabSpec("t1");
	 spec.setContent(R.id.tab1);
	 spec.setIndicator("1-Thêm Khoản Vay");
	 tab.addTab(spec);
	 //Tạo tab2
	 spec=tab.newTabSpec("t2");
	 spec.setContent(R.id.tab2);
	 spec.setIndicator("2-Danh Sách Khoản Vay");
	 tab.addTab(spec);
	 //Thiết lập tab mặc định được chọn ban đầu là tab 0
	 tab.setCurrentTab(0);
	 }
	public void getDefaultInforkv()
	 {
	 //lấy ngày hiện tại của hệ thống
	 cal=Calendar.getInstance();
	 SimpleDateFormat dft=null;
	 //Định dạng ngày / tháng /năm
	 dft=new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
	 String strDate=dft.format(cal.getTime());
	 //hiển thị lên giao diện
	 editdate1.setText(strDate);	 
	 //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
	 dateFinish=cal.getTime();
	 }
	 public void showDatePickerDialogkv()
	 {
	 OnDateSetListener callback=new OnDateSetListener() {
	 public void onDateSet(DatePicker view, int year,int monthOfYear,int dayOfMonth) {
	 //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
		 editdate1.setText((dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
	 //Lưu vết lại biến ngày hoàn thành
	 cal.set(year, monthOfYear, dayOfMonth);
	 dateFinish=cal.getTime();
	 }
	 };
	 //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
	 //sẽ giống với trên TextView khi mở nó lên
	 String s1=editdate1.getText()+"";
	 String strArrtmp1[]=s1.split("/");
	 int ngayv=Integer.parseInt(strArrtmp1[0]);
	 int thangv=Integer.parseInt(strArrtmp1[1])-1;
	 int namv=Integer.parseInt(strArrtmp1[2]);
	 DatePickerDialog pic1=new DatePickerDialog(khoanvay_activite.this,callback, namv, thangv, ngayv);
	 pic1.setTitle("Chọn ngày hoàn thành");
	 pic1.show();
	 }
	 
		public void getDefaultInforkt()
		 {
		 //lấy ngày hiện tại của hệ thống
		 cal=Calendar.getInstance();
		 SimpleDateFormat dft=null;
		 //Định dạng ngày / tháng /năm
		 dft=new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
		 String strDate=dft.format(cal.getTime());
		 //hiển thị lên giao diện 
		 editdate2.setText(strDate);
		 //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
		 dateFinish=cal.getTime();
		 }
		 public void showDatePickerDialogkt()
		 {
		 OnDateSetListener callback=new OnDateSetListener() {
		 public void onDateSet(DatePicker view, int year,int monthOfYear,int dayOfMonth) {
		 //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
			 editdate2.setText((dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
		 //Lưu vết lại biến ngày hoàn thành
		 cal.set(year, monthOfYear, dayOfMonth);
		 dateFinish=cal.getTime();
		 }
		 };
		 //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
		 //sẽ giống với trên TextView khi mở nó lên
		 String s2=editdate2.getText()+"";
		 String strArrtmp2[]=s2.split("/");
		 int ngayn=Integer.parseInt(strArrtmp2[0]);
		 int thangn=Integer.parseInt(strArrtmp2[1])-1;
		 int namn=Integer.parseInt(strArrtmp2[2]);
		 DatePickerDialog pic2=new DatePickerDialog(khoanvay_activite.this,callback, namn, thangn, ngayn);
		 pic2.setTitle("Chọn ngày hoàn thành");
		 pic2.show();
		 }
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
