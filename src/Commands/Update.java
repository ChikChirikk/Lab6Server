package Commands;

import FillCollection.HumanBeing;

import java.util.ArrayList;

/**
 * add update element in collection by its id
 *
 * @author Polina
 */
public class Update implements CommandWithObject {
    static long arg;
    String whyFailed;
    HumanCollection humans = new HumanCollection();
    String name = "update";

    public String getName() {
        return name;
    }

    /**
     * @param hum id of element
     * @return
     */
    public Object execute(Object hum) {
        if (((HumanBeing) hum).getName() != null) {
            HumanBeing human = (HumanBeing) hum;
            ArrayList collection = new ArrayList();
            collection = humans.getArray();
            collection.set((int) arg, human);
            humans.setArray(collection);
            return ("Челик [id:" + arg + "] успешно обновлен.");
        }
        return "";
    }


    @Override
    public boolean check(String arg) {
        try {
            int id = Integer.parseInt(arg);
            Update.arg = id;
            if (humans.getSize() == 0) {
                whyFailed = "Коллекция итак пустая";
                return false;
            } else if (humans.findIndexOfElemById(id) == -1) {
                whyFailed = "Такого элемента нет в коллекции";
                return false;
            } else return true;
        } catch (Exception e) {
            whyFailed = "Неверный формат аргумента";
            return false;
        }
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}

