package com.isms.servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isms.constans.Constants;
import com.isms.utils.mail.MailingUtilities;

/**
 * Servlet implementation class MailerServlet
 */
public class MailerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toAdd = request.getParameter("email");
		String subject = request.getParameter("summary");
		String content = request.getParameter("description");
		System.out.println("Mail sent Successfully...");
		response.sendRedirect("oxford_home.html");
		
		try {
			MailingUtilities.sesndMails(subject, content, "rukminipusalkar.anavatti@gmail.com");
			MailingUtilities.sesndMails("Thank you for your interest", Constants.MAIL_CONTENT_FOR_ENQUIRY, toAdd);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			request.getRequestDispatcher("errorMessageForMail.html").include(request, response);;
			request.getRequestDispatcher("sendMail.html").include(request, response);
			
		}
	}

}
