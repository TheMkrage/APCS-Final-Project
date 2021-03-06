import java.util.Random;

public class Ball extends GameObject implements Runnable{

	private static Ball instance;
	private int score1 = 0;
	private int score2 = 0;
	
	private boolean lastRunScored = false;

	public static Ball getInstance() {
		if (instance == null) {
			instance = new Ball();
		}
		return instance;
	}

	private Ball() {
		super(260, 260, "ball.png");
	}

	private int curYRate() {
		if (this.getY() < 0 || AnimatedPanel.getPaddle1().getRect().intersects(this.getRect())) {
			return (new Random()).nextInt(3) + 3;
		} else if (this.getY() + 40 > 600 || AnimatedPanel.getPaddle2().getRect().intersects(this.getRect()) ){
			return -(new Random()).nextInt(3) - 3;
		}
		return yRate;
	}

	private int curXRate() {
		if  ((AnimatedPanel.getPaddle1().getRect().intersects(this.getRect())) || (this.getX() < 0)) {
			return (new Random()).nextInt(8) + 2;
		} else if ((this.getX() + 40 > 600) || (AnimatedPanel.getPaddle2().getRect().intersects(this.getRect())) ) {
			return -(new Random()).nextInt(8)- 2 ;
		}
		return xRate;
	}
	
	public String get1Score() {
		return String.valueOf(score1);
	}
	
	public String get2Score() {
		return String.valueOf(score2);
	}

	@Override
	public void run() {
		while (true) {
			this.xRate = curXRate();
			this.yRate = curYRate();
			this.setX(this.getX() + xRate);
			this.setY(this.getY() + yRate);
			this.setContiniousMovement(xRate, yRate);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (this.getX() < AnimatedPanel.getPaddle1().getX() && !lastRunScored) {
				score1++;
				lastRunScored = true;
			}else if (this.getX() > AnimatedPanel.getPaddle2().getX() && !lastRunScored) {
				score2++;
				System.out.println("SCORE");
				lastRunScored = true;
			}else if (this.getX() < AnimatedPanel.getPaddle2().getX() && this.getX() > AnimatedPanel.getPaddle1().getX()) {
				System.out.println("HOWDY");
				lastRunScored = false;
			}
			System.out.println( MenuPanel.getInstance().getGoTo());
			if (score1 == MenuPanel.getInstance().getGoTo()) {
				Main.changeToMenu();
			}else if (score2 == MenuPanel.getInstance().getGoTo()) {
				Main.changeToMenu();
			}
		}
		
	}

}
