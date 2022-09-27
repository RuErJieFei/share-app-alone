package com.yy.content.repository;

import com.yy.content.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: yy
 * @create: 2022-09-06
 **/

public interface ShareRepository extends JpaRepository<Share, Integer> {
}
