import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaCounterService implements CounterService {

    @Override
    public Map<Integer, Long> getAssociativeMap(Collection<Integer> ints) {
        return ints.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
