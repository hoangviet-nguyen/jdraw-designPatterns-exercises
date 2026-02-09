package jdraw.figures.concrete.tools;

import java.awt.Point;
import java.awt.event.MouseEvent;
import jdraw.figures.AbstractTool;
import jdraw.figures.concrete.Ellipse;
import jdraw.framework.DrawContext;


public class EllipseTool extends AbstractTool {

    public EllipseTool(DrawContext context) {
        super(context, "Oval");
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (figure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		figure = new Ellipse(x, y, 0, 0);
		view.getModel().addFigure(figure);
    }
}
