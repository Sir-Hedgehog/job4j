package ru.job4j.crud;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version 1.0
 * @since 01.03.2020
 */

public class UploadServlet extends HttpServlet {
    private String nameOfFile = "";
    private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    /**
     * Метод загружает имя выбранной картинки в форму для создания пользователя
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        request.setAttribute("file", nameOfFile);
        request.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(request, response);
    }

    /**
     * Метод загружает выбранную картинку на сервер
     * @param request - запрос серверу
     * @param response - ответ сервера
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(request);
            LOG.info("ITEMS: " + items);
            File folder = new File("/bin/images/");
            LOG.info("FOLDER: " + folder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            for (FileItem item : items) {
                LOG.info("ITEM: " + item.getName());
                if (!item.isFormField()) {
                    nameOfFile = item.getName().substring(item.getName().lastIndexOf("\\") + 1);
                    LOG.info("NEW ITEM: " + nameOfFile);
                    LOG.info("SUM: " + folder + File.separator + nameOfFile);
                    File file = new File(folder + File.separator + nameOfFile);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException | FileNotFoundException e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }
}
