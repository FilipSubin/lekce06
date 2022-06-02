package com.engeto.lekce06;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportOfStates {
    private List<State> stateList = new ArrayList<>();
    private List<State> stateListToExport = new ArrayList<>();

    public static ImportOfStates importFromFile(
            String filename, String delimiter)
            throws RuntimeException {
        String nextLine = "";
        String[] items = new String[1];
        String shortcut;
        String name;
        double fullTaxRate;
        double loweredTaxRate;
        boolean specialDph;
        int lineNumber = 0;

        ImportOfStates result = new ImportOfStates();

        try (Scanner scanner =
                     new Scanner(new BufferedReader(
                             new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                items = nextLine.split(delimiter);

                shortcut = items[0];
                name = items[1];
                fullTaxRate = Double.parseDouble(items[2].replace(',','.'));
                loweredTaxRate = Double.parseDouble(items[3].replace(",", "."));
                specialDph = Boolean.parseBoolean(items[4]);

                result.addState(new State(shortcut,name, fullTaxRate, loweredTaxRate, specialDph));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void exportToFile(String filename, String delimiter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (State state : stateListToExport) {
                writer.println(
                        state.getShortcut()+delimiter+state.getName()+delimiter+state.getFullTaxRate()+delimiter+state.getLoweredTaxRate()+delimiter+state.isSpecialDph()
                );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addState(State state) {
        stateList.add(state);
    }

    public void addStateToExport(State state) {
        stateListToExport.add(state);
    }

    public List<State> getListOfAllStates() {
        List<State> result = new ArrayList<>();
        for (State state : stateList) {
            result.add(state);
        }
        return result;
    }
}
