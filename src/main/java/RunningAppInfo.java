public class RunningAppInfo {

  private String applicationName;
  private int memoryUsage;

  public RunningAppInfo() {
  }

  public RunningAppInfo(String applicationName, int memoryUsage) {
    this.applicationName = applicationName;
    this.memoryUsage = memoryUsage;
  }

  public String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  public int getMemoryUsage() {
    return memoryUsage;
  }

  public void setMemoryUsage(int memoryUsage) {
    this.memoryUsage = memoryUsage;
  }
}
