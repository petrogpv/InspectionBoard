package controller;

import model.Model;
import model.applicant.Applicant;
import model.applicant.ApplicantPool;
import model.universities.BiologyUniversity;
import model.universities.MathematicUniversity;
import model.universities.University;
import model.universities.VersatileUniversity;
import view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Администратор on 19.05.2017.
 */
public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void process(){
        applicationPoolFill();

        QueueProducer queueProducer = new QueueProducer(model.getApplicantPool(),
                model.getApplicantQueue());

        List<QueueConsumer> queueConsumers = new ArrayList<QueueConsumer>();
        model.getUniversities().stream().forEach(university ->
                queueConsumers.add(new QueueConsumer(queueProducer, university, model.getApplicantQueue())));

        queueProducer.start();
        queueConsumers.stream().forEach(consumer -> consumer.start());


    }
    private void applicationPoolFill(){
        ApplicantPool applicantPool = model.getApplicantPool();
        for (int i = 0; i < ControllerConstants.MATHEMATICIAN_APPLICANTS_AMOUNT; i++) {
            applicantPool.addApplicant(Applicant.MATHEMATICIAN);
        }
        for (int i = 0; i < ControllerConstants.BIOLOGY_APPLICANTS_AMOUNT; i++) {
            applicantPool.addApplicant(Applicant.BIOLOGIST);
        }
        applicantPool.shuffle();
    }


}
