package bg.softuni.contract;

import bg.softuni.enums.FragmentType;

public interface Fragment {

    String getName();

    FragmentType getType();

    Integer getPressureAffection();
}
