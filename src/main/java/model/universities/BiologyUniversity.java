package model.universities;

import model.applicant.Applicant;
import model.applicant.ApplicantQueue;

import java.util.Queue;

/**
 * Created by Администратор on 19.05.2017.
 */
public class BiologyUniversity implements University {
    @Override
    public void apply(ApplicantQueue applicantQueue) {
        while (!applicantQueue.isEmpty()){
            if (applicantQueue.checkNextApplicant().equals(Applicant.BIOLOGIST)){
                appliedList.add(applicantQueue.getNextApplicant());
            }else {
                return;
            }

        }
    }
}
