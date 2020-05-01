package com;

import com.mhalan.tsp.OptimalSolutionDistance;
import com.mhalan.tsp.SimulatedAnnealing;
import com.mhalan.tsp.TabuSearch;

import java.io.IOException;
import java.util.Scanner;

public class RunTSP {

    private String tspFilePath = "";
    private String optimalTourFilePath = "";
    private boolean IS_OPTIMAL_OPTION_TURE = true;
    private boolean IS_OPTIMAL_OPTION_FALSE = false;

    public static void main(String[] args) throws IOException {


        RunTSP runTSP = new RunTSP();
        runTSP.showOptions();

    }


    public void showOptions() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nPlease select one of the below option: ");
        System.out.println("1. Enter Files Path");
        System.out.println("2. Test correct calculation of the geographical distance by optimal route");
        System.out.println("3. Simulated Annealing");
        System.out.println("4. Tabu Search");

        String selectedOption = userInput.nextLine();
        try {
            switch (selectedOption) {

                case "1":
                    filePathOption();
                    showOptions();
                case "2":
                    if (optimalTourFilePath.isEmpty() || tspFilePath.isEmpty()) {
                        System.out.println("|**************************************************************|");
                        System.out.println("|*** You have to enter both TSP and optimal tour files path ***|");
                        System.out.println("|**************************************************************|");
                        filePathOption();
                    } else {
                        OptimalSolutionDistance optimalSolutionDistance = new OptimalSolutionDistance();
                        optimalSolutionDistance.run(tspFilePath, optimalTourFilePath);
                        showOptions();
                    }
                case "3":
                    if (tspFilePath.isEmpty()) {
                        filePathOption();
                    } else {
                        System.out.println("Simulated Annealing Result:");
                        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
                        simulatedAnnealing.loadFile(tspFilePath);
                        simulatedAnnealing.run();
                        showOptions();
                    }
                case "4":
                    if (tspFilePath.isEmpty()) {
                        filePathOption();
                    } else {
                        System.out.println("Tabu Search Result:");
                        TabuSearch tabuSearch = new TabuSearch();
                        tabuSearch.loadFile(tspFilePath);
                        tabuSearch.run();
                        showOptions();
                    }
                default:
                    showOptions();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showOptions();
        }

    }

    public void filePathOption() {


        System.out.println("|---------------------------------------------------------|");
        System.out.println("|--------------------File Path Example--------------------|");
        System.out.println("|----------Linux Syntax: /home/joe/ulysses22.tsp ---------|");
        System.out.println("|--------Windows Syntax: C:\\home\\joe\\ulysses22.tsp--------|");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("Please select one of the below option: ");
        System.out.println("1. Enter ulysses22.tsp file path");
        System.out.println("2. Enter ulysses22.opt.tour file path");
        System.out.println("3. Back");

        String selectedOption;
        Scanner userInput = new Scanner(System.in);
        selectedOption = userInput.nextLine();

        switch (selectedOption) {
            case "1":
                System.out.println("Please Enter TSP file Path: ");
                userInput = new Scanner(System.in);
                tspFilePath = userInput.nextLine();
                filePathOption();
                break;
            case "2":
                System.out.println("Please Enter Optimal Tour file Path: ");
                userInput = new Scanner(System.in);
                optimalTourFilePath = userInput.nextLine();
                filePathOption();
                break;
            case "3":
                showOptions();
                break;
            default:
                showOptions();
        }
    }


}
