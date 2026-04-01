package com.example.demo.springawsrdsdemo2.controller;

import com.example.demo.springawsrdsdemo2.domain.FileMetadata;
import com.example.demo.springawsrdsdemo2.dto.FileMetadataDto;
import com.example.demo.springawsrdsdemo2.service.FileMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/files/db")
@RequiredArgsConstructor
public class FileMetadataController {

    private final FileMetadataService service;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadataDto> upload(@RequestParam("file") MultipartFile file) {
        FileMetadata saved = service.uploadAndSave(file);        // S3 업로드 + DB 저장
        FileMetadataDto dto = FileMetadataDto.from(saved);        // 응답 DTO
        return ResponseEntity
                .created(URI.create("/files/db/" + saved.getId()))
                .body(dto);
    }

    /** 최신 50개 목록 */
    @GetMapping("/list")
    public List<FileMetadataDto> list() {
        return service.listLatest().stream().map(FileMetadataDto::from).toList();
    }

    /** 접두사(prefix)로 목록 */
    @GetMapping("/listByPrefix")
    public List<FileMetadataDto> listByPrefix(@RequestParam String prefix) {
        return service.listByPrefix(prefix).stream().map(FileMetadataDto::from).toList();
    }

    /** 단건 조회 */
    @GetMapping("/{id}")
    public FileMetadataDto get(@PathVariable UUID id) {
        return FileMetadataDto.from(service.findById(id));
    }

    /** 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** URL 재생성(선택 기능) */
    @PostMapping("/{id}/refresh-url")
    public FileMetadataDto refreshUrl(@PathVariable UUID id) {
        return FileMetadataDto.from(service.refreshUrl(id));
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> download(
            @RequestParam String key,
            @RequestParam(required = false) String filename
    ) {
        return service.download(key, filename);
    }
}
