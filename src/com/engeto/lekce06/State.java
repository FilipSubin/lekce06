package com.engeto.lekce06;

public class State implements Comparable<State>{
    private String shortcut;
    private String name;
    private double fullTaxRate;
    private double loweredTaxRate;
    private boolean specialDph;

    public State(String shortcut, String name, double fullTaxRate, double loweredTaxRate, boolean specialDph) {
        this.shortcut = shortcut;
        this.name = name;
        this.fullTaxRate = fullTaxRate;
        this.loweredTaxRate = loweredTaxRate;
        this.specialDph = specialDph;
    }

    public String getStateInfoLook1() {
        return name + " (" + shortcut + "): " + fullTaxRate + " %";
    }

    public String getStateInfoLook2() {
        return name + " (" + shortcut + "): " + fullTaxRate + "%" + " (" + loweredTaxRate + " %)";
    }

    @Override
    public int compareTo(State other) {
        int compareNames = name.compareTo(other.name);
        if (compareNames == 0) {
            return name.compareTo(other.name);
        }
        return compareNames;
    }

    public String getFullTaxAsString() {
        return Double. toString(fullTaxRate);
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFullTaxRate() {
        return fullTaxRate;
    }

    public void setFullTaxRate(double fullTaxRate) {
        this.fullTaxRate = fullTaxRate;
    }

    public double getLoweredTaxRate() {
        return loweredTaxRate;
    }

    public void setLoweredTaxRate(double loweredTaxRate) {
        this.loweredTaxRate = loweredTaxRate;
    }

    public boolean isSpecialDph() {
        return specialDph;
    }

    public void setSpecialDph(boolean specialDph) {
        this.specialDph = specialDph;
    }
}
