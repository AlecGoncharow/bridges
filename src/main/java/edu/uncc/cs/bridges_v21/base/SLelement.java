package bridges.base;

/**
 * @brief This class can be used to instantiate Singly List Elements.

 * This class extends Element and takes a generic parameter <E> for 
 *	representing application specific data.
 *
 * @author Mihai Mehedint, Kalpathi Subramanian
 *
 * @param <E>
 */
public class SLelement<E> extends Element<E> {
	protected SLelement<E> next=null; //the link to the next element 

	/**
	 * 
	 * This constructor creates an SLelement object
	 * and sets the next pointer to null
	 * 
	 */

	public SLelement() {
		super();
		this.next = null;
	}
	
	/**
	 * This constructor creates an SLelement object of value "e" and label "label"
	 * and sets the next pointer to null
	 * @param label the label of SLelement that shows up on the Bridges visualization
	 * @param e the generic object that this SLelement will hold
	 */
	public SLelement (String label, E e){
		super(label, e);
		this.next = null;
	}
	
	/**
	 * Creates a new element with value "e" and sets the next pointer
	 * to the SLelement referenced by the "next" argument 
	 * @param e the generic object that this SLelement will hold
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public SLelement (E e, SLelement<E> next) {
		super(e);
		this.setNext(next);
	}

	/**
	 * Creates a new element and sets the next pointer
	 * to the SLelement "next"
	 * @param next the SLelement that should be assigned to the next pointer
	 */
	public SLelement (SLelement<E> next) {
		this.setNext(next);
	}
	
/*
	public SLelement (SLelement<E> original) {
		super(original.getValue());
		this.setLabel(original.getLabel());
		this.setVisualizer(original.getVisualizer());
		this.setNext(original.getNext());
	}
*/
	/**
	 *	This method gets the data structure type
	 *
	 *	@return  The date structure type as a string
	 **/
	public String getDataStructType() {
		return "SinglyLinkedList";
	}
	
	/**
	 * Retrieves the next SLelement
	 * @return SLelement<E> assigned to next
	 */
	public SLelement<E> getNext() {
		return next;
	}
	
	/**
	 * Sets the pointer to the next SLelement
	 * @param next SLelement<E> that should be assigned to the next pointer
	 */
	public void setNext(SLelement<E> next) {
		this.next = next;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SLelement [next=" + next + ", getNext()=" + getNext()
				+ ", getIdentifier()=" + getIdentifier() + ", getVisualizer()="
				+ getVisualizer()
				+ ", getClassName()=" + getClassName()
				+ ", getRepresentation()=" + getRepresentation()
				+ ", getLabel()=" + getLabel() + ", getValue()=" + getValue()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
