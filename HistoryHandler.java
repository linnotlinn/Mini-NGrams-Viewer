package main;
import ngrams.NGramMap;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;


public class HistoryHandler extends NgordnetQueryHandler{

    public NGramMap ngm;

    public HistoryHandler(NGramMap map){
        ngm = map;
    }


    @Override
    public String handle(NgordnetQuery q) {
        List<String> labels = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        List<TimeSeries> lts = new ArrayList<>();
        for (String label : labels){
            lts.add(ngm.weightHistory(label, startYear, endYear));
        }

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }

}
