import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Circle	{
	private static final int RADIUS = 1;
	private double rotation;
	
	public Point getCenter()	{
		return center;
	}
	
	public Bullet(Point center, double rotation) {

		super(center, RADIUS); // define RADIUS in Bullet class

		this.rotation = rotation;

		}

		public void move() {
		center.x += 10 * Math.cos(Math.toRadians(rotation));
		center.y += 10 * Math.sin(Math.toRadians(rotation));
		}

		@Override
		public void paint(Graphics brush, Color color) {
			brush.setColor(color);
			brush.fillOval((int)center.x, (int)center.y, radius * 2, radius * 2);
		}
		
		public boolean outOfBounds()	{
			if (center.x > 800)	{
				return true;
			}
			if (center.x < 0)	{
				return true;
			}
			if (center.y > 600)	{
				return true;
			}
			if (center.y < 0)	{
				return true;
			}
			return false;
		}

}
