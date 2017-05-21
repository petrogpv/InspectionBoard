package model;

import model.applicant.ApplicantPool;
import model.applicant.ApplicantQueue;
import model.boards.InspectionBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Model {
    private List<InspectionBoard> inspectionBoards;
    private ApplicantPool applicantPool;
    private ApplicantQueue applicantQueue;

    public Model() {
        this.applicantPool = ApplicantPool.getApplicantPoolInstance();
        this.applicantQueue = ApplicantQueue.getApplicantQueueInstance();
        this.inspectionBoards = new ArrayList<>();
    }

    public List<InspectionBoard> getInspectionBoards() {
        return inspectionBoards;
    }

    public void addInspectionBoard(InspectionBoard inspectionBoard) {
        inspectionBoards.add(inspectionBoard);
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
