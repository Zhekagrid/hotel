package com.hrydziushka.finalproject.controller;

import com.hrydziushka.finalproject.model.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.hrydziushka.finalproject.controller.PagePath.HOME_PAGE;

@WebServlet(name = "/uploadController", urlPatterns = "/uploadController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 10 * 10)
public class Upload extends HttpServlet {
    private static final String UPDATE_IMAGE = "UPDATE  images SET image=? WHERE image_id=2";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        Part part = req.getPart("upload_file");
//        LogManager.getLogger().info("upload");
//        InputStream inputStream = part.getInputStream();
//        try (Connection connection = ConnectionPool.getInstance().getConnection();
//             PreparedStatement statement = connection.prepareStatement(UPDATE_IMAGE)) {
//
//            statement.setBlob(1, inputStream);
//         int row=   statement.executeUpdate();
//
//LogManager.getLogger().info("Rows" +row);
//        } catch (SQLException e) {
//          throw new ServletException(e);
//        }

          //      req.setAttribute("new_file", sb.toString());

            req.getRequestDispatcher(HOME_PAGE).forward(req, resp);


//        try (Connection connection = ConnectionPool.getInstance().getConnection();
//             PreparedStatement statement = connection.prepareStatement(SELECT_APARTMENT_IMAGE_BY_ID)) {
//            statement.setLong(1, 1);
//            statement.execute();
//            ResultSet resultSet = statement.getResultSet();
//            while (resultSet.next()) {
//                StringBuilder sb = new StringBuilder(PICTURE_HEADER);
//                InputStream pictureStream=resultSet.getBlob(1).getBinaryStream();
//                byte[] encodeImage = Base64.encodeBase64(pictureStream.readAllBytes(), false);
//                String imageStr = StringUtils.newStringUtf8(encodeImage);
//                sb.append(imageStr);
//                req.setAttribute("new_file", sb.toString());
//            }
//            req.getRequestDispatcher(HOME_PAGE).forward(req, resp);
//        } catch (SQLException e) {
//           //todo log
//        }
    }
}
