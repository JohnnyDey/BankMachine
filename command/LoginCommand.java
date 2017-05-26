package command;


import main.ConsoleHelper;
import exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command{
    private static ResourceBundle validCreditCards = ResourceBundle.getBundle("resources.verifiedCards", Locale.ENGLISH);
    private static ResourceBundle res = ResourceBundle.getBundle("resources.login_en", Locale.ENGLISH);
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        inval:while(true){

            String[] info = new String[2];
            info[0] = ConsoleHelper.readString();
            info[1] = ConsoleHelper.readString();
            if(info[0].length() != 12 || info[1].length() != 4){
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }
            for(String rb: validCreditCards.keySet()){
                if(info[0].equals(rb.toString()) && info[1].equals(validCreditCards.getString(rb))){
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), info[0]));
                    break inval;
                }
            }
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), info[0]));
            ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
        }
    }
}
