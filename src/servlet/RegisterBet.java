package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BetLogic;
import model.Player;

/**
 * Servlet implementation class RegisterBet
 */
@WebServlet("/RegisterBet")
public class RegisterBet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String betStr = request.getParameter("bet");
		int bet = Integer.parseInt(betStr);

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");

		BetLogic logic = new BetLogic();
		boolean betCheck = logic.execute(player, bet);

		String redirectPath = null;

		if(betCheck == false) {
			redirectPath = "/betIn.jsp";
		}else {
			session.setAttribute("player", player);
			redirectPath = "/GameMain";
		}

		response.sendRedirect(redirectPath);

	}

}
