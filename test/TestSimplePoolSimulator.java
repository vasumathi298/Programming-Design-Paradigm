import org.junit.Test;

import sim.PoolSimulator;
import sim.SimplePoolSimulator;

import static org.junit.Assert.assertEquals;

/**
 * This class will test the behaviours of the simple pool simulation.
 */

public class TestSimplePoolSimulator {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTableWidth() {
    PoolSimulator invalidTableWidth = new SimplePoolSimulator(-400, 70, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTableHeight() {
    PoolSimulator invalidTableHeight = new SimplePoolSimulator(400, -70, "friction");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroTableWidth() {
    PoolSimulator invalidTableWidth = new SimplePoolSimulator(0, 70, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroTableHeight() {
    PoolSimulator invalidTableWidth = new SimplePoolSimulator(90, 0, "friction");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTableType() {
    PoolSimulator invalidTableType = new SimplePoolSimulator(80, 80, "Simulation Table");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRadius() {
    PoolSimulator invalidTableType = new SimplePoolSimulator(80, 80, "simple");
    invalidTableType.start(50, 50, -9, 20, 0.98, 0.98);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTable() {
    PoolSimulator invalidTable = new SimplePoolSimulator(0, 0, "Sample");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroRadius() {
    PoolSimulator invalid = new SimplePoolSimulator(80, 80, "simple");
    invalid.start(50, 50, 0, 20, 0.98, 0.98);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroRadiusGreaterThanWidth() {
    PoolSimulator invalid = new SimplePoolSimulator(80, 80, "simple");
    invalid.start(50, 50, 90, 20, 0.98, 0.98);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroRadiusGreaterThanHeight() {
    PoolSimulator invalid = new SimplePoolSimulator(80, 60, "friction");
    invalid.start(50, 50, 90, 20, 0.98, 0.98);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSpeed() {
    PoolSimulator invalid = new SimplePoolSimulator(80, 60, "friction");
    invalid.start(50, 50, 90, -8, 0.98, 0.98);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidXposition() {
    PoolSimulator invalidBallRadiusInX = new SimplePoolSimulator(10, 10, "simple");
    invalidBallRadiusInX.start(4, 5, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidYPosition() {
    PoolSimulator invalidBallRadiusInY = new SimplePoolSimulator(10, 10, "friction");
    invalidBallRadiusInY.start(5, 4, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBallOutsidePoolXCord() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(14, 5, 1, 5, -3, -2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testBallOutsidePoolYCord() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(8, 14, 1, 5, -3, -2);
  }

  @Test(expected = IllegalStateException.class)
  public void testBallAdvanceBeforeStart() throws IllegalStateException {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.advance();
  }

  @Test
  public void testBallThatFitsTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(5, 5, 5, 10, -5, -5);
    pool.advance();
    pool.advance();
    assertEquals("Status: Ball is stationary", pool.getStatus());
  }

  @Test
  public void testValidBallInsideTable() {
    PoolSimulator validBallInside = new SimplePoolSimulator(10, 10, "simple");
    validBallInside.start(5, 5, 1, 5, -3, -2);
    assertEquals(-4.160251471689219, validBallInside.getBallVelocityX(), 0.01);
    assertEquals(-2.773500981126146, validBallInside.getBallVelocityY(), 0.01);
    assertEquals("Status: Simulation started", validBallInside.getStatus());
  }

  @Test
  public void testBeforeStartInitialState() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallPositionX(), 0.01);
    assertEquals(0.0, pool.getBallVelocityX(), 0.01);
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallPositionY(), 0.01);
    assertEquals(0.0, pool.getBallVelocityY(), 0.01);
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallRadius(), 0.01);
  }

  @Test
  public void testOneAdvanceSingleTypeRightEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "simple");
    oneAdvanceS.start(5, 6, 2, 17, 3, 2);
    oneAdvanceS.advance();
    assertEquals(8, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(8, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(-9.984603532054125, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(6.65640235470275, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceSingleTypeLeftEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "simple");
    oneAdvanceS.start(3, 4, 2, 17, -3, 4);
    oneAdvanceS.advance();
    assertEquals(2, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(5.333333333333334, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(7.199999999999999, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(9.600000000000001, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceSingleTypeTopEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "simple");
    oneAdvanceS.start(4, 7, 2, 17, 3, 4);
    oneAdvanceS.advance();
    assertEquals(4.75, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(8.0, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(7.199999999999999, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(-9.600000000000001, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceSingleTypeBottomEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "simple");
    oneAdvanceS.start(3, 3, 2, 17, 3, -4);
    oneAdvanceS.advance();
    assertEquals(3.75, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(2.0, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(7.199999999999999, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(9.600000000000001, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceFrictionTypeRightEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "friction");
    oneAdvanceS.start(5, 6, 2, 17, 3, 2);
    oneAdvanceS.advance();
    assertEquals(8, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(8, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(-13.970664803238625, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(9.313776535492416, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceFrictionTypeLeftEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "friction");
    oneAdvanceS.start(3, 4, 2, 17, -3, 4);
    oneAdvanceS.advance();
    assertEquals(2, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(5.333333333333334, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(10.142129953811477, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(13.522839938415304, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceFrictionTypeTopEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "friction");
    oneAdvanceS.start(4, 7, 2, 17, 3, 4);
    oneAdvanceS.advance();
    assertEquals(4.75, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(8.0, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(10.156628377567035, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(-13.542171170089382, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", oneAdvanceS.getStatus());
  }

  @Test
  public void testOneAdvanceFrictionTypeBottomEdge() {
    PoolSimulator oneAdvanceS = new SimplePoolSimulator(10, 10, "friction");
    oneAdvanceS.start(3, 3, 2, 17, 3, -4);
    oneAdvanceS.advance();
    assertEquals(3.75, oneAdvanceS.getBallPositionX(), 0.01);
    assertEquals(2.0, oneAdvanceS.getBallPositionY(), 0.01);
    assertEquals(10.156628377567035, oneAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(13.542171170089382, oneAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", oneAdvanceS.getStatus());
  }

  /**
   * Multiple Advance for Single and Friction Type table.
   */

  @Test
  public void testMultipleAdvanceSingleTypeRightEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(10, 10, "simple");
    multipleAdvanceS.start(2, 6, 2, 17, 2, 3);
    for (int i = 1; i <= 3; i++) {
      multipleAdvanceS.advance();
      System.out.println(multipleAdvanceS.getStatus());
    }
    assertEquals(8.0, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(3.0, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(-1.1094003924504583, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(1.6641005886756874, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceFrictionTypeRightEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(10, 10, "friction");
    multipleAdvanceS.start(2, 6, 2, 17, 2, 3);
    for (int i = 1; i <= 3; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(8.0, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(3.0, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(-9.077067049059048, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(13.615600573588573, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit right edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceSingleTypeLeftEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(20, 20, "simple");
    multipleAdvanceS.start(7, 2, 2, 30, -6, 6);
    for (int i = 1; i <= 5; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(2.0, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(7.0, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(3.5355339059327378, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(3.5355339059327378, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceFrictionTypeLeftEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(20, 20, "friction");
    multipleAdvanceS.start(7, 2, 2, 30, -6, 6);
    for (int i = 1; i <= 5; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(2.0, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(7.0, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(19.966679501773545, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(19.966679501773545, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit left edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceSingleTypeTopEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(20, 20, "simple");
    multipleAdvanceS.start(6, 6, 2, 70, 6, 6);
    for (int i = 1; i <= 6; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(18.0, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(18.0, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(-28.284271247461902, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(-28.284271247461902, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceAdvanceTypeTopEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(20, 20, "friction");
    multipleAdvanceS.start(5, 7, 2, 70, 6, 6);
    for (int i = 1; i <= 5; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(16.000000000000302, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(18, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(48.89114673740447, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(-48.89114673740447, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit top edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceSingleTypeBottomEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(20, 20, "simple");
    multipleAdvanceS.start(6, 6, 2, 70, -6, -6);
    for (int i = 1; i <= 6; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(2, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(2, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(28.284271247461902, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(28.284271247461902, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", multipleAdvanceS.getStatus());
  }

  @Test
  public void testMultipleAdvanceFrictionTypeBottomEdge() {
    PoolSimulator multipleAdvanceS = new SimplePoolSimulator(20, 20, "friction");
    multipleAdvanceS.start(6, 6, 2, 70, -2, -9);
    for (int i = 1; i <= 6; i++) {
      multipleAdvanceS.advance();
    }
    assertEquals(13.111111111111489, multipleAdvanceS.getBallPositionX(), 0.01);
    assertEquals(2, multipleAdvanceS.getBallPositionY(), 0.01);
    assertEquals(14.971862979626481, multipleAdvanceS.getBallVelocityX(), 0.01);
    assertEquals(67.37338340831917, multipleAdvanceS.getBallVelocityY(), 0.01);
    assertEquals("Status: Ball hit bottom edge", multipleAdvanceS.getStatus());
  }

  /**
   * Test cases to check if zero speed or direction is provided.
   */

  @Test
  public void testSimpleZeroSpeed() {
    SimplePoolSimulator zeroSpeedPool = new SimplePoolSimulator(200, 250, "simple");
    zeroSpeedPool.start(100, 175, 9, 0, 2, 3);

    for (int i = 1; i <= 3; i++) {
      zeroSpeedPool.advance();
    }
    assertEquals(100.0, zeroSpeedPool.getBallPositionX(), 0.01);
    assertEquals(175.0, zeroSpeedPool.getBallPositionY(), 0.01);
  }

  @Test
  public void testFrictionZeroDirection() {
    SimplePoolSimulator poolZeroDirection = new SimplePoolSimulator(300, 300, "friction");
    poolZeroDirection.start(89, 172, 10, 188, 0, 0);

    for (int i = 1; i <= 108; i++) {
      poolZeroDirection.advance();
    }

    assertEquals(89, poolZeroDirection.getBallPositionX(), 0.01);
    assertEquals(172.0, poolZeroDirection.getBallPositionY(), 0.01);
  }

  @Test
  public void testFrictionZeroSpeedDirection() {
    SimplePoolSimulator poolZeroDirection = new SimplePoolSimulator(400, 400, "friction");
    poolZeroDirection.start(98, 174, 8, 0, 0, 0);

    for (int i = 1; i <= 5; i++) {
      poolZeroDirection.advance();
    }

    assertEquals(98.0, poolZeroDirection.getBallPositionX(), 0.01);
    assertEquals(174.0, poolZeroDirection.getBallPositionY(), 0.01);
  }

  @Test
  public void testSimpleZeroSpeedDirection() {
    SimplePoolSimulator pool = new SimplePoolSimulator(200, 250, "simple");
    pool.start(100, 175, 9, 0, 0, 0);

    for (int i = 1; i <= 3; i++) {
      pool.advance();
    }
    assertEquals(100, pool.getBallPositionX(), 0.01);
    assertEquals(175.0, pool.getBallPositionY(), 0.01);
  }


  /**
   * Test cases for horizontal or vertical bounces in both table types.
   */

  @Test
  public void testSimpleTypeHorizontalBounce() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "simple");
    pool.start(92, 178, 9, 188, -6, 0);
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  @Test
  public void testSimpleTypeVerticalBounce() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "simple");
    pool.start(92, 178, 9, 188, 0, -9);
    pool.advance();
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit top edge", pool.getStatus());
  }

  @Test
  public void testFrictionTypeHorizontalBounce() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "friction");
    pool.start(92, 178, 9, 188, -6, 0);
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  @Test
  public void testFrictionTypeVerticalBounce() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400, 400, "friction");
    pool.start(92, 178, 9, 188, 0, -9);
    pool.advance();
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit top edge", pool.getStatus());
  }

  /**
   * Stationary ball when dampen effect takes place due to multiple advances.
   */

  @Test
  public void testSimpleTypeBallBeyondMovement() {
    SimplePoolSimulator statPool = new SimplePoolSimulator(400, 400, "simple");
    statPool.start(90, 175, 9, 188, 0.410000, 0.160000);
    for (int i = 1; i <= 37; i++) {
      statPool.advance();
    }
    assertEquals(-2.7947328584551805, statPool.getBallVelocityX(), 0.01);
    assertEquals(391.0, statPool.getBallPositionX(), 0.01);
    assertEquals("Status: Ball hit right edge", statPool.getStatus());

    //after this advance the speed will become 0 , so as the x and y veocities
    statPool.advance();
    assertEquals(0, statPool.getBallVelocityX(), 0.01);
    assertEquals(0, statPool.getBallVelocityY(), 0.01);
    assertEquals(281.7500000000015, statPool.getBallPositionX(), 0.01);
    assertEquals("Status: Ball is stationary", statPool.getStatus());

    //after this advance the speed will become 0 , so as the x and y veocities
    statPool.advance();
    assertEquals(0, statPool.getBallVelocityX(), 0.01);
    assertEquals(0, statPool.getBallVelocityY(), 0.01);
    assertEquals(281.7500000000015, statPool.getBallPositionX(), 0.01);
  }

  @Test
  public void testFrictionTypeBallBeyondMovement() {
    SimplePoolSimulator statPool = new SimplePoolSimulator(400, 400, "friction");
    statPool.start(90, 175, 9, 188, 0.410000, 0.160000);
    for (int i = 1; i <= 61; i++) {
      statPool.advance();
    }
    assertEquals(9.998164497925238, statPool.getBallVelocityX(), 0.01);
    assertEquals(9.0, statPool.getBallPositionX(), 0.01);
    assertEquals("Status: Ball hit left edge", statPool.getStatus());

    //after this advance the speed will become 0 , so as the x and y velocities.
    statPool.advance();
    assertEquals(0, statPool.getBallVelocityX(), 0.01);
    assertEquals(0, statPool.getBallVelocityY(), 0.01);
    assertEquals(63.69183643219767, statPool.getBallPositionX(), 0.01);
    assertEquals("Status: Ball is stationary", statPool.getStatus());

    // after this any advancement, the ball will remain stationary.
    statPool.advance();
    assertEquals(0, statPool.getBallVelocityX(), 0.01);
    assertEquals(0, statPool.getBallVelocityY(), 0.01);
    assertEquals(63.69183643219767, statPool.getBallPositionX(), 0.01);
  }

  @Test
  public void testInitialStateTouchTwo() {

    SimplePoolSimulator statPool = new SimplePoolSimulator(400, 400, "friction");
    statPool.start(399, 399, 1, 90, +2, +7);
    statPool.advance();
    assertEquals(-24.72490151076402, statPool.getBallVelocityX(), 0.01);
    assertEquals(86.53715528767408, statPool.getBallVelocityY(), 0.01);
    assertEquals(399.0000000000009, statPool.getBallPositionX(), 0.01);
    assertEquals(399.0, statPool.getBallPositionY(), 0.01);
    assertEquals("Status: Ball hit top edge", statPool.getStatus());
  }

  @Test
  public void testInitialStateTouchOneSide() {

    SimplePoolSimulator statPool = new SimplePoolSimulator(400, 400, "friction");
    statPool.start(399, 355, 1, 90, +2, +7);
    statPool.advance();
    assertEquals(24.587491105265645, statPool.getBallVelocityX(), 0.01);
    assertEquals(-86.05621886842974, statPool.getBallVelocityY(), 0.01);
    assertEquals(411.5714285714284, statPool.getBallPositionX(), 0.01);
    assertEquals(399.0, statPool.getBallPositionY(), 0.01);
    assertEquals("Status: Ball hit top edge", statPool.getStatus());
  }
}