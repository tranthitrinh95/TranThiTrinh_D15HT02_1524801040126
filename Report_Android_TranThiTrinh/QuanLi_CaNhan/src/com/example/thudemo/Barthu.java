package com.example.thudemo;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


public class Barthu {
	
	public Intent getInter(Context context)
	{
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
		        
		        Intent intent = ChartFactory.getPieChartIntent(context, distributionSeries,  defaultRenderer, "Sơ Đồ Khoản Thu");

		        return intent;
		 
		 
	}

}
