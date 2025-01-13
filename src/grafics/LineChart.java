package grafics;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LineChart(List<Double> times) {
		
		initGui(times);
		
	}

	/**
	 * Initialize the frame with the chart and the database
	 * @param times List of the times of each graph
	 * @return
	 * */
	private void initGui(List<Double> times) {
		
		XYDataset dataset = createDataset(times);
		JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method creates the data set with the list of times
	 * @param times List of the times of each graph
	 * @return returns the created data set
	 * */
	private XYDataset createDataset(List<Double> times) {
		
		XYSeries series = new XYSeries("Time");
		
		int numVertex = 50;
		for(int i = 0; i < times.size(); i++) {

			if(i == 0)
				series.add(10, times.get(i));
			
			else {
				series.add(numVertex, times.get(i));
				numVertex += 50;
			}
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
	}
	
	/**
	 * This method creates the chart
	 * @param dataset it's the data set created earlier
	 * @return the chart created
	 * */
	private JFreeChart createChart(XYDataset dataset) {
		
		JFreeChart chart = ChartFactory.createXYLineChart(
                "Average time take kruskal with bigger graphs",
                "Size of Graph (Number of Vertex)",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
		
		XYPlot plot = chart.getXYPlot();
		
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Average time take kruskal with bigger graphs", new Font("Italic", java.awt.Font.BOLD, 18)));
		
		return chart;
	}
}
