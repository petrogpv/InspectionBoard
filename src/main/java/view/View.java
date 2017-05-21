package view;

import model.Model;

/**
 * Created by Администратор on 19.05.2017.
 */
public class View {
    public void printResultMessage (Model model){
        model.getInspectionBoards().stream()
                .forEach(inspectionBoard ->
                        System.out.println(inspectionBoard.getClass().getSimpleName() +
                                "applied " +
                                inspectionBoard.getAppliedList().size()));
    }
}
