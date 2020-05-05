package Commands;

import FillCollection.HumanBeing;
import org.w3c.dom.ls.LSOutput;

/**
 * add new element in collection
 * @author Polina
 */
public class Add implements CommandWithObject, CommandWithoutArg{
	HumanCollection humans = new HumanCollection();
	String name = "add";
	public String getName() {
		return name;
	}
    /**
	 * @param arg ignore this
	 * @return
	 */
	public Object execute(Object arg){
		if (((HumanBeing) arg).getName() != null) {
			humans.addToCollection((HumanBeing) arg);
			return "Челик успешно добавлен в коллекцию";
		}
		else return "";
	}

	@Override
	public boolean check(String arg) {
		return true;
	}

	@Override
	public String whyFailed() {
		return null;
	}
}