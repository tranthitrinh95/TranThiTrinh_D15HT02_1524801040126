package com.example.thudemo;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BinderData extends BaseAdapter {
	
private static ArrayList<Itemlist> itemlsrrayList;
	
	private Integer[] imgid = {
			R.drawable.khoanchi,
			R.drawable.khoanthu,
			R.drawable.khoanno,
			R.drawable.khoanvay,
			R.drawable.theloai,
			R.drawable.thongke,
			R.drawable.thietlap,
			R.drawable.help
			};
	
	private LayoutInflater l_Inflater;

	public BinderData(Context context, ArrayList<Itemlist> results) {
		itemlsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemlsrrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemlsrrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.activity_main, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.name);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemlsrrayList.get(position).getName());
		holder.txt_itemDescription.setText(itemlsrrayList.get(position).getItemDescription());
		holder.itemImage.setImageResource(imgid[itemlsrrayList.get(position).getImageNumber() - 1]);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;

	}
	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		ImageView itemImage;
	}


}
