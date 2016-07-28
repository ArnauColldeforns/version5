/* Representa un comparador entre elementos respecto a una   *
 * relación de (al menos) preorden.                          *
 * Los símbolos precedidos de \ pueden encontrarse en la URL * 
 * http://web.ift.uib.no/Teori/KURS/WRK/TeX/symALL.html y se *
 * corresponden con los símbolos matemáticos habituales.     */
public interface ComparatorIF<E>{
  /* Sean a, b elementos de un conjunto dado y \sqsubset la  *
   * relación que establece un preorden entre ellos (nótese  *
   * que \sqsupset sería la relación recíproca, es decir, en *
   * sentido opuesto a \sqsubset):                           */ 
  public static int LT = -1; // Less than: a \sqsubset b      
  public static int EQ = 0;  // Equals: !(a \sqsubset b) && 
                             // && !(a \sqsupset b) 
  public static int GT = 1;  // Greater than: a \sqsupset b 
 
 /* Compara dos elementos respecto a un preorden e indica su *
  * relación respecto al mismo, es decir, cuál precede al    *
  * otro mediante esa relación.                              *
  * @param a el primer elemento.                             *
  * @param b el segundo elemento.                            *
  * @return LT sii a \subsesq b;                             *
  *         EQ sii !(a \subsetsq b) && !(a \sqsupset b)      *
  *         GT sii a \sqsupset b                             */
  public int compare (E a, E b);
    
 /* Determina si el primer parámetro precede en el preorden  *
  * al segundo (a < b).                                      * 
  * @param a el primer elemento.                             *
  * @param b el segundo elemento.                            *
  * @return a \sqsubset b;                                   */
  public boolean lt (E a, E b);
    
  /* Determina si el primer parámetro es igual al segundo en *
   * el preorden.                                            *
   * @param a el primer elemento.                            *
   * @param b el segundo elemento.                           *
   * @return a EQ b sii !(a \sqsubset b) && !(a \sqsupset b) */
  public boolean eq (E a, E b);
    
  /* Determina si el primer parámetro sucede en el preorden  *
   * al segundo (b > a).                                     * 
   * @param a el primer elemento.                            *
   * @param b el segundo elemento.                           *
   * @return a GT b sii b \sqsupset a                        */
  public boolean gt (E a, E b);
}