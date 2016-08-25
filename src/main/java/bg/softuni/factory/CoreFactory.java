package bg.softuni.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import bg.softuni.contract.Core;

public class CoreFactory {

    private static char coreName = 65;
    private static final String CORE_MODEL_PACKAGE = "bg.softuni.core.model.";

    public Core createCore(String type, int durability)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Core core = null;
        String name = getName();

        Class<?> coreClass = Class.forName(CORE_MODEL_PACKAGE + type + "Core");
        Constructor<?> ctor = coreClass.getConstructor(String.class, Integer.class);
        core = (Core) ctor.newInstance(name, durability);

        return core;
    }

    private String getName() {
        return String.valueOf(coreName++);
    }
}
