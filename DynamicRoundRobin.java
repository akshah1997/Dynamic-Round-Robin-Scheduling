import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

class DynamicRoundRobin {

  // declaring static variables
  static Process p[]; // objects of Process class
  static ArrayList<Process> p1 = new ArrayList<Process>(0); // hold the current process in the ready queue
  static int index=0, timeCount, mainCount;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    //int sum = 0;
    int ganttTime[];
    int ganttProcNo[];
    int ganttCount = 0;
    int ganttTimeCount = 0;
    int maxBurstTime;
    float timeQuantum = 0;
    float avgWaitingTime = 0;
    float avgTurnaroundTime = 0;
    boolean flag = true;  // flag used to check the cases and change the timeQuantum accordingly
    Vector<Integer> v = new Vector<Integer>();
    ArrayList<Process> p2;

    System.out.println("\nEnter the no. of processes: ");
    int noOfProc = sc.nextInt();

    ganttTime = new int[noOfProc];
    ganttProcNo = new int[noOfProc];

    p = new Process[noOfProc];
    for(int i=0; i<noOfProc; i++) { // initialising the Process objects
      p[i] = new Process(i);
    }

    // setting the various attributes using getters and setters
    for(int i=0; i<noOfProc; i++) {
      System.out.println("\nEnter the Arrival Time and Burst Time of "+i+" process: ");
      p[i].setArrivalTime(sc.nextInt());
      p[i].setBurstTime(sc.nextInt());
      //sum += p[i].getArrivalTime();
      v.add(i+1); // adding all process numbers to vector
    }

    Arrays.sort(p); // sorting the processes based on arrival timeCount
    mainCount = p[0].getArrivalTime();  // setting mainCount to the value of arrival time of the process that came first

    for(timeCount=0; ; ) {
      checkForProcess(timeCount, index); // check for the process that entered the system until the current time

      p2 = (ArrayList<Process>)p1.clone();
      p2.sort(new Process()); // sorting process base on their burst times

      if(flag) {
        maxBurstTime = p2.get(p2.size()-1).getBurstTime();
        //System.out.println(maxBurstTime);
        timeQuantum = 0.8f * maxBurstTime;
        //System.out.println("\n\nTime Quantum now: " + timeQuantum);
      }

      /*for(int i=0; i<p1.size(); i++)
        System.out.print((p1.get(i).getProcessNo()+1)+"\t");*/

      /*p1.get(0).setWaitingTime(timeCount-p1.get(0).getArrivalTime());
      timeCount += p1.get(0).getBurstTime();*/

      //System.out.println("\n");
      for(int i=0; i<p1.size(); i++) {
        // if the process's burst time less that current timeQuantum and not yet finished
        if(p1.get(i).getBurstTime() <= timeQuantum && !p1.get(i).isFinish()) {
          //System.out.print("Process finished: "+(p1.get(i).getProcessNo()+1)+"\n");
          p1.get(i).setWaitingTime(mainCount-p1.get(i).getArrivalTime());
          timeCount += p1.get(i).getBurstTime();
          mainCount += p1.get(i).getBurstTime();
          ganttProcNo[ganttCount] = p1.get(i).getProcessNo();
          ganttTimeCount += p1.get(i).getBurstTime();
          ganttTime[ganttCount++] = ganttTimeCount;
          //System.out.println(ganttTime);
          p1.get(i).setFinish(true);
          p1.get(i).setTurnaroundTime(mainCount-p1.get(i).getArrivalTime());
          v.removeElement(i+1);
        }
      }

      if(p1.size() == noOfProc) {
        timeQuantum = p2.get(p2.size()-1).getBurstTime();
        // System.out.println("Time Quantum now: " + timeQuantum);
        flag = false;
      }

      if(v.isEmpty()) {
        break;
      }
    }

    System.out.println("\nGantt Chart: \n");
    for(int i=0; i<noOfProc; i++) {
      System.out.print("|\tP"+ganttProcNo[i]+"\t|");
    }

    System.out.print("\n" + p1.get(0).getArrivalTime() + "\t");
    for(int i=0; i<noOfProc; i++) {
      System.out.print("\t"+ganttTime[i]+"\t");
    }

    System.out.println("\n\nProcess\t\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
    for(int i=0; i<noOfProc; i++) {
      System.out.println("   "+p[i].getProcessNo()+"\t\t   "+p[i].getArrivalTime()+"\t\t   "+p[i].getBurstTime()+"\t\t   "+p[i].getWaitingTime()+"\t\t  "+p[i].getTurnaroundTime());
      avgWaitingTime += p[i].getWaitingTime();
      avgTurnaroundTime += p[i].getTurnaroundTime();
    }

    avgWaitingTime /= noOfProc;
    avgTurnaroundTime /= noOfProc;
    System.out.println("\nAverage Waiting Time: "+avgWaitingTime);
    System.out.println("Average Turnaround Time: "+avgTurnaroundTime);

  }

  public static void checkForProcess(int time, int ind)
  {
    // this method used to add the processes that arrive till current time to p1 list
    for(int j=ind; j<p.length; j++)
    {
      if(p[j].getArrivalTime() <= time)
      {
        p1.add(p[j]);
        index++;
      }
    }
    if(index > ind)
      return;
    timeCount++;
    return;
  }
}
