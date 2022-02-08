package kz.springboot.todoList.repository;

import kz.springboot.todoList.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoListRepository {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ToDoListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ToDoList> index() {
        return jdbcTemplate.query("SELECT * FROM ToDoList", new BeanPropertyRowMapper<>(ToDoList.class));
    }

    public ToDoList show(int id) {
        return jdbcTemplate.query("SELECT * FROM ToDoList WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(ToDoList.class))
                .stream().findAny().orElse(null);
    }

    public void save(ToDoList saveList) {
        jdbcTemplate.update("INSERT INTO ToDoList VALUES(1, ?, ?, ?, ?, ?)", saveList.getTitle(), saveList.getDate(),
                saveList.getPriority() , saveList.getDescription() , saveList.isStatus());
    }

    public void update(int id, ToDoList updatedList) {
        jdbcTemplate.update("UPDATE ToDoList SET title=?, date=?, priority=? , description=? , status=? WHERE id=?", updatedList.getTitle(),
                updatedList.getDate(), updatedList.getPriority(), updatedList.getDescription() , updatedList.isStatus() , id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM ToDoList WHERE id=?", id);
    }
}
