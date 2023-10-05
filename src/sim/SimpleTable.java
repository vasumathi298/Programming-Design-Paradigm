package sim;

import java.util.Map;

/**
 * Simple table is one of the types of table that will use simple physics
 * to calculate the time.
 */
public class SimpleTable extends Table {

  private final int DAMPEN_SPEED = 5;


  public SimpleTable(int tableWidth, int tableHeight, String tableTtype, Status ballStatus) {
    super(tableWidth, tableHeight, ballStatus, tableTtype);
  }


  protected Map.Entry<String, Double> retriveCollisionSide(Ball ball) {
    collisionTime.clear();

    double ballXDirection = ball.getBallXDirection();
    double ballYDirection = ball.getBallYDirection();
    double speed = ball.getBallSpeed();

    if (ballXDirection != 0) {
      double rightOrLeftDuration = Math.abs((ballXDirection > 0
              ? tableWidth - ball.getBallXPosition() - ball.getBallRadius()
              : ball.getBallXPosition() - ball.getBallRadius())
              / (speed * ball.getBallXDirection()));
      collisionTime.put((ball.getBallXDirection() > 0) ? "+x" : "-x", rightOrLeftDuration);
    }

    if (ballYDirection != 0) {
      double topOrBottomDuration = Math.abs((ballYDirection > 0
              ? tableHeight - ball.getBallYPosition() - ball.getBallRadius()
              : ball.getBallYPosition() - ball.getBallRadius())
              / (speed * ball.getBallYDirection()));
      collisionTime.put((ball.getBallYDirection() > 0) ? "+y" : "-y", topOrBottomDuration);
    }

    return collisionTime.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .orElse(null);
  }


  protected void displaceBall(Ball ball) {
    Map.Entry<String, Double> edge = retriveCollisionSide(ball);

    if (ball.getBallSpeed() <= 0 || edge == null) {
      ball.ballSpeed = 0;
      ball.ballXVelocity = 0;
      ball.ballYVelocity = 0;

      ballStatus.curr = Message.BALL_NOT_SET_UP;
      return;
    }

    String ballCollisionSide = edge.getKey();
    double minTime = edge.getValue();

    if (ballCollisionSide.equals("+x") || ballCollisionSide.equals("-x")) {
      ball.ballYPosition += ball.ballSpeed * ball.ballYDirection * minTime;
      ball.ballXPosition = ballCollisionSide.equals("+x")
              ? tableWidth - ball.ballRadius : ball.ballRadius;
      ball.ballXDirection = -ball.ballXDirection;
      ballStatus.curr = ballCollisionSide.equals("+x")
              ? Message.BALL_HIT_RIGHT_EDGE : Message.BALL_HIT_LEFT_EDGE;
    } else {
      ball.ballXPosition += ball.ballSpeed * ball.ballXDirection * minTime;
      ball.ballYPosition = ballCollisionSide.equals("+y")
              ? tableHeight - ball.ballRadius : ball.ballRadius;
      ball.ballYDirection = -ball.ballYDirection;
      ballStatus.curr = ballCollisionSide.equals("+y")
              ? Message.BALL_HIT_TOP_EDGE : Message.BALL_HIT_BOTTOM_EDGE;
    }

    ball.ballSpeed -= DAMPEN_SPEED;

    if (ball.ballSpeed <= 0) {
      ball.ballXVelocity = 0;
      ball.ballYVelocity = 0;
      ballStatus.curr = Message.BALL_IS_STATIONARY;
    } else {
      ball.ballXVelocity = ball.ballSpeed * ball.ballXDirection;
      ball.ballYVelocity = ball.ballSpeed * ball.ballYDirection;
    }
  }

}