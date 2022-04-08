package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadImageController
 */
@WebServlet("/UploadImageController")
public class UploadImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UploadImageController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		RequestDispatcher rd = request.getRequestDispatcher("/upload.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//processRequest(request, response);
		final Part part = request.getPart("file");
		String fileName = getName(part);
		
		if(fileName.equals("")) {
			String error = "Vui lòng chọn hình ảnh";
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("/bai33.jsp");
			rd.forward(request, response);
		}else {
			fileName = rename(fileName);
			String filePath = request.getServletContext().getRealPath("/hinhanh");
			System.out.println(filePath);
			
			File dirPath = new File(filePath);
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
			filePath = filePath + File.separator + fileName;
			part.write(filePath);
		}
		
		request.setAttribute("fileName", fileName);
		RequestDispatcher rd = request.getRequestDispatcher("/hinhanh.jsp");
		rd.forward(request, response);
		
	}

	public static String getName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public static String rename(String fileName) {
		String[] arrImg = fileName.split("\\.");
		String duoiFileImg = arrImg[arrImg.length-1];
		String nameFile = "";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYYhhmmss");
		for(int i = 0; i < arrImg.length - 1; i++) {
			if(i == 0) {
				nameFile = arrImg[i];
			}else {
				nameFile += "-"+arrImg[i];
			}
		}
		nameFile = nameFile + "_"+sdf.format(date)+"."+duoiFileImg;
		return nameFile;
	}

}
