package com.prudprudi4.persons.servlets;

import com.prudprudi4.persons.Person;
import com.prudprudi4.persons.db.DBConn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Auth")
public class Auth extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String pass = req.getParameter("pass").trim();

        DBConn conn = new DBConn();
        Person person = conn.getPerson(name, pass);

        if (person == null) {
            if (!"ADMIN".equals(name)) {
                conn.incErrCount(name);
                int errCount = conn.getErrorCount(name);
                System.out.println(errCount);
                if (errCount >= 3) {
                    conn.blockPerson(name);
                }
            }

            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        boolean isBlock = conn.isBlockPerson(name);
        if (isBlock) {
            req.setAttribute("info", "Person is blocked");
            getServletContext().getRequestDispatcher("/info.jsp").forward(req, resp);
        }

        conn.nullErrorCount(name);

        HttpSession session = req.getSession();
        session.setAttribute("person", person);

        if ("ADMIN".equals(name)) {
            session.setAttribute("persons", conn.getAllPersons());
        }

        getServletContext().getRequestDispatcher("/person.jsp").forward(req, resp);
    }
}
