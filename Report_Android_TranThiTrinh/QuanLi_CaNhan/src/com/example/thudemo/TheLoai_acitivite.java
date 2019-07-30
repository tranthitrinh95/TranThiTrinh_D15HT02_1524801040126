package com.example.thudemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TheLoai_acitivite extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.theloai_activite);
		ArrayList<Itemlist> image_details = GetSearchResults();

		 final ListView lv1 = (ListView) findViewById(R.id.listkc);
		 lv1.setAdapter(new Binderdatatheloai(this, image_details));

		 lv1.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					Intent khoanchi = new Intent(TheLoai_acitivite.this,TheLoaiChi.class);
					startActivity(khoanchi);
					break;
				case 1:
					Intent theloaithu = new Intent(TheLoai_acitivite.this,TheLoaiT_acitivity.class);
					startActivity(theloaithu);
					break;
				}
			}
			});
		
	}
			private ArrayList<Itemlist> GetSearchResults() {
				ArrayList<Itemlist> results = new ArrayList<Itemlist>();

				Itemlist item_details = new Itemlist();
				item_details.setName("Thể Loại Chi");
				item_details.setItemDescription("Tên danh mục chi ra");
				item_details.setImageNumber(1);
				results.add(item_details);

				item_details = new Itemlist();
				item_details.setName("Thể Loại Thu");
				item_details.setItemDescription("Tên danh mục chi vào");
				item_details.setImageNumber(2);
				results.add(item_details);
				return results;
			}

}
