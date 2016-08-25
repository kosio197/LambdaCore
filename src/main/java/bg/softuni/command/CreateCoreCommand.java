package bg.softuni.command;

import bg.softuni.aplication_service.AplicationServiceImpl;

public class CreateCoreCommand extends Command {

    public CreateCoreCommand(AplicationServiceImpl service, String commandParam) {
        super(service, commandParam);
    }

    @Override
    public String execute() {
        return super.getService().createCore(getCommandParam());
    }

}
