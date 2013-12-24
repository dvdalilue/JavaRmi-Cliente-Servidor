/*
 *Implementation of Queue.
 */
public class Queue<E> {
    
    public Box<E> ini_queue; //First Element
    public Box<E> end_queue; //Last Element
    private int counter;      //Counter of elements

    /*
     *Constructor
     */
    public Queue() {
        this.ini_queue = null; //Initialize on null
        this.end_queue = null;
        this.counter = 0; //is empty
    }

    /*
     *Add an element to the queue of type "E"
     */    
    public void add_end(E elem) {
	Box<E> aux = new Box<E>(elem);
        if (this.counter == 0) { // Case of empty queue
            this.end_queue = this.ini_queue = aux;
        } else { // Else case 
            this.end_queue = this.end_queue.next = aux;
        }
	this.counter += 1; //Always counter raise by 1
    }

    /*
     *Add an element at the beginnig, like stack
     */
    public void add_ini(E elem) {
        Box<E> aux = new Box<E>(elem, this.ini_queue);
        if (this.counter == 0) { // Case of empty queue
            this.end_queue = this.ini_queue = aux;
        } else { // Else case
            this.ini_queue = aux;
        }
	this.counter += 1; //Always counter raise by 1
    }

    /*
     *Take the first element on the queue
     */
    public E first() {
	if (this.counter > 0) {
            E aux = this.ini_queue.get_value(); //gets the value on the first element
	    this.ini_queue = this.ini_queue.next; //lost the pointer to it
	    this.counter--; //decrese the counter by 1 
	    return aux;
	}
	return null;
    }

    /*
     *Verify if the queue is empty
     */
    public boolean is_empty() {
	if (this.counter == 0) {
	    return true; //if is 0, then true
	}
	return false;
    }

    /*Geter of a private attribute*/
    public int get_counter() {
        return this.counter;
    }
}
