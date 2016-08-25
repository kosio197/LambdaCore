package bg.softuni.command_interpreter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import bg.softuni.aplication_service.AplicationServiceImpl;
import bg.softuni.contract.Executable;

public class CommandInterpreter {

    private static final String APLICATION_COMMAND_PACKAGE = "bg.softuni.command.";
    private Map<String, Class<Executable>> commands;
    private AplicationServiceImpl service;

    public CommandInterpreter() {
        this.commands = new HashMap<>();
        this.service = new AplicationServiceImpl();
    }

    public String processCommand(String commandInfo) throws InstantiationException, IllegalAccessException,
    IllegalArgumentException, InvocationTargetException,
    NoSuchMethodException, SecurityException, ClassNotFoundException {

        String commandName = commandInfo.substring(0, commandInfo.indexOf(":"));
        if (!commands.containsKey(commandName)) {
            updateCommands(commandName);
        }

        Class<Executable> commandClass = commands.get(commandName);
        Constructor<?> ctor = commandClass.getConstructor(AplicationServiceImpl.class, String.class);
        Executable command = (Executable) ctor.newInstance(service, commandInfo);

        return command.execute();
    }

    private void updateCommands(String commandName) throws ClassNotFoundException {
        @SuppressWarnings("unchecked")
        Class<Executable> exeClass = (Class<Executable>) Class
        .forName(APLICATION_COMMAND_PACKAGE + commandName + "Command");
        this.commands.put(commandName, exeClass);
    }
}
