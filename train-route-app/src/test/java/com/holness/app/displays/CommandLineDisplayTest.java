import com.holness.app.displays.ReportDisplay;
import com.holness.app.displays.CommandLineDisplay;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommandLineDisplayTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  @Test
  public void test() {
    String testOutput = "Path does not exist";
    ReportDisplay testDisplay = new CommandLineDisplay();
    String expected = testOutput + "\n";
    testDisplay.writeOutput(testOutput);
    assertEquals(expected, outContent.toString());
  }
}
