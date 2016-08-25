package bg.softuni.fragment.model;

import bg.softuni.contract.Fragment;
import bg.softuni.enums.FragmentType;

public class BaseFragment implements Fragment {

    private String name;
    private FragmentType type;
    private Integer pressureAffection;

    protected BaseFragment(String name, Integer pressureAffection) {
        this.setName(name);
        this.setPressureAffection(pressureAffection);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public FragmentType getType() {
        return this.type;
    }

    @Override
    public Integer getPressureAffection() {
        return this.pressureAffection;
    }

    protected void setPressureAffection(Integer value) {
        this.pressureAffection = value;
    }

    protected void setName(String value) {
        this.name = value;
    }

    protected void setType(FragmentType value) {
        this.type = value;
    }

}
