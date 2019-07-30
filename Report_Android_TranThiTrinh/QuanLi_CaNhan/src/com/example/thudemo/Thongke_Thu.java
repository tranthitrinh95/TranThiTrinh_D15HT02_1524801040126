package com.example.thudemo;



import org.achartengine.ChartFactory;

import org.achartengine.model.CategorySeries;

import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import Database.DataAdapter;

import android.app.Activity;

import android.content.Intent;
import android.database.Cursor;

import android.graphics.Color;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class Thongke_Thu extends Activity {	

	private DataAdapter db;
	public Cursor cur;
		    @Override
		    public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.listtheloai);
		 
		   
		 
		        // Defining click event listener for the button btn_chart
		        OnClickListener clickListener = new OnClickListener() {
		 
		            @Override
		            public void onClick(View v) {
		                // Draw the Income vs Expense Chart
		                openChart();
		            }
		        };
		 
		        // Setting event click listener for the button btn_chart of the MainActivity layout
		 
		    }
		 
		    private void openChart(){
		    	
		        // Pie Chart Section Names
		        String[] code = new String[] {
		            "Tien Luong", "Co Phieu", "Tien Lam Them",
		            "Ngan Hang"
		        };
		        // Pie Chart Section Value
		       // double y=i db.getsotienData();
		        double[] distribution = { 5000, 10000, 3000, 5000};
		 
		        // Color of each Pie Chart Sections
		        int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
		                        Color.YELLOW };
		 
		        // Instantiating CategorySeries to plot Pie Chart
		        CategorySeries distributionSeries = new CategorySeries("Khoản Thu Trong Tháng");
		        for(int i=0 ;i < distribution.length;i++){
		            // Adding a slice with its values and name to the Pie Chart
		            distributionSeries.add(code[i], distribution[i]);
		        }
		 
		        // Instantiating a renderer for the Pie Chart
		        DefaultRenderer defaultRenderer  = new DefaultRenderer();
		        for(int i = 0 ;i<distribution.length;i++){
		        	
		            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
		            seriesRenderer.setColor(colors[i]);
		            seriesRenderer.setDisplayChartValues(true);
		            // Adding a renderer for a slice
		            defaultRenderer.addSeriesRenderer(seriesRenderer);
		        }
		 
		        defaultRenderer.setChartTitle("Khoản Thu Trong Tháng ");
		        defaultRenderer.setChartTitleTextSize(20);
		        defaultRenderer.setZoomButtonsVisible(true);
		 
		        // Creating an intent to plot bar chart using dataset and multipleRenderer
		        Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Sơ Đồ Khoản Thu");
		 
		        // Start Activity
		        startActivity(intent);
		 
		    }
		    @Override
		    public boolean onCreateOptionsMenu(Menu menu) {
		        getMenuInflater().inflate(R.menu.main, menu);
		        return true;
		    }
}
