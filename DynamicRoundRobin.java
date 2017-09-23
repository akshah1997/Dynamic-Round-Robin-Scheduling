import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

class DynamicRoundRobin {

  static Process p[];
  static ArrayList<Process> p1 = new ArrayList<Process>(0);
  static int index=0, timeCount;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int sum = 0;
    int maxBurstTime;
    float timeQuantum=0;
    float avgWaitingTime = 0;
    float avgTurnaroundTime = 0;
    boolean flag = true;
    Vector v = new Vector();
    ArrayList<Process> p2 = new ArrayList<Process>(0);

    System.out.println("\nEnter the no. of processes: ");
    int no_of_proc = sc.nextInt();

    p = new Process[no_of_proc];
    for(int i=0; i<no_of_proc; i++) {
      p[i] = new Process(i);
    }

    for(int i=0; i<no_of_proc; i++) {
      System.out.println("\nEnter the Arrival Time and Burst Time of "+i+" process: ");
      p[i].setArrivalTime(sc.nextInt());
      p[i].setBurstTime(sc.nextInt());
      sum += p[i].getArrivalTime();
      v.add(i+1);
    }

    System.out.println(v);

    Arrays.sort(p);
    int brst[] = new int[no_of_proc];
    int ind = 0;

    for(timeCount=0; ; ) {
      System.out.println("For entered");
      checkForProcess(timeCount, index);

      p2 = (ArrayList<Process>)p1.clone();
      p2.sort(new Process());

      if(flag) {
        maxBurstTime = p2.get(p2.size()-1).getBurstTime();
        System.out.println(maxBurstTime);
        timeQuantum = 0.8f * maxBurstTime;
        System.out.println(timeQuantum);
      }

      for(int i=0; i<p1.size(); i++)
        System.out.print((p1.get(i).getProcessNo()+1)+"\t");

      timeCount += p1.get(0).getBurstTime();
      System.out.println("\n");
      for(int i=0; i<p1.size(); i++) {
        if(p1.get(i).getBurstTime() <= timeQuantum) {
          System.out.println((p1.get(i).getProcessNo()+1)+"\n");
          v.removeElement(i+1);
          //p1.remove(i);
        }
      }

      if(p1.size() == no_of_proc) {
        timeQuantum = p2.get(p2.size()-1).getBurstTime();
        System.out.println(timeQuantum);
        flag = false;
      }
      /*while(p1.get(0).getBurstTime() <= timeQuantum) {
        System.out.println("removed process " + p1.get(0).getProcessNo());
        p1.remove(0);
      }*/
      if(v.isEmpty()) {
        System.out.println("Breaked");
        break;
      }
      System.out.println("For exited!");
    }

    /*for(int x=0; x<p1.size(); x++) {
      System.out.println(p1.get(x).getProcessNo()+"\n");
    }*/

  }

  public static boolean checkForProcess(int time, int ind)
  {
    for(int j=ind; j<p.length; j++)
    {
      if(p[j].getArrivalTime() <= time)
      {
        p1.add(p[j]);
        index++;
      }
    }
    if(index > ind)
      return true;
    timeCount++;
    return false;
  }
}
