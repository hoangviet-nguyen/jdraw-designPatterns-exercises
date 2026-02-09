package jdraw.figures;

import java.awt.Cursor;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.MouseEvent;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public abstract class AbstractTool implements DrawTool {
    
    private final String toolName;
	private static final String IMAGES = "/images/";
	protected final DrawContext context;
	protected final DrawView view;
	protected Figure figure;
	protected Point anchor;

    public AbstractTool(DrawContext context, String toolName) {
        this.toolName = toolName;
        this.context = context;
        view = context.getView();
    }
    
	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}

	@Override
	public void activate() {
		this.context.showStatusText(toolName + " Mode");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + toolName.toLowerCase() + ".png"));
	}

	@Override
	public String getName() {
		return toolName;
	}


	@Override
	public void mouseUp(int x, int y, java.awt.event.MouseEvent e) {
		figure = null;
		anchor = null;
		this.context.showStatusText(toolName + " Mode");
		
	}

	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		figure.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = figure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}
}
