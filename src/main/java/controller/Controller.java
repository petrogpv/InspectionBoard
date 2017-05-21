package controller;

import model.Model;
import model.applicant.Applicant;
import model.applicant.ApplicantPool;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Producer producer = new Producer(model.getApplicantPool(), model.getApplicantQueue());
        producer.start();

        List<Consumer> consumers = new ArrayList<>();

        model.getInspectionBoards().stream().forEach(inspectionBoard ->
                consumers.add(new Consumer(producer, inspectionBoard, model.getApplicantQueue())));

        try {
            executorService.invokeAll(consumers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        view.printResultMessage(model);

        System.exit(0);


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
