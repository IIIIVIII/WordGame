import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

/**
 * Exception to be thrown when files provided for word guess is malformed.
 *
 * @author Purdue CS
 * @version 2023-07-15
 */
public class WordLibrary {
    private String[] library;
    private int seed;
    private Random random;
    private String fileName;

    public WordLibrary(String fileName) throws InvalidWordException {
        BufferedReader bufferedReader;
        bufferedReader = null;
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String term;
            while ((term = bufferedReader.readLine()) != null) {
                if (term.contains(":")) {
                    String[] seed1 = term.split(":");
                    this.seed = Integer.parseInt(seed1[1].trim());
                    this.random = new Random(seed);
                } else stringBuilder.append(term).append(" ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        library = stringBuilder.toString().trim().split(" ");
        processLibrary();
    }

    public String[] getLibrary() {
        return library;
    }

    public int getSeed() {
        return seed;
    }

    public void setLibrary(String[] library) {
        this.library = library;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public void verifyWord(String word) throws InvalidWordException {
        if (word.length() != 5) {
            throw new InvalidWordException("Invalid word!");
        }
    }

    public String chooseWord() {
        return library[random.nextInt(library.length)];
    }

    public void processLibrary() {
        int delete = 0;
        for (int i = 0; i < library.length; i++) {
            try {
                verifyWord(library[i]);
            } catch (InvalidWordException e) {
                library[i] = "";
                delete++;
                System.out.println(e.getMessage());
            }
        }
        int index1 = 0;
        String[] deletedLibrary = new String[library.length - delete];
        for (int i = 0; i < library.length; i++) {
            if (!Objects.equals("", library[i])) {
                deletedLibrary[index1] = library[i];
                index1++;
            }
        }
        library = deletedLibrary;
    }
}
