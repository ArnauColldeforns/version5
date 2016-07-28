/* Representa una lista de elementos. Una lista es una          *
 * colección que mantiene un orden lineal, es decir, una        * 
 * secuencia.                                                   *
 * Los símbolos precedidos de \ pueden encontrarse en la URL    * 
 * http://web.ift.uib.no/Teori/KURS/WRK/TeX/symALL.html y se    *
 * corresponden con los símbolos matemáticos habituales.        */
public interface ListIF<E> extends CollectionIF<E>{
	
    /* Devuelve el elemento de la lista que ocupa la posición   *
     * indicada por el parámetro.                               *	
     * @param pos la posición comenzando en 1.                  *	
     * @Pre: 1 \leq pos \leq size()                             *
     * @return el elemento en la posición pos.                  */
    public E get (int pos);
    
    /* Modifica la posición dada por el parámetro pos para que  *
     * contenga el valor dado por el parámtero e.               *
     * @param pos la posición cuyo valor se debe modificar,     *
     *  comenzando en 1.                                        *
     * @param e el valor que debe adoptar la posición pos.      *
     * @Pre: 1 \leq pos \leq size()                             */
    public void set (int pos, E e);
	
    /* Inserta un elemento en la Lista.                         *
     * @param elem El elemento que hay que añadir.              *				
     * @param pos  La posición en la que se debe añadir elem,   *
     *  comenzando en 1.      	                                *
     * @Pre: 1 \leq pos \leq size()+1                           */
    public void insert (E elem, int pos);
    
    /* Elimina el elemento que ocupa la posición del parámetro  *	 
     * @param pos la posición que ocupa el elemento a eliminar, *
     *  comenzando en 1                                         *
     * @Pre: 1 \leq pos \leq size()                             */
    public void remove (int pos);
}