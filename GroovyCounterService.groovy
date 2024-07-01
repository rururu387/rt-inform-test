class GroovyCounterService implements CounterService {
    @Override
    Map<Integer, Long> getAssociativeMap(Collection<Integer> ints) {
        return ints.countBy { it }
    }
}