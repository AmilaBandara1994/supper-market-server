package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.BrandDao;
import lk.earth.earthuniversity.dao.CategoryDao;
import lk.earth.earthuniversity.entity.Brand;
import lk.earth.earthuniversity.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandDao brandDao;

    @GetMapping(path ="/list",produces = "application/json")
    public List<Brand> get(@RequestParam HashMap<String , String > param) {

        List<Brand> brands = new ArrayList<Brand>();
        if(param.isEmpty()) brands = this.brandDao.findAll();
        String  categoryid= param.get("categoryid");

        if(categoryid != null){
            brands = this.brandDao.findAllByBrand(Integer.parseInt(categoryid));
        }
        return brands;
    }

}


