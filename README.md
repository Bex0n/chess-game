# Gra w szachy.
### Projekt został stworzony przy użyciu środowiska Intellij.

## Uruchamianie projektu.

Program może zostać skompilowany w środowisku Intellij przy pomocy komendy Run Main. 

Alternatywnie program może zostać skompilowany oraz uruchomiony z wykorzystaniem polecenia
```shell
chmod +x gra.sh
./gra.sh
```

# Opis projektu

Ruchy należy podawać w formacie "{pole startowe} {pole końcowe}" np. "A2 A4".

Gra dostępna jest w wersji gracz vs bot, w celu rozgrywki dwóch graczy należy zedytować
klasę Rozgrywka w linijkach 9. i 10. w następnujący sposób:
```java
Player Player1 = new Player("bialy");
Player Player2 = new Player("czarny");
```
