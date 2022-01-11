package com.river.guestbook.service;

import com.river.guestbook.dto.GuestbookDTO;
import com.river.guestbook.dto.PageRequestDTO;
import com.river.guestbook.dto.PageResultDTO;
import com.river.guestbook.entity.Guestbook;
import com.river.guestbook.repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTest {

    @Autowired
    GuestbookService service;

    @Test
    public void testRegister(){
        GuestbookDTO dto = GuestbookDTO.builder()
                .title("Sample title")
                .content("Sample content")
                .writer("Garam")
                .build();
        System.out.println(service.register(dto));
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
        System.out.println("START : " + resultDTO.getStart());
        System.out.println("END : " + resultDTO.getEnd());
        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPages());
        System.out.println("=========================");
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        System.out.println("=========================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
