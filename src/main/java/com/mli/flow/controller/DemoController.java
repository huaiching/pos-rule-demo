package com.mli.flow.controller;

import com.mli.flow.service.RuleMainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demoController")
@Tag(name = "Demo Controller", description = "Demo API")
public class DemoController {
    @Autowired
    private RuleMainService ruleMainService;

    @PostMapping("/demo/v1")
    @Operation(summary = "Demo API", description = "Demo API")
    public ResponseEntity<String> demo(@RequestBody String message) {
        String msg = "歡迎：" + message;
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/springUtils/v1")
    @Operation(summary = "StringUtils Api", description = "StringUtil 測試用")
    public ResponseEntity<Void> stringUtils() {

        String st1 = null;
        if (StringUtils.isBlank(st1)) {
            System.out.println("空白");   // 進入這邊
        } else {
            System.out.println("非空白");
        }

        String st2 = "";
        if (StringUtils.isBlank(st2)) {
            System.out.println("空白");   // 進入這邊
        } else {
            System.out.println("非空白");
        }

        String st3 = " ";
        if (StringUtils.isBlank(st3)) {
            System.out.println("空白");   // 進入這邊
        } else {
            System.out.println("非空白");
        }

        String st4 = "abc";
        if (StringUtils.isBlank(st4)) {
            System.out.println("空白");
        } else {
            System.out.println("非空白");  // 進入這邊
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/CollectionUtils/v1")
    @Operation(summary = "CollectionUtils Api", description = "CollectionUtils 測試用")
    public ResponseEntity<Void> CollectionUtils() {

        List<String> ls1 = null;
        if (CollectionUtils.isEmpty(ls1)) {
            System.out.println("空白");   // 進入這邊
        } else {
            System.out.println("非空白");
        }

        List<String> ls2 = new ArrayList<>();
        if (CollectionUtils.isEmpty(ls2)) {
            System.out.println("空白");   // 進入這邊
        } else {
            System.out.println("非空白");
        }

        List<String> ls3 = new ArrayList<>();
        ls3.add("1");
        ls3.add("2");
        if (CollectionUtils.isEmpty(ls3)) {
            System.out.println("空白");
        } else {
            System.out.println("非空白");  // 進入這邊
        }

        return ResponseEntity.ok().build();
    }
}
