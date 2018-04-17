package com.everydayhabits.product.module.web.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {

    MultipartFile file;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
