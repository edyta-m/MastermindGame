/**
 * Specifies the colours of pegs in the game of Mastermind
 *
 * @author Edith Molda
 */
public class Peg {

    /**
     * Constructs colour values for pegs.
     */
    public enum Colour {
        /**
         * There are 6 possible colour options.
         */
        BLUE("blue"), RED("red"), GREEN("green"), YELLOW("yellow"), ORANGE("orange"), PURPLE("purple");

        private String colourName;

        public String getColourName() {
            return colourName;
        }

        /**
         * Provides each colour with a name.
         * @param colourName each colour value has a name
         */
        private Colour(String colourName) {
            this.colourName = colourName;
        }
    }

    private Colour colour;

    /**
     * Constructs a new peg instance.
     * @param colour the colour of the peg (eg. "BLUE")
     */
    public Peg(Colour colour) {
        this.colour = colour;
    }

    /**
     * Processes the addition of new pegs.
     * @param colour the colour of the peg
     */
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * Returns the colour value of the peg.
     * @return colour  if called upon, the colour value of the peg is returned
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Checks to see if two pegs have the same colour value.
     * @param p the peg object
     * @return if the peg colours match, return true; return false if colours don't match
     */
    public boolean equals(Peg p) {
        return (this.colour.equals(p.getColour()));
    }

    /**
     * Provides the display output of the peg.
     * @return the colour name of the peg
     */
    public String toString() {
        return getColour().getColourName();
    }
}
