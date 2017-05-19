package controller;


import model.applicant.ApplicantPool;
import model.applicant.ApplicantQueue;

import java.util.Random;

/**
 * Created by Администратор on 19.05.2017.
 */
public class QueueProducer extends Thread{
    private ApplicantPool applicantPool;
    private ApplicantQueue applicantQueue;
    private static final int MIN_QUEUE_SIZE = 25;
    private static final int MAX_RANDOM = 50;

    public QueueProducer (ApplicantPool applicantPool, ApplicantQueue applicantQueue){
        this.applicantPool = applicantPool;
        this.applicantQueue = applicantQueue;
    }

    @Override
    public void run() {
        synchronized (applicantQueue){
            while(!applicantPool.isEmpty()){
                if (applicantQueue.size() < MIN_QUEUE_SIZE){
                    int random = MIN_QUEUE_SIZE + new Random().nextInt(MAX_RANDOM);
                    fillQueue(random);
                    notify();
                }else{
                    try {
                        wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
    private void fillQueue (int amount){
        int correctedAmount = amount;
        if(correctedAmount < applicantPool.size()){
            correctedAmount = applicantPool.size();
        }
        for (int i = 0; i < amount; i++) {
            applicantQueue.addApplicant(applicantPool.getApplicant());
        }

    }

    public static int getMinQueueSize() {
        return MIN_QUEUE_SIZE;
    }

}
