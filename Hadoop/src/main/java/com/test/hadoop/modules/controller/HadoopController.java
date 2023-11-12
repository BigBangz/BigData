package com.test.hadoop.modules.controller;

import com.google.gson.Gson;
import com.test.hadoop.modules.service.HadoopOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hdfs")
public class HadoopController {

    @Autowired
    private HadoopOperateService operateService;

    private final Gson gson = new Gson();

    @GetMapping("/upload")
    public ModelAndView uploadPage() {
        return new ModelAndView("upload");
    }

    @GetMapping("")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws Exception {
        operateService.uploadFile(file);
        return ResponseEntity.ok("{\"result\": \"success\"}");
    }

    @GetMapping("/findByPath")
    @ResponseBody
    public ResponseEntity<String> findByPath(String path) {
        List<Map<String, String>> all = operateService.findAll(path);
        String allData = gson.toJson(all);
        return ResponseEntity.ok(allData);
    }

    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<Boolean> deleteFile(@RequestBody String path) throws Exception {
        return ResponseEntity.ok(operateService.deleteFile(path));
    }


}
