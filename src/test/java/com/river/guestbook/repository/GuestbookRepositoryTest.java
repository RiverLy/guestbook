package com.river.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.river.guestbook.entity.Guestbook;
import com.river.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void deleteAll(){
        guestbookRepository.deleteAll();
    }

    @Test
    public void insertDummies(){

        IntStream.range(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title...." + i)
                    .content("Content...." + i)
                    .writer("Writer...." + i)
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void testQuery1(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression1 = qGuestbook.title.contains(keyword);
        BooleanExpression expression2 = qGuestbook.content.contains(keyword);
        BooleanExpression expressionAll = expression1.or(expression2);

        builder.and(expressionAll);
        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }
}
