package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import database.tables.EditPetKeepersTable;
import database.tables.EditPetOwnersTable;
import mainClasses.PetKeeper;
import mainClasses.PetOwner;
import mainClasses.data.LogInData;
import mainClasses.enums.ResponseValues;
import mainClasses.enums.UserType;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LogIn", value = "/LogIn")
public class LogIn extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        String queryFormatParameters = request.getReader().lines().reduce("", (accumulator, actual)
                -> accumulator + actual);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        LogInData data = gson.fromJson(queryFormatParameters, LogInData.class);

        try {
            if(data.getUser_type() == UserType.PET_OWNER){

                EditPetOwnersTable epot = new EditPetOwnersTable();
                PetOwner po = epot.databaseToPetOwners(data.getUsername(), data.getPassword());
                System.out.println(po);
                if(po == null){
                    response.setStatus(ResponseValues.WRONG_PASSWORD_OR_USERNAME);
                }else{
                    response.setStatus(200);
                }
            }else{

                EditPetKeepersTable epkt = new EditPetKeepersTable();
                PetKeeper pk = epkt.databaseToPetKeepers(data.getUsername(), data.getPassword());
                if(pk == null){
                    response.setStatus(ResponseValues.WRONG_PASSWORD_OR_USERNAME);
                }else{
                    response.setStatus(201);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            response.setStatus(ResponseValues.EXCEPTION);
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("username", data.getUsername());
        request.getSession().setAttribute("user_type", data.getUser_type());

        System.out.println(request.getSession().getAttribute("username"));
        System.out.println(request.getSession().getAttribute("user_type"));
    }

}
