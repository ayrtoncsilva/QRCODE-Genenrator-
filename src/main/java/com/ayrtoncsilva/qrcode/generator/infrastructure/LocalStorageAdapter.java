package com.ayrtoncsilva.qrcode.generator.infrastructure;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ayrtoncsilva.qrcode.generator.ports.StoragePort;

@Component
@Profile("local")
public class LocalStorageAdapter implements StoragePort {

    @Override
    public String uploadFile(byte[] fileData, String fileName, String contentType) {
        try {
            File directory = new File("uploads");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(directory, fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(fileData);
            }

            return file.getAbsolutePath();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar arquivo local", e);
        }
    }
}
