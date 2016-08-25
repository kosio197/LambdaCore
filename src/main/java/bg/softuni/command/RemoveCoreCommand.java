package bg.softuni.command;

import bg.softuni.aplication_service.AplicationServiceImpl;

public class RemoveCoreCommand extends Command {

    public RemoveCoreCommand(AplicationServiceImpl service, String commandParam) {
        super(service, commandParam);
    }

    @Override
    public String execute() {
        return super.getService().removeCore(getCommandParam());
    }

}
