import com.holness.app.displays.ReportDisplay;

public class DisplayMock implements ReportDisplay {

	private StringBuilder sb;

	public DisplayMock() {
		sb = new StringBuilder();
	}

	public void sendOutPut(String outPut) {
		sb.append(outPut);
	}

	public String getOutPut() {
		return sb.toString();
	}

}
