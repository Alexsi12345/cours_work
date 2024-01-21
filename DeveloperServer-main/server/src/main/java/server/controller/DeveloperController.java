package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.entity.DeveloperEntity; // Изменяем импорт
import server.service.DeveloperService; // Изменяем импорт

import java.util.List;

/**
 * Класс контроллер Девелоперских Компаний
 */
@RestController
@RequestMapping("/developers") // Изменяем маппинг
public class DeveloperController {

    private final DeveloperService developerService; // Изменяем имя сервиса

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    /**
     * Получить информацию о всех девелоперских компаниях и отсортировать
     *
     * @param filter вид сортировки
     * @return список девелоперских компаний
     */
    @GetMapping("/all") // Изменяем эндпоинт
    public ResponseEntity<List<DeveloperEntity>> getDevelopers(@RequestParam(name = "filter") boolean filter) {
        List<DeveloperEntity> developers = developerService.getAllDevelopers(filter);
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    /**
     * Получить информацию о всех девелоперских компаниях по имени и отсортировать их
     *
     * @param companyName имя девелоперской компании
     * @param filter      вид сортировки
     * @return список девелоперских компаний
     */
    @GetMapping("/like") // Изменяем эндпоинт
    public ResponseEntity<List<DeveloperEntity>> getDevelopersByName(@RequestParam(name = "name") String companyName,
                                                                     @RequestParam(name = "filter") boolean filter) {
        List<DeveloperEntity> developers = developerService.getDevelopersByName(companyName, filter);
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }
}
