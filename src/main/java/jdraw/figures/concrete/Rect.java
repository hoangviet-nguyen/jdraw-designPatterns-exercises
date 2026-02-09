/*
 * Copyright (c) 2023 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures.concrete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import jdraw.figures.Rectangular;
import jdraw.framework.FigureHandle;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Rect extends Rectangular {
	private static final long serialVersionUID = 9120181044386552132L;

	/**
	 * Create a new rectangle of the given dimension.
	 * 
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		figure = new Rectangle(x, y, w, h);
		bounds = figure.getBounds();
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * 
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(Color.BLACK);
		g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * 
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */
	@Override
	public List<FigureHandle> getHandles() {
		return null;
	}
}
