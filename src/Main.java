import java.util.Random;
import java.util.*;


public class Main{
    private static int numProcesses;

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
        System.out.println("Process ID         Duration         Arrival Time");
        System.out.println(" _____________________________________________________");
        for (int i = 0; i < size; i++)
        {
            System.out.println(P[i].getID() + "                   " + P[i].getCycles()+ "                   "+P[i].getArrival());
        }
    }

    public static void GenerateProcesses(PCB [] P)
    {
        //Initializes PCB objects. Do not modify.
        int val;
        int time;
        for(int i = 1; i <= numProcesses; i++)
        {
            val = 5 + (int)(Math.random() * 60);
            time = (int)((Math.random() * 10)*(Math.random() * 10)-1);
            P[i-1] = new PCB(val, time);
            P[i-1].setID(i);
        }


    }
    public static void FCFS(FIFOQueue Q) {
        //Simulate First In First Out scheduling on processes in P
        PCB p;

        while(!Q.isEmpty()) //While more processes remain in queue
        {
            p = Q.deQueue(); //Fetch and remove process at head of queue
            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            while(p.getCycles() > 0)
            {
                    //Use 1 cpu cycle
                    p.decrementCycles();    //Use one cycle
                    System.out.println(p.getCycles() + " cycles remain.");
            }
            System.out.println("Process " + p.getID() + " has finished.");
        }


    }

    public static void SRT(FIFOQueue Q) {
        //Simulate Shortest Remaining Time
        PCB p;
        int currenttime = 0;
        while(!Q.isEmpty()) //While more processes remain in queue
        {
            Q.updateStatus(currenttime);
            p = Q.getShortestJobNext();//reorder queue
            if(p != null){
                if(p.getCycles() == p.getDuration()) {
                    System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");
                }
                p.decrementCycles();    //Use one cycle
                System.out.println(p.getCycles() + " cycles remain.");
                if(p.getCycles() == 0){
                    System.out.println("Process " + p.getID() + " has finished.");
                    Q.deQueue();
                }
            }
            currenttime++;
        }

        }

    public static void SJN(FIFOQueue Q) {
        //Simulate Shortest job Next scheduling on processes in P
        PCB p;
        PCB[] SJNorder = Q.sortSJN();
        for(int i = 0; i<SJNorder.length; i++) //While more processes remain in queue
        {
            p = SJNorder[i];
            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            while(p.getCycles() > 0)
            {
                //Use 1 cpu cycle
                p.decrementCycles();    //Use one cycle
                System.out.println(p.getCycles() + " cycles remain.");
            }
            System.out.println("Process " + p.getID() + " has finished.");
        }


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
        Scanner sc = new Scanner(System.in);
        System.out.println("How many processes would you like to scheadule?");
        int capacity = sc.nextInt();
        numProcesses = capacity;
        PCB [] P = new PCB[capacity];
        FIFOQueue Q = new FIFOQueue(capacity);
        GenerateProcesses(P);
        InitQueue(Q, P);
        do
        {
            System.out.println("Process scheduling simulator. Make your choice:");
            System.out.println("1) View Processes");
            System.out.println("2) Run processes (FCFS)");
            System.out.println("3) Run processes (SJN)");
            System.out.println("4) Run processes (SRT)");
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
                    SJN(Q);
                    break;
                case 4:
                    SRT(Q);
                    break;
                case 5:
                    RoundRobin(Q, 5);
                    break;


            }


        }while(choice != 6);

    }

}
