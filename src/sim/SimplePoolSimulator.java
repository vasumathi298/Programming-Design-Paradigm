package sim;


/**
 * SimplePoolSimulator will implement all the functions defined in PoolSimulator.
 */

public class SimplePoolSimulator implements PoolSimulator {

  Table table;
  Ball ball;
  protected Status ballStatus = new Status();

  /**
   * Constructor to initialize Table for simulation.
   * @param tableWidth table's width.
   * @param tableHeight table's height.
   * @param tableType table's type.
   * @throws IllegalArgumentException if given invalid inputs, throw execption.
   */
  public SimplePoolSimulator(int tableWidth, int tableHeight, String tableType)
          throws IllegalArgumentException {

    if (tableWidth <= 0 || tableHeight <= 0) {
      throw new IllegalArgumentException("Invalid table width/height");
    }
    if (!("friction".equals(tableType) || "simple".equals(tableType))) {
      throw new IllegalArgumentException("Invalid table type");
    }

    if (tableType.equals("friction")) {
      table = new FrictionTable(tableWidth, tableHeight, tableType, ballStatus);
    } else {
      table = new SimpleTable(tableWidth, tableHeight, tableType, ballStatus);
    }

  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException {
    if (radius <= 0 || speed < 0
            || radius >= Math.min(table.tableHeight, table.tableWidth)) {
      throw new IllegalArgumentException("Invalid Radius or Speed");
    }

    if (table.tableWidth - radius < x || x < radius
            || y > table.tableHeight - radius || y < radius) {
      throw new IllegalArgumentException("Ball is outside the pool");
    }

    ball = new Ball(x, y, radius, speed, dx, dy);
    ballStatus.curr = Message.SIMULATION_STARTED;
  }

  @Override
  public void advance() throws IllegalStateException {
    if (ball == null) {
      throw new IllegalStateException("Ball is Not Setup.");
    } else {
      table.displaceBall(ball);
    }
  }


  @Override
  public int getTableWidth() throws IllegalStateException {
    if (table == null) {
      throw new IllegalStateException("Table for simulation is Not Setup.");
    } else {
      return table.tableWidth;
    }
  }

  @Override
  public int getTableHeight() {
    if (table == null) {
      throw new IllegalStateException("Table is has not been chosen yet!");
    } else {
      return table.tableHeight;
    }
  }


  @Override
  public double getBallPositionX() {
    if (ball == null) {
      return Double.NEGATIVE_INFINITY;
    } else {
      return ball.ballXPosition;
    }
  }

  @Override
  public double getBallPositionY() {
    if (ball == null) {
      return Double.NEGATIVE_INFINITY;
    } else {
      return ball.ballYPosition;
    }
  }

  @Override
  public double getBallRadius() {
    if (ball == null) {
      return Double.NEGATIVE_INFINITY;
    } else {
      return ball.ballRadius;
    }
  }

  @Override
  public double getBallVelocityX() {
    if (ball == null) {
      return 0.0;
    } else {
      return ball.ballXVelocity;
    }
  }

  @Override
  public double getBallVelocityY() {
    if (ball == null) {
      return 0;
    } else {
      return ball.ballYVelocity;
    }
  }

  @Override
  public String getStatus() {
    return "Status: " + ballStatus.getCurrenStatus().getMessage();
  }
}