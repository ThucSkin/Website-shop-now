package com.websiteshop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.websiteshop.config.StorageProperties;
import com.websiteshop.exception.StorageException;
import com.websiteshop.exception.StorageFileNotFoundException;
import com.websiteshop.service.StorageService;

@Service
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path rootLocation;

	@Override
	public String getStoredFilename(MultipartFile file, String id) {// tao file luu tru
		String ext = file.getOriginalFilename();
		return ext;
	}

	public FileSystemStorageServiceImpl(StorageProperties properties) {// truyen thong tin cau hinh luu tru
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String storedFilename) {// luu noi dung file
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file");
			}

			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new StorageException("Failed to store file", e);
		}
	}

	@Override
	public Resource loadAsResource(String filename) {// nap noi dung file
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}

			throw new StorageFileNotFoundException("Could not read file: " + filename);
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public void delete(String storedFilename) throws IOException {
		Path destinationFile = rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
		Files.delete(destinationFile);
	}

	// @Override
	// public void init() {//khoi tao thu muc
	// try {
	// Files.createDirectories(rootLocation);
	// System.out.println(rootLocation.toString());
	// } catch (Exception e) {
	// throw new StorageFileNotFoundException("Could not initialize storage: " + e);
	// }
	// }
}
