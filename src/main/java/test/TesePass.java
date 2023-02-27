package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "TesePass", value = "/TesePass")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
	    maxFileSize = 1024 * 1024 * 50, // 50 MB
	    maxRequestSize = 1024 * 1024 * 100 // 100 MB
	)
public class TesePass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://10.64.144.5:3306/21jygr03?characterEncoding=UTF-8";
    private static final String DB_USER = "21jygr03";
    private static final String DB_PASSWORD = "21jygr03";
    private static final String SELECT_EMAIL = "SELECT * FROM emp WHERE emp_email = ?";
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contentType = request.getContentType();
        String action = null;
        if (contentType != null && contentType.startsWith("multipart/form-data")) {
        	handleMultipartFormData(request, response);
            
        } else {
            action = request.getParameter("action");
            if ("validate_password".equals(action)) {
                // 获取请求参数
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                try {
                    // 连接到数据库
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // 查询用户
                    PreparedStatement stmt = conn.prepareStatement(SELECT_EMAIL);
                    stmt.setString(1, email);
                    ResultSet rs = stmt.executeQuery();

                    // 验证密码
                    if (rs.next()) {
                        String hashedPassword = rs.getString("emp_password");
                        if (BCrypt.checkpw(password, hashedPassword)) {
                            // 密码匹配，返回200 OK
                            response.setStatus(HttpServletResponse.SC_OK);
                        } else {
                            // 密码不匹配，返回401 Unauthorized
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                    } else {
                        // 没有找到用户，返回401 Unauthorized
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                    // 关闭连接
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                    // 发生异常，返回500 Internal Server Error
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } else if ("get_username_empId".equals(action)) {
                String email = request.getParameter("email");
                String username = null;
                String empId = null;
                try {
                    // 连接到数据库
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                    // 查询用户
                    PreparedStatement stmt = conn.prepareStatement(SELECT_EMAIL);
                    stmt.setString(1, email);
                    ResultSet rs = stmt.executeQuery();

                    // 获取查询结果
                    if (rs.next()) {
                        empId = rs.getString("emp_id");
                        username = rs.getString("emp_name");
                    }

                    // 关闭资源
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }

                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(username + "," + empId);

            } 
        }
        
    }
    
    private void handleMultipartFormData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Part motionsPackPart = request.getPart("motionsPack");
        Part screenshotPackPart = request.getPart("screenshotPack");
        
        // 获取上传的其他参数
        String action = request.getParameter("action");
        
     // 处理上传的文件
        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            if (fileName != null) {
                // 处理txt文件
                if (fileName.endsWith(".txt")) {
                    try {
                        String txtfileName = getFileName(part);
                        String[] partName = txtfileName.split("_");
                        String empId = partName[0];
                        String day = partName[2];

                        List<String[]> dataList = new ArrayList<>();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            if (parts.length == 3) {
                                dataList.add(parts);
                            }
                        }

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        PreparedStatement statement = null;
                        if (conn != null) {
                            String sql = "INSERT INTO motion_data VALUES (?, ?, ?, ?, ?)";
                            statement = conn.prepareStatement(sql);
                            for (String[] data : dataList) {
                                String motionDataId = empId + data[0] + data[2];

                                statement.setString(1, motionDataId);
                                statement.setString(2, empId);
                                statement.setString(3, data[1]);
                                statement.setString(4, data[2]);
                                statement.setString(5, data[0]);
                                statement.executeUpdate();
                            }
                            statement.close();
                            conn.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                // 处理zip文件
                else if (fileName.endsWith(".zip")) {
                	try {
                        ZipInputStream zipInputStream = new ZipInputStream(part.getInputStream());
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("File uploaded successfully!");

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                        if (conn != null) {
                            // 设置自动提交为false，以便后面一次性提交所有插入操作
                            conn.setAutoCommit(false);

                            try {
                                ZipEntry entry;
                                while ((entry = zipInputStream.getNextEntry()) != null) {
                                    if (!entry.isDirectory() && entry.getName().endsWith(".png")) {
                                        String entryName = entry.getName();
                                        String[] nameParts = entryName.split("_");
                                        if (nameParts.length == 2) {
                                            String empId = nameParts[0];
                                            String time = nameParts[1].substring(0, nameParts[1].indexOf(".png"));
                                            String dateStr = time.substring(0, 8);
                                            String folderName = "\\\\10.64.144.9\\21jy0133$\\twksfile\\img\\ScreenShot\\" + empId + "\\" + dateStr;
                                            File folder = new File(folderName);
                                            if (!folder.exists()) {
                                                folder.mkdirs();
                                            }
                                            String filePath = folderName + "\\" + entryName;
                                            FileOutputStream fos = new FileOutputStream(filePath);
                                            byte[] buffer = new byte[4096];
                                            int bytesRead;
                                            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                                                fos.write(buffer, 0, bytesRead);
                                            }
                                            fos.close();

                                            String screenshot_data_id = entryName.substring(0, entryName.indexOf(".png"));
                                            String imgPath = empId + "/" + dateStr + "/" + entryName;

                                            // 插入数据
                                            String sql = "INSERT INTO screenshot_data VALUES (?, ?, ?, ?)";
                                            PreparedStatement statement = conn.prepareStatement(sql);
                                            statement.setString(1, screenshot_data_id);
                                            statement.setString(2, empId);
                                            statement.setString(3, imgPath);
                                            statement.setString(4, time);
                                            statement.executeUpdate();
                                            statement.close();
                                        }
                                    }
                                }

                                // 提交所有插入操作
                                conn.commit();

                            } catch (SQLException ex) {
                                // 出现异常时回滚所有操作
                                conn.rollback();
                                throw ex;
                            } finally {
                                // 关闭数据库连接
                                conn.close();
                            }
                            zipInputStream.close();
                            System.out.println("保存した");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }
}
