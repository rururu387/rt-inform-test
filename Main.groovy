class Main {
    static void main(String[] args) {
        // Работает и в groovy тоже =)
        var javaImpl = new JavaCounterService()
        var groovyImpl = new GroovyCounterService()

        var testData = [1, 3, 4, 5, 1, 5, 4]

        println javaImpl.getAssociativeMap(testData)
        println groovyImpl.getAssociativeMap(testData)
    }
}
