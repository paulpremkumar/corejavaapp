public class UserV1 {
    private long id;
    private String username;
    private String email;

    public UserV1(long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // Getters (and optionally setters)
    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("ID: %d, Username: %s, Email: %s", id, username, email);
    }
}
