package bg.softuni.core.model;

import java.util.ArrayList;
import java.util.List;

import bg.softuni.contract.Core;
import bg.softuni.contract.Fragment;
import bg.softuni.enums.CoreStatus;

public class AbstractCore implements Core {

    private String name;
    private Integer durability;
    private Integer pressure;
    private CoreStatus status;
    private List<Fragment> fragments;

    public AbstractCore(String name, Integer durability) {
        this.name = name;
        setDurability(durability);
        setPressure(0);
        setStatus(CoreStatus.NORMAL);
        this.fragments = new ArrayList<>();
    }

    @Override
    public Integer getPressure() {
        return this.pressure;
    }

    @Override
    public CoreStatus getStatus() {
        return this.status;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getDurability() {
        if (this.pressure <= 0) {
            return this.durability;
        }
        return this.durability - this.pressure >= 0 ? this.durability - this.pressure : 0;
    }

    protected void setDurability(Integer durability) {
        this.durability = durability;
    }

    protected void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    protected void setStatus(CoreStatus status) {
        this.status = status;
    }

    @Override
    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
        this.pressure += fragment.getPressureAffection();
        if (this.pressure > 0) {
            setStatus(CoreStatus.CRITICAL);
        } else {
            setStatus(CoreStatus.NORMAL);
        }

    }

    @Override
    public Fragment removeFragment() {
        Fragment fragment = null;
        if(this.fragments.size()>0){
            fragment =  this.fragments.remove(this.fragments.size()-1);
            this.pressure -= fragment.getPressureAffection();
            if (this.pressure > 0) {
                setStatus(CoreStatus.CRITICAL);
            } else {
                setStatus(CoreStatus.NORMAL);
            }
        }

        return fragment;
    }

    @Override
    public String toString() {
        return String.format("Core %s:\n####Durability: %d\n####Status: %s\n", getName(), getDurability(),
                getStatus().name());
    }

    @Override
    public int getCountOfAtachedFragment() {
        return this.fragments.size();
    }
}
