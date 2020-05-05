package Commands;
/**
 * Remove first element
 * @author Polina
 */
public class Remove_first implements CommandWithoutArg {
	HumanCollection humans = new HumanCollection();
	String name = "remove_first";
	public String getName() {
		return name;
	}
    /**
	 * @param arg ignore this
	 * @return
	 */
	public Object execute(Object arg){
		try{
			humans.removeHuman(0);
		return ("Первый челик успешно удален");
		}
		catch(IndexOutOfBoundsException e) {
			return ("Коллекция пустая");
		}
	}
}