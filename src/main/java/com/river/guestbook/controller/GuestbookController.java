package com.river.guestbook.controller;

import com.river.guestbook.dto.GuestbookDTO;
import com.river.guestbook.dto.PageRequestDTO;
import com.river.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register")
    public void register(){
        log.info("register......");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto...." + dto);
        Long gno = guestbookService.register(dto);
        redirectAttributes.addFlashAttribute("msg", gno + "번 글이 등록되었습니다.");
        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
        log.info("Gno : " + gno);
        GuestbookDTO dto = guestbookService.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes){

        guestbookService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";

    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes){
        guestbookService.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno + "번 글이 삭제되었습니다.");
        return "redirect:/guestbook/list";
    }

}
