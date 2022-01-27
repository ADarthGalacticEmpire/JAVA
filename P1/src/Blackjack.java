import java.util.Scanner;

public class Blackjack {

    //blackjack class contains:
    // objects for P1Random and Scanner
    // and methods for execution in main

    public static P1Random randomNumber = new P1Random();
    public static Scanner scnr = new Scanner(System.in);

    //methods to play the game and reduce redundant code in main
    public static void printStartGame(int gameNumber){
        System.out.println("START GAME #" + gameNumber + "\n");
    }

    public static void printPlayerActionOptions() {
        //playerActions
        System.out.println("1. Get another card");
        System.out.println("2. Hold hand");
        System.out.println("3. Print statistics");
        System.out.println("4. Exit\n");
    }

    public static int getPlayerAction(){
        System.out.print("Choose an option: ");
        int playerAction;
        playerAction = scnr.nextInt();

        return playerAction;
    }

    public static int getCardFromDeck(){
        int card;
        card = randomNumber.nextInt(13) +1;

        return card;
    }

    public static String getCardFace(int cardValue){
        String[] cardFaces = new String[] {"KING", "QUEEN", "JACK", "ACE"};
        String cardFace = "";

        switch(cardValue){

            case 13: {
                cardFace = cardFaces[0];
                break;
            }
            case 12: {
                cardFace = cardFaces[1];
                break;
            }
            case 11: {
                cardFace = cardFaces[2];
                break;
            }
            case 1: {
                cardFace = cardFaces[3];
                break;
            }

        }

        return cardFace;
    }

    public static void getPrintForPlayerCardDrawn(int playerCard, int playerHand) {
        if (playerCard >= 2 && playerCard <= 10) {
            printPlayerCardAndHand(playerCard, playerHand);
        }
        else {
            String cardFace;
            cardFace = getCardFace(playerCard);
            printPlayerCardWithFace(cardFace, playerHand);
        }
    }

    public static void printPlayerCardAndHand(int playerCard, int playerHand){
        System.out.println("Your card is a " + playerCard + "!");
        printPlayerHand(playerHand);
    }

    public static void printPlayerCardWithFace(String cardFace, int playerHand) {
        System.out.println("Your card is a " + cardFace + "!");
        printPlayerHand(playerHand);
    }

    public static void printPlayerHand(int playerHand){
        System.out.println("Your hand is: " + playerHand + "\n");
    }

    public static int getDealerHand(){
        int dealerHand;
        dealerHand = randomNumber.nextInt(11) +16;

        return dealerHand;
    }

    public static void printAllHands(int dealerHand, int playerHand){
        System.out.println();
        System.out.println("Dealer's hand: " + dealerHand);
        System.out.println("Your hand is: " + playerHand);
        System.out.println();
    }

    //todo - printStatistics(int playerWins, int gamesPlayed);

    public static void main (String[] args) {
        //main class is where the program behavior is organized

        int game;
        int playerCard;
        int playerHand;
        int playerAction;
        int dealerHand;
        int playerWin = 0;
        int dealerWin = 0;
        int tie = 0;
        String cardFace = "";
        //todo - variables for statistics percentage and wins / losses

        //set up game
        game = 1;
        printStartGame(game);

        playerCard = getCardFromDeck();
        playerHand = playerCard;
        getPrintForPlayerCardDrawn(playerCard, playerHand);

        printPlayerActionOptions();
        //end of game setup

        //start of user input control-flow "engine"
        playerAction = getPlayerAction();
        System.out.println();

        if (playerAction > 0 && playerAction < 4) {
            //play the game if playerAction evaluates to 1-3

            do {

                switch (playerAction) {
                    case 1: {
                        //option 1 checks for
                        //player win/lose condition
                        //if player wins/loses, start new game

                        playerCard = getCardFromDeck();
                        playerHand = playerHand + playerCard;
                        getPrintForPlayerCardDrawn(playerCard, playerHand);

                        //if playerHand is over 21, player loses, increment game
                        if (playerHand > 21){
                            System.out.println("You exceeded 21! You lose.\n");
                            playerHand = 0; //reset hand
                            game++;
                            printStartGame(game);
                        }
                        //if playerHand is 21, increment playerWin and game
                        else if (playerHand == 21){
                            System.out.println("BLACKJACK! You win!\n");
                            playerHand = 0; //reset hand
                            playerWin++;
                            game++;
                            printStartGame(game);
                        }
                        //playerHand is less than 21, continue game

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();
                        System.out.println();

                        break;
                    }

                    case 2: {
                        //option 2 deals dealerHand, prints dealerHand & playerHand
                        //checks for ties/win/lose conditions
                        //if player wins increment playerWin, start new game
                        //if player ties/loses, start new game

                        dealerHand = getDealerHand();

                        printAllHands(dealerHand, playerHand);

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();

                        //if both hands are 21 in the same round no players win
                        if (dealerHand == 21 && playerHand == 21) {

                            System.out.println("It's a tie! No one wins!\n");
                            tie++;

                        }

                        //if either hand or both hands are 21 - there is a winner
                        if (dealerHand == 21 || playerHand == 21) {

                            if (dealerHand == 21){

                                System.out.println("Dealer wins!");
                                dealerWin++;

                            }
                            else if (playerHand == 21) {

                                System.out.println("You win!");
                                playerWin++;

                            }

                        }

                        //if either hand goes over 21
                        if( (dealerHand > 21 && playerHand > 21) || ((dealerHand > 21) || (playerHand > 21)) ) {

                            if(dealerHand > 21) {

                                playerWin++;

                            }
                            else {

                                dealerWin++;
                            }

                        }

                        //no hand >= 21, check for higher hand to determine a winner
                        int playerDifference = Math.abs(21 - playerHand);
                        int dealerDifference = Math.abs(21 - dealerHand);

                        if (playerDifference > dealerDifference) {
                            playerWin++;
                        }
                        else {
                            dealerWin++;
                        }

                        dealerHand = 0;
                        playerHand = 0; //reset hands

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();
                        System.out.println();
                        game++;

                        break;
                    }

                    case 3: {

                        //todo - add logic for how to find percentage win

                        System.out.println("Number of Player wins: " + playerWin);
                        System.out.println("Number of Dealer wins: " + dealerWin);
                        System.out.println("Number of tie games: " + tie);
                        System.out.println("Total # of games played is: " + game);
                        //System.out.printf("Percentage of Player wins: " + 1%percentagef);

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();
                        System.out.println();

                        break;
                    }
                    //todo - option 4 net
                    // need to catch close program - no print
                    //todo - need default for catching invalid input here

                } //end switch

            } while (playerAction >= 1 && playerAction <= 3);
            //anything that evaluates to false in while ends program
            //find a way to tell user to enter valid input without ending the game
        }
        else if ( playerAction == 4){
            //end program
        }
        else {
            System.out.println("Invalid input! \nPlease enter an integer value between 1 and 4.");
        }

    } // end main

} // end Blackjack.java
