package controller;


import model.applicant.ApplicantPool;
import model.applicant.ApplicantQueue;
import java.util.Random;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Producer extends Thread {
    private ApplicantPool applicantPool;
    private ApplicantQueue applicantQueue;
    private static final int MIN_QUEUE_SIZE = 25;

    public Producer(ApplicantPool applicantPool, ApplicantQueue applicantQueue) {
        this.applicantPool = applicantPool;
        this.applicantQueue = applicantQueue;
    }

    @Override
    public void run() {
        System.out.println("Producer " + Thread.currentThread().getName());

        int randomAmount;

            while (true) {
                synchronized (applicantQueue) {
                while (applicantQueue.size() > 25) {
                    try {
                        applicantQueue.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (applicantPool.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " is dead");
                    applicantQueue.notifyAll();
                    return;
                }

                randomAmount = MIN_QUEUE_SIZE + new Random().nextInt(MIN_QUEUE_SIZE);
                fillQueue(randomAmount);
                System.out.println(Thread.currentThread().getName() + " filled queue ");
                System.out.println("New pool size: " + applicantPool.size());
                applicantQueue.notifyAll();

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

    public static int getMinQueueSize() {
        return MIN_QUEUE_SIZE;
    }

}
