import java.util.*;


public class Main {


    public static void main(String args[])
    {
        int duration, numPCB;
        Scanner scan = new Scanner(System.in);

        BinaryHeap<Integer> SJNQueue; //Queue for SJN ordering. Note that BinaryHeap is a template class
        FIFOQueue FCFSQueue;        //Queue for FCFS ordering. Note that FIFOQueue is NOT a template class

        System.out.println("How many processes will you create?");
        numPCB = scan.nextInt();
        SJNQueue = new BinaryHeap<Integer>(numPCB);
        FCFSQueue = new FIFOQueue((numPCB));

        for(int i = 0; i < numPCB; i++)
        {
            //Add new PCB to FIFOQueue and BinaryHeap
            System.out.println("Enter CPU duration of process " + (i+1) + ":");
            duration = scan.nextInt();
            SJNQueue.insert(duration);
            FCFSQueue.enQueue(duration);
        }

        System.out.println("-----------------FCFS Order-------------------");
        for (int i = 0; i < numPCB; i++)
        {
            System.out.println(FCFSQueue.deQueue());
        }
        System.out.println("-----------------SJN Order-------------------");
        for (int i = 0; i < numPCB; i++)
        {
            System.out.println(SJNQueue.findMin());
            SJNQueue.deleteMin();

        }




    }
}
