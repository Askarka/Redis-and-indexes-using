package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dtos.TaskDto;
import ru.itis.dtos.TaskFilter;
import ru.itis.dtos.TaskForm;
import ru.itis.services.TaskService;

@Controller
public class PageController {

    public static final int DEFAULT_PAGE_SIZE = 15;

    @Autowired
    private TaskService taskService;

    @GetMapping("/page")
    public String getTemplate(Model model){
        TaskFilter form = new TaskFilter();
        model.addAttribute("form", form);
        return "mainPage";
    }

    @GetMapping("/page/filter")
    public String getFilteredPage(Model model, TaskFilter taskFilter){
        System.out.println(taskFilter);
        Page<TaskDto> tasks = taskService.getFilteredTasks(0, DEFAULT_PAGE_SIZE, taskFilter);
        model.addAttribute("form", taskFilter);
        model.addAttribute("currentPage", 0);
        model.addAttribute("pageCount", tasks.getTotalPages());
        model.addAttribute("page", tasks.toList());
        return "mainPage";
    }

    @GetMapping(value = "/page/filter", params = "page")
    public String getFilteredPage(Model model, TaskFilter taskFilter, @RequestParam int page){
        System.out.println(taskFilter);
        Page<TaskDto> tasks = taskService.getFilteredTasks(page, DEFAULT_PAGE_SIZE, taskFilter);
        model.addAttribute("form", taskFilter);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", tasks.getTotalPages());
        model.addAttribute("page", tasks.toList());
        return "mainPage";
    }

    @GetMapping("/random")
    public String addRandom(){
        taskService.fillDBWithRandomData(200000);
        return "redirect:/page";
    }
}
