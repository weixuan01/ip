package dill.quote;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class QuoteList {
    private List<String> quotes;
    private Random random;

    public QuoteList() {
        quotes = new ArrayList<>();
    }
    public QuoteList(List<String> quotes) {
        this.quotes = quotes;
        this.random = new Random();
    }

    public String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "My quote stash is empty, but your potential is overflowing.";
        }
        int randIndex = random.nextInt(quotes.size()); // generate random index
        return quotes.get(randIndex);
    }
}
