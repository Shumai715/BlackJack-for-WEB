package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PlayReset
 */
@WebServlet("/PlayReset")
public class PlayReset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String page = request.getParameter("page");

		String redirectPath = "";
		if(page.equals("rank")) {
			redirectPath = "/Ranking";
		}else {
			redirectPath = "/MyPage";
		}

		HttpSession session = request.getSession();
		session.removeAttribute("player");
		session.removeAttribute("dealer");
		session.removeAttribute("deck");
		session.removeAttribute("graveDeck");
		response.sendRedirect(redirectPath);
	}

}
