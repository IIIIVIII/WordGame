/**
 * Exception to be thrown when files provided for word guess is malformed.
 *
 * @author Purdue CS
 * @version 2023-07-15
 */
public class WordGuesser {
    private String[][] playingField;
    private int round;
    private String solution;

    public WordGuesser(String solution) {
        this.solution = solution;
        this.round = 1;
        this.playingField = new String[5][5];
        for (int i = 0; i < playingField.length; i++) {
            for (int j = 0; j < playingField.length; j++) {
                playingField[i][j] = " ";
            }
        }

    }

    public String[][] getPlayingField() {
        return playingField;
    }

    public int getRound() {
        return round;
    }

    public String getSolution() {
        return solution;
    }

    public void setPlayingField(String[][] playingField) {
        this.playingField = playingField;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean checkGuess(String guess) throws InvalidWordException, InvalidGuessException {
        //System.out.println(guess);
        // System.out.println(solution);
        if (guess.length() != 5) {
            throw new InvalidGuessException("Invalid word!");
        }
        if (guess.equals(solution)) {
            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == solution.charAt(i)) {
                    playingField[round - 1][i] = "'" + guess.charAt(i) + "'";
                }
            }
            return true;
        } else {
            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == solution.charAt(i)) {
                    playingField[round - 1][i] = "'" + guess.charAt(i) + "'";
                } else if (solution.contains("" + guess.charAt(i))) {
                    playingField[round - 1][i] = "*" + guess.charAt(i) + "*";
                } else {
                    playingField[round - 1][i] = "{" + guess.charAt(i) + "}";
                }
            }
            round += 1;
        }
        return false;
    }

    public void printField() {
        //System.out.print(playingField[getRound()][0]);
        for (int i = 0; i < playingField.length; i++) {
            System.out.print(playingField[i][0]);
            for (int j = 1; j < playingField[0].length; j++) {
                System.out.print(" | " + playingField[i][j]);
            }
            System.out.println();
        }
    }
}


