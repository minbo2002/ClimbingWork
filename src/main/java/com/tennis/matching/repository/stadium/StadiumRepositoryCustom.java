package com.tennis.matching.repository.stadium;

import com.tennis.matching.domain.stadium.dto.StadiumSearchRequest;
import com.tennis.matching.domain.stadium.entity.Stadium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StadiumRepositoryCustom {

    Page<Stadium> findList(Pageable pageable, StadiumSearchRequest searchRequest);
}
