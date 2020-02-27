# myErp

## Correction code

* model:

  * EcritureComptable:
   - getTotalCredit() modif getDebit() au lieu de getCredit().
   - isEquilibree() modif compareTo() au lieu de equals() "car equals() compare au sens l'object strict et pas la valeur".
   - correction regex [A-Z]{1,5}-\\d{4}/\\d{5} au lieu de {1,5}-\\d{4}/\\d{5} 

## Test

* 
