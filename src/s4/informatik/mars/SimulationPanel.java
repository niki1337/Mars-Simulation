package s4.informatik.mars;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import s4.informatik.mars.gui.Bar;
import s4.informatik.mars.gui.HorizontalBar;
import s4.informatik.mars.gui.VerticalBar;

public class SimulationPanel extends JPanel {
	
	private List<Bar> bars = new ArrayList<>();
	
	private HorizontalBar energyProductionBar;
	private HorizontalBar energyUsageBar;
	
	private HorizontalBar populationBar;
	private HorizontalBar areaBar;
	
	private VerticalBar erzBar;
	private VerticalBar alumBar;
	
	int erzTicks = 0;
	boolean tick = true;
	
	public SimulationPanel() {
		setBackground(Color.BLACK);
		
		// Instantiate horizontal bars
		int hbs = 450; // y-coordinate of where the horizontal bars start
		
		energyProductionBar = new HorizontalBar(50, hbs, 900, 20, 100);
		energyProductionBar.setFill((float) Math.random() * 100);
		bars.add(energyProductionBar);
		
		energyUsageBar = new HorizontalBar(50, hbs + 60, 900, 20, 100);
		energyUsageBar.setFill((float) Math.random() * 100);
		bars.add(energyUsageBar);
		
		populationBar = new HorizontalBar(50, hbs + 120, 900, 20, 100);
		populationBar.setFill((float) Math.random() * 100);
		bars.add(populationBar);
		
		areaBar = new HorizontalBar(50, hbs + 180, 900, 20, 100);
		areaBar.setFill((float) Math.random() * 100);
		bars.add(areaBar);
		
		// Instantiate vertical bars
		erzBar = new VerticalBar(1050, 30, 30, 600, 100);
		erzBar.setFill(100);
		bars.add(erzBar);
		
		alumBar = new VerticalBar(1170, 30, 30, 600, 100);
		alumBar.setFill(50);
		bars.add(alumBar);
	}
	
	public void update() {
		for (Bar b : bars) {
			b.update();
			b.setFill(b.getFill() + 0.2f);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Platz für Energie- & Populationsleisten
		g.setColor(Color.BLUE);
		g.fillRect(0, 720 - 300, 1000, 300);
		
		// Platz für Rohstoffleisten
		g.setColor(Color.RED);
		g.fillRect(1000, 0, 280, 720);
		
		// Render elements
		for (Bar b : bars) {
			b.render(g);
		}
	}
}