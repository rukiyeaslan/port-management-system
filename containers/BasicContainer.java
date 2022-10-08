
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * 
 * @author rukiyeaslan
 *
 */
public class BasicContainer extends Container {

	/**
	 * 
	 * @param ID
	 * @param weight
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	/**
	  * {@inheritDoc}
	  */
	@Override
	public double consumption() {
		
		return 2.50 * this.getWeight();
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

