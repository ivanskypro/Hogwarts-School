package ru.hogwarts.hogwartsschool.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.hogwartsschool.model.Avatar;
import ru.hogwarts.hogwartsschool.model.Student;
import ru.hogwarts.hogwartsschool.repositories.AvatarRepository;
import ru.hogwarts.hogwartsschool.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void uploadAvatar (Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);
        Path filePatch = Path.of(avatarsDir, student + "." + getExtensions (avatarFile.getOriginalFilename()));
        Files.createDirectories(filePatch.getParent());
        Files.deleteIfExists(filePatch);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePatch, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePatch.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findById(studentId).orElse(new Avatar());
    }

    public List<Avatar> getAvatarOnPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return avatarRepository.findAll(pageRequest).toList();
    }


}
