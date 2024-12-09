package interfaade.visualizations;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.chart.renderer.LookupPaintScale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.util.Random;

/**
 * Ejemplo de un mapa de calor para visualizar las actividades realizadas 
 * por estudiantes a lo largo de un año. 
 * Adaptar según la lógica del proyecto: podría mostrar la cantidad de 
 * actividades completadas por día/mes.
 */
public class HeatMapFrame extends ApplicationFrame {

    public HeatMapFrame(String title) {
        super(title);
        XYZDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    /**
     * Crea un dataset de ejemplo. 
     * x: mes (1-12)
     * y: día de la semana (1-7)
     * z: número aleatorio (ej: cantidad de actividades)
     */
    private XYZDataset createDataset() {
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double[] xValues = new double[365];
        double[] yValues = new double[365];
        double[] zValues = new double[365];

        Random rand = new Random();
        for (int i = 0; i < 365; i++) {
            xValues[i] = (i % 12) + 1; // Meses 1 a 12
            yValues[i] = (i % 7) + 1;  // Días 1 a 7
            zValues[i] = rand.nextDouble() * 10; 
        }

        double[][] data = {xValues, yValues, zValues};
        dataset.addSeries("Actividades", data);
        return dataset;
    }

    /**
     * Crea el gráfico del mapa de calor.
     */
    private JFreeChart createChart(XYZDataset dataset) {
        NumberAxis xAxis = new NumberAxis("Mes");
        NumberAxis yAxis = new NumberAxis("Día de la Semana");
        XYBlockRenderer renderer = new XYBlockRenderer();

        // Escala de colores: más actividades = más verde.
        LookupPaintScale paintScale = new LookupPaintScale(0, 10, Color.WHITE);
        paintScale.add(0.0, new Color(255, 255, 255));
        paintScale.add(2.5, new Color(200, 255, 200));
        paintScale.add(5.0, new Color(150, 255, 150));
        paintScale.add(7.5, new Color(100, 255, 100));
        paintScale.add(10.0, new Color(50, 255, 50));
        renderer.setPaintScale(paintScale);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        JFreeChart chart = new JFreeChart("Mapa de Calor de Actividades", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        chart.addSubtitle(new TextTitle("Visualización de actividades por día y mes", new Font("SansSerif", Font.PLAIN, 12)));
        return chart;
    }

    public static void main(String[] args) {
        HeatMapFrame demo = new HeatMapFrame("Heat Map de Actividades");
        demo.pack();
        demo.setLocationRelativeTo(null);
        demo.setVisible(true);
    }
}
