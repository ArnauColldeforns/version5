/* Representa un árbol n-ario de elementos, donde el número de   *
 * hijos de un determinado nodo no está determinado de antemano  * 
 * (fan-out no prefijado, no necesariamente igual en cada nodo). *
 * Un árbol supone una ordenación jerárquica de sus elementos,   *
 * donde un nodo sólo puede tener un antecesor directo (padre)   *
 * pero n sucesores directos (hijos).                            *
 * Los símbolos precedidos de \ pueden encontrarse en la URL     *  
 * http://web.ift.uib.no/Teori/KURS/WRK/TeX/symALL.html y se     *
 * corresponden con los símbolos matemáticos habituales.         */
public interface TreeIF<E> extends CollectionIF<E>{
    public int PREORDER  = 0;
    public int POSTORDER = 1;
    public int BREADTH   = 2;
    
    /* Obtiene la raíz del árbol (único elemento sin antecesor). *
     * @Pre: !isEmpty ();                                        *
     * @return el elemento que ocupa la raíz del árbol.          */
     public E getRoot ();
      
    /* Modifica la raíz del árbol.                               *
     * @param el elemento que se quiere poner como raíz del      *
     * árbol.                                                    */ 	
     public void setRoot (E e);
     
    /* Obtiene los hijos del árbol llamante.                     *
     * @Pre: !isEmpty ();                                        *    
     * @return la lista de hijos del árbol (en el orden en que   *
     * estén almacenados en el mismo).                           */
     public ListIF <TreeIF <E>> getChildren ();

    /* Obtiene el hijo que ocupa la posición dada por parámetro. *
     * @param pos la posición del hijo que se desea obtener,     *
     * comenzando en 1.                                          *
     * @Pre 1 \leq pos \leq getChildren().size() && !isEmpty()   *
     * @return el árbol hijo que ocupa la posición pos.          */
     public TreeIF<E> getChild (int pos);
		
    /* Inserta un árbol como hijo en la posición pos.            *
     * @param pos la posición que ocupará el árbol entre sus     *
     * hermanos, comenzando en 1.                                *
     * Si pos == getChildren ().size () + 1, se añade como       *
     * último hijo.                                              *
     * @param e el hijo que se desea insertar.                   *
     * @Pre 1 \leq pos \leq getChildren().size()+1 && !isEmpty() */
     public void addChild (int pos, TreeIF<E> e);
    
    /* Elimina el hijo que ocupa la posición parámetro.          *
     * @param pos la posición del hijo con base 1.               *
     * @Pre 1 \leq pos \leq getChildren().size() && !isEmpty()   */
     public void removeChild (int pos);                         
                                                                 
    /* Determina si el árbol llamante es una hoja.               *
     * @Pre: !isEmpty (); (un arbol vacio no se considera hoja)  *    
     * @return el árbol es una hoja (no tiene hijos).            */
     public boolean isLeaf (); 
    
    /* Obtiene un iterador para el árbol.                        *
     * @param traversal el tipo de recorrido indicado por las    * 
     * constantes PREORDER (preorden o profundidad), POSTORDER   *
     * (postorden) o BREADTH (anchura)                           *
     * @return un iterador según el recorrido indicado           */
     public IteratorIF<E> iterator (int traversal);
}