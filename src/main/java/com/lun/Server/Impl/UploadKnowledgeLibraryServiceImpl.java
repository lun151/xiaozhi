package com.lun.Server.Impl;

import com.lun.Server.UploadKnowledgeLibraryService;
import com.lun.config.XiaoZhiAgentConfig;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadKnowledgeLibraryServiceImpl implements UploadKnowledgeLibraryService {

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    @Autowired
    private EmbeddingModel embeddingModel;

    @Override
    public void uploadKnowledgeLibrary(MultipartFile[] files) {
        List<Document> documents = new ArrayList<>();

        for (MultipartFile file : files){
            if(!file.isEmpty()){
                try {
                    // 保存临时文件
                    File tempFile = File.createTempFile("upload-", "-" + file.getOriginalFilename());
                    file.transferTo(tempFile);                    // 这里需要根据实际需求实现文件内容读取和转换逻辑

                    //根据文件类型选择合适的文档解析器
                    String filename = file.getOriginalFilename();
                    Document document;

                    if(filename != null && filename.toLowerCase().endsWith(".pdf")){
                        document = FileSystemDocumentLoader.loadDocument(tempFile.getAbsolutePath(),
                                new ApachePdfBoxDocumentParser());
                    }else {
                        document = FileSystemDocumentLoader.loadDocument(tempFile.getAbsolutePath());
                    }
                    documents.add(document);

                    tempFile.delete();
                } catch (Exception e) {
                    throw new RuntimeException("文件处理失败:"+ file.getOriginalFilename(),e);
                }
            }
        }

        //将文档写进数据库
        EmbeddingStoreIngestor
                .builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .build()
                .ingest(documents);
    }
}
