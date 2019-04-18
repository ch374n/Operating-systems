// package com.nimbalkar.chetan;

public class SJF extends Scheduling{
    private int timeSlab;
    private int elapsedTime = 0, rt, tt, wt;
    private float avgwt = 0, avgtt = 0;

    public void execute() {
        init();
        System.out.println("enter time slab : ");
        timeSlab = scanner.nextInt();


        Process p;
        while((p = getSRJ())!= null) {

            if(!p.isCalcRt()) {
                p.setRt(elapsedTime - p.getAt());
                p.setCalcRt(true);
            }

            if(elapsedTime < p.getAt()) {

                elapsedTime += p.getAt();
                readyQueue.add(new Process("--", 0, 0, elapsedTime, 0, 0, 0, 0));

            } else {

                if(p.getBt() <= timeSlab) {

                    elapsedTime += p.getBt();

                    p.setBt(0);
                    p.setCt(elapsedTime);

                    tt = elapsedTime - p.getAt();
                    avgtt += tt;
                    wt = tt - p.getBt();
                    avgwt += wt;
                    p.setTt(tt);
                    p.setWt(wt);

                } else {

                    elapsedTime += timeSlab;
                    p.setBt(p.getBt() - timeSlab);

                }


                readyQueue.add(new Process(p.getpName(), 0, 0, elapsedTime, 0, 0, 0, 0));
            }
        }

        waitQueue.forEach(System.out::println);
        display(readyQueue, avgtt, avgwt);
    }

    public Process getSRJ() {
        if(elapsedTime == 0) {
            return waitQueue.get(0);
        }
            int min = Integer.MAX_VALUE;
            Process process = null;
            for(Process p : waitQueue) {
                if(min > p.getBt() && p.getBt() != 0) {
                   process  = p;
                   min = p.getBt();
                }
            }
            return process;
    }
}
