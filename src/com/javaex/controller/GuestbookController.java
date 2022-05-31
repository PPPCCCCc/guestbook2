package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@WebServlet("/gb2")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 포스트 방식일때 한글깨짐 방지
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println("action");

		if ("list".equals(action)) {
			GuestBookDao guestDao = new GuestBookDao();
			List<GuestBookVo> guestList = guestDao.list();

			request.setAttribute("gList", guestList);

			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");
			rd.forward(request, response);

		} else if ("add".equals(action)) {

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");

			GuestBookVo guestVo = new GuestBookVo(name, password, content);

			GuestBookDao guestDao = new GuestBookDao();
			guestDao.insert(guestVo);

			response.sendRedirect("./gb2?action=list");

		} else if ("deleteForm".equals(action)) {

			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(action)) {

			int no = Integer.parseInt(request.getParameter("no"));
			String deletePw = request.getParameter("deletePw");

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setPassword(deletePw);

			GuestBookDao dao = new GuestBookDao();
			dao.delete(vo);

			response.sendRedirect("./gb2?action=list");

		} else {
			System.out.println("action 파라미터 없음");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
