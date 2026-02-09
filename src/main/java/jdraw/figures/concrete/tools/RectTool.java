/*
 * Copyright (c) 2023 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures.concrete.tools;

import java.awt.Point;
import java.awt.event.MouseEvent;
import jdraw.figures.AbstractTool;
import jdraw.figures.concrete.Rect;
import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author Christoph Denzler
 */
public class RectTool extends AbstractTool {

	public RectTool(DrawContext context) {
		super(context, "Rectangle");
	}

	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (figure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		figure = new Rect(x, y, 0, 0);
		view.getModel().addFigure(figure);
	}

}
