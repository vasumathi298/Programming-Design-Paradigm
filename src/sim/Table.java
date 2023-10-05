package sim;

import java.util.HashMap;

/**
 * Table is to define the table used for simulation using few attributes.
 */
public abstract class Table {
  protected int tableWidth;
  protected int tableHeight;
  protected String tableType;

  protected HashMap<String, Double> collisionTime = new HashMap<>();

  Status ballStatus;


  /**
   *  Constructor to set the table's width, height, ball status in that table and its type.
   * @param tableWidth width of the table.
   * @param tableHeight height of the table.
   * @param ballStatus status of the ball in simulation.
   * @param tableType type of the table.
   */
  public Table(int tableWidth, int tableHeight, Status ballStatus, String tableType) {
    this.tableType = tableType;
    this.tableWidth = tableWidth;
    this.tableHeight = tableHeight;
    this.ballStatus = ballStatus;
  }

  /**
   * Displace the ball by one step and calculate new x, y positions and velocity.
   * @param ball ball in simulation.
   */
  protected abstract void displaceBall(Ball ball);

  /**
   * Get Table Type simple or friction.
   * @return  table type.
   */
  public String getTableType() {
    return tableType;
  }

  /**
   * Get Table width.
   * @return table's width.
   */
  public int getTableWidth() {
    return tableWidth;
  }

  /**
   * Get Table height.
   * @return table's height.
   */
  public int getTableHeight() {
    return tableHeight;
  }

  /**
   * Get ball's status in the table.
   * @return ball's status in the table.
   */
  public Status getStatus() {
    return ballStatus;
  }
}