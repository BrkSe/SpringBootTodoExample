package com.burakkutbay.todo.controller;

import com.burakkutbay.todo.entity.Todo;
import com.burakkutbay.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        Todo todo1 = new Todo(todo.getTitle(), todo.getTitle());
        todoRepository.save(todo1);
        return "redirect:/";
    }
}
