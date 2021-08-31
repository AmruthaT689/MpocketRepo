package GenericUtils;
import java.util.Random;
/**
 * 
 * @author Amruthavalli
 *
 */

public class JavaUtility 
{
				/**
		 * This method will generate a random number
		 * @author Amruthavalli
		 * @return
		 */
		public int randomNumber() {
			Random random = new Random();
			int randomData=random.nextInt(5000);
			return randomData;
		}

	}


