package com.hh.board.domain.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/api-board")
public class FileController {

    @GetMapping("/posts/{postId}/files")
    public void getFiles(@PathVariable int postId){

    }

    @GetMapping("/posts/{postId}/files/{fileId}")
    public void getFile(@PathVariable int postId, @PathVariable int fileId){

    }

    @PostMapping("/posts/{postId}/files")
    public void saveFile(@PathVariable int postId){

    }

    @DeleteMapping("/posts/{postId}/files/{fileId}")
    public void deleteFile(@PathVariable int postId, @PathVariable int fileId){

    }
}
