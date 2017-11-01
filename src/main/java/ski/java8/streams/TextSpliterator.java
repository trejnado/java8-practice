package ski.java8.streams;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TextSpliterator implements Spliterator<String> {

    private final List<String> lines;
    private int current;
    private int to;

    public TextSpliterator(List<String> lines) {
        this.lines = lines;
        this.current = 0;
        this.to = lines.size();
    }

    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
        if (current < to) {
            action.accept(lines.get(current++));
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<String> trySplit() {
        if (current+1 < to) {
            int mid = current + (to-current)/2;
            Spliterator<String> stor = new TextSpliterator(lines.subList(mid, to));
            to = mid;
            return stor;
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return to - current;
    }

    @Override
    public int characteristics() {
        return SIZED | SUBSIZED;
    }
}
