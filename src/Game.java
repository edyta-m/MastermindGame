import java.util.Random;
import java.util.Scanner;

/**
 * Acts as the code maker and handles the mechanics of the Mastermind game
 *
 * @author Edith Molda
 */
public class Game {
    private ArrayList<Peg> pegs;
    private ArrayList<Peg> guess;
    private boolean won = false;

    /**
     * Constructs a new game instance.
     */
    public Game() {
        //instantiate code of pegs
        createPegs();
        //start game loop
        playGame();
    }

    /**
     * Create an instance of arrayList for code to crack.
     * Pegs are randomly generated.
     */
    public void createPegs() {
        pegs = new ArrayList<Peg>();
        Random rand = new Random();
        for (int i=0; i<4; i++) {
            Peg randPeg = new Peg(Peg.Colour.values()[rand.nextInt(Peg.Colour.values().length)]);
            pegs.add(randPeg);
        }
    }

    /**
     * Game loop for the Mastermind game.
     * Start with instantiating number of guesses.
     * Then prompt player for guess and send input to arrayList.
     * Then check if the guess matches the code.
     * Increment number of guesses if code wasn't completely matched.
     */
    public void playGame() {
        //instantiate guess number
        int num = 1;
        //start game loop
        while (num != 11 && !won) {
            //with exception handling
            Scanner kb = new Scanner(System.in);
            try {
                //prompt guess from player
                System.out.println("Guess #" + num + ":");
                String input = kb.nextLine();
                //send input to get put into arrayList
                playerGuess(input);
                //check if code and guess are a match and mark it accordingly
                checkMatch();
                //move on to next guess
                num++;
            } catch (Exception e) {
                System.out.println("You entered an invalid colour option, please try again:");
            }
        }
        if (num == 11)
            System.out.println("System has won!");
    }

    /**
     * ArrayList that holds pegs that represent the player's guesses
     * First split string input, then place into string arrayList.
     * Then assign official pegs to string values and place into guess arrayList.
     * @param input the string input by the player (eg. "blue yellow red purple")
     */
    public void playerGuess(String input) throws Exception {
        guess = new ArrayList<Peg>();
        //break apart player input
        String[] str = input.split(" ");
        ArrayList<String> pieces = new ArrayList<String>();
        for (String s : str) {
            pieces.add(s);
        }
        //assign pieces to pegs
        int n = 0;
        for (int i=0; i<pieces.size(); i++) {
            String peg = pieces.get(i);
            for (Peg.Colour c : Peg.Colour.values()) {
                if (c.getColourName().equals(peg.toLowerCase())) {
                    guess.add(new Peg(c));
                    n++;
                }
            }
        }
        if (n != 4)
            throw new Exception("Invalid colour option");
    }

    /**
     * Checks if guess is a match to code and then marks the outcome accordingly.
     * First iterate through the code and find correct placements and wrong placements.
     * Then gather total of correct and wrong placements.
     * Then check if guess matches code, if not display feedback.
     */
    public void checkMatch() {
        int x = 0;
        int o = 0;
        //iterate through code
        for (int i=0; i<pegs.size(); i++) {
            int correctPosition = 0;
            int wrongPosition = 0;
            //iterate through guess
            for (int j=0; j<guess.size(); j++) {
                //check if peg is right colour and in right position
                if (pegs.get(i).getColour().equals(guess.get(j).getColour()) && i==j)
                    correctPosition++;
                //check if peg colour exists in code but is in wrong position
                else if (pegs.get(i).getColour().equals(guess.get(j).getColour()) && i != j)
                    wrongPosition++;
            }
            //gather feedback
            if (correctPosition > 0)
                x++;
            else if (wrongPosition > 0)
                o++;
        }
        //check if code has been completely guessed correctly
        if (x == 4) {
            System.out.print("You cracked the code!");
            won = true;
        }
        //display feedback
        else {
            for (int i = 0; i < pegs.size(); i++) {
                //2 by 2 grid display
                if (i == 2)
                    System.out.println();
                //markings
                if (x > 0) {
                    System.out.print("x ");
                    x--;
                } else if (o > 0) {
                    System.out.print("o ");
                    o--;
                } else {
                    System.out.print("- ");
                }
            }
            System.out.print("\n\n");
        }
    }

    /**
     * Display game output.
     * @param args to display array of strings signifying the game of Mastermind
     */
    public static void main(String[] args) {
        //instantiate game and invoke game play
        Game game = new Game();
    }
}
