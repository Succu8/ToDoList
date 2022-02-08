package kz.springboot.todoList.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ToDoList {
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2 , max = 30 , message = "Title should be between 2 and 30 characters")
    private String title;

    @NotEmpty(message = "Date should not be empty")
    private String date;

    @Range(min= 1 , max=10 , message = "Priority should be between 1 and 10 characters" )
    private int priority;


    private String description;

    private boolean status = false;

    public ToDoList() { }

    public ToDoList( int id, String title, String date, int priority, String description , boolean status) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
