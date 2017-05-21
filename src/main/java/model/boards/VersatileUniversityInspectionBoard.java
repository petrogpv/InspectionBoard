package model.boards;

import model.applicant.Applicant;
import model.applicant.ApplicantQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Администратор on 19.05.2017.
 */
public class VersatileUniversityInspectionBoard implements InspectionBoard {

    private static final int MAX_VERSATILE_UNIVERSITY_APPLYING_CAPACITY = 5;
    List<Applicant> appliedList = new ArrayList();
    public void apply(ApplicantQueue applicantQueue) {
        int rand = new Random().nextInt(MAX_VERSATILE_UNIVERSITY_APPLYING_CAPACITY) + 1;
        while (!applicantQueue.isEmpty() && rand > 0){
            appliedList.add(applicantQueue.getNextApplicant());
            rand--;
        }
    }

    @Override
    public List<Applicant> getAppliedList() {
        return appliedList;
    }
}
