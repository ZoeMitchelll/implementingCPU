public class PCB implements Comparable <PCB>{
    //Represents Process Control Block object

    private int ID = 0;
    private status s;
    private int proccess_duration, cycles_remaining, arrivalTime;
    public enum status{RUNNING, READY, WAITING, HOLD};
    public PCB(int dur, int time)
    {
        //Default constructor
        ++ID;
        s = status.HOLD;
        cycles_remaining = dur;
        proccess_duration = dur;
        arrivalTime = time;
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

    public int getArrival()
    {
        //Return number of remaining CPU cycles
        return arrivalTime;
    }

    public int getDuration(){
        return proccess_duration;
    }
    public int getID()
    {
        return ID;
    }
    public void setID(int id)
    {
        ID = id;
    }
    public void setCycles(int c)
    {
        cycles_remaining = c;
    }
    public void setArrivalTime(int t)
    {
        arrivalTime = t;
    }

    public status getS() {
        return s;
    }
}
