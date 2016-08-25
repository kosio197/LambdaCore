package bg.softuni.command.interpreter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import bg.softuni.annotation.Alias;
import bg.softuni.contract.Executable;
import bg.softuni.contract.Interpreter;
import bg.softuni.framework.annotation.Component;
import bg.softuni.framework.annotation.Inject;
import bg.softuni.framework.object.contract.ObjectContainer;

@Component
public class CommandInterpreter implements Interpreter {
    @Inject
    ObjectContainer container;

    private static final String COMMAND_LOCATION = "src/main/java/bg/softuni/command";
    private static final String COMMAND_PAKAGE = "bg.softuni.command.";

    @Override
    public void interpretCommand(String command) {
        Executable currentCommand;
        try {
            currentCommand = parseCommand(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentCommand.execute();
    }

    private Executable parseCommand(String command) throws IOException {
        File commandsFolder = new File(COMMAND_LOCATION);
        Executable executable = null;

        for (File file : commandsFolder.listFiles()) {
            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            try {
                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
                @SuppressWarnings("unchecked")
                Class<Executable> exeClass = (Class<Executable>) Class.forName(COMMAND_PAKAGE + className);

                if (!exeClass.isAnnotationPresent(Alias.class)) {
                    continue;
                }

                Alias alias = exeClass.getAnnotation(Alias.class);
                String value = alias.value();

                if (!value.equalsIgnoreCase(command)) {
                    continue;
                }

                Constructor<Executable> exeCtor = exeClass.getConstructor();
                executable = exeCtor.newInstance();

                this.injectDependancies(executable, exeClass);

            } catch (ReflectiveOperationException roe) {
                roe.printStackTrace();
            }
        }
        return executable;
    }

    private void injectDependancies(Executable executable, Class<Executable> exeClass)
            throws ReflectiveOperationException {
        Field exeClassField[] = exeClass.getDeclaredFields();

        for (Field field : exeClassField) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }

            field.setAccessible(true);
            field.set(executable, container.getInstance(field.getType()));
        }
    }

}
