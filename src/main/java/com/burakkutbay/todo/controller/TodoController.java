package com.burakkutbay.todo.controller;

import com.burakkutbay.todo.entity.Todo;
import com.burakkutbay.todo.repository.TodoRepository;
import com.burakkutbay.todo.util.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    private Calculator calculator=new Calculator();

    @Valid
    private List<Todo> todoList;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        todoList = (List<Todo>) todoRepository.findAll();
        int completedTask=0;
        for (Todo todo: todoList){
            if(todo.isComplete()){
                completedTask++;
            }
        }
        String a=calculator.percentTask(todoList.size(),completedTask);

        model.addAttribute("newtodo", new Todo());
        model.addAttribute("todos", todoList);
        model.addAttribute("percentTask",a);
        return "index";

    }

    //@RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addTodo(@ModelAttribute Todo todo, Model model, HttpServletRequest request) {
        todoRepository.save(todo);
        return "redirect:/";
    }

//    @RequestMapping(value = "/update",method = RequestMethod.POST)
//    public String updateTodo(@ModelAttribute("todos") ArrayList<Todo> todoList) {
//         for (Todo todo1 : todoList) {
//            Todo todo = new Todo(todo1.getDetail(), todo1.getTitle());
//            todo.setComplete(todo1.isComplete());
//            todo.setId(todo1.getId());
//            todoRepository.save(todo);
//        }
//        return "redirect:/";
//    }

    @RequestMapping("/update")
    public String updateTodo(@RequestParam("id") Long id) {
        Todo todo = todoRepository.findById(id).get();
        if (todo.isComplete()) {
            todo.setComplete(false);
        } else {
            todo.setComplete(true);
        }
        todoRepository.save(todo);
        return "redirect:/";
    }


    @RequestMapping("/delete")
    public String deleteTodo(@RequestParam("id") Long id) {
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
