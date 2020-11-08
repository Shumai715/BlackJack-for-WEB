package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Dealer;
import model.Deck;
import model.Player;

/**
 * Servlet implementation class GameReset
 */
@WebServlet("/GameReset")
public class GameReset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");
		Dealer dealer = (Dealer) session.getAttribute("dealer");
		Deck graveDeck = (Deck) session.getAttribute("graveDeck");

		player.reset(graveDeck);
		dealer.reset(graveDeck);

		session.setAttribute("player", player);
		session.setAttribute("dealer", dealer);
		session.setAttribute("graveDeck", graveDeck);

		response.sendRedirect("/betIn.jsp");
	}
}
