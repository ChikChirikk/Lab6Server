package Commands;

import java.io.IOException;

import Utilites.JSONDecoder;
import Utilites.WriterToFile;
/**
// * save collection to file
// * @author Polina
// */
public class Save implements CommandWithoutArg{
	HumanCollection humans = new HumanCollection();
	String name = "save";
    /**
     * @param arg ignore this
     */
	@Override
	public Object execute(Object arg) throws IOException {
		try {
			WriterToFile writer = new WriterToFile();
			String data = JSONDecoder.collectionToFile(humans);
			WriterToFile.writeToFile(data);
			System.out.println("Коллекция успешно сохранена");
			return null;
		}
		catch (Exception e){
			System.out.println("Коллекция пустая");
			return null;
		}
	}

	@Override
	public String getName() {
		return name;
	}
}