package servlets;

import com.google.gson.Gson;
import database.tables.EditAdminTable;
import database.tables.EditPetOwnersTable;
import mainClasses.Admin;
import mainClasses.enums.UserType;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminLogIn", value = "/AdminLogIn")
public class AdminLogIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);


        Admin admin = new Gson().fromJson(queryFormatParameters, Admin.class);
        try {
            admin = new EditAdminTable().databaseToAdmin(admin.getName(), admin.getPassword());
            if(admin == null){
                response.setStatus(400);
            }else{
                response.setStatus(200);
                request.getSession().setAttribute("username", admin.getName());
                request.getSession().setAttribute("user_type", UserType.ADMIN);
            }
        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(500);
            throw new RuntimeException(e);
        }

    }


}
