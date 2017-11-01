package ski.java8.streams;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class WcCollector implements Collector<String,Wc,Wc> {

    @Override
    public Supplier<Wc> supplier() {
        return Wc::new;
    }

    @Override
    public BiConsumer<Wc, String> accumulator() {
        return Wc::accept;
    }

    @Override
    public BinaryOperator<Wc> combiner() {
        return Wc::combine;
    }

    @Override
    public Function<Wc, Wc> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
//        return Collections.emptySet();
        return EnumSet.of(
                Collector.Characteristics.UNORDERED,
                Collector.Characteristics.IDENTITY_FINISH);
    }
}
