package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.entity.DeveloperEntity;
import server.repository.DeveloperRepository;

import java.util.List;

/**
 * Сервис Девелоперских Компаний
 */
@Service
public class DeveloperService {
    private final DeveloperRepository developerRepo;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepo) {
        this.developerRepo = developerRepo;
    }

    /**
     * Найти все девелоперские компании и отсортировать их
     *
     * @param filter вид сортировки
     * @return список девелоперских компаний
     */
    @Transactional(readOnly = true)
    public List<DeveloperEntity> getAllDevelopers(boolean filter) {
        if (filter) {
            return developerRepo.findAllByOrderByCompanyNameDesc();
        } else {
            return developerRepo.findAllByOrderByCompanyName();
        }
    }

    /**
     * Найти все девелоперские компании по вхождению подстроки в название и отсортировать их
     *
     * @param subName часть имени
     * @param filter  вид сортировки
     * @return список девелоперских компаний
     */
    @Transactional(readOnly = true)
    public List<DeveloperEntity> getDevelopersByName(String subName, boolean filter) {
        if (filter) {
            return developerRepo.findDeveloperEntitiesByCompanyNameContainsOrderByCompanyNameDesc(subName);
        } else {
            return developerRepo.findDeveloperEntitiesByCompanyNameContains(subName);
        }
    }

}
