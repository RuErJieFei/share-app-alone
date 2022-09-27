package com.yy.content.service;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yy.content.domain.entity.Share;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @create: 2022-09-06
 **/

public interface ShareService {
    /**
     * @param id id
     * @return share
     */
    //Share findById(Integer id);

    List<Share> findAll();

    Share findById(Integer id);
}
