import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;

public class Chart {

    public Chart(String name, float[] e, float[] effectiveE) {
        // Create Chart
        final XYChart chart = new XYChartBuilder().width(600).height(400).title(name).xAxisTitle("D").yAxisTitle("E").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
        chart.addSeries("E", e);
        chart.addSeries("Effective E", effectiveE);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and set up the window.
                JFrame frame = new JFrame("Chart");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // chart
                JPanel chartPanel = new XChartPanel<XYChart>(chart);
                frame.add(chartPanel, BorderLayout.CENTER);

                // label
                JLabel label = new JLabel("Image recognition.", SwingConstants.CENTER);
                frame.add(label, BorderLayout.SOUTH);

                // Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
