package ru.sqlinvestigation.RestAPI.controllers.userDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.services.userDB.StoryImageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/userDB/stories_images")
public class StoryImageController {
    private final StoryImageService storyImageService;

    @Autowired
    public StoryImageController(StoryImageService storyImageService) {
        this.storyImageService = storyImageService;
    }

    @GetMapping("/listID")
    public List<Long> findListId(){
        return storyImageService.findListId();
    }

    @GetMapping("/findByStoryId/{id}")
    public byte[] findByStoryId(@PathVariable long id) throws Exception {
        return storyImageService.findByStoryId(id);
    }

    @GetMapping("/download/{idStories}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable long idStories) throws Exception {
        // Возвращаем ответ с содержимым файла в теле ответа
        var file = storyImageService.findByStoryId(idStories);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "id_storis" + idStories)
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .contentLength(file.length)
                .body(file);
    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
//        // Получаем файл из базы данных
//        DBFile dbFile = fileRepository.findById(fileId)
//                .orElseThrow(() -> new ResourceNotFoundException("File not found with id " + fileId));
//        // Создаем ресурс байтового массива из содержимого файла
//        ByteArrayResource resource = new ByteArrayResource(dbFile.getData());
//        // Возвращаем ответ с содержимым файла в теле ответа
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + dbFile.getFileName())
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .contentLength(dbFile.getData().length)
//                .body(resource);
//    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, long id_stories) throws IOException {
        // Возвращаем ответ с сообщением об успешной загрузке
        storyImageService.create(file, id_stories);
        return ResponseEntity.ok("File uploaded successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestParam("file") MultipartFile file, @RequestBody long storyId) throws IOException {
        storyImageService.update(file, storyId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        storyImageService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ex.getMessage()));
    }
}