package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("name");
		String loginPass = request.getParameter("pass");
		String forwardPath;

		UserDAO userDAO = new UserDAO();
		User user = userDAO.select(loginName);

		String reason;
		if(user == null) {
			reason = "name";
			request.setAttribute("reason", reason);
			forwardPath = "/WEB-INF/jsp/loginFailure.jsp";
		}else if(!user.getPass().equals(loginPass)) {
			reason = "pass";
			request.setAttribute("reason", reason);
			forwardPath = "/WEB-INF/jsp/loginFailure.jsp";
		}else {
			HttpSession session = request.getSession();
			if(session.isNew() == false) {
				session.invalidate();
				session = request.getSession();
			}
			session.setAttribute("userName", loginName);
			forwardPath = "/MyPage";
		}

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}

}
