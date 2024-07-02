set -x
printf "Запуск приложения\n"
./gradlew clean run
printf "Запуск unit тестов\n"
./gradlew clean test