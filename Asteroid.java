import java.awt.Color;
import java.awt.Graphics;

public class Asteroid extends Polygon	{
	private int xMovement;
	private int yMovement;
	private int degree;

	public Asteroid(Point[] shape, Point position, double rotation) {
		super(shape, position, rotation);
		xMovement = (int)((Math.random() - .5) * 10);
		yMovement = (int)((Math.random() - .5) * 10);
		degree = (int)((Math.random() - .25) * 5);
	}

	@Override
	public void paint(Graphics brush, Color color) {
		brush.setColor(color);
		Point[] a = getPoints();
		int[] x = new int[a.length];
			for (int i = 0; i < x.length; i++)	{
				x[i] = (int) a[i].x;
			}
		int[] y = new int[a.length];
		for (int i = 0; i < y.length; i++)	{
			y[i] = (int) a[i].y;
		}
		brush.drawPolygon(x, y, a.length);
	}

	@Override
	public void move() {
		position.x += xMovement;
		position.y += yMovement;
		rotate((int)degree);
		
		if (position.x > 800)	{
			position.x = 0;
		}
		if (position.x < 0)	{
			position.x = 799;
		}
		if (position.y > 600)	{
			position.y = 0;
		}
		if (position.y < 0)	{
			position.y = 599;
		}
	}

}
