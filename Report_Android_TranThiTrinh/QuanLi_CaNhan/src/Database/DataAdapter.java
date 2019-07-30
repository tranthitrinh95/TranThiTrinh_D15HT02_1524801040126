package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Color;
import android.util.Log;

public class DataAdapter {
	String title;
	public Activity activity;
	DatabaseHelpert data = new DatabaseHelpert(activity);
	
	/*---------------------------------Nguoi Dung--------------------------------------*/
	static final String IDnguoidung = "_id";
	static final String uesnguoidung = "uesnguoidung";
	static final String Passnguoidung = "Passnguoidung";
	static final String Tennguoidung = "Tennguoidung";

	private static final String CaNhanTable = "Nguoidung";
	/*---------------------------Khoang Chi--------------------------------------*/
	public static final String colkcID = "_id";
	public static final String mathloaichi = "tenkc";
	public static final String colsotienkc = "sotien";
	public static final String colngaychi = "ngaychi";
	private static final String KhoanChiTable = "KhoanChi";

	/*------------------------The Loai Chi------------------------------------*/

	public static final String coltlcID = "_id";
	public static final String coltentheloaic = "Tentheloai";

	private static final String TheloaichiTable = "TheLoaiChi";

	/*-------------------------Khoan Thu-------------------------------------------*/

	public static final String colktID = "_id";
	public static final String mathloaithu = "tenkt";
	public static final String colsotienkt = "sotienkt";
	public static final String colngaythu = "ngaythu";

	private static final String KhoanthuTable = "KhoanThu";
	/*------------------------------The Loai Thu-------------------------------------*/

	public static final String id = "_id";
	public static final String tentlt = "Tentlthu";
	
	private static final String TheloaithuTable = "_TheLoaiThu";
	
	/*-------------------------Khoan Vay-------------------------------------------*/

	public static final String colkvID = "_id";
	public static final String tenkhoanvay = "tenkhoanvay";
	public static final String sotienv= "sotienkv";
	public static final String laisuatv = "laisuat";
	public static final String ngayvay= "ngayvay";
	public static final String ngaymuon = "ngaymuon";
   private static final String KhoanVayTable = "KhoanVay";
	/*-------------------------Khoan Nợ-------------------------------------------*/

   public static final String colknID = "_id";
   public static final String tenkhoanno = "tenkhoanno";
   public static final String sotienn= "sotienkv";
   public static final String laisuatn = "laisuat";
   public static final String ngayno= "ngayvay";
   public static final String ngaytrano = "ngaymuon";
   
	private static final String KhoanNoTable = "KhoanNo";

	/*---------------------Create Nguoi Dung---------------------------------*/
	private static final String DATABASE_NguoiDung = "CREATE TABLE "
			+ CaNhanTable + "(" + IDnguoidung
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ uesnguoidung + " TEXT, " + Passnguoidung 
			+ " Text, " + Tennguoidung + " Text NOT NULL);";
	/*-----------------------Create Khoang Chi---------------------------------*/
	private static final String DATABASE_KhoanChi = "CREATE TABLE "
			+ KhoanChiTable + "(" + colkcID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + mathloaichi
			+ " TEXT, " + colsotienkc + " Integer, " + colngaychi
			+ " Date NOT NULL,FOREIGN KEY (" + mathloaichi + ") REFERENCES "
			+ TheloaichiTable + " (" + coltlcID + "));";
	/*------------------------Create The Loai Chi--------------------------------*/
	private static final String DATABASE_TheLoaiChi = "CREATE TABLE "
			+ TheloaichiTable + " (" + coltlcID
			+ " INTEGER PRIMARY KEY autoincrement, " + coltentheloaic + " TEXT)";
	/*--------------------------Create Khoang Thu------------------------------*/
	private static final String DATABASE_KhoanThu = "CREATE TABLE "
			+ KhoanthuTable + "(" + colktID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + mathloaithu
			+ " TEXT, " + colsotienkt + " Integer, " + colngaythu
			+ " Date NOT NULL ,FOREIGN KEY (" + mathloaithu + ") REFERENCES "
			+ TheloaithuTable + " (" + colktID + "));";
	/*---------------------------Create The Loai Thu----------------------------*/
	private static final String DATABASE_TheLoaiThu = "CREATE TABLE " + TheloaithuTable + " (" + id + " INTEGER PRIMARY KEY autoincrement ,"
			+ tentlt + " TEXT)";
/*----------------------------Create Khoan Vay--------------------------------*/
	private static final String DATABASE_KhoanVay = "CREATE TABLE " + KhoanVayTable 
			+ "(" 
			+ colkvID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
			+ tenkhoanvay + " TEXT, " 
			+ sotienv + " INTEGER, " 
			+ laisuatv + " Integer, " 
			+ ngayvay + " Date NOT NULL, " 
			+ ngaymuon + " Date NOT NULL" 
			+ ")";
	/*----------------------------Create Khoan Nợ--------------------------------*/
	private static final String DATABASE_KhoanNO = "CREATE TABLE "
	+ KhoanNoTable + "(" + colknID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
	+ tenkhoanno + " TEXT, " 
	+ sotienn + " INTEGER, " 
	+ laisuatn + " Integer, " 
	+ ngayno + " Date NOT NULL, " 
	+ ngaytrano + " Date NOT NULL)";
	
