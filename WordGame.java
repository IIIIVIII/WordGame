import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exception to be thrown when files provided for word guess is malformed.
 *
 * @author Purdue CS
 * @version 2023-07-15
 */
public class WordGame {

    public static String welcome = "Ready to play?";
    public static String yesNo = "1.Yes\n2.No";
    public static String noPlay = "Maybe next time!";
    public static String currentRoundLabel = "Current Round: ";
    public static String enterGuess = "Please enter a guess!";
    public static String winner = "You got the answer!";
    public static String outOfGuesses = "You ran out of guesses!";
    public static String solutionLabel = "Solution: ";
    public static String incorrect = "That's not it!";
    public static String keepPlaying = "Would you like to make another guess?";
    public static String fileNameInput = "Please enter a filename";

    public static void main(String[] args) {
        try {
            try {
                Scanner scanner = new Scanner(System.in);
                int count = 0;
                String[] guesses = new String[5];
                System.out.println(fileNameInput);
                String fileName = scanner.nextLine();
                WordLibrary wordSolution = new WordLibrary(fileName);
                String solution = wordSolution.chooseWord();
                WordGuesser classObj = new WordGuesser(solution);
                int ready;
                int i = 0;
                boolean solved = true;
                System.out.println(welcome);
                do {
                    //String solution = classObj.getSolution();
                    System.out.println(yesNo);
                    ready = scanner.nextInt();
                    scanner.nextLine();
                    if (ready == 2) {
                        System.out.println(noPlay);
                        break;
                    } else {
                        System.out.println(currentRoundLabel + classObj.getRound());
                        classObj.printField();
                        System.out.println(enterGuess);
                        String guess1 = scanner.nextLine();
                        guesses[i] = guess1;
                        i++;
                        wordSolution.verifyWord(guess1);
                        boolean a = classObj.checkGuess(guess1);
                        if (a) {
                            System.out.println(winner);
                            classObj.printField();
                            System.out.println(welcome);
                            solved = true;
                            updateGameLog(solution, guesses, true);
                            for (int j = 0; j < guesses.length; j++) {
                                guesses[j] = "";
                            }
                            count += 1;
                            classObj.setRound(1);
                            String[][] reset = new String[5][5];
                            for (int k = 0; k < reset.length; k++) {
                                for (int q = 0; q < reset.length; q++) {
                                    reset[k][q] = " ";
                                }
                            }
                            solution = wordSolution.chooseWord();
                            classObj = new WordGuesser(solution);
                            classObj.setPlayingField(reset);

                        } else if (classObj.getRound() > 5) {
                            System.out.println(outOfGuesses);
                            System.out.println(solutionLabel + classObj.getSolution());
                            classObj.printField();
                            System.out.println(welcome);
                            i = 0;
                            solved = false;
                            updateGameLog(solution, guesses, false);
                            for (int j = 0; j < guesses.length; j++) {
                                guesses[j] = "";
                            }
                            count += 1;
                            classObj.setRound(1);
                            String[][] reset = new String[5][5];
                            for (int k = 0; k < reset.length; k++) {
                                for (int q = 0; q < reset.length; q++) {
                                    reset[k][q] = " ";
                                }
                            }
                            classObj.setPlayingField(reset);
                        } else {
                            System.out.println(incorrect);
                            System.out.println(keepPlaying);
                        }

                    }
                } while (ready == 1);
            } catch (InvalidWordException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InvalidGuessException e) {
            e.printStackTrace();
        }
    }

    public static void updateGameLog(String solution, String[] guesses, boolean solved) throws IOException {
        ArrayList<String> gfg = new ArrayList<String>();
        int gameNumber = 1;
        gfg.add("Games Completed: " + "1");
        StringBuilder newGuesses = new StringBuilder(guesses[0]);
        BufferedReader bufferedReader = null;
        File file = new File("gamelog.txt");
        //System.out.println(gfg.size());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String read;
        //System.out.println(gfg.size());
        try {
            //int itr = 0;
            while ((read = bufferedReader.readLine()) != null) {
                //itr++;
                //if(itr==1) System.out.println("'"+read+"'size:" + read.length());
                if (read.contains("Games Completed")) {
                    String[] readSplit = read.split(":");
                    gameNumber = Integer.parseInt(readSplit[1].trim()) + 1;
                    gfg.set(0, "Games Completed: " + gameNumber);
                } else {
                    gfg.add(read);
                }
            }
            //System.out.println(gfg.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter gwrite = new BufferedWriter(new FileWriter(file));
            for (int i = 1; i < guesses.length; i++) {
                if (guesses[i] != null && (!guesses[i].equals(""))) {
                    newGuesses.append(",").append(guesses[i]);
                }
            }

            if (newGuesses.substring(0, 1).equals(",")) {
                newGuesses = new StringBuilder(newGuesses.substring(1));
            }
            //System.out.println(gfg.size());
            gfg.add("Game " + gameNumber);
            gfg.add("- Solution: " + solution);
            gfg.add("- Guesses: " + newGuesses);
            gfg.add("- Solved: " + (solved ? "Yes" : "No"));
            for (String x : gfg) {
                gwrite.write(x);
                gwrite.newLine();
            }
            gwrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
