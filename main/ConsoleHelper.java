package main;

import exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle("resources.common_en", Locale.ENGLISH);


    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String askCurrencyCode() throws InterruptOperationException
    {
        writeMessage(res.getString("choose.currency.code"));
        while (true){
            String currencyCode = readString();
            if (currencyCode.matches("[a-zA-Z]{3}")) return currencyCode.toUpperCase();
            else writeMessage(res.getString("invalid.data"));
        }
    }
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        String[] result;
        result = readString().split(" ");
        if (result.length != 2) throw new NumberFormatException();
        int denom = Integer.parseInt(result[0]);
        int count = Integer.parseInt(result[1]);
        if (denom < 0 || count < 0) throw new NumberFormatException();
        return result;
    }
    public static Operation askOperation() throws InterruptOperationException{
        while (true)
        {
            ConsoleHelper.writeMessage(String.format("%s: 1 - %s, 2 - %s, 3 - %s, 4 - %s",res.getString("choose.operation"), res.getString("operation.INFO"),res.getString("operation.DEPOSIT"),res.getString("operation.WITHDRAW"),res.getString("operation.EXIT")));
            try
            {
                return Operation.getAllowableOperationByOrdinal(Integer.valueOf(readString()));
            }catch (IllegalArgumentException e){
              writeMessage(res.getString("invalid.data"));
            }catch (InterruptOperationException e){
                throw new InterruptOperationException();
            }
        }
    }
    public static String readString() throws InterruptOperationException
    {
        String s = null;
        try{
            s = reader.readLine();
            if(s.toUpperCase().equals("EXIT")) {
                ConsoleHelper.writeMessage(res.getString("the.end"));
                throw new InterruptOperationException();
            }
        }
        catch (IOException e) {}
        return s;
    }
}
