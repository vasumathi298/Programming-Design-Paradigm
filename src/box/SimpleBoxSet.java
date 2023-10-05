package box;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Functions to add , subtract from the set of rectangles.
 */
public class SimpleBoxSet implements BoxSet {
  private Set<Rectangle> setOfRectangles;

  /**
   * Initialize set to store unique Rectangles.
   */
  public SimpleBoxSet() {
    setOfRectangles = new HashSet<>();
  }

  /**
   * Add a new rectangle to the set of rectangles, possibly splitting existing ones
   * to avoid overlap and ensure non-overlapping rectangles in the set.
   *
   * @param x      The x-coordinate of the new rectangle's top-left corner.
   * @param y      The y-coordinate of the new rectangle's top-left corner.
   * @param width  The width of the new rectangle.
   * @param height The height of the new rectangle.
   * @throws IllegalArgumentException if the width or height is not positive.
   */
  @Override
  public void add(int x, int y, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }

    Rectangle addedRectangle = new Rectangle(x, y, width, height);
    Set<Rectangle> newRectangles = new HashSet<>();

    for (Rectangle existingRectangle : setOfRectangles) {
      if (!existingRectangle.overlaps(addedRectangle)) {
        // If rectangles don't overlap, keep the existing rectangle
        newRectangles.add(existingRectangle);
      } else {
        // If rectangles overlap, subtract the added rectangle from the existing one
        newRectangles.addAll(existingRectangle.subtract(addedRectangle));
      }
    }

    // Add the added rectangle
    newRectangles.add(addedRectangle);

    setOfRectangles = newRectangles;
  }

  /**
   * Subtract a specified rectangle from the set of existing rectangles, possibly splitting
   * the existing rectangles to avoid overlap and ensure non-overlapping rectangles in the set.
   *
   * @param x      The x-coordinate of the rectangle to subtract's top-left corner.
   * @param y      The y-coordinate of the rectangle to subtract's top-left corner.
   * @param width  The width of the rectangle to subtract.
   * @param height The height of the rectangle to subtract.
   * @throws IllegalArgumentException if the width or height is not positive.
   */
  @Override
  public void subtract(int x, int y, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }

    Rectangle subtractedRectangle = new Rectangle(x, y, width, height);
    Set<Rectangle> newRectangles = new HashSet<>();

    for (Rectangle existingRectangle : setOfRectangles) {
      if (!existingRectangle.overlaps(subtractedRectangle)) {
        // If rectangles don't overlap, keep the existing rectangle
        newRectangles.add(existingRectangle);
      } else {
        // If rectangles overlap, subtract the subtracted rectangle from the existing one
        List<Rectangle> subtractions = existingRectangle.subtract(subtractedRectangle);
        newRectangles.addAll(subtractions);
      }
    }

    setOfRectangles = newRectangles;

  }


  /**
   * Get an array of integer arrays representing the rectangles in the current set.
   * Each sub-array contains four integers: x-coordinate, y-coordinate, width, and height.
   *
   * @return An array of integer arrays representing the rectangles.
   */
  @Override
  public int[][] getBoxes() {
    int[][] result = new int[setOfRectangles.size()][4];
    int i = 0;

    for (Rectangle rectangle : setOfRectangles) {
      result[i][0] = rectangle.getX();
      result[i][1] = rectangle.getY();
      result[i][2] = rectangle.getWidth();
      result[i][3] = rectangle.getHeight();
      i++;
    }

    return result;
  }

  /**
   * Get the number of rectangles currently stored in the set.
   *
   * @return The count of rectangles in the set.
   */
  @Override
  public int size() {
    return setOfRectangles.size();
  }
}
