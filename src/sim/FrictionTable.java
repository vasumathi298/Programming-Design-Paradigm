package sim;


import java.util.Map;

/**
 * FrictionTable represents a pool table simulation using Newtonian Physics
 * for time calculation.
 */
public class FrictionTable extends Table {

  private final double FRICTION_COEFFICIENT = 0.1; //Coefficient of friction for the table.


  public FrictionTable(int tableWidth, int tableHeight, String tableType, Status ballStatus) {
    super(tableWidth, tableHeight, ballStatus, tableType);
  }


  private double solveQuadraticEquation(double coefficientA,
                                        double coefficientB, double coefficientC) {
    double discriminant = (coefficientB * coefficientB) - (4 * coefficientA * coefficientC);

    if (discriminant < 0) {
      return Double.POSITIVE_INFINITY;
    }

    double root1 = ((-coefficientB + Math.sqrt(discriminant)) / (2 * coefficientA));
    double root2 = ((-coefficientB - Math.sqrt(discriminant)) / (2 * coefficientA));

    if (root1 > 0 && (root2 <= 0 || root1 < root2)) {
      return root1;
    } else if (root2 > 0) {
      return root2;
    } else {
      return Double.NEGATIVE_INFINITY;
    }
  }


  protected Map.Entry<String, Double> retriveCollisionSide(Ball ball) {
    double constantComponent = -0.5 * FRICTION_COEFFICIENT * 9.81;

    collisionTime.clear();

    double minTime;

    if (ball.ballXDirection > 0) {
      minTime = solveQuadraticEquation(constantComponent * ball.ballXDirection,
              ball.ballXVelocity,
              -(tableWidth - ball.ballXPosition - ball.ballRadius));
      collisionTime.put("+x", minTime);
    }
    else if (ball.ballXDirection < 0) {
      minTime = solveQuadraticEquation(constantComponent * ball.ballXDirection,
              ball.ballXVelocity,
              ball.ballXPosition - ball.ballRadius);
      collisionTime.put("-x", minTime);
    }

    if (ball.ballYDirection > 0) {
      minTime = solveQuadraticEquation(constantComponent * ball.ballYDirection,
              ball.ballYVelocity,
              -(tableHeight - ball.ballYPosition - ball.ballRadius));
      collisionTime.put("+y", minTime);
    } else if (ball.ballYDirection < 0) {
      minTime = solveQuadraticEquation((constantComponent * ball.ballYDirection),
              ball.ballYVelocity,
              (ball.ballYPosition - ball.ballRadius));
      collisionTime.put("-y", minTime);
    }

    return collisionTime.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .orElse(null);
  }

  protected void displaceBall(Ball ball) {
    Map.Entry<String, Double> edge = retriveCollisionSide(ball);

    if (ball.ballSpeed < 0 || edge == null) {
      ballStatus.curr = Message.BALL_NOT_SET_UP;
      return;
    }

    String ballCollisionSide = edge.getKey();
    double minTime = edge.getValue();

    double constantComponent = 0.5 * FRICTION_COEFFICIENT * 9.81;

    if (minTime != Double.POSITIVE_INFINITY && minTime != Double.NEGATIVE_INFINITY) {
      if (ballCollisionSide.equals("+x")) {
        ball.ballYPosition += (ball.ballYVelocity * minTime)
                - (constantComponent * ball.ballYDirection * minTime * minTime);
        ball.ballXPosition = tableWidth - ball.ballRadius;
        ball.ballXVelocity = -(ball.ballXVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballXDirection * minTime));
        ball.ballYVelocity = ball.ballYVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballYDirection * minTime);
        ball.ballXDirection = -ball.ballXDirection;
        ballStatus.curr = Message.BALL_HIT_RIGHT_EDGE;
      } else if (ballCollisionSide.equals("-x")) {
        ball.ballYPosition += (ball.ballYVelocity * minTime)
                - (constantComponent * ball.ballYDirection * minTime * minTime);
        ball.ballXPosition = ball.ballRadius;
        ball.ballXVelocity = -(ball.ballXVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballXDirection * minTime));
        ball.ballYVelocity = ball.ballYVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballYDirection * minTime);
        ball.ballXDirection = -ball.ballXDirection;
        ballStatus.curr = Message.BALL_HIT_LEFT_EDGE;
      } else if (ballCollisionSide.equals("+y")) {
        ball.ballXPosition += (ball.ballXVelocity * minTime)
                - (constantComponent * ball.ballXDirection * minTime * minTime);
        ball.ballYPosition = tableHeight - ball.ballRadius;
        ball.ballXVelocity = ball.ballXVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballXDirection * minTime);
        ball.ballYVelocity = -(ball.ballYVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballYDirection * minTime));
        ball.ballYDirection = -ball.ballYDirection;
        ballStatus.curr = Message.BALL_HIT_TOP_EDGE;

      } else if (ballCollisionSide.equals("-y")) {
        ball.ballXPosition += (ball.ballXVelocity * minTime)
                - (constantComponent * ball.ballXDirection * minTime * minTime);
        ball.ballYPosition = ball.ballRadius;
        ball.ballXVelocity = ball.ballXVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballXDirection * minTime);
        ball.ballYVelocity = -(ball.ballYVelocity
                - (FRICTION_COEFFICIENT * 9.81 * ball.ballYDirection * minTime));
        ball.ballYDirection = -ball.ballYDirection;
        ballStatus.curr = Message.BALL_HIT_BOTTOM_EDGE;
      }
    } // if ball does not hit edges ie has stopped inbetween.
    else {
      ballStatus.curr = Message.BALL_IS_STATIONARY;
      if (ball.ballXDirection != 0) {
        ball.ballXPosition += (ball.ballXVelocity * ball.ballXVelocity)
                / (2 * FRICTION_COEFFICIENT * 9.81 * ball.ballXDirection);
      }
      if (ball.ballYDirection != 0) {
        ball.ballYPosition += (ball.ballYVelocity * ball.ballYVelocity)
                / (2 * FRICTION_COEFFICIENT * 9.81 * ball.ballYDirection);
      }
      ball.ballSpeed = 0;
      ball.ballXVelocity = 0;
      ball.ballYVelocity = 0;
    }
  }
}