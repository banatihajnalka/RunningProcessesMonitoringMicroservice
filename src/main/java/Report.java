import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Report {

  private String username;
  private Timestamp timestamp;
  private List<String> runningProcesses;

  public Report() {
  }

  public Report(String username, List<String> runningProcesses) {
    this.username = username;
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.runningProcesses = runningProcesses;
  }

  //  public Report(String username, List<RunningAppInfo> runningProcesses) {
//    this.username = username;
//    this.timestamp = new Timestamp(System.currentTimeMillis());
//    this.runningProcesses = runningProcesses;
//  }


  @JsonProperty
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonProperty
  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  @JsonProperty
  public List<String> getRunningProcesses() {
    return runningProcesses;
  }

  public void setRunningProcesses(List<String> runningProcesses) {
    this.runningProcesses = runningProcesses;
  }

//  @JsonProperty
//  public List<RunningAppInfo> getRunningProcesses() {
//    return runningProcesses;
//  }
//
//  public void setRunningProcesses(List<RunningAppInfo> runningProcesses) {
//    this.runningProcesses = runningProcesses;
//  }
}
