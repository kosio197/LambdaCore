package bg.softuni.command;

import bg.softuni.aplication_service.AplicationServiceImpl;

public class AttachFragmentCommand extends Command {

    public AttachFragmentCommand(AplicationServiceImpl service, String commandParam) {
        super(service, commandParam);
    }

    @Override
    public String execute() {
        return super.getService().attachFragment(getCommandParam());
    }

}
