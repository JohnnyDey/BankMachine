package command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;
import exception.InterruptOperationException;
import exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle("resources.withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(ConsoleHelper.askCurrencyCode());
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        int sum = 0;
        do{
            try
            {
                sum = Integer.parseInt(ConsoleHelper.readString());
                if(sum <= 0) throw new NumberFormatException();
                if (!manipulator.isAmountAvailable(sum)) throw new NotEnoughMoneyException();
                Map<Integer, Integer> map = manipulator.withdrawAmount(sum);
                List<Integer> list = new ArrayList<>(map.keySet());
                Collections.sort(list);
                Collections.reverse(list);
                for(Integer i:list){
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), i, map.get(i)));
                }

                break;
            }catch (NumberFormatException e){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
            catch (NotEnoughMoneyException e)
            {
                if(manipulator.getTotalAmount() >= sum) ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                else ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
        }while (true);
    }
}
