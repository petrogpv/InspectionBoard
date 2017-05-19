package model.universities;

import model.applicant.Applicant;
import model.applicant.ApplicantQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Администратор on 18.05.2017.
 */
public interface University {
        List<Applicant> appliedList = new ArrayList();
        void apply(ApplicantQueue queue);
        default List<Applicant> getAppliedList(){
                return appliedList;
        }
}
