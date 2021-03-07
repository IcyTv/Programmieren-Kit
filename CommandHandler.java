import java.util.HashMap;

import edu.kit.informatik.Terminal;

public class CommandHandler {
    private final HashMap<String, CommandAction> commandMapper;
    private final HashMap<String, String> regexMapper;

    public CommandHandler() {
        this.commandMapper = new HashMap<String, CommandAction>();
        this.regexMapper = new HashMap<String, String>();
    }

    public void addCommand(String command,CommandAction action, String regex) {
        this.commandMapper.put(command, action);
        this.regexMapper.put(command, regex);
    }

    public void handle(String command) {
        String[] split = command.split(" ");

        CommandAction action = commandMapper.get(split[0]);

        if(action == null) {
            handleError("Unknown command");
            return;
        }

        if(!split[1].matches(regexMapper.get(split[0]))) {
            handleError("Wrong arguments");
            return;
        }

        action.use(split[1].split(";"));
    }

    public void handleError(String msg) {
        Terminal.println("Error, " + msg);
    }
}