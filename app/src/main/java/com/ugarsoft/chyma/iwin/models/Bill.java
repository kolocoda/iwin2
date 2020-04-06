package com.ugarsoft.chyma.iwin.models;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Chyma on 5/11/2016.
 */
public class Bill {

    @DatabaseField(generatedId = true)
    private Long id;
    @DatabaseField
    private Double company;
    @DatabaseField
    private int month;
    @DatabaseField
    private int hoursPerDay;
    @DatabaseField
    private int noOfOccupants;

    @DatabaseField
    private int incandescentLights;
    @DatabaseField
    private int energyLights;
    @DatabaseField
    private int securityLights;
    @DatabaseField
    private int fluorescentLights;
    @DatabaseField
    private int fans;
    @DatabaseField
    private int roomCooler;
    @DatabaseField
    private int fridge;
    @DatabaseField
    private int freezer;
    @DatabaseField
    private int acOneHp;
    @DatabaseField
    private int acOneHalfHp;
    @DatabaseField
    private int acTwoHp;
    @DatabaseField
    private int iron;
    @DatabaseField
    private int toaster;
    @DatabaseField
    private int waterHeater;
    @DatabaseField
    private int electricStove;
    @DatabaseField
    private int washingMachine;
    @DatabaseField
    private int pumpMotor;
    @DatabaseField
    private int blender;
    @DatabaseField
    private int homeTheatre;
    @DatabaseField
    private int microwave;
    @DatabaseField
    private int plasmaTv;

    @DatabaseField
    private int lcdTv;
    @DatabaseField
    private int ledTv;
    @DatabaseField
    private int printer;
    @DatabaseField
    private int hairDryer;
    @DatabaseField
    private int vacuumCleaner;
    @DatabaseField
    private int gameConsole;
    @DatabaseField
    private int phoneCharger;
    @DatabaseField
    private int coffeeMaker;
    @DatabaseField
    private int waterDispenser;
    @DatabaseField
    private int fishAquarium;
    @DatabaseField
    private int electricKettle;
    @DatabaseField
    private int deepFrier;
    @DatabaseField
    private int humidifier;


    public int getFridge() {
        return fridge;
    }

    public void setFridge(int fridge) {
        this.fridge = fridge;
    }

    public int getFreezer() {
        return freezer;
    }

    public void setFreezer(int freezer) {
        this.freezer = freezer;
    }

    public int getAcOneHp() {
        return acOneHp;
    }

    public void setAcOneHp(int acOneHp) {
        this.acOneHp = acOneHp;
    }

    public int getAcOneHalfHp() {
        return acOneHalfHp;
    }

    public void setAcOneHalfHp(int acOneHalfHp) {
        this.acOneHalfHp = acOneHalfHp;
    }

    public int getAcTwoHp() {
        return acTwoHp;
    }

    public void setAcTwoHp(int acTwoHp) {
        this.acTwoHp = acTwoHp;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getToaster() {
        return toaster;
    }

    public void setToaster(int toaster) {
        this.toaster = toaster;
    }

    public int getMicrowave() {
        return microwave;
    }

    public void setMicrowave(int microwave) {
        this.microwave = microwave;
    }

    public int getWaterHeater() {
        return waterHeater;
    }

    public void setWaterHeater(int waterHeater) {
        this.waterHeater = waterHeater;
    }

    public int getElectricStove() {
        return electricStove;
    }

    public void setElectricStove(int electricStove) {
        this.electricStove = electricStove;
    }

    public int getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(int washingMachine) {
        this.washingMachine = washingMachine;
    }

    public int getPumpMotor() {
        return pumpMotor;
    }

    public void setPumpMotor(int pumpMotor) {
        this.pumpMotor = pumpMotor;
    }

    public int getBlender() {
        return blender;
    }

    public void setBlender(int blender) {
        this.blender = blender;
    }

    public int getHomeTheatre() {
        return homeTheatre;
    }

    public void setHomeTheatre(int homeTheatre) {
        this.homeTheatre = homeTheatre;
    }

    public int getPlasmaTv() {
        return plasmaTv;
    }

    public void setPlasmaTv(int plasmaTv) {
        this.plasmaTv = plasmaTv;
    }

    public int getLcdTv() {
        return lcdTv;
    }

    public void setLcdTv(int lcdTv) {
        this.lcdTv = lcdTv;
    }

    public int getLedTv() {
        return ledTv;
    }

    public void setLedTv(int ledTv) {
        this.ledTv = ledTv;
    }

    public int getPrinter() {
        return printer;
    }

    public void setPrinter(int printer) {
        this.printer = printer;
    }

    public int getHairDryer() {
        return hairDryer;
    }

    public void setHairDryer(int hairDryer) {
        this.hairDryer = hairDryer;
    }

    public int getVacuumCleaner() {
        return vacuumCleaner;
    }

    public void setVacuumCleaner(int vacuumCleaner) {
        this.vacuumCleaner = vacuumCleaner;
    }

    public int getGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(int gameConsole) {
        this.gameConsole = gameConsole;
    }

    public int getPhoneCharger() {
        return phoneCharger;
    }

    public void setPhoneCharger(int phoneCharger) {
        this.phoneCharger = phoneCharger;
    }

    public int getCoffeeMaker() {
        return coffeeMaker;
    }

    public void setCoffeemaker(int coffeemaker) {
        this.coffeeMaker = coffeemaker;
    }

    public int getWaterDispenser() {
        return waterDispenser;
    }

    public void setWaterDispenser(int waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

    public int getFishAquarium() {
        return fishAquarium;
    }

    public void setFishAquarium(int fishAquarium) {
        this.fishAquarium = fishAquarium;
    }

    public int getElectricKettle() {
        return electricKettle;
    }

    public void setElectricKettle(int electricKettle) {
        this.electricKettle = electricKettle;
    }

    public int getDeepFrier() {
        return deepFrier;
    }

    public void setDeepFrier(int deepFrier) {
        this.deepFrier = deepFrier;
    }

    public Double getCompanyTariff() {
        return company;
    }

    public void setCompany(Double company) {
        this.company = company;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public int getNoOfOccupants() {
        return noOfOccupants;
    }

    public void setNoOfOccupants(int noOfOccupants) {
        this.noOfOccupants = noOfOccupants;
    }

    public int getIncandescentLights() {
        return incandescentLights;
    }

    public void setIncandescentLights(int incandescentLights) {
        this.incandescentLights = incandescentLights;
    }

    public int getEnergyLights() {
        return energyLights;
    }

    public void setEnergyLights(int energyLights) {
        this.energyLights = energyLights;
    }

    public int getSecurityLights() {
        return securityLights;
    }

    public void setSecurityLights(int securityLights) {
        this.securityLights = securityLights;
    }

    public int getFluorescentLights() {
        return fluorescentLights;
    }

    public void setFluorescentLights(int fluorescentLights) {
        this.fluorescentLights = fluorescentLights;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getRoomCooler() {
        return roomCooler;
    }

    public void setRoomCooler(int roomCooler) {
        this.roomCooler = roomCooler;
    }

    public int getHumidifier() {
        return humidifier;
    }

    public void setHumidifier(int humidifier) {
        this.humidifier = humidifier;
    }
}
