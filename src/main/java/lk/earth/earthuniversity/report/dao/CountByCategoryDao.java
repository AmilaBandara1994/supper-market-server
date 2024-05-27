package lk.earth.earthuniversity.report.dao;

import lk.earth.earthuniversity.report.entity.CountByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountByCategoryDao extends JpaRepository<CountByCategory,Integer> {

    @Query(value = "SELECT  NEW CountByCategory(c.name, COUNT(*)) FROM Item i, Subcategory s, Category c WHERE i.subcategory.id = s.id  AND s.category.id = c.id GROUP BY c.id")
    List<CountByCategory> countByCategory();

}

