package com.example.thudemo;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class BarVay {
	public Intent getInter(Context context)
	{
		  String[] code = new String[] {
		            "Ngan Hang", "Nguyen Phuoc Loc", "Le Thi Tam",
		           
		        };
		        // Pie Chart Section Value
		       // double y=i db.getsotienData();
		        double[] distribution = { 10000, 5000, 3000};
		 
		        // Color of each Pie Chart Sections
		        int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
		                        Color.YELLOW };
		 
		        // Instantiating CategorySeries to plot Pie Chart
		        CategorySeries distributionSeries = new CategorySeries("Khoản Vay Trong Tháng");
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
		 
		        defaultRenderer.setChartTitle("Khoản Vay Trong Tháng ");
		        defaultRenderer.setChartTitleTextSize(20);
		        defaultRenderer.setZoomButtonsVisible(true);
		        
		        Intent intent = ChartFactory.getPieChartIntent(context, distributionSeries,  defaultRenderer, "Sơ Đồ Khoản Vay");

		        return intent;
		 
		 
	}
}
