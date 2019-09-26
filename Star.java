import java.awt.Color;
import java.awt.Graphics;

public class Star extends Circle	{
	
	// constructor for your Star class

	public Star(Point center, int radius) {
		super(center, radius);
	}

	@Override
	public void paint(Graphics brush, Color color) {
		brush.setColor(color);
		brush.fillOval((int)center.x, (int)center.y, radius * 2, radius * 2);
	}

	@Override
	public void move() {
		//does nothing
	}

}