	private static final String DATABASE_NAME = "QuanLiTaiChinh";
	private static final int DATABASE_VERSION = 12;
	private static final String TAG = "DataAdapter";
	private final Context context;

	public static  DatabaseHelpert DBHelper;
	private SQLiteDatabase db;

	public DataAdapter(Context ctx) {
		this.context = ctx;
//		DBHelper = new DatabaseHelpert(context);
	}

	public void createDB() {
		DBHelper = new DatabaseHelpert(context);
	}
	private static class DatabaseHelpert extends SQLiteOpenHelper {
		
		DatabaseHelpert(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				Log.e("Create Thable", "");	
				db.execSQL(DATABASE_NguoiDung);
				db.execSQL(DATABASE_KhoanChi);
				db.execSQL(DATABASE_TheLoaiChi);
				db.execSQL(DATABASE_KhoanThu);
				db.execSQL(DATABASE_TheLoaiThu);
				db.execSQL(DATABASE_KhoanVay);
				db.execSQL(DATABASE_KhoanNO);
		          
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(TAG, oldVersion + " to " + newVersion
					+ ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS CaNhanTable");
			onCreate(db);

		}
		

	}
	public DataAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}
/*------------------------thêm thể loại chi-----------------*/
	public long inserttlc(String tentlc) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(coltentheloaic, tentlc);
		open();
		return db.insert(TheloaichiTable, null, initialValues);
	}
	/*public void insertSomeTLC() {
		 
		inserttlc("Mua Sắm");
		inserttlc("Cà Phê");
		inserttlc("Du Lịch");
		inserttlc("Chi Gia Đình");
		inserttlc("Đám Tiệc");
		inserttlc("Điện, Nước, Xăng");
		inserttlc("Điện thoại-TH cáp-Internet");
		inserttlc("Giáo Dục");
		inserttlc("Giải Trí");
		inserttlc("Đồ Gia Dụng");
		 
		 }*/
	/*------------------------thêm thể loại thu-----------------*/
	public long inserttlt(String tent) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(tentlt, tent);
		
		return db.insert(TheloaithuTable, null, initialValues);
	}
	/*public void theloai() {
		 
		inserttlt("Tiền Lương");
		inserttlt("Cổ Phiếu");
		inserttlt("Kinh Doanh");
		inserttlt("Tiền Làm Thêm");
		inserttlt("Tiền Cho Vay");
		inserttlt("Bất Động Sản");
	}*/
	/*-------------------------Thêm Khoản Vay----------------------------*/
	public long insertkhoanvay(String tenkv, int sotien,int laisuat, String ngayvayv, String ngaytrav) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(tenkhoanvay, tenkv);
		initialValues.put(sotienv, sotien);
		initialValues.put(laisuatv, laisuat);
		initialValues.put(ngayvay, ngayvayv);
		initialValues.put(ngaymuon, ngaytrav);
		open();
		return db.insert(KhoanVayTable, null, initialValues);
	}
	/*public void insertSomekhoanvay() {
		 
		insertkhoanvay("Ngân Hàng",20000,6,"25/6/2013","30/8/2013");
		insertkhoanvay("Nguyễn Hồng Lan",3000000,3,"10/3/2013","10/8/2013");
	}*/
	/*-------------------------Thêm Khoản No----------------------------*/
	public long insertkhoanno(String tenkn, int sotien,int laisuat, String ngayvayn, String ngaymuonn) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(tenkhoanno, tenkn);
		initialValues.put(sotienn, sotien);
		initialValues.put(laisuatn, laisuat);
		initialValues.put(ngayno, ngayvayn);
		initialValues.put(ngaytrano, ngaymuonn);
		
		return db.insert(KhoanNoTable, null, initialValues);
	}
	/*public void insertSomeno() {
		 
		insertkhoanno("Ngân Hàng",20000,6,"25/6/2013","30/8/2013");
		insertkhoanno("Nguyễn Hồng Lan",3000000,3,"10/3/2013","10/8/2013");
		insertkhoanno("Nguyễn Hùng",4000000,1,"10/4/2013","10/9/2014");
	}*/
	/*-------------------------Thêm Khoản Chi----------------------------*/
