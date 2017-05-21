import controller.Controller;
import model.Model;
import model.boards.BiologyUniversityInspectionBoard;
import model.boards.MathematicUniversityInspectionBoard;
import model.boards.VersatileUniversityInspectionBoard;
import view.View;

/**
 * Created by Администратор on 18.05.2017.
 */
public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        model.addInspectionBoard(new MathematicUniversityInspectionBoard());
        model.addInspectionBoard(new BiologyUniversityInspectionBoard());
        model.addInspectionBoard(new VersatileUniversityInspectionBoard());

        View view = new View();
        new Controller(model, view).process();

    }
}
