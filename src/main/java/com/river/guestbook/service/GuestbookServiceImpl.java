package com.river.guestbook.service;

import com.river.guestbook.dto.GuestbookDTO;
import com.river.guestbook.dto.PageRequestDTO;
import com.river.guestbook.dto.PageResultDTO;
import com.river.guestbook.entity.Guestbook;
import com.river.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO--------------------------");
        log.info(dto);
        Guestbook entity = dtoToEntity(dto);
        log.info(entity);

        guestbookRepository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        Page<Guestbook> result = guestbookRepository.findAll(pageable);
        Function<Guestbook, GuestbookDTO> fn = (entity ->
            entityToDto(entity)
        );
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = guestbookRepository.findById(gno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }
}
