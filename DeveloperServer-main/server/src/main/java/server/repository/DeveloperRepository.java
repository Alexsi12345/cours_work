package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.DeveloperEntity; // Изменяем импорт

import java.util.List;

/**
 * Репозиторий Девелоперских Компаний
 */
@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Long> {

    /**
     * Найти все девелоперские компании по названию компании, которые содержат подстроку
     *
     * @param subName часть названия девелоперской компании
     * @return список девелоперских компаний
     */
    List<DeveloperEntity> findDeveloperEntitiesByCompanyNameContains(String subName);

    /**
     * Найти все девелоперские компании по названию компании, которые содержат подстроку и отсортированы по имени в порядке убывания
     *
     * @param subName часть названия девелоперской компании
     * @return список девелоперских компаний
     */
    List<DeveloperEntity> findDeveloperEntitiesByCompanyNameContainsOrderByCompanyNameDesc(String subName);

    /**
     * Найти все девелоперские компании, отсортированные по имени компании в порядке возрастания
     *
     * @return список девелоперских компаний
     */
    List<DeveloperEntity> findAllByOrderByCompanyName();

    /**
     * Найти все девелоперские компании, отсортированные по имени компании в порядке убывания
     *
     * @return список девелоперских компаний
     */
    List<DeveloperEntity> findAllByOrderByCompanyNameDesc();
}
