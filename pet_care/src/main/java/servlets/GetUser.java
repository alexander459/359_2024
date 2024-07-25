package servlets;

import com.google.gson.Gson;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import mainClasses.data.DeleteViewUserData;
import mainClasses.enums.ResponseValues;
import mainClasses.enums.UserType;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetUser", value = "/GetUser")
public class GetUser extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);


        DeleteViewUserData data = new Gson().fromJson(queryFormatParameters, DeleteViewUserData.class);
        String username = data.getUsername();
        UserType type = data.getUser_type();

        if(type == UserType.PET_KEEPER) {
            try {
                response.getWriter().println(new EditPetKeepersTable().databasePetKeeperToJSON(username));
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);
            }


        }else if(type == UserType.PET_OWNER){
            try {
                response.getWriter().println(new EditPetOwnersTable().databasePetOwnerToJSON(username));
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);
            }
        }
    }
}
