package com.example.food.controller;

import com.example.food.domain.entity.Answer;
import com.example.food.domain.entity.Food;
import com.example.food.domain.entity.Recipe;
import com.example.food.domain.entity.User;
import com.example.food.service.AnswerService;
import com.example.food.service.FoodService;
import com.example.food.service.RecipeService;
import com.example.food.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
@Log4j2
public class MainController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AnswerService answerService;
    public MainController(FoodService foodService, UserService userService, RecipeService recipeService, AnswerService answerService) {
        this.foodService = foodService;
        this.userService = userService;
        this.recipeService=recipeService;
        this.answerService=answerService;

    }

    @GetMapping({"/","/main"})
    public String main_page(){
        log.info("main.........");

        return "/first";
    }

    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "/boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Food food) {

        foodService.write(food);

        String s = "redirect:/food/list";
        return s;
    }


    @GetMapping("/login")
    public String login() {

        return "/login";
    }


    //회원가입
    @GetMapping("/newmem")
    public String newmem(){

        return "/newmem";
    }

    @PostMapping("/newmem/pro")
    public String loginPro(User user) {

        userService.writeUser(user);
        return("/first");
    }

    //비번찾기
    @GetMapping("/findpw")
    public String findpassword(){

        return "/findpw";
    }


    //질문 리스트
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Food> list = foodService.foodList(pageable);

        int nowPage=list.getPageable().getPageNumber();
        int startPage=Math.max(1,nowPage-4);
        int endPage=Math.min(nowPage+4,list.getTotalPages());
        model.addAttribute("list",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);


        return "/list";
    }

    @GetMapping("/recipe")
    public String viewRecipe(Model model){
        model.addAttribute("list",recipeService.recipeList());

        return "/recipe";
    }

    @GetMapping("/recipe/write")
    public String writeRecipe(){

        return "/writeRecipe";
    }

    @PostMapping("/recipe/writepro")
    public String writeRecipePro(Recipe recipe) {

        recipeService.write(recipe);
        return "/first";
    }

    @GetMapping("/list/view")
    public String listView(Model model, @RequestParam("id") Integer id){
        model.addAttribute("list",foodService.listView(id));
        //model.addAttribute("board",boardService.boardView(id));

        return "/listView";
    }

    @PostMapping("/answer/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable("id") Integer foodid,
                               @RequestParam(value="content") String content) {

        this.answerService.create(content, Integer.valueOf(foodid));

        return "redirect:/food/list/view/{id}";
    }


}

//깃허브 테스트중