package Forum;

import com.mysql.cj.protocol.Resultset;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Forum {

    public Connection conn;
    

    public void connection() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingbuddydb", "root", "");
    }

    public ArrayList<Post> readForum(ForumBaseController Obj) {
        ArrayList<Post> info = new ArrayList<>();
        try {
            Statement query = conn.createStatement();
            ResultSet answer = query.executeQuery("SELECT * FROM forum");

            while (answer.next()) {
                //System.out.println("ID = " + answer.getInt("postID") + "Username = " + answer.getString("username") + "Title = " + answer.getString("title") + "Body = " + answer.getString("body") + "Tag = " + answer.getString("tag"));
                Post infoObj = new Post();
                infoObj.username = answer.getString("username");
                infoObj.title = answer.getString("title");
                infoObj.body = answer.getString("body");
                infoObj.tag = answer.getString("tag");
                infoObj.vote = answer.getInt("vote");
                infoObj.answer = answer.getInt("answer");
                infoObj.postID = answer.getInt("postID");
                info.add(infoObj);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return info;
    }

    public void writeForum(String rec_title, String rec_body, String rec_tag) throws SQLException {

        String query = "INSERT INTO `forum`(`username`, `title`, `body`, `tag`) VALUES (?,?,?,?)";
        PreparedStatement wF = conn.prepareStatement(query);
        wF.setString(1, Main.Utility.username);       
        wF.setString(2, rec_title);
        wF.setString(3, rec_body);
        wF.setString(4, rec_tag);
        wF.execute();
    }
    public ArrayList<Comment> readComment(int postID)
    {
        ArrayList<Comment> sendComm = new ArrayList<>(); 
        try {
            Statement query = conn.createStatement();
            ResultSet comm = query.executeQuery("SELECT `commentID`, `userName`, `commentBody` FROM `comment` WHERE postID = "+postID);
            while(comm.next())
            {
                System.out.println("1");
                Comment commObj = new Comment();
                commObj.userName = comm.getString("commentID");
                commObj.commentID = comm.getInt("commentID");
                commObj.commentBody = comm.getString("commentBody");
                sendComm.add(commObj);
            }
           
        } catch (SQLException ex) {
            System.out.println("Comment Exception");
        }
        return sendComm;         
    }
    public void writeComment(int postID, String commBody) throws SQLException
    {
        String query = "INSERT INTO `comment`(`postID`, `userName`, `commentBody`) VALUES (?, ?, ?)";
        PreparedStatement wC = conn.prepareStatement(query);
        wC.setString(1,Integer.toString(postID));
        wC.setString(2, Main.Utility.username);
        wC.setString(3, commBody);
        wC.execute();
    }
}