public long insertkhoanchi(String matlc,int sotien, String ngaychi) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(mathloaichi, matlc);
		initialValues.put(colsotienkc, sotien);
		initialValues.put(colngaychi, ngaychi);
		open();
		return db.insert(KhoanChiTable, null, initialValues);
	}
/*public void insertKC() {
	 
	insertkhoanchi("Ngân Hàng",20000,"25/6/2013");
}*/
/*-------------------------Thêm Khoản Thu---------------------------*/
public long insertkhoanthu(String matlt,int sotienkt, String ngaythu) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(mathloaithu, matlt);
		initialValues.put(colsotienkt, sotienkt);
		initialValues.put(colngaythu, ngaythu);
		open();
		return db.insert(KhoanthuTable, null, initialValues);
	}
/*public void insertKT() {
	 
	insertkhoanthu("Chứng Khoán",1000000,"25/6/2013");
}*/

/*----------------------------Thêm Người Dùng------------------------------*/
public long createnguoid(String tenDN, String matKhau) {       
    ContentValues cv = new ContentValues();
    cv.put(uesnguoidung, tenDN);
    cv.put(Passnguoidung, matKhau);
    cv.put(Tennguoidung, "nodata");
    open();
    return db.insert(CaNhanTable, null, cv);
}
public void insertND() {
	 
	createnguoid("nguyenhuuphu","111");
}

