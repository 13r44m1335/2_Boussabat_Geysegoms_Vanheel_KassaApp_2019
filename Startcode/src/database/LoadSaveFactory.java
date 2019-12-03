package model;

import database.LoadSave;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import model.SoortOpslag;

public class LoadSaveFactory {
    private static LoadSaveFactory ourInstance = new LoadSaveFactory();

    public static LoadSaveFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new LoadSaveFactory();
        }
        return ourInstance;
    }

    private LoadSaveFactory() {
    }

    public LoadSave createLoadSave(Properties properties) {
        LoadSave loadSave = null;
        Object inputObj = properties.get("input");
        String input = (String)inputObj;
        SoortOpslag soortOplslag = SoortOpslag.valueOf((String)input);
        try {
            Class<?> clazz = Class.forName(soortOplslag.getKlasseNaam());
            Constructor<?> constructor = clazz.getConstructor(new Class[0]);
            loadSave = (LoadSave)constructor.newInstance(new Object[0]);
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
        return loadSave;
    }
}