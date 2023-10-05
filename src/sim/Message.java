package sim;

/**
 * Enum to persist all the status which will be tracked for a ball.
 */
public enum Message {

  BALL_NOT_SET_UP("Ball not set up"),
  SIMULATION_STARTED("Simulation started"),
  BALL_HIT_LEFT_EDGE("Ball hit left edge"),
  BALL_HIT_RIGHT_EDGE("Ball hit right edge"),
  BALL_HIT_TOP_EDGE("Ball hit top edge"),
  BALL_HIT_BOTTOM_EDGE("Ball hit bottom edge"),
  BALL_IS_STATIONARY("Ball is stationary");

  private final String message;

  /**
   * Set the code and Message if new status has to be added.
   * @param message corresponding message.
   */

  Message(String message) {
    this.message = message;
  }

  /**
   * This will get the message of the status.
   * @return status of the ball.
   */
  public String getMessage() {
    return message;
  }
}