package utils;

import exception.ParseException;
import response.host1.model.UserDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostUserUtils {

    private final static String FILE_PATH = System.getenv("FILE_PATH");
    public static UserDTO parseBodyToUserDTO(String body) throws Exception{
        List<String> userInfo = new ArrayList<>();
        UserDTO user = new UserDTO();
        Pattern pattern = Pattern.compile("\": \"([A-Za-z]+)\"");
        Matcher matcher = pattern.matcher(body);
        while (matcher.find()){
            userInfo.add(matcher.group(1));
        }
        if (userInfo.size() != 3){
            throw new ParseException("Failed to parse request body to UserDTO");
        }
        user.setName(userInfo.get(0));
        user.setSurname(userInfo.get(1));
        user.setStatus(userInfo.get(2));
        return user;
    }

    public static void writeUserInfo(UserDTO user) throws Exception{
        try (FileWriter writer = new FileWriter(FILE_PATH, true)){
            String userInformation = userDTOToString(user);
            writer.write(userInformation);
        }catch (Exception e){
            throw new IOException("Failed to write user information to the file ", e);
        }
    }
    private static String userDTOToString(UserDTO user){
        return new StringBuilder()
                .append("Name - ")
                .append(user.getName())
                .append("\n")
                .append("Surname - ")
                .append(user.getSurname())
                .append("\n")
                .append("Status - ")
                .append(user.getStatus())
                .append("\n")
                .toString();
    }
}
