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

@WebServlet(name = "DeleteUser", value = "/DeleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);

        Gson gson = new Gson();
        DeleteViewUserData dud = gson.fromJson(queryFormatParameters, DeleteViewUserData.class);

        if(dud.getUser_type() == UserType.PET_KEEPER){
            try {
                new EditPetKeepersTable().deletePetKeeper(dud.getUsername());
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);
            }
            
        }else if(dud.getUser_type() == UserType.PET_OWNER){
            try {
                new EditPetOwnersTable().deletePetOwner(dud.getUsername());
                response.setStatus(200);
            } catch (SQLException | ClassNotFoundException e) {
                response.setStatus(ResponseValues.EXCEPTION);
                throw new RuntimeException(e);
            }

        }
    }
}
