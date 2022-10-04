package dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll(String name, String surname, Integer nationalityId);
    User findByEmailAndPassword(String email, String password);
    User getById(int id);
    boolean addUser(User u);
    boolean updateUser(User u);
    boolean removeUser(int id);
    public User findByEmail(String email);
}
