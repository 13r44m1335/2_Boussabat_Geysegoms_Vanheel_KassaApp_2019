package model;

import model.SoortKorting;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class KortingFactory {
    private static KortingFactory ourInstance = new KortingFactory();

    public static KortingFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new KortingFactory();
        }
        return ourInstance;
    }

    private KortingFactory() {
    }

    public Korting create(String input) {
        Object korting = null;
        SoortKorting soortKorting = SoortKorting.valueOf((String)input);
        try {
            Class<?> clazz = Class.forName(soortKorting.getKlasseNaam());
            Constructor<?> constructor = clazz.getConstructor(new Class[0]);
            korting = (Korting)constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(korting.toString());
        return (Korting)korting;
    }
}