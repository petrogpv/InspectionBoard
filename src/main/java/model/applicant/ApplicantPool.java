package model.applicant;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Администратор on 19.05.2017.
 */
public class ApplicantPool {
    private Queue<Applicant> applicantPoolList;
    private static ApplicantPool pool;

    private ApplicantPool() {
        applicantPoolList = new LinkedList<Applicant>();
    }
    public static ApplicantPool getApplicantPoolInstance(){
        if(pool == null){
            return new ApplicantPool();
        }
        else return pool;
    }
    public void addApplicant(Applicant applicant){
        applicantPoolList.add(applicant);
    }

    public Applicant getApplicant() {
        return applicantPoolList.poll();
    }
    public boolean isEmpty(){
        return applicantPoolList.isEmpty();
    }
    public int size(){
        return applicantPoolList.size();
    }
    public void shuffle(){
        Collections.shuffle((List<?>) applicantPoolList);
    }
}
