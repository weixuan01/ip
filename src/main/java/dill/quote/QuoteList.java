package dill.quote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents the list of motivational quotes stored by Dill.
 */
public class QuoteList {
    private List<String> quotes;
    private Random random;

    /**
     * Creates an instance of QuoteList and initializes an empty quote list.
     */
    public QuoteList() {
        quotes = new ArrayList<>();
    }

    /**
     * Creates an instance of QuoteList the specified list of quotes.
     * @param quotes The list of strings of motivational quotes.
     */
    public QuoteList(List<String> quotes) {
        this.quotes = quotes;
        this.random = new Random();
    }

    /**
     * Returns a random quote from the list, or a default quote if the list is empty.
     * @return A quote string
     */
    public String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "My quote stash is empty, but your potential is overflowing.";
        }
        int randIndex = random.nextInt(quotes.size()); // generate random index
        return quotes.get(randIndex);
    }
}
