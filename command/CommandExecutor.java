package command;

import main.Operation;
import exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    public static Map<Operation, Command> commandMap = new HashMap<>();
    static {
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        for(Map.Entry<Operation, Command> map : commandMap.entrySet()){
            if(map.getKey().equals(operation)) map.getValue().execute();
        }
    }
}
