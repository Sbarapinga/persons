package com.prudprudi4.persons.servlets;

import com.prudprudi4.persons.db.DBConn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/ChangePerson")
public class ChangePerson extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        boolean isBlocked = req.getParameter("isBlocked").equals("1");
        boolean limitation = req.getParameter("limitation").equals("1");

        DBConn conn = new DBConn();
        conn.changePersonByAdmin(name, isBlocked, limitation);

        HttpSession session = req.getSession();
        session.setAttribute("persons", conn.getAllPersons());
        getServletContext().getRequestDispatcher("/person.jsp").forward(req, resp);
    }
}
