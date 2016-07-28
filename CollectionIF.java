/* Representa una colección de elementos. Una colección no      *
 * tiene orden.                                                 *
 * Los símbolos precedidos de \ pueden encontrarse en la URL    * 
 * http://web.ift.uib.no/Teori/KURS/WRK/TeX/symALL.html y se    *
 * corresponden con los símbolos matemáticos habituales.        */
public interface CollectionIF<E> {

/* Los métodos add, remove, get [set] se eliminan de la         *
 * interfaz para nombrarlos apropiadamente en cada TAD.         */ 

    /* Devuelve el número de elementos de la colección.         *
     * @return: cardinalidad de la colección.                   */
    public int size ();
	
    /* Determina si la colección está vacía.                    *
     * @return: size () == 0                                    */	
    public boolean isEmpty ();
	
    /* Determina la pertenencia del parámetro a la colección    *
     * @param: el elemento cuya pertenencia se comprueba.       *
     * @return:param \in self                                   */
    public boolean contains (E e);
	
    /* Elimina todos los elementos de la colección.         */
    public void clear ();
	
    /* Devuelve un iterador sobre la colección.             *
     * @return: un objeto iterador para los elementos de    *
     * la colección.                                        */
    public IteratorIF<E> iterator (); 
}