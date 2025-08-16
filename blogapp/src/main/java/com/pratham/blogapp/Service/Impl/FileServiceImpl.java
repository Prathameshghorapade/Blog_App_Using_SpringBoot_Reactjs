package com.pratham.blogapp.Service.Impl;

import com.pratham.blogapp.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //take FileNAme
        String name =file.getOriginalFilename();

        // random name genrate for file
        String randomID = UUID.randomUUID().toString();
        String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));


        //full file path
        String filePath = path+ File.separator+fileName1;

        // Create Folder If Not Created

        File newFile=new File(path);
        if(!newFile.exists()){

            newFile.mkdir();
        }

        //File COpy

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream inputStream =new FileInputStream(fullPath);
        return inputStream;
    }
}
