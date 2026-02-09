package jdraw.figures;

import java.awt.Point;
import java.awt.geom.RectangularShape;

import jdraw.framework.FigureEvent;

public abstract class Rectangular extends FigurePublisher {

    protected RectangularShape figure;

    @Override
	public void setBounds(Point origin, Point corner) {
		if (!origin.getLocation().equals(corner.getLocation())) {
			bounds.setFrameFromDiagonal(origin, corner);
			figure.setFrame(bounds);
			notifyFigureListeners(new FigureEvent(this));
		}
	}

	@Override
	public void move(int dx, int dy) {
		if (dx != 0 || dy != 0) {
			bounds.translate(dx, dy);
			figure.setFrame(bounds);
			notifyFigureListeners(new FigureEvent(this));
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return figure.contains(x, y);
	}
    
}
