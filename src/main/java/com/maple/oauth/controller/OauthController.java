package com.maple.oauth.controller;

import com.maple.oauth.common.api.CommonResult;
import com.maple.oauth.model.dto.TokenDto;
import com.maple.oauth.service.IOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;


@RestController
@RequestMapping("/oauth")
@Validated
public class OauthController {

    @Autowired
    private IOauthService oauthService;

    /**
     * generate code
     *
     * @param clientId
     * @return
     */
    @GetMapping("/code")
    public CommonResult<String> code(@NotBlank(message = "clientId不能为空") @RequestParam("clientId") String clientId) {
        CommonResult<String> result = oauthService.code(clientId);
        return result;
    }

    /**
     * generate token
     *
     * @param clientId
     * @return
     */
    @GetMapping("/token")
    public CommonResult<TokenDto> token(@NotBlank(message = "clientId不能为空") @RequestParam("clientId") String clientId,
                                        @NotBlank(message = "clientSecret不能为空") @RequestParam("clientSecret") String clientSecret,
                                        @NotBlank(message = "code不能为空") @RequestParam("code") String code) {
        CommonResult<TokenDto> result = oauthService.token(clientId, clientSecret, code);
        return result;
    }

    /**
     * generate refresh_token
     *
     * @param refreshToken
     * @return
     */
    @GetMapping("/refreshToken")
    public CommonResult<TokenDto> refreshToken(@NotBlank(message = "refreshToken不能为空") @RequestParam("refreshToken") String refreshToken) {
        CommonResult<TokenDto> result = oauthService.refreshToken(refreshToken);
        return result;
    }


}
