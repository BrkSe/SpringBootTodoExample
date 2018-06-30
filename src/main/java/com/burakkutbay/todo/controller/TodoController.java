package com.burakkutbay.todo.controller;

import com.burakkutbay.todo.entity.Todo;
import com.burakkutbay.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;


    @RequestMapping("/")
    public String index(Model model) {
        ArrayList todoList = (ArrayList) todoRepository.findAll();
        model.addAttribute("newtodo", new Todo());
        model.addAttribute("todos", todoList);
        return "index";

    }

    @RequestMapping(value = "/add")
    public String addTodo(@ModelAttribute Todo todo, Model model) {
        todoRepository.save(todo);
        model.addAttribute("succesalertdiv",false);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateTodo(@ModelAttribute ArrayList<Todo> todoList) {
        for (Todo todo1 : todoList) {
            Todo todo = new Todo(todo1.getDetail(), todo1.getTitle());
            todo.setComplete(todo1.isComplete());
            todo.setId(todo1.getId());
            todoRepository.save(todo);
        }
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String deleteTodo(@ModelAttribute ArrayList<Todo> todoList) {
        return "redirect:/";
    }
}
