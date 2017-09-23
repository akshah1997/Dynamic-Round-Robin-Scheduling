import java.util.Comparator;

public class Process {
  private int arrivalTime;
  private int burstTime;
  private int processNo;
  private int waitingTime;
  private int turnaroundTime;

  public Process() {}

  public Process(int processNo) {
    this.processNo = processNo;
  }

  int getArrivalTime() {
    return arrivalTime;
  }

  int getProcessNo() {
    return processNo;
  }

  int getBurstTime() {
    return burstTime;
  }

  int getWaitingTime() {
    return waitingTime;
  }

  int getTurnaroundTime() {
    return turnaroundTime;
  }

  void setArrivalTime(int at) {
    arrivalTime = at;
  }

  void setBurstTime(int bt) {
    burstTime = bt;
  }

  void setWaitingTime(int wt) {
    waitingTime = wt;
  }

  void setTurnaroundTime(int tat) {
    turnaroundTime = tat;
  }
}
