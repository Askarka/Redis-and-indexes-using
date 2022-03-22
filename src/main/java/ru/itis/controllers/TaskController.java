package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dtos.TaskForm;
import ru.itis.services.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task/create")
    public String getCreatePage(){
        return "createPage";
    }

    @PostMapping("/task/create")
    public String createTask(TaskForm form){
        taskService.addTask(form);
        return "redirect:/page";
    }

    @GetMapping("/task/{id}/edit")
    public String getUpdatePage(Model model, @PathVariable Long id){
        model.addAttribute("task", taskService.getTaskById(id).orElseThrow(IllegalArgumentException::new));
        model.addAttribute("id", id);
        return "updatePage";
    }

    @PostMapping("/task/{id}/edit")
    public String update(@PathVariable Long id, TaskForm taskForm){
        taskService.updateTask(taskForm, id);
        return "redirect:/page";
    }

    @GetMapping("/task/{id}/delete")
    public String delete(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/page";
    }
}
