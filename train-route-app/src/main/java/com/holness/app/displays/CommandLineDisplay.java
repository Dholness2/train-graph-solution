package com.holness.app.displays;

import com.holness.app.displays.ReportDisplay;

public class CommandLineDisplay implements ReportDisplay {

  public void writeOutput(String output) {
    System.out.println(output);
  }

}
