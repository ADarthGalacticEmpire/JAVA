import java.util.Scanner;

public class Blackjack {

    //blackjack class contains:
    // objects for P1Random and Scanner
    // and methods for execution in main

    public static P1Random randomNumber = new P1Random();
    public static Scanner scnr = new Scanner(System.in);

    //methods to play the game and reduce redundant code in main

    //prints start game message with game number
    public static void printStartGame(int gameNumber){
        System.out.println("START GAME #" + gameNumber);
    }

    //prints player action/options menu
    public static void printPlayerActionOptions() {
        System.out.println("\n1. Get another card");
        System.out.println("2. Hold hand");
        System.out.println("3. Print statistics");
        System.out.println("4. Exit\n");
    }

    //prints choose an option and then waits for user input, returns user input
    public static int getPlayerAction(){
        System.out.print("Choose an option: ");
        int playerAction;
        playerAction = scnr.nextInt();

        return playerAction;
    }

    //calls for a random number 1-13, returns the number
    public static int getCardFromDeck(){
        int card;
        card = randomNumber.nextInt(13) +1;

        return card;
    }

    //takes a card value, evaluates it for a face value, returns string of face value
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

    //takes a card value and a hand value, returns nothing
    // evaluates if the hand should be sent to Face printing method
    // or standard number printing method
    public static void getPrintForPlayerCardDrawn(int playerCard, int playerHand) {
        if (playerCard >= 2 && playerCard <= 11) {
            printPlayerCardAndHand(playerCard, playerHand);
        }
        else {
            String cardFace;
            cardFace = getCardFace(playerCard);
            printPlayerCardWithFace(cardFace, playerHand);
        }
    }

    // takes player card and player hand values, returns nothing
    // prints the card as an int, and then calls print hand
    public static void printPlayerCardAndHand(int playerCard, int playerHand){
        System.out.println("\nYour card is a " + playerCard + "!");
        printPlayerHand(playerHand);
    }

    //takes player card face as string and player hand value, returns nothing
    //prints the card with face string and calls print hand
    public static void printPlayerCardWithFace(String cardFace, int playerHand) {
        System.out.println("\nYour card is a " + cardFace + "!");
        printPlayerHand(playerHand);
    }

    //takes a player hand value, returns nothing
    // prints player hand statement
    public static void printPlayerHand(int playerHand){
        System.out.println("Your hand is: " + playerHand);
    }

    //take no parameters, returns dealerHand
    //generates random number from 16 - 26
    public static int getDealerHand(){
        int dealerHand;
        dealerHand = randomNumber.nextInt(11) +16;

        return dealerHand;
    }

    //takes dealer and player hand values, returns nothing
    //prints dealer hand and then player hand statements
    public static void printAllHands(int dealerHand, int playerHand){
        //System.out.println();
        System.out.println("\nDealer's hand: " + dealerHand);
        System.out.println("Your hand is: " + playerHand);
        System.out.println();
    }

