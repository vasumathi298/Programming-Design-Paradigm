package sim;

/**
 * Ball class is to signify the Ball's property
 * with respect to the below
 * position (x,y)
 * speed s,
 * radius r,
 * velocity (v_x,v_y),
 * direction (d_x, d_y).
 */
public class Ball {

  public double ballXPosition;
  public double ballYPosition;
  protected double ballXVelocity;
  protected double ballYVelocity;
  public double ballRadius;
  public double ballSpeed;
  public double ballXDirection;
  public double ballYDirection;


  /**
   * Constructor to create a ball with certain attributes.
   *
   * @param ballXPosition  x coordinate of the ball's center in table.
   * @param ballYPosition  y coordinate of the ball's center in table.
   * @param ballRadius     radius of the ball.
   * @param ballSpeed      speed at which ball is moving.
   * @param ballXDirection x direction in which the ball is moving.
   * @param ballYDirection y direction in which the ball is moving.
   */
  public Ball(double ballXPosition, double ballYPosition, double ballRadius,
              double ballSpeed, double ballXDirection, double ballYDirection) {

    this.ballXPosition = ballXPosition;
    this.ballYPosition = ballYPosition;
    this.ballRadius = ballRadius;
    this.ballSpeed = ballSpeed;
    if (this.ballSpeed == 0) {
      this.ballXVelocity = 0.0;
      this.ballYVelocity = 0.0;
    } else {
      double normalizeMagnitude = Math.sqrt(ballXDirection * ballXDirection
              + ballYDirection * ballYDirection);

      this.ballXDirection = (normalizeMagnitude != 0) ? ballXDirection / normalizeMagnitude : 0.0;
      this.ballYDirection = (normalizeMagnitude != 0) ? ballYDirection / normalizeMagnitude : 0.0;

      this.ballXVelocity = ballSpeed * this.ballXDirection;
      this.ballYVelocity = ballSpeed * this.ballYDirection;
    }
  }

  public double getBallXPosition() {
    return ballXPosition;
  }

  public double getBallYPosition() {
    return ballYPosition;
  }

  public double getBallXVelocity() {
    return ballXVelocity;
  }

  public double getBallYVelocity() {
    return ballYVelocity;
  }

  public double getBallRadius() {
    return ballRadius;
  }

  public double getBallSpeed() {
    return ballSpeed;
  }

  public double getBallXDirection() {
    return ballXDirection;
  }

  public double getBallYDirection() {
    return ballYDirection;
  }

}