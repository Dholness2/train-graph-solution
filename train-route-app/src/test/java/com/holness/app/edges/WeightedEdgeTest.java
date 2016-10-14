import com.holness.app.edges.WeightedEdge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WeightedEdgeTest {
  WeightedEdge testEdge;

  @Before
  public void setup() {
    int startPoint = 0;
    int endPoint = 4;
    int weight = 5;
    testEdge = new WeightedEdge(startPoint, endPoint, weight);
  }

  @Test 
  public void testReturnStartPoint() {
    int expected = 0;
    int result = testEdge.getStartPoint();
    assertEquals(expected, result);
  }

  @Test
  public void testReturnEndPoint() {
    int expected = 4;
    int result = testEdge.getEndPoint();
    assertEquals(expected, result);
  }

  @Test
  public void testReturnWeight() {
    int expected = 5;
    int result = testEdge.getWeight();
    assertEquals(expected, result);
  }

  @Test
  public void testReturnsWeightedState() {
    assertTrue(testEdge.isWeighted());
  }

  @Test
  public void testComparableToOtherEdges() {
    int start = 4;
    int end = 5;
    int weight = 200;
    WeightedEdge maxEdge = new WeightedEdge(start, end, weight);
    int lessThan = -1;
    int greaterThan = 1;
    assertEquals(lessThan, (testEdge.compareTo(maxEdge)));
    assertEquals(greaterThan, (maxEdge.compareTo(testEdge)));
  }
}


