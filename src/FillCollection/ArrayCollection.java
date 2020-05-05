package FillCollection;

import Commands.HumanCollection;
import Utilites.JSONDecoder;
import Utilites.ReaderFromFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * complete the program
 *
 * @author Polina
 */
public class ArrayCollection{
    ReaderFromFile reader = new ReaderFromFile();
    HumanCollection collection = new HumanCollection();
    ArrayList<HumanBeing> humans = new ArrayList<HumanBeing>();
    public ArrayCollection(){
        HumanBeing.setN(0);
    }
    /**
     * @param filename to file
     * @see JSONDecoder
     * @see ReaderFromFile
     */

    public String fillCollection(String filename) throws ClassNotFoundException, IOException {
        try {
            String data = reader.read(filename);
            HashMap<String, String>[] mapsOfParams = JSONDecoder.getParamsOfExemplars(data);
            for (HashMap mapOfParams : mapsOfParams) {
                HumanBeing human = new HumanBeing();
                human.setCreationDate(LocalDate.now());
                if (mapOfParams.get("id") != null) {
                    human.setId(Long.parseLong((String) mapOfParams.get("id")));
                    HumanBeing.setN(Long.parseLong((String) mapOfParams.get("id")));
                }
                human.setName((String) mapOfParams.get("name"));
                human.setHasToothpick(Boolean.parseBoolean((String) mapOfParams.get("hasToothpick")));
                human.setRealHero(Boolean.parseBoolean((String) mapOfParams.get("realHero")));
                human.setImpactSpeed(Double.parseDouble((String) mapOfParams.get("impactSpeed")));
                human.setSoundtrackName((String) mapOfParams.get("soundtrackName"));
                human.setMinutesOfWaiting(Double.parseDouble((String) mapOfParams.get("minutesOfWaiting")));
                HumanBeing.Car car = human.new Car();
                car.setCarName((String) mapOfParams.get("carName"));
                car.setCarCool(Boolean.parseBoolean((String) mapOfParams.get("carCool")));
                human.setCar(car);
                HumanBeing.Coordinates coords = human.new Coordinates();
                coords.setX(Long.parseLong((String) mapOfParams.get("x")));
                if (Long.parseLong((String) mapOfParams.get("y")) <= 867)
                    coords.setY(Long.parseLong((String) mapOfParams.get("y")));
                else throw new IllegalArgumentException("Значение \"y\" превышает 876");
                human.setCoordinates(coords);
                human.setWeaponType(HumanBeing.WeaponType.valueOf((String) mapOfParams.get("weaponeType")));
                humans.add(human);

            }
            collection.setArray(humans);
            return ("Коллекция успешно заполнена\n");
        } catch (Exception e) {
            return reader.whyFailed();

        }
    }
}
