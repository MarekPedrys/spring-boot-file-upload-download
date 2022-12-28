package pl.marekpedrys.filemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.marekpedrys.filemanagement.entity.Attachment;
import pl.marekpedrys.filemanagement.model.AttachmentDTO;
import pl.marekpedrys.filemanagement.repository.AttachmentRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    public AttachmentDTO saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Attachment attachment = Attachment.builder()
                .fileName(fileName)
                .fileType(file.getContentType())
                .data(file.getBytes())
                .build();
        attachment = attachmentRepository.save(attachment);
        return AttachmentDTO.from(attachment);
    }

    public ResponseEntity<Resource> downloadFile(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

}
