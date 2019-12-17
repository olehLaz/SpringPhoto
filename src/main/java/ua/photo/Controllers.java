package ua.photo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.photo.exception.PhotoErrorException;
import ua.photo.exception.PhotoNotFoundException;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/")
public class Controllers {
    private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView onView(@RequestParam("photo_id") long id) {
        if (photos.containsKey(id)) {
            return new ModelAndView("result", "photo_id", id);
        } else {
            throw new PhotoNotFoundException();
        }
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public ModelAndView onAddPhoto(@RequestParam MultipartFile photo) {
        if (photo.isEmpty()) {
            throw new PhotoErrorException();
        }

        try {
            long id = System.currentTimeMillis();
            photos.put(id, photo.getBytes());

            return new ModelAndView("result", "photo_id", id);
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") long id) {
        if (photos.remove(id) == null) {
            throw new PhotoNotFoundException();
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/delete_checked", method = RequestMethod.POST)
    public ModelAndView onDeleteChecked(@RequestParam("photo_keys") String[] keyList) {
        long id;
        for(String key: keyList) {
            id = Long.parseLong(key);
            photos.remove(id);
        }

        if (photos.isEmpty()) {
            return new ModelAndView("index");
        } else {
            return setupGallery();
        }
    }

    @RequestMapping("/gallery")
    public ModelAndView onGallery() {
        return setupGallery();
    }

    private ModelAndView setupGallery() {
        if (photos.isEmpty()) {
            throw new PhotoErrorException();
        }

        List<Long> photoKeys = new ArrayList<>(photos.keySet());
        return new ModelAndView("gallery", "photoKeys", photoKeys);
    }

    private ResponseEntity<byte[]> photoById(long id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
