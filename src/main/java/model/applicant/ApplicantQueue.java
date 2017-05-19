package model.applicant;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Администратор on 19.05.2017.
 */
public class ApplicantQueue {
    private Queue<Applicant> queue;
    private static ApplicantQueue applicantQueue;

    private ApplicantQueue() {
        queue = new LinkedList<Applicant>();
    }
    public static ApplicantQueue getApplicantQueueInstance (){
        if(applicantQueue == null){
            return new ApplicantQueue();
        }
        else return applicantQueue;
    }
    public void addApplicant(Applicant applicant){
        queue.add(applicant);
    }
    public Applicant checkNextApplicant(){
        return queue.element();
    }
    public Applicant getNextApplicant(){
        return queue.element();
    }
    public boolean isEmpty(){
        return applicantQueue.isEmpty();
    }
    public int size(){
        return queue.size();
    }

}
