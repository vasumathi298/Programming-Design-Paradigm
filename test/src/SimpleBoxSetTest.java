import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import box.SimpleBoxSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test cases to test all functions in assignment 1.
 */
public class SimpleBoxSetTest {
  private SimpleBoxSet box;

  @Before
  public void setUp() {
    box = new SimpleBoxSet();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeWidth() {
    box.add(0, 2, -23, 23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeHeight() {
    box.add(0, 2, 23, -23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeWidthAndHeight() {
    box.add(-0, -2, -23, -23);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleWithZeroWidth() {
    box.add(0, 0, 0, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleWithZeroHeight() {
    box.add(0, 0, 4, 0);
  }

  /**
   * Test case for overlap from lower right.
   * Will return three boxes below.
   * [1,2,2,3]
   * [3,0,3,3]
   * [3,3,2,2]
   */
  @Test
  public void testAddfromLowerRightOverlap() {
    SimpleBoxSet testLowerRightOverlap = new SimpleBoxSet();
    testLowerRightOverlap.add(1, 2, 4, 3);
    testLowerRightOverlap.add(3, 0, 3, 3);
    assertEquals(3, testLowerRightOverlap.size());
  }

  /**
   * Test case for overlap from right side.
   * Will return two boxes below.
   * [1,1,1,3]
   * [2,1,4,3]
   */
  @Test
  public void testAddfromRightOverlap() {
    SimpleBoxSet testRightOverlap = new SimpleBoxSet();
    testRightOverlap.add(1, 1, 3, 3);
    testRightOverlap.add(2, 1, 4, 3);
    assertEquals(2, testRightOverlap.size());
  }

  /**
   * Test case for overlap from top right side.
   * Will return two boxes below.
   * [3,2,2,2]
   * [2,1,1,2]
   * [3,1,1,1]
   */
  @Test
  public void testAddfromTopRightOverlap() {
    SimpleBoxSet testTopRightOverlap = new SimpleBoxSet();
    testTopRightOverlap.add(2, 1, 2, 2);
    testTopRightOverlap.add(3, 2, 2, 2);
    assertEquals(3, testTopRightOverlap.size());
  }

  /**
   * Test case for overlap from top.
   * Will return two boxes below
   * [2,2,2,3]
   * [2,1,2,1]
   */
  @Test
  public void testAddfromTopOverlap() {
    SimpleBoxSet testtopOverlap = new SimpleBoxSet();
    testtopOverlap.add(2, 1, 2, 2);
    testtopOverlap.add(2, 2, 2, 3);
    int[][] array = testtopOverlap.getBoxes();
    assertEquals(2, testtopOverlap.size());
  }

  /**
   * Test case for overlap from top left side.
   * Will return three boxes below.
   * [2,2,2,2]
   * [3,1,1,1]
   * [4,1,1,2]
   */
  @Test
  public void testAddfromTopLeftOverlap() {
    SimpleBoxSet testRightOverlap = new SimpleBoxSet();
    testRightOverlap.add(3, 1, 2, 2);
    testRightOverlap.add(2, 2, 2, 2);
    int[][] array = testRightOverlap.getBoxes();
    assertEquals(3, testRightOverlap.size());
  }

  /**
   * Test case for overlap from top left side.
   * Will return three boxes below.
   * [1,1,3,2]
   * [4,1,1,2]
   */
  @Test
  public void testAddfromLeftOverlap() {
    SimpleBoxSet testRightOverlap = new SimpleBoxSet();
    testRightOverlap.add(3, 1, 2, 2);
    testRightOverlap.add(1, 1, 3, 2);
    assertEquals(2, testRightOverlap.size());
  }

  /**
   * Test case for overlap from top left side.
   * Will return three boxes below.
   * [2,1,2,3]
   * [4,3,1,2]
   * [3,4,1,1]
   */
  @Test
  public void testAddfromLowerLeftOverlap() {
    SimpleBoxSet testRightOverlap = new SimpleBoxSet();
    testRightOverlap.add(3, 3, 2, 2);
    testRightOverlap.add(2, 1, 2, 3);
    assertEquals(3, testRightOverlap.size());
  }

  /**
   * Test case for overlap from top left side.
   * Will return three boxes below.
   * [2,2,3,2]
   * [2,4,3,1]
   */
  @Test
  public void testAddfromLowerOverlap() {
    SimpleBoxSet testRightOverlap = new SimpleBoxSet();
    testRightOverlap.add(2, 3, 3, 2);
    testRightOverlap.add(2, 2, 3, 2);
    assertEquals(2, testRightOverlap.size());
  }


  /**
   * Fully Contained in topLeft.
   * The below test case will return three rectangles.
   * [1,3,1,2]
   * [2,1,2,4]
   * [1,1,1,2]
   */
  @Test
  public void testAddFullyContainedInTopLeft() {
    SimpleBoxSet testFullyContainedInTopLeft = new SimpleBoxSet();
    testFullyContainedInTopLeft.add(1, 1, 3, 4);
    testFullyContainedInTopLeft.add(1, 3, 1, 2);
    assertEquals(3, testFullyContainedInTopLeft.size());
  }

  /**
   * Fully Contained in topRight.
   * The below test case will return three rectangles.
   * [2,4,2,1]
   * [2,1,2,3]
   * [1,1,1,4]
   */
  @Test
  public void testAddFullyContainedInTopRight() {
    SimpleBoxSet testFullyContainedInTopRight = new SimpleBoxSet();
    testFullyContainedInTopRight.add(1, 1, 3, 4);
    testFullyContainedInTopRight.add(2, 4, 2, 1);
    int[][] array = testFullyContainedInTopRight.getBoxes();
    assertEquals(3, testFullyContainedInTopRight.size());
  }

  /**
   * Fully Contained in BottomLeft.
   * The below test case will return three rectangles.
   * [2,1,2,4]
   * [1,3,1,2]
   * [1,1,1,2]
   */
  @Test
  public void testAddFullyContainedInBottomLeft() {
    SimpleBoxSet testFullyContainedInBottomLeft = new SimpleBoxSet();
    testFullyContainedInBottomLeft.add(1, 1, 3, 4);
    testFullyContainedInBottomLeft.add(1, 1, 1, 2);
    assertEquals(3, testFullyContainedInBottomLeft.size());
  }

  /**
   * Fully Contained in BottomRight.
   * The below test case will return three rectangles.
   * [2,4,2,1]
   * [1,1,1,4]
   * [2,1,2,3]
   */
  @Test
  public void testAddFullyContainedInBottomRight() {
    SimpleBoxSet testFullyContainedInBottomRight = new SimpleBoxSet();
    testFullyContainedInBottomRight.add(1, 1, 3, 4);
    testFullyContainedInBottomRight.add(2, 1, 2, 3);
    assertEquals(3, testFullyContainedInBottomRight.size());
  }

  /**
   * Fully Contained in FullyContainedInMiddle.
   * The below test case will return three rectangles.
   * [2,4,2,2]
   * [1,1,1,5]
   * [2,2,2,2]
   * [4,1,1,5]
   * [2,1,2,1]
   */
  @Test
  public void testAddFullyContainedInMiddle() {
    SimpleBoxSet testAddFullyContainedInMiddle = new SimpleBoxSet();
    testAddFullyContainedInMiddle.add(1, 1, 4, 5);
    testAddFullyContainedInMiddle.add(2, 2, 2, 2);
    assertEquals(5, testAddFullyContainedInMiddle.size());
  }


  /**
   * TouchRightSide.
   * The below test case will return three rectangles.
   * [4,1,3,3]
   * [1,1,3,3]
   */
  @Test
  public void testAddTouchRightSide() {
    SimpleBoxSet testAddTouchRightSide = new SimpleBoxSet();
    testAddTouchRightSide.add(1, 1, 3, 3);
    testAddTouchRightSide.add(4, 1, 3, 3);
    assertEquals(2, testAddTouchRightSide.size());
  }

  /**
   * TouchLeftSide.
   * The below test case will return three rectangles.
   * [4,1,3,3]
   * [1,1,3,3]
   */
  @Test
  public void testAddTouchLeftSide() {
    SimpleBoxSet testAddTouchLeftSide = new SimpleBoxSet();
    testAddTouchLeftSide.add(4, 1, 3, 3);
    testAddTouchLeftSide.add(1, 1, 3, 3);
    assertEquals(2, testAddTouchLeftSide.size());
  }

  /**
   * TouchTopSide.
   * The below test case will return three rectangles.
   * [1,1,2,2]
   * [1,3,2,3]
   */
  @Test
  public void testAddTouchTopSide() {
    SimpleBoxSet testAddTouchTopSide = new SimpleBoxSet();
    testAddTouchTopSide.add(1, 1, 2, 2);
    testAddTouchTopSide.add(1, 3, 2, 2);
    assertEquals(2, testAddTouchTopSide.size());
  }

  /**
   * TouchBottomSide.
   * The below test case will return three rectangles.
   * [1,1,2,2]
   * [1,3,2,3]
   */
  @Test
  public void testAddTouchBottomSide() {
    SimpleBoxSet testAddTouchBottomSide = new SimpleBoxSet();
    testAddTouchBottomSide.add(1, 3, 2, 2);
    testAddTouchBottomSide.add(1, 1, 2, 2);
    assertEquals(2, testAddTouchBottomSide.size());
  }

  /**
   * BiggerRectangleFromBottomSide.
   * The below test case will return three rectangles.
   * [1,1,4,3]
   * [2,4,2,1]
   */
  @Test
  public void testAddBiggerRectangleFromBottomSide() {
    SimpleBoxSet biggerRectangleFromBottomSide = new SimpleBoxSet();
    biggerRectangleFromBottomSide.add(2, 3, 2, 2);
    biggerRectangleFromBottomSide.add(1, 1, 4, 3);
    assertEquals(2, biggerRectangleFromBottomSide.size());
  }

  /**
   * BiggerRectangleFromTopSide.
   * The below test case will return three rectangles.
   * [1,2,5,4]
   * [1,1,3,1]
   */
  @Test
  public void testAddBiggerRectangleFromTopSide() {
    SimpleBoxSet biggerRectangleFromTopSide = new SimpleBoxSet();
    biggerRectangleFromTopSide.add(1, 1, 3, 2);
    biggerRectangleFromTopSide.add(1, 2, 5, 4);
    assertEquals(2, biggerRectangleFromTopSide.size());
  }

  /**
   * BiggerRectangleFromLeftSide.
   * The below test case will return three rectangles.
   * [4,2,1,1]
   * [2,1,2,3]
   */
  @Test
  public void testAddBiggerRectangleFromLefttSide() {
    SimpleBoxSet biggerRectangleFromLeftSide = new SimpleBoxSet();
    biggerRectangleFromLeftSide.add(3, 2, 2, 1);
    biggerRectangleFromLeftSide.add(2, 1, 2, 3);
    assertEquals(2, biggerRectangleFromLeftSide.size());
  }

  /**
   * Add Rectangle in Negative Plane.
   */
  @Test
  public void testAddInNegativePlane() {
    SimpleBoxSet addRectanglesInNegativePlane = new SimpleBoxSet();
    addRectanglesInNegativePlane.add(-4, -5, 3, 3);
    addRectanglesInNegativePlane.add(-3, -4, 1, 1);
    assertEquals(5, addRectanglesInNegativePlane.size());
  }

  @Test
  public void testAddWithoutOverlap() {
    SimpleBoxSet addWithoutOverlap = new SimpleBoxSet();
    addWithoutOverlap.add(1, 1, 3, 2);
    addWithoutOverlap.add(5, 1, 2, 2);
    assertEquals(2, addWithoutOverlap.size());
  }

  @Test
  public void testSubtractFromLowerLeft() {
    SimpleBoxSet subtractFromLowerLeft = new SimpleBoxSet();
    subtractFromLowerLeft.add(1, 2, 4, 3);
    subtractFromLowerLeft.subtract(3, 0, 3, 3);
    assertEquals(2, subtractFromLowerLeft.size());
  }

  @Test
  public void testSubtractFromRight() {
    SimpleBoxSet subtractFromRight = new SimpleBoxSet();
    subtractFromRight.add(1, 1, 3, 3);
    subtractFromRight.subtract(2, 1, 4, 3);
    assertEquals(1, subtractFromRight.size());
  }

  @Test
  public void testSubtractFromTopRight() {
    SimpleBoxSet subtractFromTopRight = new SimpleBoxSet();
    subtractFromTopRight.add(2, 1, 2, 2);
    subtractFromTopRight.subtract(3, 2, 2, 2);
    assertEquals(2, subtractFromTopRight.size());
  }

  @Test
  public void testSubtractFromTop() {
    SimpleBoxSet subtractFromBottom = new SimpleBoxSet();
    subtractFromBottom.add(2, 1, 2, 2);
    subtractFromBottom.subtract(2, 2, 2, 3);
    assertEquals(1, subtractFromBottom.size());
  }

  @Test
  public void testSubtractFromBottomRight() {
    SimpleBoxSet subtractFromBottomRight = new SimpleBoxSet();
    subtractFromBottomRight.add(3, 1, 2, 2);
    subtractFromBottomRight.subtract(2, 2, 2, 2);
    assertEquals(2, subtractFromBottomRight.size());
  }

  @Test
  public void testSubtractFromLeft() {
    SimpleBoxSet subtractFromLeft = new SimpleBoxSet();
    subtractFromLeft.add(3, 1, 2, 2);
    subtractFromLeft.subtract(1, 1, 3, 2);
    assertEquals(1, subtractFromLeft.size());
  }

  @Test
  public void testSubtractFromBottomLeft() {
    SimpleBoxSet sutractFromBottomLeft = new SimpleBoxSet();
    sutractFromBottomLeft.add(3, 3, 2, 2);
    sutractFromBottomLeft.subtract(2, 1, 2, 3);
    assertEquals(2, sutractFromBottomLeft.size());
  }

  @Test
  public void testSubtractFromBottom() {
    SimpleBoxSet subtractFromBottom = new SimpleBoxSet();
    subtractFromBottom.add(2, 3, 3, 2);
    subtractFromBottom.subtract(2, 2, 3, 2);
    assertEquals(1, subtractFromBottom.size());
  }

  @Test
  public void testSubtractContainsInTopLeft() {
    SimpleBoxSet subtractFromContainsTopLeft = new SimpleBoxSet();
    subtractFromContainsTopLeft.add(1, 1, 3, 4);
    subtractFromContainsTopLeft.subtract(1, 3, 1, 2);
    assertEquals(2, subtractFromContainsTopLeft.size());
  }

  @Test
  public void testSubtractContainsTopRight() {
    SimpleBoxSet subtractFromContainsTopRight = new SimpleBoxSet();
    subtractFromContainsTopRight.add(1, 1, 3, 4);
    subtractFromContainsTopRight.subtract(2, 4, 2, 1);
    assertEquals(2, subtractFromContainsTopRight.size());
  }

  @Test
  public void testSubtractContainsBottomLeft() {
    SimpleBoxSet subtractFromBottomLeft = new SimpleBoxSet();
    subtractFromBottomLeft.add(1, 1, 3, 4);
    subtractFromBottomLeft.subtract(1, 1, 1, 2);
    assertEquals(2, subtractFromBottomLeft.size());
  }

  @Test
  public void testSubtractContainsBottomRight() {
    SimpleBoxSet subtractFromBottomRight = new SimpleBoxSet();
    subtractFromBottomRight.add(1, 1, 3, 4);
    subtractFromBottomRight.subtract(2, 1, 2, 3);
    assertEquals(2, subtractFromBottomRight.size());
  }

  @Test
  public void testSubtractFullyContains() {
    SimpleBoxSet subtractFullyContains = new SimpleBoxSet();
    subtractFullyContains.add(1, 1, 4, 5);
    subtractFullyContains.subtract(2, 2, 2, 2);
    assertEquals(4, subtractFullyContains.size());
  }

  @Test
  public void testSubtractTouchRight() {
    SimpleBoxSet subtractTouchRight = new SimpleBoxSet();
    subtractTouchRight.add(1, 1, 3, 3);
    subtractTouchRight.subtract(4, 1, 3, 3);
    assertEquals(1, subtractTouchRight.size());
  }

  @Test
  public void testTouchLeft() {
    SimpleBoxSet subtractTouchLeft = new SimpleBoxSet();
    subtractTouchLeft.add(4, 1, 3, 3);
    subtractTouchLeft.subtract(1, 1, 3, 3);
    assertEquals(1, subtractTouchLeft.size());
  }

  @Test
  public void testSubtractTouchTop() {
    SimpleBoxSet subtractTouchTop = new SimpleBoxSet();
    subtractTouchTop.add(1, 1, 2, 2);
    subtractTouchTop.subtract(1, 3, 2, 2);
    assertEquals(1, subtractTouchTop.size());
  }

  @Test
  public void testSubtractTouchBottom() {
    SimpleBoxSet subtractTouchBottom = new SimpleBoxSet();
    subtractTouchBottom.add(1, 3, 2, 2);
    subtractTouchBottom.subtract(1, 1, 2, 2);
    assertEquals(1, subtractTouchBottom.size());
  }

  @Test
  public void testSubtractSmallerRectangleFromTop() {
    SimpleBoxSet subtractBigRectBottom = new SimpleBoxSet();
    subtractBigRectBottom.add(1, 1, 4, 3);
    subtractBigRectBottom.subtract(2, 3, 2, 2);
    assertEquals(3, subtractBigRectBottom.size());
  }

  @Test
  public void testSubtractSmallerRectangleFromBottom() {
    SimpleBoxSet subtractBigRectBottom = new SimpleBoxSet();
    subtractBigRectBottom.add(1, 2, 5, 4);
    subtractBigRectBottom.subtract(2, 1, 3, 2);
    assertEquals(3, subtractBigRectBottom.size());
  }

  @Test
  public void testSubtractSmallerRectangleFromLeft() {
    SimpleBoxSet subtractSmallFromLeft = new SimpleBoxSet();
    subtractSmallFromLeft.add(2, 1, 3, 4);
    subtractSmallFromLeft.subtract(1, 2, 2, 2);
    assertEquals(3, subtractSmallFromLeft.size());
  }

  @Test
  public void testSubtractFullyContainedInNegativePlane() {
    SimpleBoxSet fullyContainedNeg = new SimpleBoxSet();
    fullyContainedNeg.add(-4, -5, 3, 3);
    fullyContainedNeg.subtract(-3, -4, 1, 1);
    assertEquals(4, fullyContainedNeg.size());
  }

  @Test
  public void testSubtractWithoutOverlap() {
    SimpleBoxSet subWithoutOverlap = new SimpleBoxSet();
    subWithoutOverlap.add(1, 1, 3, 2);
    subWithoutOverlap.subtract(5, 1, 2, 2);
    assertEquals(1, subWithoutOverlap.size());
  }

  @Test
  public void testAddSameRectangle() {
    SimpleBoxSet addSameRectangle = new SimpleBoxSet();
    addSameRectangle.add(1, 2, 3, 2);
    addSameRectangle.add(1, 2, 3, 2);
    assertEquals(1, addSameRectangle.size());
  }

  @Test
  public void testZeroSet() {
    SimpleBoxSet testZeroSet = new SimpleBoxSet();
    testZeroSet.add(1, 2, 3, 2);
    testZeroSet.subtract(1, 2, 3, 2);
    assertEquals(0, testZeroSet.size());
  }

  @Test
  public void testAddInNegativePositivePlane() {
    SimpleBoxSet sample = new SimpleBoxSet();
    sample.add(0, 0, 4, 3);
    sample.add(-2, -1, 4, 2);

    assertEquals(3, sample.size());
  }

  @Test
  public void testSubtractInNegativePositivePlane() {
    SimpleBoxSet sample = new SimpleBoxSet();
    sample.add(0, 0, 4, 3);
    sample.subtract(-2, -1, 4, 2);
    assertEquals(2, sample.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddOrSubtractInvalidRectangles() {
    SimpleBoxSet boxSet = new SimpleBoxSet();
    boxSet.add(-2, -4, 0, -4);
    boxSet.add(2, -1, -10, 3);
    boxSet.add(-2, -1, 9, 0);
    boxSet.subtract(-9, 3, -9, 6);
    boxSet.subtract(5, 3, 2, -8);
    boxSet.subtract(8, 9, 0, 5);
  }

  @Test
  public void testSizeOfRectangleSet() {
    SimpleBoxSet testSizeBoxSet = new SimpleBoxSet();
    testSizeBoxSet.add(1, 1, 4, 5);
    assertEquals(1, testSizeBoxSet.size());
    testSizeBoxSet.add(3, 4, 3, 3);
    assertEquals(3, testSizeBoxSet.size());
    testSizeBoxSet.subtract(2, 3, 3, 3);
    assertEquals(5, testSizeBoxSet.size());
    testSizeBoxSet.subtract(2, 2, 3, 2);
    assertEquals(5, testSizeBoxSet.size());
    testSizeBoxSet.add(2, 2, 3, 4);
    assertEquals(6, testSizeBoxSet.size());
  }

  @Test
  public void testOverlappingMultipleAddBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(1, 1, 9, 9);
    simpleBoxSet.add(1, 1, 3, 3);
    simpleBoxSet.add(7, 0, 4, 5);
    simpleBoxSet.add(10, 0, 2, 11);
    simpleBoxSet.add(1, 6, 11, 4);
    assertEquals(8, simpleBoxSet.size());
  }

  @Test
  public void testOverlappingSubMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(1, 1, 11, 11);
    simpleBoxSet.subtract(1, 1, 4, 4);
    simpleBoxSet.subtract(7, 1, 4, 5);
    simpleBoxSet.add(10, 1, 2, 11);
    simpleBoxSet.subtract(1, 6, 11, 4);
    assertEquals(7, simpleBoxSet.size());
  }

  @Test
  public void testAddAndSubtractOperations() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(12, 0, 5, 5);
    simpleBoxSet.add(20, 10, 8, 8);
    simpleBoxSet.subtract(5, 5, 7, 7);
    simpleBoxSet.subtract(22, 12, 6, 6);

    simpleBoxSet.add(15, 15, 5, 5);

    int[][] actual = simpleBoxSet.getBoxes();
    int[][] expected = {
            {5, 0, 5, 5},
            {20, 10, 2, 8},
            {15, 15, 5, 5},
            {22, 10, 6, 2},
            {0, 0, 5, 10},
            {12, 0, 5, 5}
    };

    for (int i = 0; i < expected.length; i++) {
      Arrays.sort(expected[i]);
      Arrays.sort(actual[i]);
    }
    Arrays.sort(expected, (a, b) -> Arrays.compare(a, b));
    Arrays.sort(actual, (a, b) -> Arrays.compare(a, b));
    assertArrayEquals(expected, actual);
  }

  @Test
  public void testDummy(){
    SimpleBoxSet s= new SimpleBoxSet();
    s.add(0,0,20,10);
    s.subtract(-22,-22,23,23);
    assertEquals(s.size(),2);

  }
}
