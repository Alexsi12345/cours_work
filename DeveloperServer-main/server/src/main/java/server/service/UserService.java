package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.entity.UserEntity;
import server.exceptions.UserAlreadyExistException;
import server.exceptions.UserNotFoundException;
import server.repository.UserRepository;
import server.utils.StringToHashUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Логика работы с сущностью Пользователя
 */
@Service
public class UserService {

    private static final String USER_ALREADY_EXIST = "Пользователь уже зарегистрирован";
    private static final String USER_NOT_FOUND_BY_EMAIL = "Пользователь с почтой %s не найден";
    private static final String USER_NOT_FOUND_BY_ID = "Пользователь c id %d не найден";
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Добавить пользователя в БД
     *
     * @param user сущность пользователя
     * @return сохраненного пользователя
     */
    @Transactional
    public UserEntity saveUser(UserEntity user) throws UserAlreadyExistException {
        UserEntity saveUser = new UserEntity();
        saveUser.setLogin(user.getLogin());
        saveUser.setFirstName(user.getFirstName());
        saveUser.setLastName(user.getLastName());
        saveUser.setEmail(user.getEmail());
        saveUser.setPassword(StringToHashUtil.convert(user.getPassword()));
        return userRepo.save(saveUser);
    }

    /**
     * Обновить пользователя в БД
     * (логин, имя, фамилия, почта, пароль, номер телефона, дата рождения)
     *
     * @param user сущность пользователя
     */
    @Transactional
    public void updateByEmail(UserEntity user) {
        userRepo.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, user.getEmail())));
        String email = user.getEmail();
        String password = StringToHashUtil.convert(user.getPassword());
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate birthDate = user.getBirthDate();
        String phoneNumber = user.getPhoneNumber();
        userRepo.updateByEmail(email, password, firstName, lastName, birthDate, phoneNumber);
    }

    /**
     * Удалить пользователя из БД по email
     *
     * @param email почта
     */
    @Transactional
    public void deleteByEmail(String email) {
        userRepo.deleteByEmail(email);
    }


    /**
     * Метод совпадения логина и пароля
     *
     * @param login    логин
     * @param password пароль
     * @return
     */
    @Transactional(readOnly = true)
    public UserEntity checkAuth(String login, String password) {

        Optional<UserEntity> userEntity = userRepo.findUserEntityByEmailAndPassword(login, password);

        return userEntity.orElse(null);
    }

}