    //main class is where the program behavior is organized
    public static void main (String[] args) {
        int game;
        int playerAction;
        int playerCard;
        String cardFace = "";
        int royalCard = 10;
        int playerHand = 0;
        int dealerHand = 0;
        double playerWin = 0;
        double dealerWin = 0;
        int tie = 0;
        double percentage = 0;

        //set up game
        game = 1;
        printStartGame(game);

        playerCard = getCardFromDeck();

        //Royals have a value of 10
        if (playerCard >= 11){
            playerHand = playerHand + royalCard;
        }
        else{
            playerHand = playerHand + playerCard;
        }

        getPrintForPlayerCardDrawn(playerCard, playerHand);
        //end of game setup

        //start of user input control-flow "engine"
        while( true ) {
            printPlayerActionOptions();
            playerAction = getPlayerAction();
            
        //play the game if playerAction evaluates to 1-3
        if (playerAction < 0 && playerAction > 4) {
            System.out.println("Invalid");
            continue;
        }
            do {
                switch (playerAction) {
                    case 1: {
                        //option 1 checks for
                        //player win/lose condition
                        //if player wins/loses, start new game

                        playerCard = getCardFromDeck();

                        //Royals have a value of 10
                        if (playerCard >= 11) {
                            playerHand = playerHand + royalCard;
                        } else {
                            playerHand = playerHand + playerCard;
                        }

                        getPrintForPlayerCardDrawn(playerCard, playerHand);

                        //if playerHand is over 21, player loses, increment game
                        if (playerHand > 21) {
                            System.out.println("You exceeded 21! You lose.\n");
                            playerHand = 0; //reset hand
                            dealerWin++;
                            game++;
                            printStartGame(game);
                            playerCard = getCardFromDeck();

                            //Royals have a value of 10
                            if (playerCard >= 11) {
                                playerHand = playerHand + royalCard;
                            } else {
                                playerHand = playerHand + playerCard;
                            }

                            printPlayerCardAndHand(playerCard, playerHand);
                        }
                        //if playerHand is 21, increment playerWin and game
                        else if (playerHand == 21) {
                            System.out.println("BLACKJACK! You win!\n");
                            playerHand = 0; //reset hand
                            playerWin++;
                            game++;
                            printStartGame(game);
                            playerCard = getCardFromDeck();

                            //Royals have a value of 10
                            if (playerCard >= 11) {
                                playerHand = playerHand + royalCard;
                            } else {
                                playerHand = playerHand + playerCard;
                            }

                            printPlayerCardAndHand(playerCard, playerHand);
                        }

                        //playerHand is less than 21, continue game

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();

                        break;
                    }

                    case 2: {
                        //when player holds, the current game needs to be decided
                        //if no hand >= 21, check for higher hand to determine a winner

                        dealerHand = getDealerHand();

                        printAllHands(dealerHand, playerHand);

                        //if both hands are 21 no players win
                        if (dealerHand == playerHand) {
                            System.out.println("It's a tie! No one wins!\n");
                            tie++;
                        }
                        else {
                            //if either hand is 21 - there is a winner
                            if (dealerHand == 21 || playerHand == 21) {
                                if (dealerHand == 21) {
                                    System.out.println("Dealer wins!\n");
                                    dealerWin++;
                                } else if (playerHand == 21) {
                                    System.out.println("You win!\n");
                                    playerWin++;
                                }
                            }
                            //if the dealer hand goes over 21
                            if (dealerHand > 21) {
                                System.out.println("You win!\n");
                                playerWin++;
                            }

                            if (dealerHand < 21) {

                                int playerDifference = Math.abs(21 - playerHand);
                                int dealerDifference = Math.abs(21 - dealerHand);

                                if (playerDifference < dealerDifference) {
                                    System.out.println("You win!\n");
                                    playerWin++;
                                } else {
                                    System.out.println("Dealer wins!\n");
                                    dealerWin++;
                                }
                            }
                        }

                        dealerHand = 0;
                        playerHand = 0; //reset hands

                        game++;
                        printStartGame(game);

                        playerCard = getCardFromDeck();

                        //Royals have a value of 10
                        if (playerCard >= 11) {
                            playerHand = playerHand + royalCard;
                        }
                        else {
                            playerHand = playerHand + playerCard;
                        }

                        getPrintForPlayerCardDrawn(playerCard, playerHand);

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();

                        break;
                    }

                    case 3: {
                        game = game - 1;
                        System.out.print("Number of Player wins: ");
                        System.out.printf("%.0f", playerWin);
                        System.out.println();
                        System.out.print("Number of Dealer wins: ");
                        System.out.printf("%.0f", dealerWin);
                        System.out.println();
                        System.out.println("Number of tie games: " + tie);
                        System.out.println("Total # of games played is: " + game);
                        percentage = Math.round((playerWin / game)*100);
                        System.out.print("Percentage of Player wins: ");
                        System.out.printf("%.1f", percentage);
                        System.out.println("%");

                        game = game + 1;

                        printPlayerActionOptions();
                        playerAction = getPlayerAction();
                        System.out.println();

                        break;
                    }

                    case 4: {
                        System.exit(0); //close program completely

                        break;
                    }

                    default:
                        System.out.println("Invalid input! \nPlease enter an integer value between 1 and 4.");
                        printPlayerActionOptions();
                        playerAction = getPlayerAction();
                        System.out.println();

                        break;

                } //end switch

            } while (playerAction >= 0 || playerAction <= 0);

        if (playerAction == 4) {
            break; //end program
        }

        continue;
    } //end while

    } // end main

} // end Blackjack.java