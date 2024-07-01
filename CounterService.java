import java.util.Collection;
import java.util.Map;

interface CounterService {
    Map<Integer, Long> getAssociativeMap(Collection<Integer> ints);
}
