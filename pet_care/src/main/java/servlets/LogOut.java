package servlets;

import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "LogOut", value = "/LogOut")
public class LogOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // Don't create if it doesn't exist
        if (session != null) {
            session.removeAttribute("username");
            session.removeAttribute("user_type");
            session.invalidate();
            response.setStatus(200);
        }else{
            response.setStatus(500);
            throw new RuntimeException();
        }

    }

}

