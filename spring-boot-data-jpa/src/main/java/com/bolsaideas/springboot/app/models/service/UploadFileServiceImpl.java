package com.bolsaideas.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final static String UPLOADS_FOLDER = "C://uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathFoto = getPath(filename);
		log.info("pathFoto" + pathFoto);
		log.info("pathFotoAbsolute" + pathFoto);
		Resource recurso = null;

		recurso = new UrlResource(pathFoto.toUri());
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error, el recurso no existe");
		}

		return recurso;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		/*
		 * byte[] bytes = file.getBytes(); Path rutaCompleta = Paths.get(rootPath + "//"
		 * + file.getOriginalFilename()); Files.write(rutaCompleta,bytes); esto hace lo
		 * mismo que las siguiente lineas
		 */
		String uniqueFilename = UUID.randomUUID().toString() + " " + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {

		Path pathFoto = getPath(filename);

		try {
			if (Files.exists(pathFoto) && Files.isRegularFile(pathFoto)) {
				Files.deleteIfExists(pathFoto);
			}
			return true;
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

	}

	public Path getPath(String filename) {
		Path pathFoto = Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
		return pathFoto;
	}

}
