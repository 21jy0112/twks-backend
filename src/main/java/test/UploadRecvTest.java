package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadRecvTest
 */
@WebServlet("/UploadRecvTest")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
public class UploadRecvTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploadFiles";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadRecvTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = null;
        String savePath = null;
        String contentType = request.getContentType();

        if (contentType != null && contentType.startsWith("multipart/")) {
            // 处理multipart/form-data类型请求
            Part actionPart = request.getPart("action");
            InputStream actionInputStream = actionPart.getInputStream();
            byte[] actionBytes = new byte[actionInputStream.available()];
            actionInputStream.read(actionBytes);
            action = new String(actionBytes, "UTF-8");

            if ("upload_file_screenshot".equals(action)) {
                // 处理文件上传请求
                Part screenshotPart = request.getPart("screenshot");
                String fileName = request.getParameter("filename"); // 获取原文件名
                String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
                String uuid = UUID.randomUUID().toString();
                savePath = request.getSession().getServletContext().getRealPath("/uploadFiles/") + uuid + "." + fileExtName;

                // 将上传的文件保存到服务器指定路径下
                InputStream input = screenshotPart.getInputStream();
                FileOutputStream output = new FileOutputStream(savePath);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                output.close();
                input.close();

                // 响应上传结果
                response.getWriter().write("文件上传成功，保存路径为：" + savePath);
            } else {
                response.getWriter().write("不支持的请求类型：" + action);
            }
        } else {
            response.getWriter().write("不支持的请求类型：" + contentType);
        }
    }

    /**
     * 获取上传文件的文件名
     * 
     * @param part
     *            上传文件的Part对象
     * @return 上传文件的文件名
     */
    private String getFileName(Part part) {
        String contentDispositionHeader = part.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
