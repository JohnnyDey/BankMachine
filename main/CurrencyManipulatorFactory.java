package main;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> list = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        for(Map.Entry<String, CurrencyManipulator> manipulator: list.entrySet()){
            if (manipulator.getKey().equals(currencyCode)) return  manipulator.getValue();
        }
        CurrencyManipulator manipulator = new CurrencyManipulator(currencyCode);
        list.put(currencyCode, manipulator);
        return manipulator;
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return list.values();
    }

    private CurrencyManipulatorFactory(){
    }
}
