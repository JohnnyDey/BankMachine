package command;

import main.ConsoleHelper;
import main.CurrencyManipulator;
import main.CurrencyManipulatorFactory;

import java.util.ResourceBundle;


class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle("resources.info_en");
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean hasMoney = false;
        for(CurrencyManipulator manipulator: CurrencyManipulatorFactory.getAllCurrencyManipulators()){
            String money = String.valueOf(manipulator.getTotalAmount());
            if(manipulator.hasMoney() && !money.equals("0")){
                ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + money);
                hasMoney = true;
            }
        }
        if(!hasMoney) ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
