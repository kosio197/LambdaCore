package bg.softuni.fragment.model;

import bg.softuni.enums.FragmentType;

public class NuclearFragment extends BaseFragment {

    public NuclearFragment(String name, Integer pressureAffection) {
        super(name, pressureAffection);
        this.setType(FragmentType.Nuclear);
    }

    @Override
    protected void setPressureAffection(Integer value) {
        super.setPressureAffection(value * 2);
    }
}
