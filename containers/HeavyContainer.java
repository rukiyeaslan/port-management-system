
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * 
 * @author rukiyeaslan
 *
 */
public class HeavyContainer extends Container {
	
	/**
	 * 
	 * @param ID
	 * @param weight
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
	
	}
	
	/**
	  * {@inheritDoc}
	  */
	@Override
	public double consumption() {
		return 3.00 * this.getWeight();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

