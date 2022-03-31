package Internship;

import java.util.Optional;

public interface Dao<T> {
	void save(T t);
	Optional<T> finallyId(int id);
	void update (T t);
	void delete (T t);
}
