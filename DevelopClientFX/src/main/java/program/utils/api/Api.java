package program.utils.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import program.models.Developer;
import program.models.Person;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс Api - нужен для связи с сервером по HTTP протоколу
 */
public class Api {
    private final String HOST = "http://localhost:8080";
    public Person currentLoginPerson;

    /**
     * Метод проверки авторизации пользователя на сервере
     *
     * @param login    логин
     * @param password пароль
     * @return true если есть в БД
     */
    public Boolean checkUserExists(String login, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("password", password);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        String URL = String.format("%s/users/auth", HOST);
        String response = HttpRequest.sendPost(URL, json);

        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response).getAsJsonObject();
            if (jsonResult.get("success").getAsBoolean()) {
                JsonObject result = jsonResult.get("result").getAsJsonObject();
                currentLoginPerson = new Person();
                currentLoginPerson.setLogin(result.get("login").getAsString());
                currentLoginPerson.setEmail(result.get("email").getAsString());
                currentLoginPerson.setFirstName(result.get("firstName").getAsString());
                currentLoginPerson.setLastName(result.get("lastName").getAsString());


                try {
                    currentLoginPerson.setPhoneNumber(result.get("phoneNumber").getAsString());
                } catch (RuntimeException e) {
                    currentLoginPerson.setPhoneNumber(null);
                }
                try {
                    LocalDate birthDay = DateConvert.stringToDate(result.get("birthDate").getAsString());
                    currentLoginPerson.setBirthday(birthDay);

                } catch (RuntimeException e) {
                    currentLoginPerson.setBirthday(null);
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Метод регистрации пользователя
     *
     * @param firstName имя
     * @param lastName  фамилия
     * @param login     логин
     * @param email     почта
     * @param password  пароль
     * @return true  если добавлен в БД
     */
    public Boolean createUser(String firstName, String lastName, String login, String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("email", email);
        map.put("password", password);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        String URL = String.format("%s/users/registration", HOST);
        String response = HttpRequest.sendPost(URL, json);
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response).getAsJsonObject();
            if (jsonResult.get("success").getAsBoolean()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод обновление информации о пользователе
     *
     * @param firstName   имя
     * @param lastName    фамилия
     * @param login       логин
     * @param email       почта
     * @param phoneNumber номер телефона
     * @param birthDate   дата рождения
     * @param password    пароль
     * @return true если создали пользователя в БД
     */
    public Boolean updateUser(String firstName, String lastName, String login,
                              String email, String phoneNumber, LocalDate birthDate, String password) {
        String URL = String.format("%s/users/update", HOST);
        Map<String, String> user = new HashMap<>();
        user.put("login", login);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        if (birthDate != null) {
            user.put("birthDate", String.valueOf(birthDate));
        }
        user.put("phoneNumber", phoneNumber);
        user.put("password", password);

        Gson gson = new Gson();
        String json = gson.toJson(user);
        String response = HttpRequest.sendPut(URL, json);
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response).getAsJsonObject();
            if (jsonResult.get("success").getAsBoolean()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Получаем всю информацию авиакомпаний
     *
     * @param filter вид сортировки
     * @return список авиакомпаний
     */
    public List<Developer> getAllDevelopers(Boolean filter) {
        String URL = String.format("%s/developers/all?filter=%s", HOST, filter);
        List<Developer> result = new ArrayList<>();
        String response = HttpRequest.sendGet(URL);

        if (response != null) {
            JsonArray jsonDeveloperArray = JsonParser.parseString(response).getAsJsonArray();
            for (int i = 0; i < jsonDeveloperArray.size(); i++) {
                JsonObject developerJson = jsonDeveloperArray.get(i).getAsJsonObject();

                Developer developer = new Developer();
                developer.setId(developerJson.get("developer_id").getAsInt());
                developer.setCode(developerJson.get("companyCode").getAsString());
                developer.setName(developerJson.get("companyName").getAsString());
                developer.setWebsite(developerJson.get("website").getAsString());
                try {
                    developer.setPhoneNumber(developerJson.get("phone").getAsString());
                } catch (RuntimeException e) {
                    developer.setPhoneNumber(null);
                }
                try {
                    developer.setEmail(developerJson.get("email").getAsString());
                } catch (RuntimeException e) {
                    developer.setEmail(null);
                }
                result.add(developer);
            }
        }

        return result;
    }

    public List<Developer> getDevelopersBySubCompanyName(String subString, Boolean filter) {
        subString = URLEncoder.encode(subString, StandardCharsets.UTF_8);

        String URL = String.format("%s/developers/like?name=%s&filter=%s", HOST, subString, filter);
        List<Developer> result = new ArrayList<>();
        String response = HttpRequest.sendGet(URL);

        if (response != null) {
            JsonArray jsonDeveloperArray = JsonParser.parseString(response).getAsJsonArray();
            for (int i = 0; i < jsonDeveloperArray.size(); i++) {
                JsonObject developerJson = jsonDeveloperArray.get(i).getAsJsonObject();

                Developer developer = new Developer();
                developer.setId(developerJson.get("developer_id").getAsInt());
                developer.setCode(developerJson.get("companyCode").getAsString());
                developer.setName(developerJson.get("companyName").getAsString());
                developer.setWebsite(developerJson.get("website").getAsString());
                try {
                    developer.setPhoneNumber(developerJson.get("phone").getAsString());
                } catch (RuntimeException e) {
                    developer.setPhoneNumber(null);
                }
                try {
                    developer.setEmail(developerJson.get("email").getAsString());
                } catch (RuntimeException e) {
                    developer.setEmail(null);
                }
                result.add(developer);
            }
        }
        return result;
    }


    /**
     * Метод удаления пользователя по почте
     *
     * @param email    почта
     * @param password пароль
     * @return true если удалили пользователя из БД
     */
    public boolean deleteUser(String email, String password) {
        password = URLEncoder.encode(password, StandardCharsets.UTF_8);

        String URL = String.format("%s/users/delete?email=%s&password=%s", HOST, email, password);
        String response = HttpRequest.sendGet(URL);
        if (response != null) {
            return true;
        } else {
            return false;
        }

    }
}
