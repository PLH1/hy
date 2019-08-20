package com.example.demo.redis.controller;
import com.example.demo.redis.mode.Depa;
import com.example.demo.redis.serivce.DepaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panlihuan
 * @date 2019/8/20
 */
@RestController
@RequestMapping("/depa")
public class DepaController {
    @Autowired
    DepaService depaService;

    @GetMapping(value="/{title}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "根据title得到dept对象", response = Depa.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "令牌失效"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Depa getDeptByTitle(@ApiParam("title") @PathVariable String title) {
        return depaService.getDepa(title);
    }
}
