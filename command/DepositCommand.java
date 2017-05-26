package command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle("resources.deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        do
        {
            try
            {
                String[] s = ConsoleHelper.getValidTwoDigits(manipulator.getCurrencyCode());
                int one = Integer.parseInt(s[0]), two = Integer.parseInt(s[1]);
                manipulator.addAmount(one, two);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), one, two));
                break;
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }while (true);

    }
}
