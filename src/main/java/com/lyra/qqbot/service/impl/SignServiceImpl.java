package com.lyra.qqbot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyra.qqbot.common.constance.UriConstance;
import com.lyra.qqbot.entity.*;
import com.lyra.qqbot.mapper.LogMapper;
import com.lyra.qqbot.service.AuthService;
import com.lyra.qqbot.service.ILogService;
import com.lyra.qqbot.service.ISignService;
import com.lyra.qqbot.utils.QQBotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignServiceImpl implements ISignService {
    private static final Logger log = LoggerFactory.getLogger(SignServiceImpl.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private QQBotUtils qqBotUtils;

    private String url;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ILogService logService;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String login(User user) {
        HttpHeaders loginHeader = new HttpHeaders();
        loginHeader.setContentType(MediaType.APPLICATION_JSON);

        LoginFromData loginFromData = new LoginFromData();
        loginFromData.setEmail(user.getUsername());
        loginFromData.setPasswd(user.getPassword());

        HttpEntity<LoginFromData> httpEntity = new HttpEntity<>(loginFromData, loginHeader);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url + "/auth/login", httpEntity, String.class);

        SignResult signResult = null;
        try {
            signResult = objectMapper.readValue(responseEntity.getBody(), SignResult.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (signResult != null && signResult.getRet() != 1) {
            throw new RuntimeException("登录失败");
        }

        List<String> loginCookie = responseEntity.getHeaders().get("Set-Cookie");

        log.debug("login result: {}", responseEntity);
        log.debug("login result body:{}", responseEntity.getBody());


        StringBuffer stringBuffer = new StringBuffer();
        if (loginCookie != null) {
            loginCookie.forEach((cookie) -> stringBuffer.append(cookie).append(";"));
        }

        return stringBuffer.toString();
    }

    @Override
    public void sign(String cookie) {
        HttpHeaders signHeader = new HttpHeaders();
        signHeader.setContentType(MediaType.APPLICATION_JSON);

        signHeader.set("cookie", cookie);
        HttpEntity<Object> signHttpEntity = new HttpEntity<>(null, signHeader);
        SignResult signResult = restTemplate.postForObject(url + UriConstance.SING_URL, signHttpEntity, SignResult.class);

        log.debug("sign result: {}", signResult);

        Log log = new Log();
        assert signResult != null;
        log.setLog(signResult.getMsg());
        logService.save(log);
    }

    @Override
    public void get7TotalSignLog(String messageType, Long groupId, Long userId) {
        if (authService.auth(userId)) {
            List<Log> logList = logMapper.get7TotalSignLog();

            String logMessage = logList.stream().map((e) -> e.getLog() + "\t" + e.getCreateDate() + "\n").collect(Collectors.joining());

            qqBotUtils.sendMessage(messageType, userId, groupId, logMessage, true);
        } else {
            qqBotUtils.sendMessage(messageType, userId, groupId, "该用户不是管理员，没有该指令权限。", true);
        }


    }
}
