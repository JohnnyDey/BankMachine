package main;

import command.CommandExecutor;
import exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static final String RESOURCE_PATH = "resources.";
    public static void main(String[] args){
        try
        {
            Locale.setDefault(Locale.ENGLISH);
            CommandExecutor.execute(Operation.LOGIN);
            Operation isOperation;
            do{
                isOperation = ConsoleHelper.askOperation();
                CommandExecutor.execute(isOperation);
            }while(isOperation != Operation.EXIT);
        }catch (InterruptOperationException e){
            //ConsoleHelper.writeMessage("Good bye!");
        }
    }
}
