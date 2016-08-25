package bg.softuni.core.model;

public class ParaCore extends AbstractCore {

    public ParaCore(String name, Integer durability) {
        super(name, durability);
    }

    @Override
    protected void setDurability(Integer durability) {
        super.setDurability(durability / 3);
    }
}
