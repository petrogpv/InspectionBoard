package model;

import model.applicant.ApplicantPool;
import model.applicant.ApplicantQueue;
import model.universities.University;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Model {
    private List<University> universities;
    private ApplicantPool applicantPool;
    private ApplicantQueue applicantQueue;

    public Model() {
        this.applicantPool = ApplicantPool.getAplicantPoolInstance();
        this.applicantQueue = ApplicantQueue.getApplicantQueueInstance();
        this.universities = new ArrayList<University>();
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void addUniversity(University university) {
        universities.add(university);
    }

    public ApplicantPool getApplicantPool() {
        return applicantPool;
    }

    public void setApplicantPool(ApplicantPool applicantPool) {
        this.applicantPool = applicantPool;
    }

    public ApplicantQueue getApplicantQueue() {
        return applicantQueue;
    }

    public void setApplicantQueue(ApplicantQueue applicantQueue) {
        this.applicantQueue = applicantQueue;
    }
}
