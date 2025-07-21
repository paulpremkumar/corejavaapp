public class User {
    public Long id;
    public String username;
    public String email;
    public String password;

    public User() {}

    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String toJSON() {
        return String.format("{\"id\":%d,\"username\":\"%s\",\"email\":\"%s\"}", id, username, email);
    }
}
