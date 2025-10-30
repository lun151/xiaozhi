package com.lun.Server;

import org.springframework.web.multipart.MultipartFile;

public interface UploadKnowledgeLibraryService {

    public void uploadKnowledgeLibrary(MultipartFile[] files);
}
