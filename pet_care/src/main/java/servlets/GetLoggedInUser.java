package servlets;

import database.tables.EditAdminTable;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import mainClasses.enums.ResponseValues;
import mainClasses.enums.UserType;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "GetLoggedInUser", value = "/GetLoggedInUser")
public class GetLoggedInUser extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String uname = (String) request.getSession().getAttribute("username");
        String json = "";
        UserType type = (UserType) request.getSession().getAttribute("user_type");

        if(uname == null || type == null){
            response.setStatus(400);
            return;
        }

        if(type == UserType.PET_OWNER){
            try {
                json = new EditPetOwnersTable().databasePetOwnerToJSON(uname);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);

            }

        }else if (type == UserType.PET_KEEPER){
            try {
                json = new EditPetKeepersTable().databasePetKeeperToJSON(uname);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);
            }

        }else{
            try {
                json = new EditAdminTable().databaseAdminToJSON();
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);
            }
        }

        response.getWriter().println(json);
        response.setStatus(200);
    }

}
