package model.boards;

import model.applicant.Applicant;
import model.applicant.ApplicantQueue;

import java.util.List;

/**
 * Created by Администратор on 18.05.2017.
 */
public interface InspectionBoard {
    void apply(ApplicantQueue queue);
    List<Applicant> getAppliedList();
}
