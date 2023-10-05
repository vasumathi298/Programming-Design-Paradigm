package box;

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class with member variables and other utility functions.
 */
public class Rectangle {
  private final int x;
  private final int y;
  private final int width;
  private final int height;

  /**
   * Creates a new rectangle with lower left x, lower left y, width , height.
   * Throws exception if width and height is less than or equal to zero.
   *
   * @param x      lower left x coordinate rectangles.
   * @param y      lower left y coordinate rectangles.
   * @param width  width of the rectangle.
   * @param height height of the rectangle.
   */
  public Rectangle(int x, int y, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive.");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Get lower left's x coordinate.
   *
   * @return left lower x coordinate of rectangle.
   */
  public int getX() {
    return x;
  }

  /**
   * Get lower left's y coordinate.
   *
   * @return left lower y coordinate of rectangle.
   */
  public int getY() {
    return y;
  }

  /**
   * Get width of the rectangle.
   *
   * @return height of rectangle.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get height of the rectangle.
   *
   * @return height of rectangle.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Checks if this rectangle overlaps with another rectangle.
   *
   * @param other The rectangle to check for overlap with.
   * @return True if there is any overlap otherwise retuns false.
   */
  boolean overlaps(Rectangle other) {
    return this.x < other.x + other.width && this.x + this.width > other.x
            && this.y < other.y + other.height && this.y + this.height > other.y;
  }


  /**
   * Subtract a given rectangle from this rectangle and return a list of resulting rectangles.
   *
   * @param subtracted The rectangle to subtract from this rectangle.
   * @return list of rectangles after subraction.
   */
  List<Rectangle> subtract(Rectangle subtracted) {
    List<Rectangle> result = new ArrayList<>();

    // Check if the rectangles completely overlap
    if (this.equals(subtracted)) {
      // If they are identical, there's nothing left after subtraction
      return result;
    }

    if (this.x < subtracted.x) {
      // Add the left part of the original rectangle
      result.add(new Rectangle(this.x, this.y, subtracted.x - this.x, this.height));
    }

    if (this.x + this.width > subtracted.x + subtracted.width) {
      // Add the right part of the original rectangle
      result.add(new Rectangle(subtracted.x + subtracted.width,
              this.y, this.x + this.width -
              (subtracted.x + subtracted.width), this.height));
    }

    if (this.y < subtracted.y) {
      // Add the upper part of the original rectangle
      result.add(new Rectangle(Math.max(this.x, subtracted.x),
              this.y, Math.min(this.x + this.width,
              subtracted.x + subtracted.width) -
              Math.max(this.x, subtracted.x),
              subtracted.y - this.y));
    }

    if (this.y + this.height > subtracted.y + subtracted.height) {
      // Add the lower part of the original rectangle
      result.add(new Rectangle(Math.max(this.x, subtracted.x), subtracted.y
              + subtracted.height,
              Math.min(this.x + this.width, subtracted.x + subtracted.width)
                      - Math.max(this.x, subtracted.x), this.y + this.height
              - (subtracted.y + subtracted.height)));
    }

    return result;
  }

}
