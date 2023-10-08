package com.ServerManager.server.controller;

import com.ServerManager.server.Enums.Status;
import com.ServerManager.server.Service.Implementation.ServerServiceImpl;
import com.ServerManager.server.model.Response;
import com.ServerManager.server.model.Server;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    ServerServiceImpl serverServiceImpl;
    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder().timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("Servers",serverServiceImpl.list(30)))
                        .message("Servers Retrieved")
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) {
        Server server = serverServiceImpl.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder().timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("Server",server))
                        .message(server.getStatus() == Status.SERVER_UP ?"ping Success" : "ping Failed")
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
            return  ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .data(Map.of("Server",serverServiceImpl.createServer(server)))
                            .message("server created")
                            .build()
            );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        Server server = serverServiceImpl.get(id);
        return ResponseEntity.ok(
                Response.builder().timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("Server",server))
                        .message("server fetched")
                        .build()
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        Boolean isDeleted = serverServiceImpl.delete(id);
        return ResponseEntity.ok(
                Response.builder().timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Map.of("Server",isDeleted))
                        .message(isDeleted==Boolean.TRUE ?"Deleted server " : " failed to Delete Server")
                        .build()
        );
    }

    @GetMapping(path = "/images/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"Documents/images/"+fileName));
    }
}
