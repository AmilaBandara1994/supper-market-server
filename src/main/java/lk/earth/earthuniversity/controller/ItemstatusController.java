package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CategoryDao;
import lk.earth.earthuniversity.dao.ItemstatusDao;
import lk.earth.earthuniversity.entity.Category;
import lk.earth.earthuniversity.entity.Itemstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/itemstatuses")
public class ItemstatusController {

    @Autowired
    private ItemstatusDao itemstatusDao;

    @GetMapping(path ="/list",produces = "application/json")
    public List<Itemstatus> get() {

        List<Itemstatus> itemstatuses = this.itemstatusDao.findAll();

        itemstatuses = itemstatuses.stream().map(
                itemstatus -> { Itemstatus is = new Itemstatus();
                            is.setId(itemstatus.getId());
                            is.setName(itemstatus.getName());
                            return is; }
        ).collect(Collectors.toList());

        return itemstatuses;

    }

}

