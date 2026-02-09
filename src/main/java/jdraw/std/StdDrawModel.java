/*
 * Copyright (c) 2023 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.std;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import jdraw.framework.*;
import jdraw.framework.DrawModelEvent.Type;

/**
 * Provide a standard behavior for the drawing model. This class initially does
 * not implement the methods in a proper way. It is part of the course
 * assignments to do so.
 * 
 * @author Nguyen Hoang Viet
 *
 */
public class StdDrawModel implements DrawModel {

	private final List<DrawModelListener> listeners = new CopyOnWriteArrayList<>();
	private final List<Figure> figures = new CopyOnWriteArrayList<>();
	private final Map<Figure, FigureListener> figureListener = new HashMap<>();
	public void handleEvent(DrawModelEvent event) {
		for (DrawModelListener listener:listeners) {
			listener.modelChanged(event);
		}
	}
	@Override
	public void addFigure(Figure f) {
		if (!figures.contains(f)) {
			figureListener.put(f, new ModelListener());
			f.addFigureListener(figureListener.get(f));
			figures.add(f);
			handleEvent(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_ADDED));
		}
	}

	private class ModelListener implements FigureListener {
		@Override
		public void figureChanged(FigureEvent event){
			handleEvent(new DrawModelEvent(StdDrawModel.this, event.getFigure(), Type.FIGURE_CHANGED));
		}
	}

	@Override
	public Stream<Figure> getFigures() {
		return figures.stream();
	}

	@Override
	public void removeFigure(Figure f) {
		if (figures.contains(f)) {
			f.removeFigureListener(figureListener.get(f));
			figureListener.remove(f);
			figures.remove(f);
			handleEvent(new DrawModelEvent(this, f, Type.FIGURE_REMOVED));
		}
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		assert listener != null;
		listeners.add(listener);
	}



	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		listeners.remove(listener);
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * 
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int desiredIndex) {
		if (desiredIndex < 0 || desiredIndex >= figures.size()) {
			throw new IndexOutOfBoundsException("Given index is out of bound");
		} else if (!figures.contains(f)) {
			throw new IllegalArgumentException("Figure does not exist");
		}
		int currentIndex = figures.indexOf(f);

		//check if indexes are the same
		if (currentIndex == desiredIndex) return;

		//Bring front Figure to Back;
		if (currentIndex > desiredIndex) {
			while(currentIndex > desiredIndex) {
				Collections.swap(figures, currentIndex, currentIndex -1);
				currentIndex--;
			}
		//Bring back Figure to Front
		} else {
			while (currentIndex < desiredIndex) {
				Collections.swap(figures, currentIndex, currentIndex +1);
				currentIndex++;
			}
		}
		handleEvent(new DrawModelEvent(this, f, Type.DRAWING_CHANGED));
	}

	@Override
	public void removeAllFigures() {
		for (Figure f: figures) {
			f.removeFigureListener(figureListener.get(f));
		}
		figures.clear();
		figureListener.clear();
		handleEvent(new DrawModelEvent(this, null, Type.DRAWING_CLEARED));

	}

}
