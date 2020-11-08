package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RecordDAO;
import model.PlayRecord;

/**
 * Servlet implementation class MyPage
 */
@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		RecordDAO recordDAO = new RecordDAO();

		PlayRecord nowRecord = (PlayRecord)session.getAttribute("nowRecord");
		if(nowRecord != null) {
			session.removeAttribute("nowRecord");
		}

		int playCount = recordDAO.getPlayCount(userName);
		int maxScore = recordDAO.getMaxScore(userName);

		request.setAttribute("playCount", playCount);
		request.setAttribute("maxScore", maxScore);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp");
		dispatcher.forward(request, response);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
		RecordDAO recordDAO = new RecordDAO();

		PlayRecord nowRecord = (PlayRecord)session.getAttribute("nowRecord");
		if(nowRecord != null) {
			session.removeAttribute("nowRecord");
		}

		int playCount = recordDAO.getPlayCount(userName);
		int maxScore = recordDAO.getMaxScore(userName);

		request.setAttribute("playCount", playCount);
		request.setAttribute("maxScore", maxScore);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/myPage.jsp");
		dispatcher.forward(request, response);

	}

}
