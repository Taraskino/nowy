package pl.edu.wszib.springjpa;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.springjpa.dao.ToDoDao;
import pl.edu.wszib.springjpa.model.ToDo;

import java.util.List;

@RestController
@RequestMapping("/ToDo")
public class ToDoController {

    @Autowired
    private ToDoDao dao;

    @GetMapping

    public List<ToDo> list(){
        return dao.findAll();
    }
    @GetMapping("/{id}")
    public ToDo get(@PathVariable Integer id){
        return  dao.findById(id).orElse(null);
    }
    @PostMapping
    public ToDo create(@RequestBody ToDo toDo  ){
        toDo.setId(null);
        return dao.save(toDo);
    }
    @PutMapping
    public ToDo update(@RequestBody ToDo toDo  ){
        dao.findById(toDo.getId()).orElseThrow(()-> new RuntimeException());
        return dao.save(toDo);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        dao.deleteById(id);
    }
}
