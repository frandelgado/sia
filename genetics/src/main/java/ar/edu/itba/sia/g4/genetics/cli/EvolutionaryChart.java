package ar.edu.itba.sia.g4.genetics.cli;

import ar.edu.itba.sia.g4.genetics.engine.Inspector;
import ar.edu.itba.sia.g4.genetics.problem.Species;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

public class EvolutionaryChart<T extends Species> extends JFrame implements Inspector<T> {

    private static final String CHART_TITLE = "Fitness per Generation";
    private static final String CHART_X_LABEL = "Generation";
    private static final String CHART_Y_LABEL = "Fitness";

    private XYSeriesCollection dataset;
    private XYLineAndShapeRenderer renderer;
    private JFreeChart lineChart;
    private XYPlot plot;

    public EvolutionaryChart() {
        dataset = initDataset();
        renderer = initRenderer();

        lineChart = ChartFactory.createXYLineChart(CHART_TITLE, CHART_X_LABEL, CHART_Y_LABEL,
         dataset, PlotOrientation.VERTICAL, true, false, false);

        //Subtitles
        lineChart.addSubtitle(0, new TextTitle("Generation: 0"));
        lineChart.addSubtitle(1, new TextTitle("Best: 0"));

        //Customization
        plot = initPlot(lineChart, renderer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension( 800 , 600 ));
        add(chartPanel, BorderLayout.CENTER);
        setContentPane(chartPanel);
    }

    private XYPlot initPlot(JFreeChart lineChart, XYLineAndShapeRenderer renderer) {
        XYPlot plot = lineChart.getXYPlot();
        plot.setRenderer(renderer);

        //Setting background color for the plot
        plot.setBackgroundPaint(Color.WHITE);

        //Setting visibility and paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        return plot;
    }

    private XYLineAndShapeRenderer initRenderer() {

        //Setting custom renderer for the series lines
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);

        // sets paint color for each series
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, new Color(128,0,128));
        renderer.setSeriesPaint(2, Color.BLUE);

        // sets thickness for series (using strokes)
        renderer.setSeriesStroke(0, new BasicStroke(2.5f));
        renderer.setSeriesStroke(1, new BasicStroke(2.5f));
        renderer.setSeriesStroke(2, new BasicStroke(3.0f));
        return renderer;
    }

    private XYSeriesCollection initDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries data1 = new XYSeries("Best");
        data1.setDescription("BestFitness");
        dataset.addSeries(data1);

        XYSeries data2 = new XYSeries("Avg");
        data1.setDescription("AvgFitness");
        dataset.addSeries(data2);

        XYSeries data3 = new XYSeries("Min");
        data1.setDescription("MinFitness");
        dataset.addSeries(data3);

        return dataset;
    }


    public void addToLineChart(double bestFitness, double avgFitness, double mintFitness, int generations) {
        ((TextTitle)(lineChart.getSubtitle(0))).setText("Generation: " + generations);
        ((TextTitle)(lineChart.getSubtitle(1))).setText("Best: " + bestFitness);

        dataset.getSeries("Best").add((double) generations, bestFitness);
        dataset.getSeries("Avg").add((double) generations, avgFitness);
        dataset.getSeries("Min").add((double) generations, mintFitness);

    }

    @Override
    public void onGeneration(List<T> prev, List<T> cur, long generation) {
        double avgFitness = cur.parallelStream().mapToDouble(Species::getFitness).average().orElse(0);
        double fittest = cur.parallelStream().mapToDouble(Species::getFitness).max().orElse(0);
        double fattest = cur.parallelStream().mapToDouble(Species::getFitness).min().orElse(0);

        addToLineChart(fittest, avgFitness, fattest, (int) generation);
    }
}
