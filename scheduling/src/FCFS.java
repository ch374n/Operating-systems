// package com.nimbalkar.chetan;

public class FCFS extends Scheduling{
    public void execute() {
        init();
        int elapsedTime = 0, rt, tt, wt;
        float avgtt = 0, avgwt = 0;
        for(Process p : waitQueue) {
            if(elapsedTime < p.getAt()) {
                elapsedTime += p.getAt();
                readyQueue.add(new Process("--", 0, 0, elapsedTime, 0, 0, 0, 0));
            } else {
                rt = elapsedTime - p.getAt();
                elapsedTime += p.getBt();
                tt = elapsedTime - p.getAt();
                avgtt += tt;
                wt = tt - p.getBt();
                avgwt += wt;
                readyQueue.add(new Process(p.getpName(), p.getAt(), p.getBt(), elapsedTime, tt, wt, rt, 0));
            }
        }
        display(readyQueue, avgtt, avgwt);
    }
}