/*----------Xóa tất cả thể loại chi------------------------*/
	public int deleteAll() {

		return db.delete(TheloaichiTable, null, null);

	}
	/*----------Xóa tất cả thể loại chi------------------------*/
	public int deleteAlltlt() {

		return db.delete(TheloaithuTable, null, null);

	}
	/*----------Xóa tất cả Khoan vay------------------------*/
	public int deleteAllkv() {
		return db.delete(KhoanVayTable, null, null);

	}
	/*----------Xóa tất cả Khoan No------------------------*/
	public int deleteAllkn() {

		return db.delete(KhoanNoTable, null, null);

	}
	/*----------Xóa tất cả Khoan Thu------------------------*/
	public int deleteAllkt() {

		return db.delete(KhoanthuTable, null, null);

	}
	/*----------------------Xóa id Khoan Chi----------------------*/

	public boolean deletekc(long rowId) {
		return db.delete(KhoanChiTable, colkcID + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id Khoan Thu----------------------*/

	public boolean deletekt(long rowId) {
		return db.delete(KhoanthuTable, colktID + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id the loai chi----------------------*/

	public boolean deletetlc(long rowId) {
		return db.delete(TheloaichiTable, coltlcID + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id the loai thu----------------------*/

	public boolean deletetlt(long rowId) {
		return db.delete(TheloaithuTable, id + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id khoan vay----------------------*/

	public boolean deletetkv(long rowId) {
		return db.delete(KhoanVayTable, colkvID + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id khoan No----------------------*/

	public boolean deletetkn(long rowId) {
		return db.delete(KhoanNoTable, colknID + "=" + rowId, null) > 0;
	}
	
/*-----------------------------liệt kê tất cả người dung----------------------*/
	 public String getDatandung() {
	        String[] columns = new String[] {IDnguoidung,uesnguoidung,Passnguoidung,Tennguoidung};
	        Cursor c = db.query(CaNhanTable, columns, null, null, null, null, null);
	        /*if(c==null)
	            Log.v("Cursor", "C is NULL");*/
	        String result="";
	        int iRow = c.getColumnIndex(IDnguoidung);
	        int iN = c.getColumnIndex(uesnguoidung);
	        int iMK = c.getColumnIndex(Passnguoidung);
	        int iHoTen = c.getColumnIndex(Tennguoidung);
	        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){           
	            result = result +" "+ c.getString(iRow)
	                    + " - id:" + c.getString(iN)
	                    + " - pw:" + c.getString(iMK)
	                    + " - ten:" + c.getString(iHoTen) + "\n";
	        }
	        c.close();
	        //Log.v("Result", result);
	        return result;
	    }
/*----------------------------liệt kê tất cả thể loại chi----------------------*/
	public Cursor getAlltlc() {
		return db.query(TheloaichiTable, new String[] { coltlcID,
				coltentheloaic }, null, null, null, null, null);
	}
	/*----------------------------liệt kê tất cả Khoan Chi----------------------*/
	public Cursor getAllkc() {
		return db.query(KhoanChiTable, new String[] { colkcID,mathloaichi,colsotienkc,
				colngaychi }, null, null, null, null, null);
	}
	
    public List<String> getAllLabelskc(){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TheloaichiTable;
      
       
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
         
        // returning lables
        return labels;
    }
	/*----------------------------liệt kê tất cả Khoan Thu---------------------*/
	public Cursor getAllkt() {
		return db.query(KhoanthuTable, new String[] { colktID,mathloaithu,colsotienkt,
				colngaythu }, null, null, null, null, null);
	}
	
	public List<String> getAllLabelskt(){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TheloaithuTable;
      
       
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
         
        // returning lables
        return labels;
    }
	/*----------------------------liệt kê tất cả thể loại thu----------------------*/
	public Cursor getAlltlt() {
		
		return db.query(TheloaithuTable, new String[] { 
				id,tentlt}, null, null, null, null, null);
	}
	/*----------------------------liệt kê tất cả khoản vay----------------------*/
	public Cursor getAllkv() {
		return db.query(KhoanVayTable, new String[] { colkvID,
				tenkhoanvay,sotienv,laisuatv,ngayvay,ngaymuon}, null, null, null, null, null);
	}
	/*----------------------------liệt kê tất cả khoản No----------------------*/
	public Cursor getAllkn() {
		if(db !=null)
		return db.query(KhoanNoTable, new String[] { colknID,
				tenkhoanno,sotienn,laisuatn,ngayno,ngaytrano}, null, null, null, null, null);
		return null;
	}
	/*----------------------------liệt kê _id Khoan Chi----------------------*/
	public Cursor laysotien (long sType)
    {
       return db.rawQuery("select coltentheloaic from KhoanChiTable where mathloaichi =\""+sType+"\"", null);
    }
	/*--------------Liệt kê _id thể loại thu----------------------------*/
	public Cursor gettlt (long rowId) throws SQLException {
		Cursor mCursor = db.query(true, TheloaithuTable, new String[] {
				id, tentlt }, id + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	/*--------------Liệt kê _id thể loại chi----------------------------*/
	public Cursor gettlc(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, TheloaichiTable, new String[] {
				coltlcID, coltentheloaic }, coltlcID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	/*--------------Liệt kê _id Khoản Vay----------------------------*/
	public Cursor getkhoanvay(long rowId) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		String ngayvay = sdf.format(new Date());
		String ngaymuon = sdf.format(new Date());
		Cursor mCursor = db.query(true, KhoanVayTable, new String[] {
				colkvID, tenkhoanvay,sotienv,laisuatv,ngayvay,ngaymuon }, colkvID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	
	}
	/*--------------Liệt kê _id Khoản No----------------------------*/
	public Cursor getkhoanno(long rowId) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		String ngayno = sdf.format(new Date());
		String ngaytrano = sdf.format(new Date());
		Cursor mCursor = db.query(true, KhoanNoTable, new String[] {
				colknID, tenkhoanno,sotienn,laisuatn,ngayno,ngaytrano }, colknID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	
	}
	/*--------------Liệt kê _id Khoản Chi----------------------------*/
	public Cursor getkhoanchi(long rowId) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		String colngaychi = sdf.format(new Date());
		Cursor mCursor = db.query(true, KhoanChiTable, new String[] {
				colkcID, mathloaichi,colsotienkc,colngaychi }, colkcID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	
	}
/*--------------------Updater thể Loại chi----------------------------*/
	public boolean updatetlc(long rowId, String name) {
		ContentValues args = new ContentValues();
		args.put(coltentheloaic, name);
		return db.update(TheloaichiTable, args, coltlcID + "=" + rowId, null) > 0;
	}
	/*--------------------Updater thể Loại thu----------------------------*/
	public boolean updatetlt (long rowId, String name) {
		ContentValues args = new ContentValues();
		args.put(tentlt, name);
		return db.update(TheloaithuTable, args, id + "=" + rowId, null) > 0;
	}
	/*--------------------Updater Khoan Vay----------------------------*/
	public boolean updatekhoanvay(long rowId, String name,int sotien,int laisuat,String ngayvayv, String ngaymuonv) {
		ContentValues args = new ContentValues();
		args.put(tenkhoanvay, name);
		args.put(sotienv, sotien);
		args.put(laisuatv, laisuat);
		args.put(ngayvay, ngayvayv);
		args.put(ngaymuon, ngaymuonv);
		return db.update(KhoanVayTable, args, colkvID + "=" + rowId, null) > 0;
	}
	/*--------------------Updater Khoan No----------------------------*/
	public boolean updatekhoanno(long rowId, String name,int sotien,int laisuat,String ngaynon, String ngaytra) {
		
		
		ContentValues args = new ContentValues();
		args.put(tenkhoanno, name);
		args.put(sotienn, sotien);
		args.put(laisuatn, laisuat);
		args.put(ngayno, ngaynon);
		args.put(ngaytrano, ngaytra);
		return db.update(KhoanNoTable, args, colknID + "=" + rowId, null) > 0;
	}
	/*--------------------Updater Khoan Chi----------------------------*/
	public boolean updatekhoanchi(long rowId, String name,int sotien,String ngaychi) {
		
		
		ContentValues args = new ContentValues();
		args.put(mathloaichi, name);
		args.put(colsotienkc, sotien);
		args.put(colngaychi, ngaychi);
		return db.update(KhoanChiTable, args, colkcID + "=" + rowId, null) > 0;
	}
	/*--------------------Updater Khoan Thu----------------------------*/
	public boolean updatekhoanthu(long rowId, String name,int sotienkt,String ngaythu) {
		
		
		ContentValues args = new ContentValues();
		args.put(mathloaithu, name);
		args.put(colsotienkt, sotienkt);
		args.put(colngaythu, ngaythu);
		return db.update(KhoanthuTable, args, colktID + "=" + rowId, null) > 0;
	}
	
/*-------------------------Seach Khoản Vay----------------------------*/	
	public Cursor fetchCountriesByName(String inputText) throws SQLException {
		  Log.w(TAG, inputText);
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = db.query(KhoanVayTable, new String[] {colkvID,
		     tenkhoanvay, sotienv, laisuatv, ngayvay,ngaymuon}, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = db.query(true, KhoanVayTable, new String[] {colkvID,
				   tenkhoanvay, sotienv, laisuatv, ngayvay,ngaymuon}, 
				   tenkhoanvay + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
		 }
/*------------------------------Seach Khoản Nợ----------------------------*/
	public Cursor seachkhoanno(String inputText) throws SQLException {
		  Log.w(TAG, inputText);
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = db.query(KhoanNoTable, new String[] {colknID,
		     tenkhoanno, sotienn, laisuatn, ngayno,ngaytrano}, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = db.query(true, KhoanNoTable, new String[] {colknID,
				   tenkhoanno, sotienn, laisuatn, ngayno,ngaytrano}, 
				   tenkhoanno + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
		 }
	/*-------------------------------Seach Khoản Thu---------------------------*/
	public Cursor seachkhoanthu(String inputText) throws SQLException {
		  Log.w(TAG, inputText);
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = db.query(KhoanthuTable, new String[] {colktID,
		     mathloaithu, colsotienkt, colngaythu}, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = db.query(true, KhoanthuTable, new String[] {colktID,
				   mathloaithu, colsotienkt, colngaythu}, 
				   mathloaithu + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
	}
	/*--------------------------------Seach Khoản Chi---------------------------------*/
	public Cursor seachkhoanchi(String inputText) throws SQLException {
		  Log.w(TAG, inputText);
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = db.query(KhoanChiTable, new String[] {colkcID,
		     mathloaichi, colsotienkc, colngaychi}, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = db.query(true, KhoanChiTable, new String[] {colkcID,
				   mathloaichi, colsotienkc, colngaychi}, 
				   mathloaichi + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
	}
    public String getBMIDataData(){

        String[] column =
                    new String[]{ tenkhoanvay };
            Cursor c = 
            		DBHelper.getWritableDatabase().query( KhoanVayTable, column, null, null, null, null, null );

            String result = "";
            int iData = c.getColumnIndex( tenkhoanvay );

            for ( c.moveToFirst(); ! c.isAfterLast(); c.moveToNext() ){
                result = result + c.getString( iData );
            }


        return result;
    }
    
    public Cursor getcount(String ten){
    	
    	return db.rawQuery("Select tenkhoanvay, from KhoanVay",new String[] {ten});
    
    }
    
    
    
    
    public String getBMIsoiem(){

        String[] column =
                    new String[]{ sotienv };
            Cursor c = 
            		DBHelper.getWritableDatabase().query( KhoanVayTable, column, null, null, null, null, null );

            String result = "";
            int iData = c.getColumnIndex( sotienv );

            for ( c.moveToFirst(); ! c.isAfterLast(); c.moveToNext() ){
                result = result + c.getString( iData );
            }


        return result;
    }
    
   public XYMultipleSeriesDataset getDemoDataset(String title) {

	    String[] column = new String[]{ tenkhoanvay };
	    Cursor c = DBHelper.getWritableDatabase().query( KhoanVayTable, column, null, null, null, null, null );

	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	    XYSeries firstSeries = new XYSeries("Sample series One");
	    TimeSeries series2 = new TimeSeries(title);

	    

	    while (!c.isAfterLast()) {
	        int date = c.getColumnIndex(DataAdapter.tenkhoanvay);
	        int weight = c.getColumnIndex(DataAdapter.sotienv);
	        firstSeries.add(weight, date);
	        c.moveToNext();
	    }

	    c.close();

	    dataset.addSeries(firstSeries);
	    dataset.addSeries(series2);

	    return dataset;
	}


	public Intent getIntent(Context context) {

		 //Lager TimeSeries for den første linja
	    XYMultipleSeriesDataset dataset = getDemoDataset("BA đồ thị");

	    //Kode for render
	    XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

	    //Optimalisering linje1
	    XYSeriesRenderer renderer = new XYSeriesRenderer();
	    renderer.setColor(Color.YELLOW);
	    renderer.setPointStyle(PointStyle.CIRCLE);
	    renderer.setFillPoints(true);

	    // Optimalisering linje2 husk rekke følgen
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	    renderer2.setColor(Color.BLUE);
	    renderer2.setPointStyle(PointStyle.SQUARE);
	    renderer2.setFillPoints(true);

	    //Legger til render seriene
	    mRenderer.addSeriesRenderer(renderer);

	    //Optimalisering grafen
	    mRenderer.setChartTitle("Test");
	    mRenderer.setZoomEnabled(true);
	    mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setXTitle("Dager");
	    mRenderer.setShowGrid(true);

	    mRenderer.addSeriesRenderer(renderer2);


	    Intent intent = ChartFactory.getLineChartIntent(context, dataset, 
	            mRenderer, "Line Graph Title");

	    return intent;
	}
    
}
