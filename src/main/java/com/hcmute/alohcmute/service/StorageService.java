package com.hcmute.alohcmute.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class StorageService {

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        BlobId blobId = BlobId.of("alohcmute-71b8f.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        Storage storage = StorageClient.getInstance().bucket("alohcmute-71b8f.appspot.com").getStorage(); 
        Blob blob = storage.create(blobInfo, multipartFile.getBytes());
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", blob.getBucket(), blob.getName());
    }
}
