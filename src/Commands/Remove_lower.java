package Commands;

import FillCollection.HumanBeing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Removes all movies from collection
 *
 * @author Diana
 */
public class Remove_lower implements CommandWithoutArg, CommandWithObject {
    String whyFailed;
    HumanCollection humans = new HumanCollection();
    String name = "remove_lower";
    public String getName() {
        return name;
    }
    /**
     * @param arg ignore this
     * @return
     */
    @Override
    public Object execute(Object arg) throws IOException {
        try {
            if (((HumanBeing) arg).getName() != null) {
                humans.setArray((ArrayList) humans.getArray().stream()
                        .filter(h -> h.compareTo((HumanBeing) arg) > 0)
                        .collect(Collectors.toList()));
                return ("Все элементы коллекции, меньшие, чем заданный успешно удалены");
            }
            else return "";
        } catch (Exception e) {
            return ("Коллекция итак пустая.");
        }
    }

    @Override
    public boolean check(String arg) {
        if (humans.getSize() > 0)
        return true;
        else{
            whyFailed = "Коллекция итак пустая";
        return false;
        }
    }

    @Override
    public String whyFailed() {
        return whyFailed;
    }
}
