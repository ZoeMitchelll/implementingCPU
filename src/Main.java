import java.util.*;


public class Main {


    public static void main(String args[])
    {
        int duration, numPCB, arrival;
        Scanner scan = new Scanner(System.in);

        FIFOQueue SJNQueue; //Queue for SJN ordering. Note that BinaryHeap is a template class
        FIFOQueue FCFSQueue;        //Queue for FCFS ordering. Note that FIFOQueue is NOT a template class

        System.out.println("How many processes will you create?");
        numPCB = scan.nextInt();
        SJNQueue = new FIFOQueue((numPCB));
        FCFSQueue = new FIFOQueue((numPCB));

        for(int i = 0; i < numPCB; i++)
        {
            //Add new PCB to FIFOQueue and BinaryHeap
            System.out.println("Enter CPU duration of process " + (i+1) + ":");
            duration = scan.nextInt();
            System.out.println("Enter arrival time duration of process " + (i+1) + ":");
            arrival = scan.nextInt();
            //Add PCB not duration PCB has arrival and duration inside
            SJNQueue.enQueue(new PCB(duration,arrival));
            FCFSQueue.enQueue(new PCB(duration,arrival));
        }
        SJNQueue.sortSJN();

        System.out.println("-----------------FCFS Order-------------------");
        for (int i = 0; i < numPCB; i++)
        {
            System.out.println(FCFSQueue.deQueue().getDuration());
        }
        System.out.println("-----------------SJN Order-------------------");
        for (int i = 0; i < numPCB; i++)
        {
            System.out.println(SJNQueue.deQueue().getDuration());

        }




    }
}
