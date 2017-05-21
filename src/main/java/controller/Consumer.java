package controller;

import model.applicant.ApplicantQueue;
import model.boards.InspectionBoard;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Consumer implements Callable<InspectionBoard> {
    private InspectionBoard inspectionBoard;
    private ApplicantQueue applicantQueue;
    private Thread queueProducer;
    private Lock lock;

    public Consumer(Thread queueProducer, InspectionBoard inspectionBoard, ApplicantQueue applicantQueue) {
        this.queueProducer = queueProducer;
        this.inspectionBoard = inspectionBoard;
        this.applicantQueue = applicantQueue;
        this.lock = applicantQueue.getLock();
    }

    @Override
    public InspectionBoard call() {
        Thread.currentThread().setName(inspectionBoard.getClass().getSimpleName());

        while (true) {
            lock.lock();
                try {
                    if (applicantQueue.size() < 25 && queueProducer.isAlive()) {
                        continue;
                    }
                    if (applicantQueue.size() == 0 && !queueProducer.isAlive()) {
                        return inspectionBoard;
                    }
                    inspectionBoard.apply(applicantQueue);
                }finally {
                    lock.unlock();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



        }
    }


}
