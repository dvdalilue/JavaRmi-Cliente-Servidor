public class Box<E> {
    
    private E value;
    public Box<E> next;

    /*
     *Constructors
     */
    public Box() {// Empty constructor
	this.value = null;
	this.next = null;
    }
    public Box(E e) { //Constructor with element
	this.value = e;
	this.next = null;
    }

    public Box(E e, Box<E> next) {
        this.value = e;
	this.next = next;
    }
    
    /*
      Return true if the obj is equal
    */    
    public boolean equals(Box<E> obj) { //Depends of type E
	return (this.value == obj.get_value()); // Can Change!!
    }

    /*
      Return the value private
    */
    public E get_value() {
	return this.value;
    }
}
