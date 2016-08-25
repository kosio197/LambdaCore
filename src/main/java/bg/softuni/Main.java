package bg.softuni;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import bg.softuni.command_interpreter.CommandInterpreter;

public class Main {
    public static void main(String[] args)
            throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommandInterpreter commandInterpreter = new CommandInterpreter();

        while (true) {
            String command = br.readLine();
            if (command.equals("System Shutdown!")) {
                return;
            }

            String result = commandInterpreter.processCommand(command);
            if (result != null) {
                System.out.print(result);
            }
        }
    }
}
