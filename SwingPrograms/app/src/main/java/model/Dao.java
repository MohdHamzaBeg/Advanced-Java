package model;

import java.util.Optional;

public interface Dao <User>{
	void save(User t);
	void update (User t);
	void delete (User t);
	Optional findbyId(int id);
}
