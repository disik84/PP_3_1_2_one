package web.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List findAllUser() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).orElse(new User());
    }

    @Override
    @Transactional
    public void editUser(Long id, String name, String lastName, byte age) {
        User user = userDao.getReferenceById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userDao.save(user);
    }
}
