
/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */
import java.awt.*;
import java.awt.Polygon;
import java.util.ArrayList;

public class Asteroids extends Game {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	Asteroid asteroidOne;
	Asteroid asteroidTwo;
	Asteroid asteroidThree;
	Ship milleniumFalcon;
	boolean collision = false;
	int collisionCooldown;
	Star[] stars;
	private int shipHealth;
	private ArrayList<Bullet> bulletArr = new ArrayList<Bullet>(100);
	private ArrayList<Asteroid> asteroidArr = new ArrayList<Asteroid>(3);
	boolean asteroidDestroyed;
	int dap;

	static int counter = 0;

	public Asteroids() {
		super("Asteroids!", SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.requestFocus();
		shipHealth = 100;
		
		Point originOne = new Point(100,80);
		Point[] shapeOne = new Point[] {new Point(0,50),new Point(50,40), new Point(20,0)};
		asteroidOne = new Asteroid(shapeOne, originOne, .1);
				
		Point originTwo = new Point(600,200);
		Point[] shapeTwo = new Point[] {new Point(40,0),new Point(60,50), new Point(50,70), new Point(0, 60), new Point(0, 10)};
		asteroidTwo = new Asteroid(shapeTwo, originTwo, 0);
		
		Point originThree = new Point(200,450);
		Point[] shapeThree = new Point[] {new Point(0,0), new Point(100,0), new Point(100,30), new Point(60,30), new Point(60,50), new Point(100,50), new Point(100,80), new Point(0,80), new Point(0,50), new Point(40,50), new Point(40,30), new Point(0,30), new Point(0,0)};
		asteroidThree = new Asteroid(shapeThree, originThree, 2);
		
		//puts all the asteroid objects into an array so they can be destroyed by bullets later
		asteroidArr.add(asteroidOne);
		asteroidArr.add(asteroidTwo);
		asteroidArr.add(asteroidThree);
		
		Point shipOrigin = new Point(400,300);
		Point[] shipShape = new Point[] {new Point(0,0),new Point(0,16), new Point(25,8)};
		milleniumFalcon = new Ship(shipShape, shipOrigin, 0);
		this.addKeyListener(milleniumFalcon);
		
		stars = createStars(7, 5);
	}

	public void paint(Graphics brush) {
		asteroidDestroyed = false;
		brush.setColor(Color.black);
		brush.fillRect(0,0,width,height);
		if (asteroidArr.isEmpty())	{
			brush.setColor(Color.white);
			brush.drawString("You Won!", 390, 300);
		}
		else if(shipHealth <= 0)	{
			brush.setColor(Color.WHITE);
			brush.drawString("Game over", 390, 300);
		}
		else	{
//		moves the asteroids
		for (int x = 0; x < asteroidArr.size(); x++)	{
			asteroidArr.get(x).move();
		}
	
		milleniumFalcon.move();
		
		//checks for collisions between asteroids and milleniumFalcon
		for (int x = 0; x < asteroidArr.size(); x++)	{
			if (asteroidArr.get(x).collision(milleniumFalcon))	{
				collision = true;
				collisionCooldown = 100;
				shipHealth-=1;
			}
		}
	
//		moves the bullets
		for(int n = 0; n < milleniumFalcon.getBullets().size(); n++)	{
			milleniumFalcon.getBullets().get(n).move();
			//removes bullets that go out of bounds
			if (milleniumFalcon.getBullets().get(n).outOfBounds())	{
				milleniumFalcon.getBullets().remove(n);
			}
		}
		

		//removes the asteroids if they contain a bullet
		for (int i = 0; i < asteroidArr.size(); i++) {
			for (int j = 0; j < milleniumFalcon.getBullets().size(); j++)	{
				if (asteroidArr.get(i).contains(milleniumFalcon.getBullets().get(j).getCenter()))	{
					asteroidDestroyed = true;
					dap = i;
					milleniumFalcon.getBullets().remove(j);
				}
			}
		}
		//the problem was that the program was crashing when i tried to remove the asteroid that was being acted upon in the 
		//for loop above. now that im removing the asteroid later it all runs fine.
		if (asteroidDestroyed)	{
			asteroidArr.remove(dap);
		}

//		painting begins here
//		painting the background stars
		for (int i = 0; i < stars.length; i+=2)	{
			if (counter % 2 ==0)	{
				stars[i].paint(brush, Color.blue);
			}
			else	{
				stars[i].paint(brush, Color.black);
			}
		}
		for (int i = 1; i < stars.length; i+=2)	{
			if (counter % 2 ==0)	{
				stars[i].paint(brush, Color.black);
			}
			else	{
				stars[i].paint(brush, Color.blue);
			}
		}
		

//		paints the bullets
		for (int n = 0; n < milleniumFalcon.getBullets().size(); n++)	{
			milleniumFalcon.getBullets().get(n).paint(brush, Color.GREEN);
		}
		
//		uses the asteroidArr to consecutively paint the asteroids
		for (Asteroid a : asteroidArr)	{
			a.paint(brush, Color.ORANGE);
		}
//		paints the milleniumFalcon red if collision is triggered
		if (collision && collisionCooldown > 0)	{
				milleniumFalcon.paint(brush, Color.red);
				collisionCooldown--;
			}
			else	{
				milleniumFalcon.paint(brush, Color.GRAY);
			}
		
		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter,10,10);
		brush.drawString("Ship Health: " + shipHealth + "%", 10, 30);
		}
		
	}
	
	 public Star[] createStars(int numberOfStars, int maxRadius) {
         Star[] stars = new Star[numberOfStars];
         for(int i = 0; i < numberOfStars; ++i) {
                 Point center = new Point
                 (Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
                 int radius = (int) (Math.random() * maxRadius);
                 if(radius < 1) {
                         radius = 1;
                 }
                 stars[i] = new Star(center, radius);
         }
         return stars;
	 }

	public static void main (String[] args) {
		
		Asteroids a = new Asteroids();
		a.repaint();

		
	}
}