package pl.marekpedrys.filemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.marekpedrys.filemanagement.model.AttachmentDTO;
import pl.marekpedrys.filemanagement.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public AttachmentDTO uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        return attachmentService.saveFile(file);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        return attachmentService.downloadFile(id);
    }

}
