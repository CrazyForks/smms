package com.github.pupilcc.smms.service;

import cn.hutool.http.HttpRequest;
import com.github.pupilcc.smms.domain.SmmsService;
import com.github.pupilcc.smms.dto.ImageDataDTO;
import com.github.pupilcc.smms.dto.ProfileDataDTO;
import com.github.pupilcc.smms.response.BaseDataResponse;
import com.github.pupilcc.smms.response.BaseListDataResponse;
import com.github.pupilcc.smms.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.InputStream;

@SpringBootTest
@Slf4j
class SmmsServiceTests {

    @Autowired
    private SmmsService smmsService;

    @Test
    @Disabled
    void getProfile() {
        BaseDataResponse<ProfileDataDTO> dto = smmsService.getProfile();
        log.info("用户信息:" + dto.getData());

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getSuccess(), true);
    }

    @Test
    @Disabled
    void uploadHistory() {
        BaseListDataResponse<ImageDataDTO> dto = smmsService.uploadHistory();

        log.info(dto.toString());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getSuccess(), true);
    }

    @Test
    @Disabled
    void uploadImage() throws Exception {
        String url = "https://vip2.loli.io/2022/07/03/U3h1ODWtNS2YClq.jpg";
        InputStream in = HttpRequest.get(url).execute().bodyStream();
        File file = new File("test.jpg");
        FileUtils.copyInputStreamToFile(in, file);
        BaseDataResponse<ImageDataDTO> dto = smmsService.uploadImage(file, "json");
        FileUtils.delete(file);

        log.info(dto.toString());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getSuccess(), true);
    }

    @Test
    @Disabled
    void deleteImage() {
        BaseResponse dto = smmsService.deleteImage("Jc6CUkezGLPfytn5xI48vMFTQK", "json");

        log.info(dto.toString());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(dto.getSuccess(), true);
    }
}
