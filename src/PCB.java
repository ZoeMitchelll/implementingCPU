public class PCB implements Comparable <PCB>{
    //Represents Process Control Block object

    private static int ID = 0;
    private status s;
    private int cycles_remaining;
    public enum status{RUNNING, READY, WAITING, HOLD};
    public PCB()
    {
        //Default constructor
        ++ID;
        s = status.HOLD;
        cycles_remaining = 1000;
    }

    public int compareTo(PCB p) {
        if (this.getCycles() < p.getCycles()) {
            return -1;
        } else if (this.getCycles() > p.getCycles()) {
            return 1;
        } else {
            return 0;
        }
    }
    public PCB(int cycles)
    {
        //Constructor to create PCB with specified number of remaining CPU cycles
        cycles_remaining = cycles;
    }

    public void changeStatus(status newstat)
    {
        s = newstat;
    }
    public void decrementCycles()
    {
        //Process one CPU cycle
        --cycles_remaining;
    }
    public int getCycles()
    {
        //Return number of remaining CPU cycles
        return cycles_remaining;
    }

}
