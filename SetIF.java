/* Representa un conjunto de elementos. Se trata del concepto     *
 * matemático de conjunto finito (no tiene orden).                *
 * Los símbolos precedidos de \ pueden encontrarse en la URL      *
 * http://web.ift.uib.no/Teori/KURS/WRK/TeX/symALL.html y se      *
 * corresponden con los símbolos matemáticos habituales.          */

public interface SetIF<E> extends CollectionIF<E> {                
	                                                               
    /* Devuelve la unión del conjunto llamante con el parámetro   *
     * @param: el conjunto con el que realizar la unión           *      
     * @return: self \cup @param                                  */
    public SetIF<E> union (SetIF<E> s);                            
	                                                                 
    /* Devuelve la intersección con el parámetro.                 *
     * @param: el conjunto con el que realizar la intersección.   *      
     * @return: self \cap @param                                  */
    public SetIF<E> intersection (SetIF<E> s);                     
	                                                               
    /* Devuelve la diferencia con el parámetro (los elementos	  * 
     * que están en el llamante pero no en el parámetro).         *
     * @param: el conjunto con el que realizar la diferencia.     *      
     * @return: self \setminus @param                             */
    public SetIF<E> difference (SetIF<E> s);                       
                                                                    	
    /* Determina si el parámetro es un subconjunto del llamante.  *
     * @param: el posible subconjunto del llamante.               *      
     * @return: self \subseteq @param                             */
    public boolean isSubset (SetIF<E> s);                           
}

