package bg.softuni.command;

import bg.softuni.aplication_service.AplicationServiceImpl;

public class SelectCoreCommand extends Command {

    public SelectCoreCommand(AplicationServiceImpl service, String commandParam) {
        super(service, commandParam);
    }

    @Override
    public String execute() {
        return super.getService().selectCore(getCommandParam());
    }

}
