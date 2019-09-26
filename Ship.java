import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Ship extends Polygon implements KeyListener	{
//	int xMovement;
	boolean movingUp = false;
	boolean movingRight = false;
	boolean movingLeft = false;
	//int i = 0;
	private ArrayList<Bullet> bulletArr = new ArrayList<Bullet>(100);
	
	public Ship(Point[] shape, Point position, double rotation) {
		
		super(shape, position, rotation);
//		xMovement = 5;
//		rotation = 0;

	}
	public ArrayList<Bullet> getBullets()	{
		return bulletArr;
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
		brush.fillPolygon(x, y, a.length);
	}

	@Override
	public void move() {
//		position.x += xMovement;
		if (movingUp)	{
			position.x += 3 * Math.cos(Math.toRadians(rotation));
			position.y += 3 * Math.sin(Math.toRadians(rotation));
		}
		if (movingLeft)	{
			rotation -= 5;
		}
		if (movingRight)	{
			rotation += 5;
		}
		
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
	
	public void fillPolygon()	{
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			movingUp = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingLeft = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)	{
			Bullet bullet = new Bullet(this.getPoints()[2], this.rotation);
			bulletArr.add(bullet);
			//i++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			movingUp = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingLeft = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)	{
			//do nothing
		}
	}

}
