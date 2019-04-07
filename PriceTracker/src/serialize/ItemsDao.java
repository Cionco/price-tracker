package serialize;

import java.util.ArrayList;

public class ItemsDao {

	public static ArrayList<String> getItemList() {
		ArrayList<String> result = new ArrayList<>();
		result.add("Engelsfeder");
		result.add("Vollmondkristall");
		result.add("Wildhüter");
		result.add("Makrele");
		result.add("Riesiger Erholungstrank");
		result.add("Göttlicher Erholungstrank");
		result.add("Angriffstrank");
		result.add("Verteidigungstrank");
		return result;
	}
	
}
