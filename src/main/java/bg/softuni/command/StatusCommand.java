package bg.softuni.command;

import bg.softuni.aplication_service.AplicationServiceImpl;

public class StatusCommand extends Command {

    public StatusCommand(AplicationServiceImpl service, String commandParam) {
        super(service, commandParam);
    }

    @Override
    public String execute() {
        return super.getService().getStatus(getCommandParam());
    }

}
