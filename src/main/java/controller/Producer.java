package controller;


import model.applicant.ApplicantPool;
import model.applicant.ApplicantQueue;
import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Producer extends Thread {
    private ApplicantPool applicantPool;
    private ApplicantQueue applicantQueue;
    private Lock lock;
    private static final int MIN_QUEUE_SIZE = 25;

    public Producer(ApplicantPool applicantPool, ApplicantQueue applicantQueue) {
        this.applicantPool = applicantPool;
        this.applicantQueue = applicantQueue;
        this.lock = applicantQueue.getLock();
    }

    @Override
    public void run() {
        int randomAmount;

        while (true) {
            lock.lock();
            try {
                if (applicantQueue.size() > MIN_QUEUE_SIZE) {
                    continue;
                }
                if (applicantPool.isEmpty()) {
                    return;
                }
                randomAmount = MIN_QUEUE_SIZE + new Random().nextInt(MIN_QUEUE_SIZE);
                fillQueue(randomAmount);
            } finally {
                lock.unlock();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void fillQueue(int amount) {
        int correctedAmount = amount;

        if (amount > applicantPool.size()) {
            correctedAmount = applicantPool.size();
        }
        for (int i = 0; i < correctedAmount; i++) {
            applicantQueue.addApplicant(applicantPool.getApplicant());
        }

    }

}
