package bg.softuni.contract;

import bg.softuni.enums.CoreStatus;

public interface Core {

    String getName();

    Integer getDurability();

    Integer getPressure();

    CoreStatus getStatus();

    void addFragment(Fragment fragment);

    Fragment removeFragment();

    int getCountOfAtachedFragment();
}
