package lk.earth.earthuniversity.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountByCategory {

    @Id
    private Integer id;
    private String categoryname;
    private Long count;

    public CountByCategory() {  }

    public CountByCategory(String categoryname, Long count) {
        this.categoryname = categoryname;
        this.count = count;
    }

    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    public Long getCount() {
        return count;
    }
    public void setCount(Long count) {
        this.count = count;
    }
    public void setId(Integer id) { this.id = id; }
    public Integer getId() {
        return id;
    }

}
