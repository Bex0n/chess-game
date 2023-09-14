# Gra w szachy.
Konsolowa gra w szachy na bazie kodowania znaków w Unicode. Gracz opisuje w terminalu ruch formatem

```
AB CD
```

Gdzie AB to pole ruszanej figury (np. B5), a CD to pole miejsca na które figura jest przesuwana (np. B6).

### Uruchamianie projektu.

Kompilacja:

```shell
javac -d out/production/Szachy src/*.java
```

Uruchomienie:
```shell
java -classpath out/production/Szachy Main
```

# Opis projektu

Gra dostępna jest w wersji gracz vs bot, w celu rozgrywki dwóch graczy należy zedytować
klasę Rozgrywka w linijkach 9. i 10. w następnujący sposób:
```java
Player Player1 = new Player("bialy");
Player Player2 = new Player("czarny");
```

Gra nie posiada jeszcze możliwości promocji pionków oraz bicia w przelocie.
