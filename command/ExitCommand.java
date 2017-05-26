package command;

import main.ConsoleHelper;
import exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle("resources.exit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
            ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
            String answer = ConsoleHelper.readString();
            if (answer.equals(res.getString("yes"))) {
                ConsoleHelper.writeMessage(res.getString("thank.message"));
            }
    }
}
