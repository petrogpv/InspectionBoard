package model.applicant;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Администратор on 19.05.2017.
 */
public class ApplicantQueue {
    private Queue<Applicant> queue;
    private Lock lock;
    private static ApplicantQueue applicantQueue;

    private ApplicantQueue() {
        queue = new LinkedList<>();
        lock = new ReentrantLock(true);
    }

    public static ApplicantQueue getApplicantQueueInstance() {
        if (applicantQueue == null) {
            return new ApplicantQueue();
        } else {
            return applicantQueue;
        }
    }

    public Lock getLock() {
        return lock;
    }

    public void addApplicant(Applicant applicant) {
        queue.add(applicant);
    }

    public Applicant checkNextApplicant() {
        return queue.peek();
    }

    public Applicant getNextApplicant() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

}
