/* Representa una cola de elementos. Una cola es una		* 
 * especialización de una lista, que mantiene un orden      * 
 * explícito de sus elementos y una política de acceso 	    *
 * First In First Out (FIFO)                                */
public interface QueueIF <E> extends CollectionIF<E>{

    /* Devuelve el primer elemento de la cola.                  *
     * @Pre !isEmpty()                                          * 
     * @return la cabeza de la cola (su primer elemento).       */
    public E getFirst ();

    /* Incluye un elemento al final de la cola. Modifica el     *
     * tamaño de la misma (crece en una unidad).                *
     * @param elem el elemento que debe encolar (añadir).       */
    public void enqueue (E elem);
    
    /* Elimina el primer elemento de la cola. Modifica la       *
     * tamaño de la misma (decrece en una unidad).              *
     * @Pre !isEmpty();                                         */
    public void dequeue ();        
}
