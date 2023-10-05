package sim;

/**
 *  PoolSimulator, an interface to simulate the ball's movement inside the table.
 *  Certain functionalities like's ball's advancement and ball's status are implemented here.
 *  Before all that, it also will have a function definition to start the ball.
 */
public interface PoolSimulator {

  /**
   * Start method will initiate the ball's movement by calculating / setting
   * the ball's initial displacement, velocity, speed and position.
   * @param x x coordinate of the ball's center in table.
   * @param y y coordinate of the ball's center in table.
   * @param radius  radius of the ball
   * @param speed speed at which ball is moving.
   * @param dx x direction in which the ball is moving.
   * @param dy y direction in which the ball is moving.
   * @throws IllegalArgumentException Throws exceptions if invalid inputs are fed.
   */
  void start(int x,int y,int radius,int speed,double dx,double dy) throws IllegalArgumentException;

  /**
   * Advance method will create a displacement for the ball in a table
   * based on the type of the table (simple or friction).
   */
  void advance();

  /**
   * Function below will get the width of a table in simulation.
   * @return width of the table.
   */
  int getTableWidth();

  /**
   * Function below will get the width of a table in simulation.
   * @return height of the table.
   */
  int getTableHeight();

  /**
   * Function below will get x-coordinate of ball's center in simulation.
   * @return X-Position of the ball.
   */
  double getBallPositionX();

  /**
   * Function below will get y-coordinate of ball's center in simulation.
   * @return Y-Position of the ball.
   */
  double getBallPositionY();

  /**
   * Function below will get ball's radius in simulation.
   * @return radius of the ball.
   */
  double getBallRadius();

  /**
   * Function below will get x-coordinate of ball's velocity in simulation.
   * @return X-Velocity of the ball.
   */
  double getBallVelocityX();

  /**
   * Function below will get y-coordinate of ball's center in simulation.
   * @return Y-Position of the ball.
   */
  double getBallVelocityY();

  /**
   * Function below will get present status of ball in simulation.
   * @return status of the ball during simulation.
   */
  String getStatus();
}