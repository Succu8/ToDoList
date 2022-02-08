package kz.springboot.todoList.controller;

import kz.springboot.todoList.repository.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import kz.springboot.todoList.model.ToDoList;

import javax.validation.Valid;

@Controller
@RequestMapping("/toDoList")
public class ToDoListController {

    private final ToDoListRepository toDoListRepository;

   @Autowired
    public ToDoListController(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    @GetMapping()
    public String index(Model model) {
       model.addAttribute("tasks" , toDoListRepository.index());
       return "index";
    }

    @GetMapping("/new")
    public String newTasks(@ModelAttribute("tasks")ToDoList todoList){return "new";}

    @PostMapping()
    public String create(@ModelAttribute("tasks") @Valid ToDoList todoList,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        toDoListRepository.save(todoList);
        return "redirect:/toDoList";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("tasks", toDoListRepository.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("tasks") @Valid ToDoList todoList, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";

        toDoListRepository.update(id, todoList);
        return "redirect:/toDoList";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        toDoListRepository.delete(id);
        return "redirect:/toDoList";
    }
}
