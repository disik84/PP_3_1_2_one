package web.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;
import java.util.Objects;

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
        return userDao.getReferenceById(id);
    }

    @Override
    @Transactional
    public String editUser(Long id, String name, String lastName, byte age) {
        User user = userDao.getReferenceById(id);
        System.out.println(name + lastName + age);
        if (name != "" && lastName != "" && age != 0) {
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            userDao.save(user);
            return "redirect:/";
        } else {
            return "error-edit-user";
        }

    }
}
