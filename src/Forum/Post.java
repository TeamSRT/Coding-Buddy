package Forum;

public class Post {

    public String username, title, body, tag, postDate, postTime;
    public int postID, vote, answer;
    
    Post() {

    }

    public Post(String username, String title, String body, String tag, int postID, int vote, int answer, String postDate, String postTime) {
        this.username = username;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.postID = postID;
        this.vote = vote;
        this.answer = answer;
        this.postDate = postDate;
        this.postTime = postTime;
    }
}
