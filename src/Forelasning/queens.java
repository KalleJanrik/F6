package Forelasning;

/*
Vi kan välja en 2d boolean matris som enda datastruktur och markera en drottning med true men
        det blir hyggligt jobbigt att kontrollera om en viss plats är möjlig.
        Enklare då att förutom 2d-matrisen ha en array column, en array neDiagonal (-> ) och en array
        nwDiagonal ( <-) som håller reda på om dessa är upptagna.
        Om column[3] är true betyder det då att den tredje kolumnen är upptagen.


        Med diagonalerna är det lite trickigare.
        Om neDiagonal[4] är true betyder att den diagonal där rad+kolumn=4 är upptagen
        Alla element på en ne-diagonal har samma värde för rad+kolumn


        Om nwDiagonal[4] är true betyder det att den diagonal där rad-kolumn+7=4 är upptagen.
        Alla element på en nw diagonal har samma värde för rad-kolumn men lagras på plats rad-kolumn+7
*/

public class queens {
    /*
    addQueen(rad)
    för kolumn 1 till 8
        om kolumn möjlig
            boka platsen
            om rad==8
                skriv ut lösning
            annars
                addQueen(rad+1)
            avboka platsen
    Anropas då med addQueen(1)
     */
}
