package ski.java8.streams;

import java.util.function.Consumer;

public class Wc implements Consumer<String> {

    private long charCount;
    private long wordCount;

    public long charCount() {
        return charCount;
    }

    public long wordCount() {
        return wordCount;
    }

    @Override
    public void accept(String line) {
        this.charCount += line.length();
        this.wordCount += line.split("\\s+").length;
    }

    public Wc combine(Wc other) {
        this.charCount += other.charCount;
        this.wordCount += other.wordCount;
        return this;
    }
}
