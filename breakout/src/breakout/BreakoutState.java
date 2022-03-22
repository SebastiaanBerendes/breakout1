package breakout;

// TODO: implement, document
public class BreakoutState {
	
	private BallState[] balls;
	private BlockState[] blocks;
	private Point bottomRight;
	private PaddleState paddle;

	
	public BreakoutState(BallState[] balls, BlockState[] blocks, Point bottomRight, PaddleState paddle) {
		this.balls = balls;
		this.blocks = blocks;
		this.bottomRight = bottomRight;
		this.paddle = paddle;
	}
	
	public BallState[] getBalls() {
		return this.balls;
	}

	public BlockState[] getBlocks() {
		return this.blocks;
	}

	public PaddleState getPaddle() {
		return this.paddle;
	}

	public Point getBottomRight() {
		return this.bottomRight;
	}

	public void tick(int paddleDir) {
		int length_balls = balls.length;
		int length_blocks = blocks.length;
		for (int i = 0; i < length_balls; i++) {
			balls[i] = new BallState(balls[i].getCenter().plus(balls[i].getVelocity()), balls[i].getVelocity(), balls[i].getDiameter());
			
//			block collision detection
			if (length_blocks != 0) {
				for (int j = 0; j < length_blocks; j++) {
					Vector m_block = blocks[j].collision(balls[i].getCenter(), balls[i].getDiameter());
					if (m_block.getSquareLength() == 1) {
						balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_block), balls[i].getDiameter());
					}
//					index moet nog bijgehouden worden zodat de block verwijderd kan worden
				}
			}
//			paddle collision detection
			Vector m_paddle = paddle.collision(balls[i].getCenter(), balls[i].getDiameter());
			if (m_paddle.getSquareLength() == 1) {
				balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(m_paddle), balls[i].getDiameter());
			}
			
			
//			if (0 > balls[i].getTL().getX()) {
//				balls[i] = new BallState(balls[i].getCenter(), balls[i].getVelocity().mirrorOver(balls[i].getVelocity()), balls[i].getDiameter());
//			}
//			if (50000 < balls[i].getBR().getX()) {
//				
//			}
//			if (0 > balls[i].getTL().getY()) {
//																CHECK OPGAVE IETS MET RECTANGLE
//			}
//			if (30000 < balls[i].getBR().getY()) {
//				
//			}
		}
	}

	public void movePaddleRight() {
		Vector shift = new Vector(50,0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		if (newBR.getX()<=50000)	
			paddle = new PaddleState(newCenter, newTL, newBR);
	}

	public void movePaddleLeft() {
		Vector shift = new Vector(-50,0);
		Point newCenter = paddle.getCenter().plus(shift);
		Point newTL = paddle.getTL().plus(shift);
		Point newBR = paddle.getBR().plus(shift);
		if (newTL.getX() >= 0)
			paddle = new PaddleState(newCenter, newTL, newBR);
	}
	
	public boolean isWon() {
		if (this.balls.length > 0 && this.blocks.length == 0) {
			return true;}
		else {
			return false;}
	}

	public boolean isDead() {
		if (this.balls.length < 1) {
			return true; }
		else {
			return false; }
	}
}
