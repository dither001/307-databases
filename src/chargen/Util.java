package chargen;

public abstract class Util {

	public static <T> int indexOfEnum(String string, T[] array) {
		int index = -1;
		for (int i = 0; i < array.length; ++i) {
			if (array[i].toString().compareToIgnoreCase(string) == 0) {
				index = i;
				break;
			}
		}

		return 1+index;
	}

}
