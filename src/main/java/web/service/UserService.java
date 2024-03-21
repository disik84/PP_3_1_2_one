package web.service;

import web.dao.UserDao;
import web.model.User;

import java.util.List;

public interface UserService {
    public List findAllUser();

    public void addUser(User user);

    public void deleteUser(Long id);

    public User getUserById(Long id);

    public String editUser(Long id, String name, String lastName, byte age);
}
