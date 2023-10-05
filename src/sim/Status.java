package sim;

/**
 *  Status, a class to track the current status of the ball.
 *  Track if ball has hit all the edges, or stationery.
 */
public class Status {
  protected Message curr;

  /**
   * Constructor will set the initial status as Not Setup.
   */
  public Status() {
    // Initialize currentStatus to a default value
    this.curr = Message.BALL_NOT_SET_UP;
  }

  /**
   * Method to get the current status of ball in simulation.
   * @return status of the ball.
   */
  public Message getCurrenStatus() {
    return this.curr;
  }
}