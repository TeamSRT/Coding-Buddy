package Forum;


import Main.*;
import java.sql.*;
import java.util.*;

public class Forum {

    public Connection conn;
    DateAndTime obj = new DateAndTime();

    public void connection() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/codingbuddydb", "root", "");
    }

    public ArrayList<Post> readForum(ForumBaseController Obj) {
                   
        ArrayList<Post> info = new ArrayList<>();
        try {
            Statement query = conn.createStatement();
            ResultSet answer = query.executeQuery("SELECT * FROM forum ORDER BY postID DESC");

            while (answer.next()) {
       
                Post infoObj = new Post();
                infoObj.username = answer.getString("username");
                infoObj.title = answer.getString("title");
                infoObj.body = answer.getString("body");
                infoObj.tag = answer.getString("tag");
                infoObj.vote = answer.getInt("vote");                
                infoObj.postID = answer.getInt("postID");
                infoObj.postDate = answer.getDate("postDate").toString();
                infoObj.postTime = answer.getTime("postTime").toString();
                info.add(infoObj);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return info;
    }

    public void writeForum(String rec_title, String rec_body, String rec_tag) throws SQLException {
               
        String query = "INSERT INTO `forum`(`username`, `title`, `body`, `tag`, `postDate`, `postTime`) VALUES (?,?,?,?,?,?)";
        PreparedStatement wF = conn.prepareStatement(query);        
        wF.setString(1, Main.Utility.username);       
        wF.setString(2, rec_title);
        wF.setString(3, rec_body);
        wF.setString(4, rec_tag);
        wF.setString(5, obj.getDate());
        wF.setString(6, obj.getTime());
        wF.execute();
    }
    public void updateForum(String rec_title, String rec_body, String rec_tag, int postID) throws SQLException
    {
        String query = "UPDATE forum SET title = ?,body = ?,tag = ? WHERE postID = ? AND username = ?";
        PreparedStatement uF = conn.prepareStatement(query); 
        uF.setString(1, rec_title);
        uF.setString(2, rec_body);
        uF.setString(3, rec_tag);
        uF.setInt(4, postID);
        uF.setString(5, Main.Utility.username);
        uF.execute();
    }
    public void deleteForum(int postID) throws SQLException
    {
        String query = "DELETE FROM forum WHERE postID = ?";
        PreparedStatement dF = conn.prepareStatement(query);
        dF.setInt(1, postID);
        dF.executeUpdate();        
    }
    public void deleteComment(int commentID) throws SQLException
    {
        String query = "DELETE FROM `comment` WHERE commentID = ?";
        PreparedStatement dC = conn.prepareStatement(query);
        dC.setInt(1, commentID);
        dC.executeUpdate();        
    }
    public ArrayList<Comment> readComment(int postID)
    {
        ArrayList<Comment> sendComm = new ArrayList<>(); 
        try {
            Statement query = conn.createStatement();
            ResultSet comm = query.executeQuery("SELECT `commentID`, `userName`, `commentBody`,`commDate`,`commTime` FROM `comment` WHERE postID = "+postID);
            while(comm.next())
            {
            
                Comment commObj = new Comment();
                commObj.userName = comm.getString("userName");
                commObj.commentID = comm.getInt("commentID");
                commObj.commentBody = comm.getString("commentBody"); 
                commObj.commDate = comm.getDate("commDate").toString();
                commObj.commTime = comm.getTime("commTime").toString();
                sendComm.add(commObj);
            }
           
        } catch (SQLException ex) {
            System.out.println("Comment Exception");
        }
        return sendComm;         
    }
    public void writeComment(int postID, String commBody) throws SQLException
    {
        String query = "INSERT INTO `comment`(`postID`, `userName`, `commentBody`,`commDate`,`commTime`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement wC = conn.prepareStatement(query);
        wC.setString(1,Integer.toString(postID));
        wC.setString(2, Main.Utility.username);
        wC.setString(3, commBody);       
        wC.setString(4, obj.getDate());
        wC.setString(5, obj.getTime());
        wC.execute();
    }
    public void updateComment(int commentID ,String commBody) throws SQLException
    {        
        String query = "UPDATE comment SET commentBody = ? WHERE commentID = ?";
        PreparedStatement uC = conn.prepareStatement(query);
        uC.setString(1, commBody);
        uC.setInt(2, commentID);
        uC.execute();
    }
    public void writeVote(int postID, int vote) throws SQLException
    {
        String query = "UPDATE `forum` SET `vote`= ? where postID = ?";
        PreparedStatement sV = conn.prepareStatement(query);       
        sV.setInt(1, vote);
        sV.setInt(2, postID);
        sV.executeUpdate();
    }
    public void updateVote(int track, int postID) throws SQLException 
    {
       
        int Store;
        String query1 = "UPDATE `postvote` SET postTrack = ? WHERE postID = ? AND username = ?";    
        try{
        PreparedStatement uV1 = conn.prepareStatement(query1);
        uV1.setInt(1 ,track);
        uV1.setInt(2, postID);
        uV1.setString(3, Main.Utility.username);                
        Store = uV1.executeUpdate();
        }
        catch(SQLException e)
        {
            Store = 0;
        }
        if(Store == 0)
        {    
            String query2 = "INSERT INTO `postvote`(postTrack, postID, username) VALUES (?,?,?)";
            PreparedStatement uV2 = conn.prepareStatement(query2);
            uV2.setInt(1 ,track);
            uV2.setInt(2, postID);
            uV2.setString(3, Main.Utility.username);
            uV2.execute();
        }
    }
    public String readAnsCount(int postID) throws SQLException
    {
        String sql = "Select count(commentID) from comment where postID = " +postID;               
        PreparedStatement psAns = conn.prepareStatement(sql);
        ResultSet rsAns = psAns.executeQuery();
        String sum = "";
        if(rsAns.next())
        {
            sum = rsAns.getString("count(commentID)");            
        }
        psAns.execute();
        return sum;
    }
    
    public int readVoteStatus(int postID) throws SQLException
    {
        int status = 0;
        String query = "SELECT `postTrack` FROM `postvote` WHERE postID = "+postID +" AND username = '"+Main.Utility.username +"'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next())
        {
            status = rs.getInt("postTrack");            
        }
        return status;
        
    }
  
    public int readCommVoteStatus(int postID, int commentID) throws SQLException
    {
        int status = 0;
        String query = "SELECT `commTrack` FROM `commvote` WHERE postID = "+postID +" AND username = '"+Main.Utility.username +"' AND commentID = "+commentID;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next())
        {
            status = rs.getInt("commTrack");            
        }
        return status;
    }
    
    public void updateCommVote(int track, int postID, int commentID) throws SQLException 
    {
       
        int Store;
        String query1 = "UPDATE `commvote` SET commTrack = ? WHERE postID = ? AND username = ? AND commentID = ?";    
        try{
        PreparedStatement uV1 = conn.prepareStatement(query1);
        uV1.setInt(1 ,track);
        uV1.setInt(2, postID);
        uV1.setString(3, Main.Utility.username);
        uV1.setInt(4, commentID);
        Store = uV1.executeUpdate();
        }
        catch(SQLException e)
        {
            Store = 0;
        }
        if(Store == 0)
        {    
            String query2 = "INSERT INTO `commvote`(commTrack, postID, username, commentID) VALUES (?,?,?,?)";
            PreparedStatement uV2 = conn.prepareStatement(query2);
            uV2.setInt(1 ,track);
            uV2.setInt(2, postID);
            uV2.setString(3, Main.Utility.username);
            uV2.setInt(4, commentID);
            uV2.execute();
        }
    }
    public int commVoteCount(int postID, int commentID) throws SQLException
    {
        String sql = "Select sum(commTrack) from `commvote` where postID = " + postID + " AND commentID = "+ commentID;               
        PreparedStatement psAns = conn.prepareStatement(sql);
        ResultSet rsAns = psAns.executeQuery();
        int sum = 0;
        if(rsAns.next())
        {
            sum = rsAns.getInt("sum(commTrack)");            
        }        
        psAns.execute();
        
        return sum;
    }
}
