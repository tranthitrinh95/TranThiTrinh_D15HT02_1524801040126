package com.example.thudemo;

import java.util.ArrayList;

import com.example.thudemo.BinderData.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Binderdatatheloai extends BaseAdapter {
	
private static ArrayList<Itemlist> itemlsrrayListkc;
	
	private Integer[] imgid = {
			R.drawable.khoanchi,
			R.drawable.khoanthu,
			};
	
	private LayoutInflater l_Inflater;
	public Binderdatatheloai(Context context, ArrayList<Itemlist> results) {
		itemlsrrayListkc = results;
		l_Inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemlsrrayListkc.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemlsrrayListkc.get(position);
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
			convertView = l_Inflater.inflate(R.layout.listtheloai, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView.findViewById(R.id.nametl);
			holder.txt_itemDescription = (TextView) convertView.findViewById(R.id.itemDescriptiontl);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.phototl);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.txt_itemName.setText(itemlsrrayListkc.get(position).getName());
		holder.txt_itemDescription.setText(itemlsrrayListkc.get(position).getItemDescription());
		holder.itemImage.setImageResource(imgid[itemlsrrayListkc.get(position).getImageNumber() - 1]);
//		imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg", holder.itemImage);

		return convertView;
	}
	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_itemDescription;
		ImageView itemImage;
	}

}
