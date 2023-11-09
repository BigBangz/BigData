package com.test.hadoop.modules.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HadoopOperateService{
    @Autowired
    private FileSystem fileSystem ;

    /**
     * 查询
     */
    public List<Map<String, String>> findAll(){
        try {
            Path dir =new Path("/");
            FileStatus[] fss = fileSystem.listStatus(dir);
            List<Map<String, String>> list = Arrays.stream(fss).map(file ->
                    Map.of("Directory", Boolean.toString(file.isDirectory()),
                            "Path", file.getPath().getName(),
                            "ModificationTime", Long.toString(file.getModificationTime()),
                            "Len", Long.toString(file.getLen())))
                    .collect(Collectors.toList());
            return list;
        } catch (IOException e) {
            log.error("查询HDFS错误：{}", e);
        }
        return List.of();
    }

    public void uploadFile(MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        log.info("name: {}", fileName);

        //获取HDFS文件系统的输出流
        FSDataOutputStream fos = fileSystem.create(new Path("/" + fileName));
        //获取本地文件的输入流
        //FileInputStream fis = new FileInputStream(inputStream);

        //上传文件：通过工具类把输入流拷贝到输出流里面，实现本地文件上传到HDFS
        IOUtils.copyBytes(inputStream,fos,1024,true);
    }
}