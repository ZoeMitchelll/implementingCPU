import java.util.Random;
import java.util.*;


public class Main{

    public static void InitQueue(FIFOQueue Q, PCB [] P)
    {
        //Initialize ready queue. Do not modify.
        for(int i = 0; i < P.length; i++)
        {
            Q.enQueue(P[i]);
        }
    }

    public static void ViewProcesses(PCB [] P, int size)
    {
        //View all process objects. Do not modify.
        System.out.println("Process ID         Duration");
        System.out.println(" __________________________");
        for (int i = 0; i < size; i++)
        {
            System.out.println(P[i].getID() + "            " + P[i].getCycles());
        }
    }

    public static void GenerateProcesses(PCB [] P)
    {
        //Initializes PCB objects. Do not modify.
        int val;
        int time;
        for(int i = 1; i <= 5; i++)
        {
            val = 5 + (int)(Math.random() * 60);
            time = (int)((Math.random() * 10)*(Math.random() * 10)-1);
            P[i-1] = new PCB(val, time);
            P[i-1].setID(i);
        }


    }
    public static void FCFS(FIFOQueue Q) {
        //Your code here
        //You will implement the First Come First Served scheduling algorithm
        //You may assume all jobs arrive at time 0.
    }

    public static void SRT(FIFOQueue Q) {
        //Your code here
        //You will implement the First Come First Served scheduling algorithm
        //You may assume all jobs arrive at time 0.
    }

    public static void SJN(FIFOQueue Q) {
        //Your code here
        //You will implement the Shortest Job Next scheduling algorithm
        //You may assume all jobs arrive at time 0.

    }

    public static void RoundRobin(FIFOQueue Q, int quantum)
    {
        //Simulate Round Robin scheduling on processes in P using the
        //given time quantum
        int ticks = 0;
        PCB p;

        while(!Q.isEmpty()) //While more processes remain in queue
        {
            p = Q.deQueue(); //Fetch and remove process at head of queue
            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            for(int i = p.getCycles(); i > 0; i--)
            {

                ticks++;
                if(p.getCycles() > 0 && ticks <= quantum)
                {
                    //Use 1 cpu cycle
                    p.decrementCycles();    //Use one cycle
                    System.out.println(p.getCycles() + " cycles remain.");
                }
                if(ticks > quantum)
                {
                    //Time's up. Go to next process.
                    System.out.println("Process " + p.getID() + " preempted");
                    ticks = 0;
                    Q.enQueue(p); //Add preempted process to back of queue
                    break;

                }
                else if(p.getCycles() == 0)
                {
                    System.out.println("Process " + p.getID() + " has finished.");
                    ticks = 0;
                    break;
                }
            }

        }


    }

    public static void main(String []args)
    {

        int choice;
        PCB [] P = new PCB[5];
        FIFOQueue Q = new FIFOQueue();
        GenerateProcesses(P);
        InitQueue(Q, P);
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("Process scheduling simulator. Make your choice:");
            System.out.println("1) View Processes");
            System.out.println("2) Run processes (FCFS)");
            System.out.println("3) Run processes (SJN)");
            System.out.println("4) Run processes (SRT");
            System.out.println("5) Run processes (Round Robin)");
            System.out.println("6) Quit");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    ViewProcesses(P, P.length);
                    break;
                case 2:
                    FCFS(Q);
                    break;
                case 3:
                    //SJN(PQ);
                    break;
                case 4:
                    //SRT(Q)
                    break;
                case 5:
                    RoundRobin(Q, 5);
                    break;


            }


        }while(choice != 6);

    }

}
