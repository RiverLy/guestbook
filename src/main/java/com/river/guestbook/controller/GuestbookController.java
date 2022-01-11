package com.river.guestbook.controller;

import com.river.guestbook.dto.PageRequestDTO;
import com.river.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Log4j2
public class GuestbookController {

    private final GuestbookService guestbookService;

    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/", "/list"})
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list..........");
        model.addAttribute("result", guestbookService.getList(pageRequestDTO));
    }

}
