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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

public class FIFOQueue{
    // Array used to implement the queue.
    private PCB[] queueRep;         //**** Queue of integers
    private int size, front, rear;

    // Length of the array used to implement the queue.
    private static int CAPACITY = 100;	//Default Queue size


    // Initializes the queue to use an array of default length.
    public FIFOQueue (){
        queueRep = new PCB [CAPACITY];  //****
        size  = 0; front = 0; rear  = 0;
    }

    // Initializes the queue to use an array of given length.
    public FIFOQueue (int cap){
        queueRep = new PCB [cap];
        size  = 0; front = 0; rear  = 0;
    }

    // Inserts an element at the rear of the queue.
    public void enQueue (PCB data) throws NullPointerException, IllegalStateException{
        rear++;
        size++;
        if(queueRep[0]==null){
            queueRep[0] = data;
        }
        else{
            for(int i = 0; i< size; i++){ //[arrival 2] data = arrival 3 i = 0
                if(i == size-1){
                    queueRep[i] = data;
                }
                else if(queueRep[i].getArrival()< data.getArrival()) {
                    continue;
                }
                else{
                    adjustNdx(i);
                    queueRep[i] = data;
                    break;
                }
            }
        }
    }

    // Inserts an element at the rear of the queue.
    public void sortSJN () throws NullPointerException, IllegalStateException{
        PCB[] order = new PCB[size];
        order[0] = queueRep[0];
        int jobDone = queueRep[0].getArrival()+queueRep[0].getDuration();
        PCB nextJob = null;
        ArrayList<PCB> remainingJobs = new ArrayList<PCB>(Arrays.asList(queueRep));
        Iterator<PCB> itr = remainingJobs.iterator();
        for(int i = 1; i<size; i++) {
            for(PCB currPCB: remainingJobs){
                if (currPCB.getArrival() < jobDone) {
                    if (nextJob == null) {
                        nextJob = currPCB;
                    } else if (currPCB.getDuration() < nextJob.getDuration()) {
                        nextJob = currPCB;
                    }
                }
            }
            remainingJobs.remove(nextJob);
            order[i] = nextJob;
            jobDone = jobDone + nextJob.getDuration();
            nextJob = null;
        }
        for (PCB p: order) {
            System.out.println("order " + " " +p.getDuration() + "size " + size);
        }
        System.out.println();
        queueRep = order;
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

    private void adjustNdx(int startNdx){
        for(int i = queueRep.length-1; i>startNdx; i--){
            if(queueRep[i] == null && queueRep[i-1] == null){
                continue;
            }
            queueRep[i] = queueRep[i-1];
        }
    }

}