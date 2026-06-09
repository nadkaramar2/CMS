package com.TranEco.cardManagement.cryptservice;

import org.springframework.web.multipart.MultipartFile;

public interface FileEncryptDecryptService
{

    String decryptFile(MultipartFile file);
    
}
