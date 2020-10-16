/*Copyright (c) Dec 21, 2014 CareerMonk Publications and others.
 * E-Mail           	: info@careermonk.com 
 * Creation Date    	: 2015-01-10 06:15:46 
 * Last modification	: 2006-05-31 
               by		: Narasimha Karumanchi 
 * File Name			: FIFOQueue.java
 * Book Title			: Data Structures And Algorithms Made In Java
 * Warranty         	: This software is provided "as is" without any 
 * 							warranty; without even the implied warranty of 
 * 							merchantability or fitness for a particular purpose. 
 * Note                 : Code modified slightly by Matthew Hayes
 */


public class FIFOQueue{
    // Array used to implement the queue.
    private PCB[] queueRep;         //**** Queue of PCB
    private int size, front;
    private static int CAPACITY;
    private PCB[] deletedPCB;

    // Initializes the queue to use an array of given length.
    public FIFOQueue (int cap){
        queueRep = new PCB [cap];
        size  = 0; front = 0; //only uses front and size
        CAPACITY = cap;
        deletedPCB = new PCB[cap];
    }

    // Inserts an element at the rear of the queue.
    public void enQueue (PCB data) throws NullPointerException, IllegalStateException{
        size++;
        if(queueRep[0]==null){
            queueRep[0] = data; //only PCB is always first PCB
        }
        else{
            for(int i = 0; i< size; i++){ //[arrival 2] data = arrival 3 i = 0
                if(i == size-1){
                    queueRep[i] = data; //latest arriving PCB is always last in queue
                }
                else if(queueRep[i].getArrival()< data.getArrival()) {
                    continue; //later PCB is placed behind earlier ones
                }
                else{
                    adjustNdx(i); //all PCB are moved back by one index
                    queueRep[i] = data; //"empty" index is where PCB go since everything infront is earlier and behind is later
                    break;
                }
            }
        }
    }

    // Inserts an element in order of SJN if waiting status.
    public PCB[] sortSJN () throws NullPointerException, IllegalStateException{
        PCB[] order = new PCB[size];
        order[0] = queueRep[0];
        deletedPCB[0] = order[0];
        int jobDone = queueRep[0].getArrival()+queueRep[0].getDuration();
        PCB nextJob = null;
        PCB[] remainingJobs = queueRep;
        for(int i = 1; i<size; i++) {// earliest job is completed and waiting jobs must be sorted
            for(PCB currPCB: remainingJobs){
                if(remaining(currPCB)) {
                    if (currPCB.getArrival() < jobDone) {
                        if (nextJob == null) {
                            nextJob = currPCB; //if there is only one waiting job then that job is next
                        } else if (currPCB.getDuration() < nextJob.getDuration()) {
                            nextJob = currPCB; //if there is another smaller waiting job then it replaces the previous larger waiting job
                        }
                    }
                }
            }
            order[i] = nextJob;
            deletedPCB[i] = nextJob; //jobs are only completed once
            jobDone = jobDone + nextJob.getDuration();
            nextJob = null;
        }
       return order;
    }
    private boolean remaining(PCB job){
        boolean remaining = true;
        for(PCB deleted: deletedPCB){
            if(job == deleted){
                remaining = false;
            }
        }
        return remaining;
    }

    // Removes the front element from the queue.
    public PCB deQueue () throws IllegalStateException{
        // Effects:   If queue is empty, throw IllegalStateException,
        // else remove and return oldest element of this
        if (size == 0)
            throw new IllegalStateException ("Queue is empty: Underflow");
        else {
            size--;
            front++;
            return queueRep[front-1];
        }
    }

    // Checks whether the queue is empty.
    public boolean isEmpty(){
        return (size == 0);
    }

    // Checks whether the queue is full.
    public boolean isFull(){
        return (size == CAPACITY);
    }

    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }

    private void adjustNdx(int startNdx){ //start ndx is "empty" and element of start ndx is moved back 1 ndx along with every element after starting ndx
        for(int i = queueRep.length-1; i>startNdx; i--){
            if(queueRep[i] == null && queueRep[i-1] == null){
                continue;
            }
            queueRep[i] = queueRep[i-1];
        }
    }

    public void updateStatus(int enteredTime) {
        for(PCB job: queueRep){
            if(job.getArrival() == enteredTime){
                job.changeStatus(PCB.status.READY);
            }
        }
    }

    public PCB getShortestJobNext(){
        PCB shortest = null;
        for(PCB job: queueRep) {
            if (job.getS() == PCB.status.READY){
                if(shortest == null || job.getCycles()<shortest.getCycles()){
                    shortest = job;
                }
            }
        }
        return shortest;
    }
//    public PCB[] SJNsort(){
//        PCB[] order = new PCB[size];
//        order[0] = queueRep[0];
//        int nextJobArrives;
//        PCB shortestJobReady = null;
//        PCB[] remainingJobs = queueRep;
//        for(int i = 1; i<size; i++) {// earliest job is completed and waiting jobs must be sorted
//            for(PCB currPCB: remainingJobs){
//                if(remaining(currPCB)) {
//                    if (currPCB.getArrival() < jobDone) {
//                        if (nextJob == null) {
//                            nextJob = currPCB; //if there is only one waiting job then that job is next
//                        } else if (currPCB.getDuration() < nextJob.getDuration()) {
//                            nextJob = currPCB; //if there is another smaller waiting job then it replaces the previous larger waiting job
//                        }
//                    }
//                }
//                deletedPCB[size-1] = nextJob; //jobs are only completed once
//            }
//            order[i] = nextJob;
//            jobDone = jobDone + nextJob.getDuration();
//            nextJob = null;
//        }
//        return order;
//    }
}
