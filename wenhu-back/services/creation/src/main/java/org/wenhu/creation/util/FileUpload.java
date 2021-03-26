package org.wenhu.creation.util;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wenhu.common.util.AliyunOss;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;

import java.io.IOException;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/26
 */

@RestController
@RequestMapping(name = "fileUpload",value = "fileUpload")
public class FileUpload {
    @PostMapping("imageUpload")
    public Result<String> imageUpload(@RequestParam MultipartFile image,@RequestParam String id) {
        String data = null;
        String code = null;
        String message=null;
        try {
            data = AliyunOss.ossFileUpload("images/" +id+ image.getOriginalFilename(), image.getInputStream());
            message= ResultCode.SUCCESS.getMessage();
            code= ResultCode.SUCCESS.getCode();
        } catch (IOException e) {
            e.printStackTrace();
            message= ResultCode.OPERATION_FAIL_D0001.getMessage();
            code= ResultCode.OPERATION_FAIL_D0001.getCode();
        }
        return Result.succeed(code,message,data);
    }
    @PostMapping("videoUpload")
    public Result<String> videoUpload(@RequestParam MultipartFile image,@RequestParam String id)  {
        String data = null;
        String code = null;
        String message=null;
        try {
            data = AliyunOss.ossFileUpload("video/" +id+ image.getOriginalFilename(), image.getInputStream());
            message= ResultCode.SUCCESS.getMessage();
            code= ResultCode.SUCCESS.getCode();
        } catch (IOException e) {
            e.printStackTrace();
            message= ResultCode.OPERATION_FAIL_D0001.getMessage();
            code= ResultCode.OPERATION_FAIL_D0001.getCode();
        }
        return Result.succeed(code,message,data);
    }
}
