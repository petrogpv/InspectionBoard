package model.boards;

import model.applicant.Applicant;
import model.applicant.ApplicantQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 19.05.2017.
 */
public class BiologyUniversityInspectionBoard implements InspectionBoard {
    List<Applicant> appliedList = new ArrayList();
    @Override
    public void apply(ApplicantQueue applicantQueue) {
        while (!applicantQueue.isEmpty()){
            if (applicantQueue.checkNextApplicant().equals(Applicant.BIOLOGIST)){
                appliedList.add(applicantQueue.getNextApplicant());
            }else {
//                System.out.println("apply return works");
                return;
            }

        }
    }

    @Override
    public List<Applicant> getAppliedList() {
        return appliedList;
    }
}
