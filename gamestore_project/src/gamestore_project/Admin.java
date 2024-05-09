package gamestore_project;

public class Admin { 
    // Admin a a class for the admins of the game store, it has a constructor that sets
    // values for the attribuites and it has getters
    private String username;
    private String password;

    public Admin(String user, String pass) {
        username = user;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}