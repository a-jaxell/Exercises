# LABORATION 2


## Uppgift 1

    1. X Skapa 2 klasser. Shape & Circle
    2. X Skapa lämpliga fält samt getter, setter, konstruktorer
        X för att initialisera läsa och uppdatera fältens data.
    3. X Båda klasserna ska örva från en abstrakt klass
        X som ska definiera de abstrakta metoderna getArea samt
        X getPerimeter. Implementera dessa metoder i klasserna.
    4.  X Statisk metod(er) på Shape som kan användas för
        X att skapa nya shapes av önskad typ.

    5. X låt Shapes implementera det generiska interfacet Comparable<T>
       X För standard implementation så den jämför shapes baserat på area.
       X (naturlig ordning mellan shapes) men andra t.ex omkrets kan även
        X implementeras.

## Uppgift 2
    
    1.X Skapa ett program som skapar ett par objekt av 
        X respektive typ och spara dem i en ArrayList.
    2.  X Använd .sort med null som param för att utnyttja naturlig
        X sorteringsordning.
    3.  X Skriv ut listan.

## Uppgift 3

    1.  Utöka programmet till att även innehålla ett Set
        för att lagra typer. (HashSet) 
    2.  Skapa ett antal olika och lägg till.
    3. Låt minst 2 shapes ha samma mått och typ
        Endast ett av de borde lagras, men vad händer när du skriver ut innehållet?
    
    4. implementera .equals och hashCode metoder till shape klasser
        efter detta borde bara ett ex lagras.