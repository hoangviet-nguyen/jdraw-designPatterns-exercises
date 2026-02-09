package jdraw.figures.concrete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.geom.Ellipse2D;

import jdraw.figures.Rectangular;
import jdraw.framework.FigureHandle;



public class Ellipse extends Rectangular {

    public Ellipse(double x, double y, double w, double h) {
        figure = new Ellipse2D.Double(x, y, w, h);
        bounds = figure.getBounds();
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        int x = bounds.x, y = bounds.y;
        int w = (int) figure.getWidth(), h = (int) figure.getHeight();
        g.fillOval(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, w, h);
    }


    @Override
    public List<? extends FigureHandle> getHandles() {
        return null;
    }
}
