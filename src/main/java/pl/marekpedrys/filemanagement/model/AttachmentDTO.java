package pl.marekpedrys.filemanagement.model;

import lombok.Builder;
import lombok.Data;
import pl.marekpedrys.filemanagement.entity.Attachment;

@Data
@Builder
public class AttachmentDTO {

    private Long id;
    private String fileName;

    public static AttachmentDTO from(Attachment attachment) {
        return AttachmentDTO.builder()
                .id(attachment.getId())
                .fileName(attachment.getFileName())
                .build();
    }
}
