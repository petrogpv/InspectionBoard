package controller;

import model.applicant.ApplicantQueue;
import model.universities.University;

/**
 * Created by Администратор on 19.05.2017.
 */
public class QueueConsumer extends Thread {
    private University university;
    private ApplicantQueue applicantQueue;
    private QueueProducer queueProducer;

    public QueueConsumer (QueueProducer queueProducer, University university, ApplicantQueue applicantQueue){
        this.queueProducer = queueProducer;
        this.university = university;
        this.applicantQueue = applicantQueue;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        synchronized (applicantQueue) {
            while (!applicantQueue.isEmpty() || queueProducer.isAlive()) {

                if (queueProducer.isAlive()) {
                    if (applicantQueue.size() >= queueProducer.getMinQueueSize()) {
                        university.apply(applicantQueue);
                        notify(); /*  waiting for producer*/
                    } else {
                        try {
                            wait(); /*  waiting for producer*/
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    university.apply(applicantQueue);
                    notify(); /*  waiting for other consumers*/

                }
            }
        }


    }
}
