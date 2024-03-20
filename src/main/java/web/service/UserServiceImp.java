package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp {
    UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    public List findAllUser() {
        return userDao.findAll();
    }

    public void addUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public User getUserById(Long id) {
        return userDao.getReferenceById(id);
    }

    public void editUser(Long id, String name, String lastName, byte age) {
        User user = userDao.getReferenceById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userDao.save(user);
    }
}
