package com.aloha.reactive.sec02.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.aloha.reactive.common.DefaultSubscriber;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * FileServiceImpl provides methods to read, write, and delete files
 * using reactive programming principles with Project Reactor.
 * It implements the FileService interface.
 */
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public Mono<String> read(String filePath) {
        // Implementation for reading a file
        return Mono.fromSupplier(() -> {
            try {
                return Files.readString(Path.of(filePath));
            } catch (IOException e) {
                throw new RuntimeException("Error reading file: " + filePath, e);
            }
        });
    }

    @Override
    public Mono<Void> write(String filePath, String content) {
        return Mono.fromRunnable(() -> {
            try {
                Files.writeString(Path.of(filePath), content);
            } catch (IOException e) {
                throw new RuntimeException("Error writing to file: " + filePath, e);
            }
        });
    }

    @Override
    public Mono<Void> delete(String filePath) {
        return Mono.fromRunnable(() -> {
            try {
                Files.deleteIfExists(Path.of(filePath));
            } catch (IOException e) {
                throw new RuntimeException("Error deleting file: " + filePath, e);
            }
        });
    }

    /**
     * Main method to demonstrate the FileService implementation.
     * It writes to a file, reads from it, and then deletes it.
     *
     * @param args command line arguments (not used)
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/sec02.txt";
        FileService fileService = new FileServiceImpl();

        // Write to the file
        log.info("\n\n== Writing to file: {} ==", filePath);
        String content = "Reactive programming is a programming paradigm focused on building asynchronous,"
                + " event-driven applications that respond to data streams and changes in real-time."
                + " It emphasizes handling dynamic data flows, such as user inputs, network requests,"
                + " or sensor data, in a non-blocking, declarative way.";
        Mono<Void> writeMono = fileService.write(filePath, content);
        writeMono.subscribe(DefaultSubscriber.create("Writer"));

        // Read the file
        log.info("== Reading file: {} ==", filePath);
        Mono<String> readMono = fileService.read(filePath);
        readMono.subscribe(DefaultSubscriber.create("Reader"));

        // Delete the file
        log.info("== Deleting file: {} ==", filePath);
        Mono<Void> deleteMono = fileService.delete(filePath);
        deleteMono.subscribe(DefaultSubscriber.create("Deleter"));
    }

}
