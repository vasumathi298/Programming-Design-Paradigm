package box;

/**
 * This interface represents a set of non-overlapping axis-aligned boxes.
 */
public interface BoxSet {


  /**
   * Add a given rectangle to this set, and make this set the result.
   *
   * @param x      lower left x coordinate.
   * @param y      lower right y coordinate.
   * @param width  width of the rectangle.
   * @param height height of the rectangle.
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  void add(int x, int y, int width, int height) throws IllegalArgumentException;

  /* *
   * Subtract the given rectangle element from set if they overlap.
   * @param x lower left x coordinate.
   * @param y lower right x coordinate.
   * @param width width of the rectangle.
   * @param height height of the rectangle.
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  void subtract(int x, int y, int width, int height) throws
          IllegalArgumentException;

  /**
   * Get all the rectangles in this set.
   *
   * @return an array with each element representing a Rectangle.
   */
  int[][] getBoxes();

  /**
   * Returns size of the set.
   *
   * @return size of set.
   */
  int size();
}
