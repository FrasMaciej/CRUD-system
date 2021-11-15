Uzasadnienie wyboru struktury danych: Do przechowywania danych słownikowych skorzystałem z kolekcji TreeMap, ponieważ jest ona moim zdaniem najbardziej naturalnym wyborem do prezentacji
danych słownikowych - w mapach może istnieć tylko jeden klucz(w przypadku mojego programu jest to hasło słownikowe), natomiast wartości w postaci rekordów nie muszą być unikalne.
Zdecydowałem się konkretnie na implementację TreeMap ze względu na gwarancję sortowania, obecnego równiez w prawdziwym słowniku.

Przykładowe komendy:

javac -d bin src/gui/* src/editing/* src/file/* src/model/* src/reading/*
jar cf lab03_pop.jar lab03_pop

