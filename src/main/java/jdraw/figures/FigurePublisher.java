package jdraw.figures;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

public abstract class FigurePublisher implements Figure {

	protected Rectangle bounds;
	protected Shape figure;
    private List<FigureListener> listeners = new CopyOnWriteArrayList<>();

    protected void notifyFigureListeners(FigureEvent event){
        for (FigureListener listener : listeners) {
            listener.figureChanged(event);
        }
	}

    @Override
	public void addFigureListener(FigureListener listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		if (listeners.contains(listener)) {
			listeners.remove(listener);
		}
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
	}

    @Override
    public Figure clone() {
        return null;
    }
}
