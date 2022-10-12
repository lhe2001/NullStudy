package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadController
 */
@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	 public FileDownloadController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doHandle(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doHandle(request, response);
		}

		protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String file_repo = "C:\\image_file";
			String b_imageFile = (String) request.getParameter("b_imageFile");
			String b_articleNo = request.getParameter("b_articleNo");
			System.out.println("b_imageFile : " + b_imageFile);
			
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Content-disposition", "attachment; fileName="+"b_imageFile");

			OutputStream out = response.getOutputStream();
			// 글번호에 대한 파일 경로
			String downFile = file_repo +"\\"+ b_articleNo + "\\" + b_imageFile;
			
			File f = new File(downFile);
			FileInputStream in = new FileInputStream( f );
			// 버퍼를 이용해 8kb씩 전송
			byte[] buf = new byte[1024 * 8];
			while(true) {
				int count = in.read(buf);
				if(count == -1) {
					break;
				}
				out.write(buf, 0, count);
			}
			
			in.close();
			out.close();
		}
}
