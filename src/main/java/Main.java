import controller.Controller;
import model.Model;
import model.universities.BiologyUniversity;
import model.universities.MathematicUniversity;
import model.universities.VersatileUniversity;
import view.View;

/**
 * Created by Администратор on 18.05.2017.
 */
public class Main {
    public static void main(String[] args) {

        Model model = new Model();
        model.addUniversity(new MathematicUniversity());
        model.addUniversity(new BiologyUniversity());
        model.addUniversity(new VersatileUniversity());

        View view = new View();
        new Controller(model, view).process();

    }
}
