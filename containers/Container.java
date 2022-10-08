
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * 
 * @author rukiyeaslan
 *
 */
public abstract class Container {
	private int ID;
	private int weight;
		
/**
 * 
 * @param ID
 * @param weight
 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	
	}
	/**
	 * 
	 * @return fuel consumption required by the container
	 */
	public abstract double consumption();
	
	
	//checking type??
	
	/**
	 * 
	 * @param other
	 * @return if two containers are same or not
	 */
	public  boolean equals(Container other) {
		if (this.ID == other.getID() && this.weight == other.getWeight() && this.getClass().equals(other.getClass())) {
			return true;
		}
		else {
			return false;}
	}
		
	/**
	 * 
	 * @return ID of the container
	 */
	public int getID() {
		return ID;
	}
    
    /**
     * 
     * @return weight of the container
     */
	public int getWeight() {
		return weight;
	}
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

