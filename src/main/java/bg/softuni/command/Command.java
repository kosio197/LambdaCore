package bg.softuni.command;

import bg.softuni.aplication_service.AplicationServiceImpl;
import bg.softuni.contract.Executable;

public abstract class Command implements Executable {

    private String commandParam;
    private AplicationServiceImpl service;

    public Command(AplicationServiceImpl service, String commandParam) {
        setCommandParam(commandParam);
        this.service = service;
    }

    protected String getCommandParam() {
        return commandParam;
    }

    protected void setCommandParam(String commandParam) {
        this.commandParam = commandParam;
    }

    protected AplicationServiceImpl getService() {
        return service;
    }

}
