package com.yy.content.controller;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.alibaba.fastjson.JSONObject;
import com.yy.content.auth.CheckAuthorization;
import com.yy.content.domain.dto.ShareAuditDto;
import com.yy.content.domain.dto.ShareDto;
import com.yy.content.domain.entity.Share;
import com.yy.content.domain.entity.User;
import com.yy.content.openfeign.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.yy.content.common.ResponseResult;
import com.yy.content.service.ShareService;

/**
 * @description:
 * @author: yy
 * @create: 2022-09-06
 **/

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;

    private final UserService userService;

    @GetMapping("/all")
    public ResponseResult getAllShares() {
        return ResponseResult.success(shareService.findAll());
    }

    @GetMapping("{id}")
    ResponseResult getShareById(@PathVariable Integer id) {
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
        ShareDto shareDTO = ShareDto.builder().share(share).nickname(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDTO);
    }

    @PostMapping("/check")
    @CheckAuthorization("admin")
    ResponseResult auditContent(@RequestBody ShareAuditDto shareAuditDto) throws IllegalAccessException {
        return ResponseResult.success(shareService.auditShare(shareAuditDto));
    }
}
