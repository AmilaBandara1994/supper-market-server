package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.EmployeeDao;
import lk.earth.earthuniversity.dao.ItemDao;
import lk.earth.earthuniversity.entity.Employee;
import lk.earth.earthuniversity.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemDao itemDao;



    @GetMapping(produces = "application/json")
//    @PreAuthorize("hasAuthority('employee-select')")
    public List<Item> get(@RequestParam HashMap<String,String> params) {
        List<Item> items =  this.itemDao.findAll();

        if(params.isEmpty()) return items;

        String itemname = params.get("itemname");
        String itemstatusid = params.get("itemstatusid");
        String categoryid = params.get("categoryid");

        Stream<Item> itemStream = items.stream();
        if(itemname != null) itemStream = itemStream.filter(i -> i.getName().contains(itemname));
        if(itemstatusid != null) itemStream = itemStream.filter(i -> i.getItemstatus().getId() == Integer.parseInt(itemstatusid));
        if(categoryid != null) itemStream = itemStream.filter(i -> i.getSubcategory().getCategory().getId() == Integer.parseInt(categoryid));

        return itemStream.collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('Employee-Insert')")
    public HashMap<String,String> add(@RequestBody Item item){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        if(itemDao.findByCode(item.getCode())!=null)
            errors = errors+"<br> Existing Code";
        if(itemDao.findByName(item.getName())!=null)
            errors = errors+"<br> Existing Name";


        if(errors=="")
        itemDao.save(item);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(item.getId()));
        responce.put("url","/items/"+item.getId());
        responce.put("errors",errors);

        return responce;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasAuthority('Employee-Update')")
    public HashMap<String,String> update(@RequestBody Item item){

        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Item item1 = itemDao.findByCode(item.getCode());
        Item item2 = itemDao.findByName(item.getName());

        if(item1!=null && item.getId()!=item1.getId())
            errors = errors+"<br> Existing Code";
        if(item2!=null && item.getId()!=item2.getId())
            errors = errors+"<br> Existing Name";

        if(errors=="") itemDao.save(item);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(item.getId()));
        responce.put("url","/items/"+item.getId());
        responce.put("errors",errors);

        return responce;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){


        HashMap<String,String> responce = new HashMap<>();
        String errors="";

        Item item1 = itemDao.findByMyId(id);

        if(item1==null)
            errors = errors+"<br> Item Does Not Existed";

        if(errors=="") itemDao.delete(item1);
        else errors = "Server Validation Errors : <br> "+errors;

        responce.put("id",String.valueOf(id));
        responce.put("url","/items/"+id);
        responce.put("errors",errors);

        return responce;
    }

}



