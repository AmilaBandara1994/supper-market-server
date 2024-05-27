package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CategoryDao;
import lk.earth.earthuniversity.dao.SubcategoryDao;
import lk.earth.earthuniversity.entity.Category;
import lk.earth.earthuniversity.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/subcategories")
public class subcategoryController {

    @Autowired
    private SubcategoryDao subcategoryDao;

    @GetMapping(path ="/list",produces = "application/json")
    public List<Subcategory> get(@RequestParam HashMap<String, String> params) {

        List<Subcategory> subcategories = this.subcategoryDao.findAll();

        if(params.isEmpty()) return subcategories;
        String categoryid = params.get("categoryid");
        Stream<Subcategory> subcategoryStream = subcategories.stream();
        if(categoryid != null) subcategoryStream =  subcategoryStream.filter(subcategory -> subcategory.getCategory().getId() == Integer.parseInt(categoryid));

        return subcategoryStream.collect(Collectors.toList());
    }

}


