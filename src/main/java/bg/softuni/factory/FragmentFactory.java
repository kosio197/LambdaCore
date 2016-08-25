package bg.softuni.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import bg.softuni.contract.Fragment;

public class FragmentFactory {

    private static final String FRAGMENT_MODEL_PACKAGE = "bg.softuni.fragment.model.";

    public Fragment createFragment(String type, String name, Integer pressureAffection)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Fragment fragment = null;

        Class<?> fragmentClass = Class.forName(FRAGMENT_MODEL_PACKAGE + type + "Fragment");
        Constructor<?> ctor = fragmentClass.getConstructor(String.class, Integer.class);
        fragment = (Fragment) ctor.newInstance(name, pressureAffection);

        return fragment;
    }
}
