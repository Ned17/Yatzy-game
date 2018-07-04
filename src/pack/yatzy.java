package pack;

import java.util.*;

public class yatzy {

        static int nrOfDice = 5;
        static int nrOfDieValues = 6;
        static boolean miniMenu = false;
        static int userInput = 0;
        static int[] dice = {nrOfDice};





        public static void main(String[] args) {
            dice = new int[]{0, 0, 0, 0, 0};
            while(userInput != -1) {


                printMenu();


            }

        }




        static void printMenu() {
            Scanner inputUserchoice = new Scanner(System.in);

            if (miniMenu == false) {

                System.out.println("\nMENU:");
                System.out.println("0. Display the menu:");
                System.out.println("1. Make a random throw");
                System.out.println("2. Enter die values for a throw");
                System.out.println("3. Display the die values for the throw");
                System.out.println("4. Display the score for the throw");
                System.out.println("-1. End program" + " \n");

                System.out.print("\n" + "Make your choice: ");
                userInput = inputUserchoice.nextInt();

                miniMenu = true;

            }else {
                System.out.println("");
                System.out.print("\nMake your choice: ");
                userInput = inputUserchoice.nextInt();
            }


            switch (userInput) {
                case 0:
                    miniMenu = false;
                    printMenu();
                    break;
                case 1:
                    throwDice(dice, nrOfDice, nrOfDieValues);
                    break;
                case 2:
                    readDieValues(dice, nrOfDice);
                    break;
                case 3:
                    printDice(dice, nrOfDice);
                    break;
                case 4:
                    printScores(dice, nrOfDice, nrOfDieValues);
                    break;
                case -1:
                    System.out.println("out!!!!");
                    break;
                default:
                    System.out.println("invalid no");
            }

        }

        private static void throwDice(int[] dice, int nrOfDice, int nrOfDieValues) {


            System.out.print("\nEnter seed (1 gives a random seed):");

            Scanner input = new Scanner(System.in);
            int seed = input.nextInt();

            Random rnd = new Random();

            if(seed == 1){
                Random rand = new Random();
                rnd.setSeed(rand.nextInt());

            }else {
                rnd.setSeed(seed);
            }

            //int randomNo = rnd.nextInt(nrOfDieValues - 1 + 1) + 1;

            System.out.print("Your dice: ");
            for(int i = 0; i < nrOfDice; i++) {
                int randomNo = 1+rnd.nextInt(nrOfDieValues);
                dice[i] = randomNo;
                System.out.print(" " + randomNo);
            }

        }


        static void readDieValues(int dice[], int nrOfDice) {

            Scanner input = new Scanner(System.in);
            int userInput;

            for(int i = 0 ; i <= nrOfDice -1 ; i++) {

                System.out.print("Die " + (i + 1) + ": ");
                userInput = input.nextInt();
                if (userInput >= 1 && userInput <= 6) {
                    dice[i] = userInput;
                }else {
                    System.out.println("Number should be between 1-6 !!! ");
                    i--;
                }
            }
        }

        static void printDice(int dice[], int nrOfDice) {
            for(int i = 0; i <= nrOfDice -1; i++) {

                System.out.print(dice[i] + " ");


            }


        }

        static void printScores(int dice[], int nrOfDice, int nrOfDieValues) {

            if(isFourOfAkind(dice, nrOfDieValues) != 0){
                int dieValue = isFourOfAkind(dice, nrOfDieValues);
                System.out.println("Four of a kind "  + dieValue + ":s, " + (dieValue*4) + " points");
            }else{
                System.out.println("No four of a kind");
            }

            if (isThreeOfAKind(dice, nrOfDieValues)!= 0){
                int dieValue = isThreeOfAKind(dice, nrOfDieValues);
                System.out.println("Three of a kind "  + dieValue + ":s, " + (dieValue*3) + " points");

            }else if(isThreeOfAKind(dice, nrOfDieValues)== 0){
                System.out.println("No three of a kind");
            }

            if(isSmallStraight(dice, nrOfDieValues) == 1){
                System.out.println("Small Straight : 15 points");
            }else if(isSmallStraight(dice, nrOfDieValues) == 0){
                System.out.println("No small straight");
            }

            if(isLargeStraight(dice, nrOfDieValues)==1) {
                System.out.println("Large Straight : 20 points");
            }else if(isLargeStraight(dice, nrOfDieValues) == 0){
                System.out.println("No large straight");
            }
        }

        static int isFourOfAkind(int dieValues[], int nrOfDieValues) {
            for(int i = 0; i < dieValues.length; i++) {
                for (int j = i+1 ; j < dieValues.length; j++ ) {
                    if(dieValues[i] == dieValues[j]) {
                        for (int k = j+1; k < dieValues.length; k++) {
                            if(dieValues[j] == dieValues[k]) {
                                for (int l=k+1; l < dieValues.length; l++) {
                                    if(dieValues[k] == dieValues[l]) {
                                        return dieValues[l];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return 0;
        }

         static int isThreeOfAKind(int dieValues[], int nrOfDieValues) {
            nrOfDieValues = 0;
             for(int i = 0; i < dieValues.length; i++) {
                 for (int j = i+1 ; j < dieValues.length; j++ ) {
                     if(dieValues[i] == dieValues[j]) {
                         for (int k = j+1; k < dieValues.length; k++) {
                             if((dieValues[j] == dieValues[k]) && isFourOfAkind(dice, nrOfDieValues)==0) {
                                 return dieValues[i];
                             }
                         }
                     }
                 }
             }
          return 0;
        }

        static int isSmallStraight(int dieValues[], int nrOfDieValue) {
            int[] sortedArr = (int[])dieValues.clone();
            int[] smallStraight = new int[]{1, 2, 3, 4, 5};
            Arrays.sort(sortedArr);
            return Arrays.equals(smallStraight, sortedArr) ? 1 : 0;
        }

        static int isLargeStraight(int dieValues[], int nrOfDieValue) {
            int[] sortedArr = (int[])dieValues.clone();
            int[] smallStraight = new int[]{2, 3, 4, 5, 6};
            Arrays.sort(sortedArr);
            return Arrays.equals(smallStraight, sortedArr) ? 1 : 0;
        }


    }


