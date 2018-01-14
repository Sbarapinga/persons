package com.prudprudi4.persons.servlets;

import com.prudprudi4.persons.db.DBConn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String oldPass = req.getParameter("oldPass");
        String newPass = req.getParameter("newPass");

        DBConn conn = new DBConn();
        conn.changePass(name, oldPass, newPass);

        HttpSession session = req.getSession();
        session.setAttribute("persons", conn.getAllPersons());
        getServletContext().getRequestDispatcher("/person.jsp").forward(req, resp);

    }
}
