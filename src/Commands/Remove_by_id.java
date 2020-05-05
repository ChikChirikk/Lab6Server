package Commands;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Removes element by its id
 *
 * @author Diana
 */
public class Remove_by_id implements Commandable {
    HumanCollection humans = new HumanCollection();
    String name = "remove_by_id";

    public String getName() {
        return name;
    }

    /**
     * @param arg id
     * @return
     */
    public Object execute(Object arg) {
        try {
            String ans = "Нет челика с таким id.";
            if (humans.getArray().stream().filter(h -> h.getId() == Long.parseLong((String) arg)).count() != 0) {
                humans.setArray((ArrayList) humans.getArray().stream()
                        .filter(h -> h.getId() == Long.parseLong((String) arg))
                        .collect(Collectors.toList()));
                return "Челик успешно удален";
            } else return "Челик успешно удален";

        } catch (NumberFormatException exp) {
            return ("Значение аргумента должно быть типа:\"long\".");

        }

    }
}
