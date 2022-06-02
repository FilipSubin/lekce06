package com.engeto.lekce06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String FILENAME = "vat-eu.csv";
    public static final String DELIMITER = "\t";
    public static final int STATUS_CANNOT_READ_FILE = 1;

    public static void main(String[] args) {
        ImportOfStates states = null;
        try {
            states =
                    ImportOfStates.importFromFile(
                            FILENAME, DELIMITER);
        } catch (RuntimeException e) {
            System.err.println(
                    "Chyba při čtení souboru "+FILENAME
                            +":\n"+e.getLocalizedMessage());
            System.exit(STATUS_CANNOT_READ_FILE);
        }

//      Vypíše všechny státy v požadovaném tvaru
        for (State state : states.getListOfAllStates()) {
            System.out.println(state.getStateInfoLook1());
        }

//      Načte dotazovanou VAT hodnotu
        System.out.println("---------------------------");
        System.out.print("Vlož dotazovanou VAT hodnotu:");
        Integer input = null;
        input = Support.safeReadInt();
        System.out.println("---------------------------");
        if(input == -1){
            input = 20;
        }
        List<State> inStateList = new ArrayList<>();
        List<State> otherStateList = new ArrayList<>();

//      Roztřídí státy podle vložené VAT
        for (State state : states.getListOfAllStates()) {
            if(state.getFullTaxRate() > input && state.isSpecialDph() == false){
                inStateList.add(state);
                states.addStateToExport(state);
            }else{
                otherStateList.add(state);
            }
        }

//      Seřadí státy podle nevyšší VAT hodnoty
        Collections.sort(inStateList, new Comparator<State>() {
                    @Override
                    public int compare(State o1, State o2) {
                        return o1.getFullTaxAsString().compareTo(o2.getFullTaxAsString());
                    }
                }.reversed());


//      Vypíše vyhovující státy
        for (State state : inStateList) {
            System.out.println(state.getStateInfoLook2());
        }
        System.out.println("====================");
        System.out.print("Sazba VAT " + input + " % nebo nižší nebo používají speciální sazbu: ");
        for (State state : otherStateList) {
            System.out.print(state.getShortcut()+", ");
        }

//      Vyexportuje státy do .txt souboru
        states.exportToFile("vat-over-"+input+".txt", DELIMITER);
    }
}
