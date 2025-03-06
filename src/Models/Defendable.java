package Models;

public interface Defendable {
	 Type getType();
	public void defend(Pokemon target);
	String getName();
}