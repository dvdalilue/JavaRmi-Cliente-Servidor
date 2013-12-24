/**
 * @author      David Lilue <dvdalilue@gmail.com>
 * @version     1.0          
 * @since       2013-12-23
 */
public class Box<E> {
    
    /**
     * Valor del contenido de la caja 
     */
    private E value;
    /**
     * Apuntador al elemento siguiente de la cola
     */
    public Box<E> next;

    /**
     * Constructor de la clase Box, vacia.
     * <p>
     * Crea una estructura con dos elementos, el valor(contenido)
     * y un apuntador a una elemento del mismo tipo. Inicializa 
     * los dos en null.
     * <p>
     */
    public Box() {// Empty constructor
	this.value = null;
	this.next = null;
    }
    /**
     * Constructor de la clase Box, solo con el elemento.
     * <p>
     * Crea una estructura con dos elementos, el valor(contenido)
     * y un apuntador a una elemento del mismo tipo. Inicializa 
     * el valor con el elemento y next con null.
     * <p>
     *
     * @param  e elemento con que se creara la caja
     */
    public Box(E e) { //Constructor with element
	this.value = e;
	this.next = null;
    }
    /**
     * Constructor de la clase Box completa.
     * <p>
     * Crea una estructura con dos elementos, el valor(contenido)
     * y un apuntador a una elemento del mismo tipo. Inicializa 
     * el valor con el elemento y next con la otra caja.
     * <p>
     *
     * @param  e elemento con que se creara la caja
     * @param  next elemento que sera apuntado por esta caja
     */
    public Box(E e, Box<E> next) {
        this.value = e;
	this.next = next;
    }
    
    /**
     * Verifica si esta caja tiene el mismo valor que otra.
     * <p>
     * Compara con el operador "==", esta sujeto a cambios.
     * Al momento de que el tipo con el cual fue definida la caja
     * no pueda compararse con ese operador, dara una excepcion.
     * <p>
     *
     * @return true si los valores son iguales, sino false
     */
    public boolean equals(Box<E> obj) { //Depends of type E
	return (this.value == obj.get_value()); // Can Change!!
    }

    /**
     * Obtiene el valor de la caja.
     *
     * @return valor contenido en la caja
     */
    public E get_value() {
	return this.value;
    }
}
