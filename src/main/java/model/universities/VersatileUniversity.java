package model.universities;

import model.applicant.Applicant;
import model.applicant.ApplicantQueue;

import java.util.Queue;
import java.util.Random;

/**
 * Created by Администратор on 19.05.2017.
 */
public class VersatileUniversity implements University {
    private static final int MAX_VERSATILE_UNIVERSITY_APPLYING_CAPACITY = 5;
    public void apply(ApplicantQueue applicantQueue) {
        int rand = new Random().nextInt(MAX_VERSATILE_UNIVERSITY_APPLYING_CAPACITY) + 1;
        while (!applicantQueue.isEmpty() && rand > 0){
            appliedList.add(applicantQueue.getNextApplicant());
            rand--;
        }
    }
}
