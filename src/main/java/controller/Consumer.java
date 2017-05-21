package controller;

import model.applicant.ApplicantQueue;
import model.boards.InspectionBoard;

import java.util.concurrent.Callable;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Consumer implements Runnable {
    private InspectionBoard inspectionBoard;
    private ApplicantQueue applicantQueue;
    private Thread queueProducer;

    public Consumer(Thread queueProducer, InspectionBoard inspectionBoard, ApplicantQueue applicantQueue) {
        this.queueProducer = queueProducer;
        this.inspectionBoard = inspectionBoard;
        this.applicantQueue = applicantQueue;
    }

    @Override
    public void run(){
        Thread.currentThread().setName(inspectionBoard.getClass().getSimpleName());
        System.out.println("Consumer " + Thread.currentThread().getName());



            while (true) {
                synchronized (applicantQueue) {
                while (applicantQueue.size() < 25 && queueProducer.isAlive()) {
                    try {
                        applicantQueue.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (applicantQueue.size() == 0 && !queueProducer.isAlive()) {
                    System.out.println(Thread.currentThread().getName() + " is dead ");
                    return;
                }

                System.out.println(Thread.currentThread().getName() + " applying ");
                inspectionBoard.apply(applicantQueue);
                applicantQueue.notifyAll();

                    try {
                        applicantQueue.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }

    }

}
