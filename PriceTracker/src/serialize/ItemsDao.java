package serialize;

import java.util.ArrayList;

public class ItemsDao {

	public static ArrayList<String> getItemList() {
		ArrayList<String> result = new ArrayList<>();
		result.add("Engelsfeder");
		result.add("Vollmondkristall");
		result.add("Wildh�ter");
		result.add("Makrele");
		result.add("Riesiger Erholungstrank");
		result.add("G�ttlicher Erholungstrank");
		result.add("Angriffstrank");
		result.add("Verteidigungstrank");
		return result;
	}
	
}
