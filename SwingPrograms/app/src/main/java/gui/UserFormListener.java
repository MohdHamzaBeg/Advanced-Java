package gui;

@FunctionalInterface
// Observer pattern
public interface UserFormListener {
	void formSubmitted(String name, String password);
}
